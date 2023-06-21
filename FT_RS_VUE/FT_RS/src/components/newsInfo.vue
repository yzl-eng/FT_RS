<template>
    <el-table :data="newsData" style="width: 100%" border size="middle">

        <el-table-column fixed type="selection" width="55" />
        <el-table-column fixed prop="title" label="新闻标题" width="160" />

        <el-table-column prop="img" label="海报" width="80">
            <template #default="slotProps">
                <img :src="slotProps.row.img" style="max-width: 100%; max-height: 100%" />
            </template>
        </el-table-column>
        <el-table-column prop="author" label="作者" width="100" />
        <el-table-column prop="context" label="新闻内容" width="200" type="textarea" show-overflow-tooltip/>

        <el-table-column prop="createTime" label="创建时间" width="200" type="date" sortable />
        <el-table-column prop="updateTime" label="修改时间" width="200" type="date" sortable />


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

import { reactive, ref } from 'vue'


const dialogVisible = ref(false)
const handleClick = () => {
    // 点击编辑按钮时显示对话框
    dialogVisible.value = true;
}




import API from '../plugins/axiosInstance';
const newsData = ref()
const getNews = function () {
    API({
        url: '/news/findAll',
        method: 'get'
    }).then((res) => {
        newsData.value = res.data.data;
    });
}
getNews()




</script>
<style scoped></style>