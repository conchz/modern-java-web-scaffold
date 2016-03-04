<template>
    <div>
        <h3>Message: {{msg}}</h3>
        <input class="form-control" v-model="msg">
        <br>
        <button class="btn btn-default" @click="greet">Greet</button>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                msg: 'Hello, welcome to use Vue!'
            }
        },
        methods: {
            greet: function (event) {
                alert(event.target.tagName)
            }
        }
    }
</script>
