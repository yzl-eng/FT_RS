<template>
    <div class="body">
        <el-container>
            <el-header>
                <div>最近热播</div>
            </el-header>
            <el-main>
                <el-space wrap size="small">
                    <el-row :gutter="20">
                        <el-col v-for="(video, index) in  films " :key="index" :span="6">
                            <el-card :body-style="{ padding: '0px' }" shadow="hover" class="card"
                                @click="goToDetail(video.id)" style="border-radius: 20px;">
                                <el-image :src="video.img" class="image" fit="fill" />
                                <div style="padding: 14px">
                                    <el-rate :model-value="score(video.score)" disabled text-color="#f7ba2a" />
                                    <div><el-text style="color: #f7ba2a;">{{ video.score }}</el-text>
                                        <div style="height: 35px;">
                                            <el-text class="mx-1">{{ video.name }}</el-text>
                                        </div>
                                    </div>
                                </div>
                            </el-card>
                        </el-col>
                    </el-row>
                </el-space>
                <div class="pagination"><el-pagination class="pagebutton" background="true" layout="prev, pager, next"
                        :total="total" /></div>
            </el-main>
        </el-container>
    </div>
</template>

<script setup>
import router from '../../router'
import { ref, watchEffect } from 'vue'
import API from "../../plugins/axiosInstance"

const scroe = ref(3)
const films = ref()
const total = ref()
const getFilmData = function () {
    const params = {
        pageNum: 1,
        pageSize: 6,
        typeId: 2

    };
    API.get("/video/queryData", { params })
        .then((res) => {
            films.value = res.data.data.records;
            total.value = res.data.data.total;
        });
    return {
        films
    }

}
const score = (value) => {
    return Math.round(value / 2)
}
getFilmData()
import { useRouter } from 'vue-router'
const goToDetail = (videoId) => {
    // 在这里使用 Vue Router 进行路由跳转
    // const router=useRouter()
    router.push({ path: `/videoDetail/${videoId}` })
}
</script>

<style lang="less" scoped>
.body {
    max-width: 1100px;
    margin: 0 auto;
    margin-bottom: 50px;
    margin-top: 100px;

    .card {

        .mx-1 {
            font-size: small;
        }
    }

    .pagination {
        max-width: 1100px;
        display: flex;
        justify-content: center;
        align-items: flex-end;
        position: fixed;
        bottom: 30px;
        width: 100%;

    }
}
</style>
