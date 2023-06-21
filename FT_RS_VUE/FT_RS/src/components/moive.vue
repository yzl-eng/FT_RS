<template>
    <el-space wrap size="small" >
        <el-row justify="center">
            <el-col v-for="(video, index) in  videos " :key="index" :span="4"  >
                <el-card :body-style="{ padding: '0px' }" shadow="hover">
                    <template #header>
                        <el-image :src="video.img" class="image" fit="fill"/>
                    </template>
                    <!-- <img :src="video.img" class="image"/> -->
                    <div style="padding: 14px">
                        <el-rate v-model="video.score" disabled :show-score="ture" text-color="#f7ba2a"
                             />
                             <div>{{ video.score }}</div>
                        <div>
                            <el-text class="mx-1">{{ video.name }}</el-text>
                        </div>
                       
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </el-space>
</template>
  
<script setup>
import { ref, watchEffect } from 'vue'
import API from "../plugins/axiosInstance"
const currentDate = ref(new Date())

const videos = ref(
    // name: '默认标题',
    // author: '默认作者',
    // description: '默认描述',
    // score: 0.0,
    // img: 'https://example.com/default.jpg'
[])
const getData = function () {
    API({
        url: '/video/findAll',
        method: 'get'
    }).then((res) => {
        alert('请求成功!');
        videos.value = res.data.data;
    });
    return {
        videos
    }

}

getData()



</script>
<style>

</style>
  