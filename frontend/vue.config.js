module.exports = {
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true,
        pathRewrite: {
          '^/': ''
        }
      }
    }
  }
}

//경로가 /인 경우 우회헤서 실행하겠다.