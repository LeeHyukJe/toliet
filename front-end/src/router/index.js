import Vue from 'vue'
import Router from 'vue-router'
import LoginPage from '@/views/LoginPage'
import RegisterPage from '@/views/RegisterPage'
import HomePage from '@/views/HomePage'
import KakaoMap from '@/views/KakaoMap'
import BoardPage from '@/views/BoardPage'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [{
    path: '/login',
    name: 'login',
    component: LoginPage
  }, {
    path: '/register',
    name: 'register',
    component: RegisterPage
  }, {
    path: '/',
    name: 'home',
    component: HomePage
  }, {
    path: '/kakaomap',
    name: 'kakaomap',
    component: KakaoMap
  }, {
    path: '/board/:boardId',
    name: 'board',
    component: BoardPage
  }]
})
