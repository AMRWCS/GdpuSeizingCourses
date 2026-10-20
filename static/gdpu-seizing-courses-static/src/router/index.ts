import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: '/home',
    },
    {
      path: '/home',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/seize',
      component: () => import('../views/SeizeView.vue'),
      children: [
        { path: '', redirect: '/seize/select' },
        {
          path: 'select',
          name: 'seize-select',
          component: () => import('../views/seize/SelectView.vue'),
        },
        {
          path: 'remove',
          name: 'seize-remove',
          component: () => import('../views/seize/RemoveView.vue'),
        },
      ],
    },
  ],
})

export default router
