<template>
  <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" :ellipsis="false" @select="handleSelect" router>
    <el-menu-item index="0">LOGO</el-menu-item>
    <div class="flex-grow" />
    <el-menu-item index="/home">前台</el-menu-item>
    <el-sub-menu index="2">
      <template #title><el-avatar> <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" />
        </el-avatar><el-text truncated size="small">{{ user.username }}</el-text></template>
      <el-menu-item index="/">个人中心</el-menu-item>
      <el-menu-item index="/login">退出登录</el-menu-item>
    </el-sub-menu>
  </el-menu>
</template>
  
<script lang="ts" setup>
import { ref } from 'vue'
import API from "./../plugins/axiosInstance"

const activeIndex = ref('1')
const handleSelect = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}

const user = ref({
    username: '',
}

)

const item = JSON.parse(window.localStorage.getItem('user'))
const findUser = function () {
   const params={
    id:item.id
   }
    API.get('/user/findById', {params}).then((res) => {
        user.value = res.data.data;
    });
}
</script>
  
<style>
.flex-grow {
  flex-grow: 1;
}
</style>