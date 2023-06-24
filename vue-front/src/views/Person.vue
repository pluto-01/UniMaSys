<template>
  <div class="container">
    <el-card>
      <el-form label-width="80px" size="small">
        <el-upload
          class="avatar-uploader"
          action="http://localhost:8888/file/upload"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
        >
          <img v-if="userForm.avatar" :src="userForm.avatar" class="avatar" />
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
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

        <el-form-item style="text-align: right">
          <el-button type="primary" @click="update">保存</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "Person",
  data() {
    return {
      userForm: {},
      userInfo: localStorage.getItem("user")
        ? JSON.parse(localStorage.getItem("user"))
        : {},
    };
  },
  created() {
    this.load();
  },
  methods: {
    load() {
      this.request
        .get("/user/username/" + this.userInfo.username)
        .then((res) => {
          if (res.code === "200") {
            this.userForm = res.data;
            console.log(this.userForm);
          } else {
            console.log(res);
          }
        });
    },
    update() {
      this.request.post("/user/update", this.userForm).then((res) => {
        if (res.code === "200") {
          this.$message.success("保存成功");
          this.load();
          this.$emit("refreshUser");
        } else {
          this.$message.error("操作失败");
        }
      });
    },
    handleAvatarSuccess(res) {
      console.log(res);
      this.userForm.avatar = res;
    },
  },
};
</script>

<style scoped>
.container {
  width: 500px;
  padding: 20px;
}
.avatar-uploader {
  text-align: center;

  padding-bottom: 15px;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 10px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 138px;
  height: 138px;
  line-height: 138px;
  text-align: center;
}
.avatar {
  width: 138px;
  height: 138px;
  display: block;
  border-radius: 8px;
}
</style>