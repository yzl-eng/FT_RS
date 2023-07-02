
export default{
  server: {
    port: 3000,
    proxy: {
      '/api': {
        target:'http://127.0.0.1:8081', // 你请求的第三方接口
        // target就是你要访问的目标地址，可以是基础地址，这样方便在这个网站的其他api口调用数据
        ws: true,
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ''),
        // 要记得加rewrite这句
      },
    },
  },
}