<script setup lang="ts">
import SeizeNav from '@/components/seize/SeizeNav.vue'
</script>

<template>
  <div class="seize">
    <!-- 原 80px var(--lr-align-padding) 20px -->
    <el-container>
      <!-- 原 270px -->
      <el-aside width="15.89vw">
        <div class="seizeNav">
          <SeizeNav />
        </div>
      </el-aside>
      <el-main style="padding: 0; overflow: visible">
        <div class="mainForm"><RouterView /></div>
      </el-main>
    </el-container>
  </div>
</template>

<style scoped lang="scss">
.seize {
  flex: 1;
  display: flex;
}

.el-container {
  padding: 7vh var(--lr-align-padding) 2.14vh;
}

.seizeNav {
  display: flex;
  align-items: center;
  justify-content: flex-start;
  margin: 20.1vh 0; /* 原 188px 0   */
  padding-left: 16px;
}

.mainForm {
  height: 75.91vh; /* 原 710px   */
  background: #fff;

  border-radius: 60px;
  box-shadow: var(--box-shadow);

  padding: 2.14vh; /* 原 20px   */

  overflow: hidden;
}

/*
  原内容：
  无
  响应式内容：
*/
@media (max-width: 768px) {
  .seize :deep(.el-container) {
    flex-direction: column;
    padding: 4vh var(--lr-align-padding) 2.14vh;
  }
  .seize :deep(.el-aside) {
    width: 100% !important;
  }

  .seizeNav {
    margin: 0 0 16px;
    padding-left: 0;
    justify-content: center;
  }

  .mainForm {
    height: auto;
    min-height: 400px;
    border-radius: 24px;
    padding: 16px;
  }
}
</style>
