<template>
  <el-menu
    :default-openeds="['2']"
    style="min-height: 100%; overflow-x: hidden"
    active-text-color="pink"
    background-color="rgb(34, 34, 34)"
    text-color="#fff"
    :collapse-transition="false"
    :collapse="isCollapse"
    router
  >
    <el-menu-item index="/main">
      <i class="el-icon-house"></i><span>主页</span>
    </el-menu-item>
    <el-submenu index="2">
      <template slot="title"
        ><i class="el-icon-menu"></i><span>系统管理</span></template
      >
      <el-menu-item index="/user"
        ><i class="el-icon-s-custom"></i><span>用户管理</span></el-menu-item
      >
      <el-menu-item index="/role"
        ><i class="el-icon-s-custom"></i><span>角色管理</span></el-menu-item
      >
      <el-menu-item index="/file"
        ><i class="el-icon-document"></i><span>文件管理</span></el-menu-item
      >
    </el-submenu>
  </el-menu>
</template>

<script>
export default {
  name: "SideBar",
  props: {
    isCollapse: Boolean,
  },
};
</script>

<style scoped>
</style>