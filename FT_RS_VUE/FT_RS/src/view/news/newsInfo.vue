<template>
    <div class="common-layout">
        <el-container>
            <el-header>
                <el-button type="primary" :icon="Plus" @click="addClick">添加</el-button>
                <addNews v-model="addDialogVisible"></addNews>
                <el-button type="danger" :icon="Delete">批量删除</el-button>
            </el-header>
            <el-main>
                <el-table :data="tableData" style="width: 100%" border size="middle">

                    <el-table-column fixed type="selection" width="55" />
                    <el-table-column fixed prop="title" label="新闻标题" width="160" />

                    <el-table-column prop="img" label="封面" width="80">
                        <template #default="slotProps">
                            <img :src="slotProps.row.img" style="max-width: 100%; max-height: 100%" />
                        </template>
                    </el-table-column>

                    <el-table-column prop="author" label="作者" width="120" show-overflow-tooltip />




                    <el-table-column prop="context" label="内容" width="300" show-overflow-tooltip />
                    <el-table-column prop="createTime" label="创建时间" width="200" type="date" sortable />
                    <el-table-column prop="updateTime" label="更新时间" width="200" />



                    <el-table-column fixed="right" label="操作" width="180">

                        <template #default="scope">
                            <el-button type="primary" size="middle" @click="editClick(tableData[scope.$index])" >编辑</el-button>


                            <editNews v-model="editdialogVisible" lock-scroll :editData="editData" @cancel="cancelDialog"></editNews>

                            <el-button type="danger" size="middle" @click="deleteClick(tableData[scope.$index])">删除</el-button>
                        </template>

                    </el-table-column>
                </el-table>
                <el-pagination @current-change="handlePaginationChange" background layout="prev, pager, next, "
                    :total="total" current-page="currentPage" :page-size="pageSize" />
            </el-main>
        </el-container>
    </div>
    
</template>
  
<script lang="ts" setup>
import editNews from './editNews.vue'
import addNews from './addNews.vue';
import { reactive, ref } from 'vue'
import { Plus, Delete } from '@element-plus/icons-vue'


const addDialogVisible = ref(false)
const addClick = () => {
    addDialogVisible.value = true;
}


import API from '../../plugins/axiosInstance';
import { watchEffect } from "vue";


const tableData = ref([]); // 表格数据
const total = ref(0); // 总条目数
const currentPage = ref(1); // 当前页码
const pageSize = ref(7); // 每页显示条目数

// 监听页码和条目数变化
watchEffect(() => {
    fetchData();
});

//删除
const deleteClick = (obj:any) => {
    API({
        url: '/news/delete',
        method: 'post',
        data: obj
    }).then((res) => {
        fetchData(); // 删除成功后刷新数据列表
        alert("成功")
    });
}
const editData=ref()
//编辑数据
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


// 获取分页数据
function fetchData() {
    const params = {
        pageNum: currentPage.value,
        pageSize: pageSize.value,

    };

    API
        .get("/news/queryData", { params })
        .then((response) => {
            const { records } = response.data.data;
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






</script>
<style scoped></style>