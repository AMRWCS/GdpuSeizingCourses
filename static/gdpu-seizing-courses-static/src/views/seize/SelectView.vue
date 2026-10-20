<script setup lang="ts">
import SeizeMain from '@/components/seize/SeizeMain.vue'
import { selectCourse } from '@/api/course'
</script>

<template>
  <SeizeMain type="Select" :onSubmit="selectCourse" />
</template>

<style scoped lang="scss"></style>
