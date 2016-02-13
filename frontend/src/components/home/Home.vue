<template>
    <div class="nav">
        <a href="#signIn">登录</a>
        <a href="#signUp">注册</a>
    </div>
    <div>
        <h3>Message: {{msg}}</h3>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                msg: 'Hello, welcome to use Vue!'
            }
        },
        methods: {}
    }
</script>
