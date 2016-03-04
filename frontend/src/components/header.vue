<template>
    <nav class="navbar navbar-default">
        <div class="container">
            <ul class="nav navbar-nav">
                <li><a v-link="{ path: '/', exact: true }">首页</a></li>
                <li><a v-link="{ path: '/login', exact: true }" v-if="!user.authenticated">登录</a></li>
                <li><a v-link="{ path: '/signUp', exact: true }" v-if="!user.authenticated">注册</a></li>
                <li><a v-link="{ path: '/about', exact: true }" v-if="!user.authenticated">关于</a></li>
                <li><a v-link="{ path: '/secretQuote', exact: true }" v-if="user.authenticated">Secret Quote</a></li>
                <li><a v-link="'login'" v-if="user.authenticated" @click="logout()">退出</a></li>
            </ul>
        </div>
    </nav>
</template>

<script>
    import auth from 'src/auth'

    export default {
        data() {
            return {
                user: auth.user
            }
        },
        methods: {
            logout() {
                auth.logout()
            }
        }
    };
</script>