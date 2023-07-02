<template>
    <el-container>
        <el-header>
            <div>最近热播</div>
        </el-header>
        <el-main>
            <el-space wrap size="small">
                <el-row :gutter="10">
                    <el-col v-for="(video, index) in  videos_hot " :key="index" :span="4">
                        <el-card :body-style="{ padding: '0px' }" shadow="hover" class="card" @click="goToDetail(video.id)" style="border-radius: 20px;">
                            <el-image :src="video.img" class="image" fit="fill" />
                            <div style="padding: 14px">
                                <el-rate :model-value="score(video.score)"  disabled text-color="#f7ba2a"/>
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
        </el-main>
    </el-container>
</template>
<script setup>
import router from '../../router'
import { ref, watchEffect } from 'vue'
import API from "../../plugins/axiosInstance"

const scroe = ref(3)
const videos_hot = ref()
const getHotData = function () {
    const params = {
        pageNum: 1,
        pageSize: 6,

    };
    API.get("/video/findNum", { params })
        .then((res) => {
            videos_hot.value = res.data.data;
            if(res.data.code==0){
                router.push('/login')
            }
        });
    return {
        videos_hot
    }

}
const score = (value) => {
  return Math.round(value / 2)
}
getHotData()
import { useRouter} from 'vue-router'
const goToDetail=(videoId)=> {
    // 在这里使用 Vue Router 进行路由跳转
    // const router=useRouter()
    router.push({ path: `/videoDetail/${videoId}` })
  }

</script>
<style scoped lang="less">
.card {

    .mx-1 {
        font-size: small;
    }
}
</style>