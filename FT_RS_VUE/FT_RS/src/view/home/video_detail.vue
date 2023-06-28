<template>
    <div>
        <div class="header" :style="{ backgroundImage: `url(${video.img})` }">
            <div class="intro">
                <div class="main">
                    <el-row :gutter="10">
                        <el-col :span="6">
                            <h2>{{ video.name }}</h2>
                            <el-image style="width: 200px; height: 300px" :src="video.img" :fit="fit" />
                        </el-col>
                        <el-col :span="8" style="margin-top: 70px;" class="middle">
                            <div>导演：<span v-text="video.director"></span></div>
                            <div>编剧：<span v-text="video.screenwriter"></span></div>
                            <div>主演：<span v-text="video.starring"></span></div>
                            <div>形式：<span v-text="getType(video.typeId).type"></span></div>
                            <!-- <div>类型：<span v-text="getCategory(video.typeId).value"></span></div> -->
                            <div>出品国家/地区：<span v-text="video.countryRegion"></span></div>
                            <div>语言：<span v-text="video.language"></span></div>
                            <div>上线时间：<span v-text="video.releasedate"></span></div>
                            <div>时长：<span v-text="video.length"></span>（分钟）</div>
                            <div>简介：<span v-text="video.introduction"></span></div>


                        </el-col>
                        <el-col :span="5" style="margin-top: 70px;" class="right" :offset="5">
                            <el-rate :model-value="score(video.score)" disabled text-color="#f7ba2a" size="large" />
                            <div>
                                <span style="color: #f7ba2a;" v-text="video.score"></span>
                            </div>
                        </el-col>
                    </el-row>
                </div>
            </div>
        </div>

        <div class="body">
            <div class="top">
                <h2><el-icon>
                        <ChatDotRound />
                    </el-icon>&nbsp;相关评论</h2>
            </div>
            <div class="mark">
                <el-rate v-model="rate" :texts="['很差', '较差', '一般', '推荐', '强推']" show-text allow-half />
                <el-button type="primary" size="middle" style="float: right;">发表评价</el-button>
                <div style="margin: 20px 0" />
                <el-input v-model="textarea" :autosize="{ minRows: 2, maxRows: 4 }" type="textarea"
                    placeholder="留下你的评价" />
            </div>
            <div class="comment">
                <el-row :gutter="10">
                    <el-col v-for="(item, index) in comments" :key="index" :span="12">
                        <el-card :body-style="{ padding: '0px' }" shadow="hover" class="card">
                            <template #header>
                                <div class="card-header">
                                    <span>Card name</span>&emsp;&emsp;
                                    <el-rate :model-value="score(item.score)" disabled text-color="#f7ba2a" />
                                    &emsp;&emsp; <span v-text="item.createTime"
                                        style="color: rgb(215, 216, 216);font-size: small;"></span>
                                </div>

                            </template>
                            <div style="padding: 14px">

                                <div style="max-height: 100px;">
                                    <el-text class="mx-1">{{ item.remark }}</el-text>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                </el-row>
            </div>
        </div>
    </div>
</template>
  
<script setup>
import { ref } from 'vue'
// import route from '../../router'
import API from "../../plugins/axiosInstance"

import { useRoute } from 'vue-router'
const route = useRoute()
const videoid = route.params.id// 获取路由参数中的 ID 值
const video = ref({
    name: '错误'
})
const type = ref()
const getType = (typeId) => {
    const params = {
        id: typeId

    };

    API.get("/type/findById", { params })
        .then((res) => {
            type.value = res.data.data.type;
        });
    return {
        type
    }
}
const score = (value) => {
    return Math.round(value / 2)
}
const getVideoDetail = function () {
    const params = {
        id: videoid

    };
    API.get("/video/findById", { params })
        .then((res) => {
            video.value = res.data.data;
        });
    return {
        video
    }
    // API({
    //     url: '/type',
    //     method: 'post',
    //     data: videoid
    // }).then((res) => {

}
getVideoDetail()

const comments = ref()
const textarea = ref('')
const getCommentData = function () {
    const params = {
        pageNum: 1,
        pageSize: 5,
        id: videoid

    };
    API.get("/score/findAll", { params })
        .then((res) => {
            comments.value = res.data.data.records;
        });
    return {
        comments
    }

}
const rate = ref()

getCommentData()
const getData = function () {
    const params = {
        id: comments

    };
    API.get("/score/findAll", { params })
        .then((res) => {
            comments.value = res.data.data.records;
        });
    return {
        comments
    }

}


</script>

<style scoped lang="less">
.header {
    width: 100%;
    height: 550px;
    background-size: cover;
    background-repeat: no-repeat;
    background-position: center center;
    margin-top: 50px;

    .intro {
        width: 100%;
        height: 100%;
        padding: 16px;
        box-sizing: border-box;
        backdrop-filter: blur(30px);
        background-color: rgba(0, 0, 0, 0.518);
        color: #ffffff;
        z-index: 5;
        /* 提高层叠顺序 */
    }

    .main {
        padding-top: 20px;
        max-width: 1200px;
        margin: 0 auto;


        .left {}

        .middle {
            padding-top: 10px;
            font-size: 14px;
            line-height: 20px;
            color: #cecdcd;

        }

        .right {
            font-size: 30px;

        }
    }


}

.body {
    padding-top: 20px;
    max-width: 1200px;
    margin: 0 auto;
    height: 100%;

    .top {}
        .mark{
            margin-bottom: 30px;
        }
        .comment {
            .card {
                height: 100%;

                .mx-1 {
                    height: 100%;
                }
            }

        }
    }

</style>