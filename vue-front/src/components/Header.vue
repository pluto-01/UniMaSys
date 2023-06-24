<template>
  <div class="header">
    <div style="flex: 1">
      <span
        :class="collapseBtnClass"
        style="cursor: pointer"
        @click="collapse"
        class="foldIcon"
      ></span>
      <el-breadcrumb class="bread" separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>{{ currentPathName }}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <!-- <span class="title">通用后台管理系统</span> -->
    <!-- <div class="avatar">
      <el-avatar size="small" :src="userInfo.avatar"></el-avatar>
    </div> -->
    <el-dropdown class="dropDown">
      <div style="display: inline-block">
        <img :src="userInfo.avatar" alt="" class="avatar" />
        <span>{{ userInfo.nickname }}</span>
      </div>

      <i class="el-icon-arrow-down" style="margin-left: 5px"></i>
      <el-dropdown-menu
        slot="dropdown"
        style="width: 100px; text-align: center"
      >
        <el-dropdown-item style="font-size: 14px; padding: 5px 0">
          <router-link
            to="/person"
            class="person"
            style="text-decoration: none; color: black"
            >个人中心</router-link
          >
        </el-dropdown-item>
        <el-dropdown-item style="font-size: 14px; padding: 5px 0">
          <span
            to="/login"
            style="text-decoration: none; color: black"
            @click="signOut"
            >退出登录</span
          >
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
export default {
  name: "Header",
  props: {
    collapseBtnClass: String,
    collapse: Function,
  },
  data() {
    return {
      userInfo: localStorage.getItem("user")
        ? JSON.parse(localStorage.getItem("user"))
        : {},
    };
  },
  methods: {
    signOut() {
      this.$router.push("/login");
      localStorage.removeItem("user");
      console.log(localStorage.getItem("user"));
      this.$message.success("退出成功");
    },
  },
  computed: {
    currentPathName() {
      return this.$store.state.currentPathName;
    },
  },
  watch: {
    currentPathName(newVal, oldVal) {
      console.log(newVal);
    },
  },
};
</script>

<style scoped>
.header {
  position: relative;
  text-align: right;
  font-size: 12px;
  display: flex;
}
.title {
  font-size: 20px;
  position: absolute;
  left: 50px;
  line-height: 60px;
}
.foldIcon {
  font-size: 18px;
  position: absolute;
  left: 10px;
  line-height: 60px;
}
.bread {
  /* display: inline-block; */
  margin-left: 50px;
  line-height: 60px;
}
.el-breadcrumb /deep/ .el-breadcrumb__inner {
  color: #fff;
}
.dropDown {
  width: auto;
  text-align: right;
  cursor: pointer;
  color: #fff;
}

.avatar {
  width: 30px;
  border-radius: 50%;
  position: relative;
  top: 10px;
  right: 10px;
}
</style>