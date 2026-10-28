<script setup lang="ts"></script>

<template>
  <div class="seizeBtns">
    <router-link to="/seize/select" class="linkBtn" exact-active-class="active">Select</router-link>
    <router-link to="/seize/remove" class="linkBtn" exact-active-class="active">Remove</router-link>
  </div>
</template>

<style scoped lang="scss">
.seizeBtns {
  height: 35.28vh; /* 原 330px   */
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.linkBtn {
  width: var(--seize-button-width);
  display: flex;
  align-items: center;
  justify-content: center;

  background: #4197fa;
  border-radius: var(--radius-rounded);
  font-weight: bold;
  box-shadow: var(--box-shadow);

  transition: all 0.3s ease;

  &.active {
    border-color: #e2e2e2;
    transform: scale(1.2);
  }

  &:not(.active):hover {
    transform: scale(1.1);
  }

  // 原内容：
  //height: var(--seize-button-height);
  //font-size: larger;
  //border: 10px solid #e368ff;
  // 响应式内容：
  box-sizing: border-box;
  aspect-ratio: 2 / 1;
  border: clamp(6px, 0.6vw, 10px) solid #e368ff;
  white-space: nowrap;
  overflow: hidden;
  font-size: clamp(10px, 1.3vw, 24px);
}

/*
  原内容：
  无
  响应式内容：
*/
@media (max-width: 768px) {
  .seizeBtns {
    height: auto;
    flex-direction: row;
    justify-content: center;
    margin: 16px 0;
    gap: 40px;
  }
  .linkBtn {
    width: 100px;
    padding: 0 20px;
    border-width: 6px;
    font-size: 16px;
  }
}
</style>
