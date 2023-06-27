<template>
    <el-container>
        <el-header>
            <div>最近热播</div>
        </el-header>
        <el-main>
            <el-space wrap size="small">
                <el-row :gutter="10">
                    <el-col v-for="(video, index) in  videos_hot " :key="index" :span="4">
                        <el-card :body-style="{ padding: '0px' }" shadow="hover" class="card">
                                <el-image :src="video.img" class="image" fit="fill" />
                            <div style="padding: 14px">
                                <el-rate v-model="video.score" disabled :show-score="ture" text-color="#f7ba2a" />
                                <div><span v-text="video.score"></span></div>
                                <div>
                                    <el-text class="mx-1">{{ video.name }}</el-text>
                                </div>

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

const videos_hot = ref()
const getHotData = function () {
    const params = {
        pageNum: 1,
        pageSize: 5,

    };
    API.get("/video/findNum", { params })
        .then((res) => {
            videos_hot.value = res.data.data;
        });
    return {
        videos_hot
    }

}

getHotData()

</script>
<style scoped lang="less">
.card{
    
    .mx-1{
        font-size:small;
    }
}
</style>