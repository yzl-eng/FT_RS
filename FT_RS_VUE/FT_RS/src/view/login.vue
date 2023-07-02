<template>
    <div>
        <el-form v-model="userData" label-width="80px" class="login-box">
            <h3 class="login-title">欢迎登录</h3>
            <el-form-item label="账号">
                <el-input type="text" placeholder="请输入账号" v-model="userData.username" />
            </el-form-item>
            <el-form-item label="密码">
                <el-input type="password" placeholder="请输入密码" v-model="userData.password" />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" v-on:click="onLogin">登录</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>



<script setup>
import { ref } from 'vue';
import API from './../plugins/axiosInstance'


import { reactive } from 'vue'
import useUserStore from "../store/user"

const userData = ref({
    username: '',
    password: '',
})

// 实例化 store
const userStore = useUserStore()

const onLogin = async () => {
    // 使用 actions，当作函数一样直接调用
    // login action 定义为了 async 函数，所以它返回一个 Promise
     await userStore.login(userData.value)
    // const username = userData.value.username
    // const password = userData.value.password
    //  await API.post('/user/login', { username, password })

}
const onLogout = () => {
    userStore.logout()
}

</script>

<style scoped>
.login-box {
    border: 1px solid #DCDFE6;
    width: 350px;
    margin: 180px auto;
    padding: 35px 35px 15px 35px;
    border-radius: 5px;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    box-shadow: 0 0 25px #909399;
}

.login-title {
    text-align: center;
    margin: 0 auto 40px auto;
    color: #303133;
}
</style>
    
    