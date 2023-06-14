<!-- Home.vue -->
<template>
    <div class="home">
      <h1>欢迎来到影视大厅</h1>
      <MediaList />
    </div>
  </template>
  
  <script>
  import MediaList from './MediaList.vue';
  
  export default {
    name: 'Home',
    components: {
      MediaList,
    },
  };
  </script>