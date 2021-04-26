<template>
  <div id="app">
    <el-row class="tac">
      <el-col :span="3">
        <h5>开放网关</h5>
        <el-menu
            default-active="2"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose">
          <el-menu-item index="1">
            <i class="el-icon-menu"></i>
            <span slot="title">应用管理</span>
          </el-menu-item>
          <el-menu-item index="2" >
            <i class="el-icon-menu"></i>
            <span slot="title">调用方管理</span>
          </el-menu-item>
          <el-menu-item index="3">
            <i class="el-icon-menu"></i>
            <span slot="title">接口管理</span>
          </el-menu-item>
          <el-menu-item index="4">
            <i class="el-icon-menu"></i>
            <span slot="title">订阅配置管理</span>
          </el-menu-item>
        </el-menu>
      </el-col>
    </el-row>
  </div>
</template>
<script>
export default {
  name: 'MainIndex',
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    }
  }
}
</script>
<style>
@import url("//unpkg.com/element-ui@2.15.1/lib/theme-chalk/index.css");
.el-menu-item{
  text-align: left;
}
</style>
