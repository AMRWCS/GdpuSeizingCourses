<script setup lang="ts">
import SeizeMain from '@/components/seize/SeizeMain.vue'
import { removeCourse } from '@/api/course'
</script>

<template>
  <SeizeMain type="Remove" :onSubmit="removeCourse" />
</template>

<style scoped lang="scss"></style>
