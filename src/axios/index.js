import axios from "axios";


import Vue from "vue";

const baseURL = '/api'

const ax = axios.create({
    baseURL
})

ax.interceptors.request.use(config => {
    let token = localStorage.getItem('token')
    if (token){
        config.headers['Authorization'] = 'Bearer ' + token
    }
    return config;
})

ax.interceptors.response.use(response => {
    return response.data
}, error => {
    Vue.prototype.$message.error(error.message)
    return Promise.reject(error);
})


export default {
    service: ax,
    baseURL
}
