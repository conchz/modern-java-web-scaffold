import Vue from 'vue';
import VueRouter from 'vue-router';
import VueStrap from 'vue-strap';

Vue.config.debug = true;

import AppComponent from './components/app-component/app-component';

new Vue({
    el: '#app',
    components: {
        'app-component': AppComponent
    },
    data: {}
});
