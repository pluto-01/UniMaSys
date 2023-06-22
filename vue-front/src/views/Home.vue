<template>
  <el-container style="min-height: 100vh">
    <el-aside :width="asideWidth + 'px'">
      <SideBar :isCollapse="isCollapse"></SideBar>
    </el-aside>
    <el-container>
      <el-header>
        <Header
          :collapseBtnClass="collapseBtnClass"
          :collapse="collapse"
        ></Header
      ></el-header>
      <el-main>
        <!-- 表示当前页面的子路由会在此显示 -->
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import SideBar from "@/components/SideBar.vue";
import Header from "@/components/Header.vue";
export default {
  name: "Home",
  components: {
    SideBar,
    Header,
  },
  data() {
    return {
      collapseBtnClass: "el-icon-s-fold",
      isCollapse: false,
      asideWidth: 200,
    };
  },

  methods: {
    collapse() {
      //点击收缩菜单按钮触发
      this.isCollapse = !this.isCollapse;
      if (this.isCollapse) {
        this.asideWidth = 64;
        this.collapseBtnClass = "el-icon-s-unfold";
      } else {
        this.asideWidth = 200;
        this.collapseBtnClass = "el-icon-s-fold";
      }
    },
  },
};
</script>

<style scoped>
.el-header {
  background-color: rgb(34, 34, 34);
  color: #fff;
  line-height: 60px;
}
.el-aside {
  color: #333;
}
</style>