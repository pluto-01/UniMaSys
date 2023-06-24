package com.zed.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.zed.common.Result;
import com.zed.entity.Files;
import com.zed.entity.User;
import com.zed.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件上传相关接口
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Resource
    private FileService fileService;

    /**
     * 文件上传接口
     *
     * @param file 前端传来的文件
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();  //获取原始文件
        String type = FileUtil.extName(originalFilename);   //获取文件类型
        long size = file.getSize();    //获取文件大小
        //先存储到磁盘
        File uploadParentFile = new File(fileUploadPath);
        //判断配置的文件目录是否存在，若不存在则创建新的目录
        if (!uploadParentFile.exists()) {
            uploadParentFile.mkdirs();
        }
        //定义一个文件唯一的标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileUUID = uuid + StrUtil.DOT + type;
        File uploadFile = new File(fileUploadPath + fileUUID);
        //获取文件的md5  避免上传相同内容的文件
        String md5 = SecureUtil.md5(file.getInputStream());
        //访问数据库比对是否存在相同的md5文件
        List<Files> filesList = fileService.selectMd5(md5);

        //获取文件的url
        String url;
        if (filesList.size() != 0) {
            url = filesList.get(0).getUrl();
        } else {
            //把获取到的文件存储到磁盘目录
            file.transferTo(uploadFile);
            url = "http://localhost:8888/file/" + fileUUID;
        }
        //存储数据库
        fileService.addFile(originalFilename, type, (size / 1024), url, md5);
        return url;
    }

    /**
     * 文件下载接口   http://localhost:8888/file/{fileUUID}
     *
     * @param fileUUID
     * @param response
     * @throws IOException
     */
    @GetMapping("/{fileUUID}")
    public void download(@PathVariable String fileUUID, HttpServletResponse response) throws IOException {
        //根据文件的唯一标识码获取文件
        File uploadFile = new File(fileUploadPath + fileUUID);
        //设置输出流的格式
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileUUID, "UTF-8"));
        response.setContentType("application/octet-stream");
        //读取文件的字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }

    @GetMapping("/page")
    public Map<String, Object> findUserByPage(@RequestParam Integer pageNum,
                                              @RequestParam Integer pageSize,
                                              @RequestParam(defaultValue = "") String name) {
        pageNum = (pageNum - 1) * pageSize;
        List<Files> data = fileService.selectFileByPage(pageNum, pageSize, name);
        Integer totalFile = fileService.selectTotalFile(name);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("totalFile", totalFile);
        return res;
    }

    @DeleteMapping("/{id}")
    public Result deleteFile(@PathVariable Integer id) { //表示url参数
        return Result.success(fileService.deleteFileById(id));
    }

    @PostMapping("/del/batch")
    public Result deleteFileBatch(@RequestBody List<Integer> ids) {
        return Result.success(fileService.deleteFileBatchById(ids));
    }

    @PostMapping("/update/enable")
    public Result updateFileEnable(@RequestBody Files files) {
        return Result.success(fileService.updateFileEnable(files));
    }

    @PostMapping("/update/name")
    public Result updateFileName(@RequestBody Files files) {
        return Result.success(fileService.updateFileName(files));
    }
}
