<template>
    <div class="common-layout">
        <el-container>
            <el-header>
                <el-button type="primary" :icon="Plus" @click="addClick">添加</el-button>
                <addVideoInfo v-model="addDialogVisible"></addVideoInfo>
                <el-button type="danger" :icon="Delete">批量删除</el-button>
            </el-header>
            <el-main>
                <el-table :data="tableData" style="width: 100%" border size="middle">

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
                        <template #default="scope">
                            <el-button type="primary" size="middle" @click="editClick(tableData[scope.$index])">编辑</el-button>


                            <edit-video-info v-model="editdialogVisible" lock-scroll  :editData="editData" @cancel="cancelDialog"></edit-video-info>



                            <el-button type="danger" size="middle" @click="deleteClick(tableData[scope.$index])">删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <el-pagination @current-change="handlePaginationChange" background layout="prev, pager, next, " :total="total"
                    current-page="currentPage" :page-size="pageSize" />
            </el-main>
        </el-container>
    </div>
</template>
  
<script lang="ts" setup>
import addVideoInfo from './addVideoInfo.vue';
import editVideoInfo from './editVideoInfo.vue';
import { reactive, ref } from 'vue'
import { Plus, Delete } from '@element-plus/icons-vue'

const dialogVisible = ref(false)
const handleClick = () => {
    // 点击编辑按钮时显示对话框
    dialogVisible.value = true;
}


const addDialogVisible = ref(false)
const addClick = () => {
    addDialogVisible.value = true;
}


import API from '../../plugins/axiosInstance';
import { watchEffect } from "vue";


const tableData = ref([]); // 表格数据
const total = ref(0); // 总条目数
const currentPage = ref(1); // 当前页码
const pageSize = ref(5); // 每页显示条目数

// 监听页码和条目数变化
watchEffect(() => {
    fetchData();
});

// 获取分页数据
function fetchData() {
    const params = {
        pageNum: currentPage.value,
        pageSize: pageSize.value,

    };

    API
        .get("/video/queryData", { params })
        .then((response) => {
            const { records} = response.data.data;
            tableData.value = records;
            total.value = response.data.data.total;
        })
        .catch((error) => {
            console.error();
        });


}

// 处理分页切换事件
function handlePaginationChange(pageIndex) {
    currentPage.value = pageIndex;
    fetchData()
}
const videoId=ref()
//删除
const deleteClick = (obj:any) => {
    API({
        url: '/video/delete',
        method: 'post',
        data: obj
    }).then((res) => {
        const params={
            videoId:obj.id
        }
        
        API.get("/videoCategory/delete", {params} )
        fetchData(); // 删除成功后刷新数据列表
    });
}

//编辑操作
const editData=ref()
const editdialogVisible = ref(false)
const editClick = (obj) => {
    // 点击编辑按钮时显示对话框
    editdialogVisible.value = true;
    editData.value=obj
}

function cancelDialog () {
    editdialogVisible.value = false;
    fetchData();
}



</script>
<style scoped></style>