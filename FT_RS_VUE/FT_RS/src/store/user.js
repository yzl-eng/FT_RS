import API from "../plugins/axiosInstance"
import { defineStore } from 'pinia'
import router from "../router"
// 创建 store
const useUserStore = defineStore('user', {
    // 定义状态：一个函数，返回一个对象
    state: () => ({
        token: '',
        id:''
    }),

    // 定义 getters，等同于组件的计算属性
    getters: {
        // getter 函数接收 state 作为参数，推荐使用箭头函数
        hello: state => 'Hello!' + state.username
    },

    // 定义 actions，有同步和异步两种类型
    actions: {
        // 异步 action，一般用来处理异步逻辑
        async login(userData) {
            const username=userData.username
            const password=userData.password
            const result = await API.post('/user/login', {username,password})
            const { data, code } = result.data
            if (code === 1) {
                // action 中修改状态
                this.token = data.token
                this.id=data.id
                window.localStorage.setItem("token", data.token)
                router.push('/home')
            }
            else{
                router.push('/login')
            }
        },

        // 同步 action
        logout() {
            this.token = ''
        }
    },
    persist: true,
})

export default useUserStore
