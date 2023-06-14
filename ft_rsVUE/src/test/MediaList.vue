<!-- MediaList.vue -->
<template>
    <div class="media-list">
      <el-row :gutter="20">
        <MediaItem v-for="media in mediaList" :key="media.id" :media="media" />
      </el-row>
    </div>
  </template>
  

  <script>
  import MediaItem from './MediaItem.vue';

  export default {
    name: 'MediaList',
    components: {
      MediaItem,
    },
    data() {
      return {
        mediaList: [
          {
            id: 1,
            title: '权力的游戏',
            description: '描述1',
            coverImage: 'https://picsum.photos/id/856/500/500',
          },
          {
            id: 2,
            title: '霹雳娇娃',
            description: '描述2',
            coverImage: 'https://picsum.photos/id/1018/500/500',
          },
          {
            id: 3,
            title: '少年的你',
            description: '描述3',
            coverImage: 'https://picsum.photos/id/888/500/500',
          },
          {
            id: 4,
            title: '世界级赌场',
            description: '描述4',
            coverImage: 'https://picsum.photos/id/639/500/500',
          },
        ],
      };
    },
  };
  </script>