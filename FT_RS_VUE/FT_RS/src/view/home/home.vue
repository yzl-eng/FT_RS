<template>
    <div class="common-layout">
        <el-container>
            <el-header height="80px" class="header">
                <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" :ellipsis="false"
                    @select="handleSelect" active-text-color="#9ecc3e" style="height: 80px;" router>
                    <el-menu-item index="0">LOGO</el-menu-item>
                    <div class="flex-grow" />
                    <el-menu-item index="/main"><el-icon>
                            <House />
                        </el-icon>首页</el-menu-item>
                    <el-menu-item index="/films"><el-icon>
                            <Film />
                        </el-icon>电影</el-menu-item>
                    <el-menu-item index="/teleplays"><el-icon>
                            <Platform />
                        </el-icon>电视剧</el-menu-item>
                    <el-menu-item index="4"><el-icon>
                            <VideoPlay />
                        </el-icon>综艺</el-menu-item>
                    <el-menu-item index="5"><el-icon>
                            <VideoCameraFilled />
                        </el-icon>纪录片</el-menu-item>
                    <div class="flex-grow" />
                    <div class="research"><el-input v-model="input1" size="large" placeholder="搜索感兴趣的影视"
                            :prefix-icon="Search" class="input"/>
                    </div>
                    <div class="flex-grow" />

                </el-menu>
            </el-header>
            <el-container>
                <el-main>
                    <router-view></router-view>
                </el-main>
            </el-container>


        </el-container>
    </div>
</template>
  
<script lang="ts" setup>
import router from '../../router'
import { ref, watchEffect } from 'vue'
import API from "../../plugins/axiosInstance"
import Main from "./main.vue"

const activeIndex = ref('1')
const handleSelect = (key: string, keyPath: string[]) => {
    console.log(key, keyPath)
}

import { Search } from '@element-plus/icons-vue'
const input1 = ref('')
</script>

  
<style scoped lang="less">
.header {
    position: fixed;
    width: 100%;
    top: 0;
    z-index: 6;

    .research {
        display: flex;
        justify-content: center;
        /* 水平居中 */
        align-items: center;

        /* 垂直居中 */
        .input:focus {
            border-color: #9ecc3e;
            // box-shadow: 0 0 2px rgba(0, 0, 255, 0.5);
        }
    }

}

.flex-grow {
    flex-grow: 1;
}
</style>