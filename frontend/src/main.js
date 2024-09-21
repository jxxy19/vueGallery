import { createApp } from 'vue'
import store from "@/js/store"
import router from "@/js/router";
import App from './App.vue'


createApp(App).use(store).use(router).mount('#app')
