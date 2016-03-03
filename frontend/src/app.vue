<template>
    <div class="container">
        <p>current path: {{$route.path}}</p>
        <a v-link="{ path: '/', exact: true }">首页</a>
        <a v-link="{ path: '/login', exact: true }">登录</a>
        <a v-link="{ path: '/signUp', exact: true }">注册</a>
        <a v-link="{ path: '/about', exact: true }">关于</a>
        <router-view transition-mode="out-in"></router-view>
    </div>
</template>

<script>
    import * as VueStrap from 'vue-strap'

    export default {
        events: {}
    };
</script>
