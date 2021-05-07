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
          <el-menu-item index="1" route = "/application-manage" @click="onClickMenuItem">
            <i class="el-icon-menu"></i>
            <span slot="title">应用管理</span>
          </el-menu-item>
          <el-menu-item index="2" route = "/subscriber-manage" @click="onClickMenuItem">
            <i class="el-icon-menu"></i>
            <span slot="title">调用方管理</span>
          </el-menu-item>
          <el-menu-item index="3" route="/api-manage" @click="onClickMenuItem">
            <i class="el-icon-menu"></i>
            <span slot="title">接口管理</span>
          </el-menu-item>
          <el-menu-item index="4" route="/subscribe-config-manage" @click="onClickMenuItem">
            <i class="el-icon-menu"></i>
            <span slot="title">订阅配置管理</span>
          </el-menu-item>
        </el-menu>
      </el-col>
      <el-col :span="30">
        <el-tabs v-model="currentTab" type="card" closable @tab-remove="removeTab">
          <el-tab-pane
              v-for="(item, index) in openedTabs"
              :key="item.name"
              :label="item.title"
              :name="item.name"
              :index="index"
          >
            {{item.content}}
          </el-tab-pane>
        </el-tabs>
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
    },
    onClickMenuItem(source){
      let route = source.route;
      this.openedTabs.push({title:route,name:route,content:route});
      this.openedTabs.currentTab = route;
    },
    removeTab(targetName){
      let tabs = this.openedTabs;
      let activeName = this.currentTab;
      if(activeName === targetName){
        tabs.forEach((tab,index)=>{
          if(tab.name === targetName){
            let nextTab = tabs[index+1] || tabs[index - 1];
            if(nextTab){
              activeName = nextTab.name;
            }
          }
        });
      }
      this.currentTab = activeName;
      this.openedTabs = tabs.filter(tab=>tab.name!==targetName);
    }
  },
  data(){
    return {
      openedTabs:[
        {title:'tab1',name:'1',content:'tab 1 content'}
      ],
      currentTab:''
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
