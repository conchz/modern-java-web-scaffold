import Vue from 'vue';
import VueResource from 'vue-resource';
import VueRouter from 'vue-router';
import VueStrap from 'vue-strap';

Vue.config.debug = true;
Vue.use(VueResource);

import HomeComponent from './components/home/Home';

let app = new Vue({
    el: '#app',
    components: {
        'home': HomeComponent
    },
    data: {
        currentView: 'home'
    },
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
