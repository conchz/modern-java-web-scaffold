import styles from './css/styles.css'

import Vue from 'vue'
import VueResource from 'vue-resource'
import VueRouter from 'vue-router'
import * as VueStrap from 'vue-strap'

import app from './app'
import { configRouter } from './routers'

Vue.config.debug = true;

let frontendApp = new Vue({
    http: {
        root: '/api',
        headers: {
            Authorization: 'Basic YXBpOnBhc3N3b3Jk'
        },
        options: {
            emulateJSON: true,
            emulateHTTP: true
        }
    }
});

Vue.use(VueResource);
Vue.use(VueRouter);

let router = new VueRouter({
    hashbang: false,
    history: true,
    mode: 'html5'
});

configRouter(router);

router.start(Vue.extend(app), '#app');
