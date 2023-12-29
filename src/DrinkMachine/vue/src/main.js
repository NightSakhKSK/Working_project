import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import global from './common/global.js'

createApp(App).use(store).use(router).use(global).mount('#app')
