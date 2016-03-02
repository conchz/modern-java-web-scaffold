import './css/public.css'

import Vue from 'vue'
import VueResource from 'vue-resource'
import VueRouter from 'vue-router'

import app from './app'
import auth from './auth'
import configRoutes from './routes'

new Vue({
    http: {
        options: {
            emulateJSON: true,
            emulateHTTP: true
        }
    }
});

Vue.use(VueResource);
Vue.use(VueRouter);

auth.checkAuth();

export const router = new VueRouter({
    hashbang: false,
    history: true,
    mode: 'html5'
});

configRoutes(router);

router.start(Vue.extend(app), '#app');
