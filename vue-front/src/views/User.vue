<template>
  <div>
    <div style="margin: 10px 0">
      <el-input
        style="width: 200px"
        suffix-icon="el-icon-search"
        placeholder="请输入用户名称"
        v-model="nickname"
      ></el-input>
      <el-input
        style="width: 200px; margin-left: 5px"
        suffix-icon="el-icon-phone"
        placeholder="请输入用户手机号"
        v-model="phone"
      ></el-input>
      <el-input
        style="width: 200px; margin-left: 5px"
        suffix-icon="el-icon-message"
        placeholder="请输入用户邮箱"
        v-model="email"
      ></el-input>
      <el-button style="margin-left: 5px" type="primary" @click="load"
        >搜索</el-button
      >
      <el-button style="margin-left: 5px" type="warning" @click="reset"
        >重置</el-button
      >
    </div>
    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd"
        >新增<i class="el-icon-circle-plus-outline"></i
      ></el-button>
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
      <el-table-column
        prop="nickname"
        label="用户名"
        width="150"
        align="center"
      >
      </el-table-column>
      <el-table-column
        prop="gender"
        label="性别"
        width="170"
        align="center"
      ></el-table-column>
      <el-table-column prop="phone" label="手机号" width="170" align="center">
      </el-table-column>
      <el-table-column prop="email" label="邮箱" width="170" align="center">
      </el-table-column>
      <el-table-column prop="address" label="地址" align="center">
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template v-slot:default="scope">
          <el-button type="success" @click="handleEdit(scope.row)"
            >编辑<i class="el-icon-edit"></i
          ></el-button>
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
        :total="totalUser"
      >
      </el-pagination>
    </div>
    <!-- 新增用户对话框 -->
    <el-dialog
      title="用户信息"
      :visible.sync="DialogVisible"
      center
      width="30%"
    >
      <el-form label-width="80px" size="small">
        <el-form-item label="用户名">
          <el-input v-model="userForm.nickname" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-input v-model="userForm.gender" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="userForm.phone" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="userForm.email" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="userForm.address" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="DialogVisible = false">取消</el-button>
        <el-button type="primary" @click="add">确定</el-button>
      </div>
    </el-dialog>
    <!-- 编辑用户对话框 -->
    <el-dialog
      title="用户信息"
      :visible.sync="editDialogVisible"
      center
      width="30%"
    >
      <el-form label-width="80px" size="small">
        <el-form-item label="用户名">
          <el-input
            v-model="editUserForm.nickname"
            auto-complete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-input
            v-model="editUserForm.gender"
            auto-complete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="editUserForm.phone" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="editUserForm.email" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="地址">
          <el-input
            v-model="editUserForm.address"
            auto-complete="off"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="edit">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import request from "../utils/request";

export default {
  name: "User",
  data() {
    return {
      tableData: [],
      nickname: "",
      phone: "",
      email: "",
      totalUser: 0,
      pageNum: 1,
      pageSize: 5,
      userForm: {},
      editUserForm: {},
      DialogVisible: false,
      editDialogVisible: false,
      multipleSelection: [],
    };
  },
  created() {
    this.load();
  },
  methods: {
    load() {
      request
        .get("/user/page", {
          params: {
            pageNum: this.pageNum,
            pageSize: this.pageSize,
            nickname: this.nickname,
            phone: this.phone,
            email: this.email,
          },
        })
        .then((res) => {
          this.tableData = res.data;
          this.totalUser = res.totalUser;
        });
    },
    deleteBatch() {
      let ids = this.multipleSelection.map((v) => v.id); //把对象数组转换为只有id的数组
      if (this.multipleSelection != "") {
        request.post("/user/del/batch", ids).then((res) => {
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
    handleAdd() {
      this.DialogVisible = true;
      this.userForm = {};
    },
    handleEdit(row) {
      this.editUserForm = JSON.parse(JSON.stringify(row));
      this.editDialogVisible = true;
    },
    handleDelete(id) {
      request.delete("/user/" + id).then((res) => {
        if (res.code === "200") {
          this.$message.success("删除成功");
          this.load();
        } else {
          this.$message.error("删除失败");
        }
      });
    },
    add() {
      request.post("/user/add", this.userForm).then((res) => {
        if (res.code === "200") {
          this.$message.success("保存成功");
          this.DialogVisible = false;
          this.load();
          this.DialogVisible = false;
        } else {
          this.$message.error("操作失败");
        }
      });
    },
    edit() {
      request.post("/user/update", this.editUserForm).then((res) => {
        if (res.code === "200") {
          this.$message.success("保存成功");
          this.editDialogVisible = false;
          this.load();
          this.editDialogVisible = false;
        } else {
          this.$message.error("操作失败");
        }
      });
    },
    reset() {
      (this.nickname = ""), (this.email = ""), (this.phone = ""), this.load();
    },
  },
};
</script>

<style>
</style>