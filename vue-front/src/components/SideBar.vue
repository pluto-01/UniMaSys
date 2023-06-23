<template>
  <el-menu
    :default-openeds="['1', '3']"
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
    </el-submenu>
    <el-submenu index="3">
      <template slot="title"
        ><i class="el-icon-setting"></i><span>导航一</span></template
      >
      <el-menu-item-group>
        <el-menu-item index="3-1">选项1</el-menu-item>
        <el-menu-item index="3-2">选项2</el-menu-item>
      </el-menu-item-group>
      <el-menu-item-group title="分组2">
        <el-menu-item index="3-3">选项3</el-menu-item>
      </el-menu-item-group>
      <el-submenu index="3-4">
        <template slot="title">选项4</template>
        <el-menu-item index="3-4-1">选项4-1</el-menu-item>
      </el-submenu>
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