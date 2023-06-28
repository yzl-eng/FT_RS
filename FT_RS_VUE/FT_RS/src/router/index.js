// 导入router所需的方法
import { createRouter, createWebHistory } from 'vue-router'

import videoInfo from '../view/video/videoInfo.vue'
import menu from '../components/menu.vue'
import newsInfo from '../view/news/newsInfo.vue'
import home from '../view/home/home.vue'
import videoDetail from '../view/home/video_detail.vue'
import main from '../view/home/main.vue'
import films from '../view/home/films_display.vue'
import teleplays from '../view/home/teleplays_display.vue'
import login from '../view/login.vue'
// 导入路由页面的配置
// import routes from './routes'

const routes = [
    
    {
        path:'/',
        name:'menu',
        component: menu,
        children:[
            {
                path: '/videoInfo',
                component: videoInfo 
            },
            {
                path: '/newsInfo',
                component: newsInfo 
            },
            
        ]
    },
    {
        path:'/home',
        name:'home',
        component: home,
        children:[
            {
                path: '/main',
                component: main 
            },
            {
                path: '/videoDetail/:id',
                component: videoDetail 
            },
            {
                path: '/films',
                component: films 
            },
            {
                path: '/teleplays',
                component: teleplays 
            },
        
        ]
    },
    {
        path:'/login',
        name:'login',
        component: login,
    }
    
]

// 路由参数配置
const router = createRouter({
    // 使用hash(createWebHashHistory)模式，(createWebHistory是HTML5历史模式，支持SEO)
    history: createWebHistory(process.env.BASE_URL),
    routes: routes, 
})


// 全局前置守卫，这里可以加入用户登录判断
// router.beforeEach((to, from, next) => {
//     // 继续前进 next()
//     // 返回 false 以取消导航
//     next()
// })

// 全局后置钩子，这里可以加入改变页面标题等操作
// router.afterEach((to, from) => {
//     const _title = to.meta.title
//     if (_title) {
//         window.document.title = _title
//     }
// })

// 导出默认值
export default router