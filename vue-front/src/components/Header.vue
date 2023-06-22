<template>
  <div class="header">
    <div>
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
    <el-dropdown class="dropDown">
      <span>王小虎</span
      ><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item>个人中心</el-dropdown-item>
        <el-dropdown-item @click.native="signOut"
          >退出登录
          <!-- <router-link to="/login" style="text-decoration: none; display: block"
            >退出登录</router-link
          > -->
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
import router from "@/router";
export default {
  name: "Header",
  props: {
    collapseBtnClass: String,
    collapse: Function,
  },
  data() {
    return {};
  },
  methods: {
    signOut() {
      this.$router.push("/login");
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
  position: absolute;
  right: 40px;
  line-height: 60px;
  width: 100px;
  color: #fff;
  cursor: pointer;
}
</style>