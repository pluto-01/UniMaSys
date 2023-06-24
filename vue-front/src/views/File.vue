<template>
  <div>
    <div style="margin: 10px 0">
      <el-input
        style="width: 200px"
        suffix-icon="el-icon-search"
        placeholder="请输入文件名称"
        v-model="name"
      ></el-input>
      <el-button style="margin-left: 5px" type="primary" @click="load"
        >搜索</el-button
      >
      <el-button style="margin-left: 5px" type="warning" @click="reset"
        >重置</el-button
      >
    </div>
    <div style="margin: 10px 0">
      <el-upload
        action="http://localhost:8888/file/upload"
        :show-file-list="false"
        style="display: inline-block"
        :on-success="handleUploadSuccess"
      >
        <el-button type="primary" style="margin-left: 5px"
          >上传文件<i class="el-icon-top"></i
        ></el-button>
      </el-upload>
      <el-popconfirm
        style="margin-left: 5px"
        confirm-button-text="确定"
        cancel-button-text="取消"
        icon="el-icon-info"
        icon-color="red"
        title="您确定批量删除吗？"
        @confirm="deleteBatch"
      >
        <el-button type="danger" slot="reference"
          >批量删除<i class="el-icon-remove-outline"></i
        ></el-button>
      </el-popconfirm>
    </div>
    <el-table
      :data="tableData"
      stripe
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55"> </el-table-column>
      <el-table-column prop="id" label="ID" width="150" align="center">
      </el-table-column>
      <el-table-column prop="name" label="文件名" width="150" align="center">
      </el-table-column>
      <el-table-column
        prop="type"
        label="文件类型"
        width="170"
        align="center"
      ></el-table-column>
      <el-table-column
        prop="size"
        label="文件大小(kb)"
        width="170"
        align="center"
      >
      </el-table-column>
      <el-table-column label="下载" width="170" align="center">
        <template v-slot:default="scope">
          <el-button type="primary" @click="download(scope.row.url)"
            >下载</el-button
          >
        </template>
      </el-table-column>
      <el-table-column
        prop="enable"
        label="启用"
        align="center"
      >
        <template v-slot:default="scope">
          <el-switch
            v-model="scope.row.enable"
            active-color="#13ce66"
            inactive-color="#ccc"
            @change="changeEnable(scope.row)"
          ></el-switch>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template v-slot:default="scope">
          <el-popconfirm
            style="margin-left: 5px"
            confirm-button-text="确定"
            cancel-button-text="取消"
            icon="el-icon-info"
            icon-color="red"
            title="您确定删除吗？"
            @confirm="handleDelete(scope.row.id)"
          >
            <el-button type="danger" slot="reference"
              >删除<i class="el-icon-remove-outline"></i
            ></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="padding: 10px 0">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :page-sizes="[5, 10, 15, 20]"
        :currentPage-="pageNum"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="totalFile"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: "File",
  data() {
    return {
      tableData: [],
      name: "",
      multipleSelection: [],
      pageNum: 1,
      pageSize: 5,
      totalFile: 0,
    };
  },
  created() {
    this.load();
  },
  methods: {
    load() {
      this.request
        .get("/file/page", {
          params: {
            pageNum: this.pageNum,
            pageSize: this.pageSize,
            name: this.name,
          },
        })
        .then((res) => {
          this.tableData = res.data;
          this.totalFile = res.totalFile;
        });
    },
    handleDelete(id) {
      this.request.delete("/file/" + id).then((res) => {
        if (res.code === "200") {
          this.$message.success("删除成功");
          this.load();
        } else {
          this.$message.error("删除失败");
        }
      });
    },
    deleteBatch() {
      let ids = this.multipleSelection.map((v) => v.id); //把对象数组转换为只有id的数组
      if (this.multipleSelection != "") {
        this.request.post("/file/del/batch", ids).then((res) => {
          if (res.code === "200") {
            this.$message.success("批量删除成功");
            this.load();
          } else {
            this.$message.error("批量删除失败");
          }
        });
      } else {
        this.$message.error("请先选择您要删除的对象");
      }
    },
    // 批量删除
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    handleSizeChange(pageSize) {
      console.log(pageSize);
      this.pageSize = pageSize;
      this.load();
    },
    handleCurrentChange(pageNum) {
      console.log(pageNum);
      this.pageNum = pageNum;
      this.load();
    },

    handleUploadSuccess(res) {
      this.load();
    },
    reset() {
      (this.name = ""), this.load();
    },
    //下载
    download(url) {
      window.open(url);
    },
    //启用
    changeEnable(row) {
      this.request.post("/file/update", row).then((res) => {
        if (res.code === "200") {
          this.$message.success("操作成功");
          // this.selectable = false;
        } else {
          this.$message.error("操作失败");
        }
      });
    },
  },
};
</script>

<style>
</style>