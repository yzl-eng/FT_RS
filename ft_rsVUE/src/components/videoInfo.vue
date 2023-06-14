<template>
    <el-table :data="videoData" style="width: 100%" border size="middle">

        <el-table-column fixed type="selection" width="55" />
        <el-table-column fixed prop="name" label="作品名称" width="160" />

        <el-table-column prop="img" label="海报" width="80">
            <template #default="slotProps">
                <img :src="slotProps.row.img" style="max-width: 100%; max-height: 100%" />
            </template>
        </el-table-column>

        <el-table-column prop="director" label="导演" width="120" show-overflow-tooltip />
        <el-table-column prop="screenwriter" label="编剧" width="120" show-overflow-tooltip />
        <el-table-column prop="starring" label="主演" width="140" show-overflow-tooltip />
        <el-table-column prop="language" label="语言" width="120" />
        <el-table-column prop="countryRegion" label="出品国家/地区" width="120" />

        <el-table-column prop="typeId" label="形式" width="100">
            <template #default="slotProps">
                <!-- 自定义插槽的内容 -->
                <span v-if="slotProps.row.typeId === 1">电影</span>
                <span v-else-if="slotProps.row.typeId === 2">电视剧</span>
                <span v-else-if="slotProps.row.typeId === 3">综艺</span>
                <span v-else-if="slotProps.row.typeId === 4">纪录片</span>
            </template>
        </el-table-column>

        <el-table-column prop="releasedate" label="上线日期" width="150" type="date" sortable />
        <el-table-column prop="length" label="时长(分钟)" width="100" />
        <el-table-column prop="introduction" label="简介" width="150" show-overflow-tooltip />


        <el-table-column fixed="right" label="操作" width="180">
            <template #default>
                <el-button type="primary" size="middle" @click="handleClick">编辑</el-button>

                
                        <add-video-info v-model="dialogVisible" lock-scroll></add-video-info>
                   


                <el-button type="danger" size="middle">删除</el-button>
            </template>
        </el-table-column>
    </el-table>
    <el-pagination background layout="prev, pager, next" :total="1000" />
</template>
  
<script lang="ts" setup>
import addVideoInfo from './addVideoInfo.vue'
import { reactive, ref } from 'vue'


const dialogVisible = ref(false)
const handleClick = () => {
    // 点击编辑按钮时显示对话框
    dialogVisible.value = true;
}




import API from '../plugins/axiosInstance';
const videoData = ref()
const getVideo = function () {
    API({
        url: '/video/findAll',
        method: 'get'
    }).then((res) => {
        videoData.value = res.data.data;
    });
}
getVideo()




</script>
<style scoped></style>