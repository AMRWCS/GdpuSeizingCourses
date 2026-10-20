<script setup lang="ts">
import SeizeNav from '@/components/seize/SeizeNav.vue'
</script>

<template>
  <div class="seize">
    <el-container style="padding: 80px var(--lr-align-padding) 20px">
      <el-aside width="270px">
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

.seizeNav {
  display: flex;
  align-items: center;
  justify-content: flex-start;
  margin: 188px 0;
  padding-left: 16px;
}

.mainForm {
  height: 710px;
  background: #fff;

  border-radius: 60px;
  box-shadow: var(--box-shadow);

  padding: 20px;

  overflow: auto;
}
</style>
