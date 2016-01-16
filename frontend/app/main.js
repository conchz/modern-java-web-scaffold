import Vue from 'vue';
Vue.config.debug = true;

import AppComponent from './components/app-component/app-component';

new Vue({
  el: '#app',
  components: {
    'app-component': AppComponent
  },
  data: {
  }
});
