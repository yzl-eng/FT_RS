<template>
   <div>
<!-- <add-video-info></add-video-info> -->
<moive></moive>
<!-- <main_header></main_header> -->
<!-- <admin-home></admin-home> -->
<!-- <video-info></video-info> -->
   </div>

</template>

<script setup >
import { defineComponent } from 'vue'
import { Delete, Edit, Search, Share, Upload } from '@element-plus/icons-vue'
import { ElConfigProvider } from 'element-plus'
import About from './components/About.vue'
import moive from './components/moive.vue'
import addVideoInfo from './components/addVideoInfo.vue'
import main_header from './components/main_header.vue'
// import Home from './test/Home.vue'
import test from './components/test.vue'
// import adminHome from './components/adminHome.vue'
import videoInfo from './components/videoInfo.vue'

</script>About