import axios from 'axios'
import store from '../store'

// 공통 설정을 할 수 있는 api
const instance = axios.create({
  baseURL: process.env.VUE_APP_API_URL,
});

// request   

instance.interceptors.request.use(
  config => {
    if (store.state.token !== null) {
      config.headers.common['Authorization'] = 'Bearer ' + store.state.token; //           token            
    }
    //console.log('show headers', config.headers);
    return config
  },
  error => {
    // Do something with request error
    console.log('error', error) // for debug
    Promise.reject(error)
  },
)

// response    
instance.interceptors.response.use(
  response => {
    const res = response;
    console.log(res);
    if (res.status !== 200) {
      console.log(res);
    } else {
      return response;
    }
  },
  error => {
    console.log('err' + error) // for debug
    return Promise.reject(error)
  }
)

function post(apiName, userData) {
  console.log('baseURL', process.env.VUE_APP_API_URL);
  return instance.post(apiName, userData);
}

export { post };