import Vue from 'vue'
import VueRouter from 'vue-router'
import store from '../store'
Vue.use(VueRouter)

const routes = [

    {
        path: '/',
        name: 'Home',
        component: () => import('../views/Home.vue'),
        redirect: "/main",
        children:
            [
                {
                    path: 'user',
                    name: '用户管理',
                    component: () => import('../views/User.vue'),
                },
                {
                    path: 'main',
                    name: '首页',
                    component: () => import('../views/MainPage.vue'),
                },
                {
                    path: 'person',
                    name: '个人中心',
                    component: () => import('../views/Person.vue'),
                },
            ]
    },
    {
        path: '/login',
        name: "登录",
        component: () => import('../views/Login.vue'),
    }
]


const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

//路由守卫
router.beforeEach((to, from, next) => {
    localStorage.setItem("currentPathName", to.name)  // 设置当前的路由名称
    store.commit("setPath")

    // // 未找到路由的情况
    // if (!to.matched.length) {
    //     const storeMenus = localStorage.getItem("menus")
    //     if (storeMenus) {
    //         next("/404")
    //     } else {
    //         // 跳回登录页面
    //         next("/login")
    //     }
    // }
    // 其他的情况都放行
    next()

})

export default router;