<script setup lang="ts"></script>

<template>
  <div class="seizeBtns">
    <router-link to="/seize/select" class="linkBtn" exact-active-class="active">Select</router-link>
    <router-link to="/seize/remove" class="linkBtn" exact-active-class="active">Remove</router-link>
  </div>
</template>

<style scoped lang="scss">
.seizeBtns {
  height: 330px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.linkBtn {
  height: var(--seize-button-height);
  width: var(--seize-button-width);
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: larger;

  background: #4197fa;
  border: 10px solid #e368ff;
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
}
</style>
