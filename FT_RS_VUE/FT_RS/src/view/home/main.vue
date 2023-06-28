<template>
    <div>
        <div class="carousel">
                        <el-carousel :interval="5000" arrow="always" height="600px">
                            <el-carousel-item v-for="(video, index) in  videos_carousel " :key="index">
                                <div class="carousel_img" :style="{ backgroundImage: `url(${video.img})` }">
                                    <div class="intro">
                                        <h1 style="font-size:x-large;"><span v-text="video.name"></span></h1>
                                        <h1 style="font-size:small;"><span v-text="video.releasedate"></span>&emsp;评分：<span
                                                v-text="video.score"  style="color: #f7ba2a" ></span></h1>
                                        <el-text class="mx-1">{{ video.introduction }}</el-text>
                                    </div>
                                </div>
                            </el-carousel-item>
                        </el-carousel>
                    </div>

                    <div class="body1">
                        <hot_play></hot_play>
                    </div>
                    <div class="body1">
                        <news_dispaly></news_dispaly>
                    </div>
    </div>
</template>
<script lang="ts" setup>
import router from '../../router'
import { ref, watchEffect } from 'vue'
import API from "../../plugins/axiosInstance"
import hot_play from './hot_play.vue';
import news_dispaly from './news_dispaly.vue';

const videos_carousel = ref()
const display_num = ref(4)

const getCarouselData = function () {
    const params = {
        pageNum: 1,
        pageSize: display_num.value,

    };

    API.get("/video/findNum", { params }).then((res) => {
        videos_carousel.value = res.data.data;
    });
    return {
        videos_carousel
    }

}
getCarouselData()
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
.carousel {
   padding-top:80px ;
    max-width: 1100px;
    margin: 0 auto;
    margin-bottom: 50px;


    .carousel_img {
        width: 100%;
        height: 100%;
        background-size: cover;
        background-repeat: no-repeat;
        // border-radius: 50px;

    }

    .intro {
        position: absolute;
        bottom: 0;
        left: 0;
        width: 100%;
        padding: 16px;
        box-sizing: border-box;
        background-color: rgba(0, 0, 0, 0.192);
        color: #ffffff;
        z-index: 1;
        /* 提高层叠顺序 */
    }

    .mx-1 {
        font-size: small;
        line-height: 20px;
        height: 50px;
        color: #ffffff;
    }
}

.body1 {
    max-width: 1100px;
    margin: 0 auto;
    margin-bottom: 50px;
}

.el-carousel__item h3 {
    color: #475669;
    opacity: 0.75;
    line-height: 300px;
    margin: 0;
    text-align: center;
}

.el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
    background-color: #d3dce6;
}
</style>