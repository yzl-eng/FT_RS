<template>
    <el-container>
        <el-header>
            <div>相关新闻</div>
        </el-header>
        <el-main>
            <el-space wrap size="small">
                <el-row :gutter="10">
                    <el-col v-for="(item, index) in  news " :key="index" :span="4">
                        <el-card :body-style="{ padding: '0px' }" shadow="hover" class="card">
                            <el-image :src="item.img" class="image" fit="fill" />
                            <div style="padding: 14px">
                                <div><span v-text="item.title"></span></div>

                            </div>
                        </el-card>
                    </el-col>
                </el-row>
            </el-space>
        </el-main>
    </el-container>
</template>
<script setup>
import { ref, watchEffect } from 'vue'
import API from "../../plugins/axiosInstance"

const news = ref()
const getNewsData = function () {
    const params = {
        pageNum: 1,
        pageSize: 5,

    };
    API.get("/news/findNum", { params })
        .then((res) => {
            news.value = res.data.data;
        });
    return {
        news
    }

}

getNewsData()

</script>
<style scoped lang="less">
.card {
    font-size: small;
}
</style>