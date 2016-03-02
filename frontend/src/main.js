import styles from './css/public.css'

import Vue from 'vue'
import VueResource from 'vue-resource'
import VueRouter from 'vue-router'
import * as VueStrap from 'vue-strap'

import app from './app'
import configRoutes from './routes'

new Vue({
    http: {
        root: '/api',
        options: {
            emulateJSON: true,
            emulateHTTP: true
        }
    }
});

Vue.use(VueResource);
Vue.use(VueRouter);

export const router = new VueRouter({
    hashbang: false,
    history: true,
    mode: 'html5'
});

configRoutes(router);

router.start(Vue.extend(app), '#app');
