<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <menu_header></menu_header>
      </el-header>
      <el-container>
        <el-aside width="250px">
          <menu_aside></menu_aside>
        </el-aside>
        <el-main><router-view></router-view></el-main>
      </el-container>
    </el-container>
  </div>
</template>
  
<script lang="ts" setup>
import router from '../router'
import menu_header from './menu_header.vue';
import menu_aside from './menu_aside.vue'

</script>

  
<style></style>