import { createRouter, createWebHashHistory } from 'vue-router'
import Main from '../views/Main.vue'
import Login from '../views/Login.vue'

const routes = [
  {
    path: '/',
    component: Main,
    redirect: '/student-list',
    children: [
      {
        path: '/student-list',
        component: () => import('../views/StudentList.vue')
      },
      {
        path:'/schoolAdministration',
        component: () => import('../views/SchoolAdministration.vue')
      },
      {
        path:'/major',
        component: () => import('../views/Major.vue')
      },
      {
        path:'/technique',
        component: () => import('../views/Technique.vue')
      },
      {
        path:'/achievement',
        component: () => import('../views/Achievement.vue')
      },
      {
        path:'/parameter',
        component: () => import('../views/Parameter.vue')
      },
      {
        path:'/questions',
        component: () => import('../views/Questions.vue')
      },
    ]
  },
  {
    path: '/login',
    component: Login
  }
]



const router = createRouter({
  history: createWebHashHistory(),
  routes
})


export default router
