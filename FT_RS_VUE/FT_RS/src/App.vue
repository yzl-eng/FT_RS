<template>
   
   <div>
      <router-view></router-view>
<!-- <add-video-info></add-video-info> -->
<!-- <moive></moive> -->
<!-- <main_header></main_header> -->
<!-- <admin-home></admin-home> -->
<!-- <videoInfo></videoInfo> -->
<!-- <Menu></Menu> -->
<!-- <addNews></addNews> -->
<!-- <newsInfo></newsInfo> -->
   </div>

</template>

<script setup >
import { defineComponent } from 'vue'
import { Delete, Edit, Search, Share, Upload } from '@element-plus/icons-vue'
import { ElConfigProvider } from 'element-plus'
import About from './components/About.vue'
import moive from './components/moive.vue'
// import addVideoInfo from './components/addVideoInfo.vue'
// import main_header from './components/main_header.vue'
// import Home from './test/Home.vue'
import test from './components/test.vue'
// import adminHome from './components/adminHome.vue'
import videoInfo from './view/video/videoInfo.vue'
// import Menu from './components/menu.vue'
import addNews from './view/news/addNews.vue'
// import newsInfo from './components/newsInfo.vue'
</script>About