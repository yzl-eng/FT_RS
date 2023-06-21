<!-- MediaItem.vue -->
<template>
    <div class="media-item">
      <el-card shadow="hover" :body-style="{ padding: '0px' }">
        <img :src="media.coverImage" class="media-cover" />
        <div class="media-title">{{ media.title }}</div>
        <div class="media-description">{{ media.description }}</div>
      </el-card>
    </div>
  </template>
  
  <script>
  export default {
    name: 'MediaItem',
    props: {
      media: {
        type: Object,
        required: true,
      },
    },
  };
  </script>
  
  <style scoped>
  .media-item {
    margin-bottom: 20px;
  }
  
  .media-cover {
    width: 100%;
    height: 300px;
    object-fit: cover;
  }
  
  .media-title {
    font-size: 16px;
    font-weight: bold;
    margin: 10px 0;
  }
  
  .media-description {
    font-size: 14px;
    color: #909399;
    margin-bottom: 10px;
  }
  </style>