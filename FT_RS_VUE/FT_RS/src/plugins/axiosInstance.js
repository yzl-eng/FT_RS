//axiosInstance.js
//导入axios
import axios from 'axios'

//使用axios下面的create([config])方法创建axios实例，其中config参数为axios最基本的配置信息。
const userString = localStorage.getItem('user');
let token=''
const user = JSON.parse(userString);
if(user!==null){
	token = user.token;
}
else{
	token=''
}

const API = axios.create({
	// baseURL:'/api', //请求后端数据的基本地址，自定义
	baseURL:'http://localhost:8081',
	timeout: 2000,                   //请求超时设置，单位ms
	headers:{'token':window.localStorage.getItem("token")

	}
})

//导出我们建立的axios实例模块，ES6 export用法
export default API