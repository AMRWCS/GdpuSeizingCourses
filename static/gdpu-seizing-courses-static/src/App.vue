<script setup lang="ts">
import { RouterView } from 'vue-router'
import AppNav from '@/components/app/AppNav.vue'
import AppFooter from '@/components/app/AppFooter.vue'
</script>

<template>
  <div class="app-layout">
    <AppNav />
    <main class="page-body">
      <RouterView />
    </main>
    <AppFooter />
  </div>
</template>

<style scoped lang="scss">
.app-layout {
  display: flex;
  flex-direction: column; /* 纵向布局 */

  // 原内容：
  // min-height: 100vh;
  // 响应式内容：
  min-height: 100dvh;
  width: 100%;
}
.page-body {
  flex: 1; /* 页面内容占满剩余空间 */

  // 原内容：
  // 无
  // 响应式内容：
  min-height: 0;
  overflow-x: hidden;
}
</style>
