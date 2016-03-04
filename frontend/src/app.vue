<template>
    <header-component></header-component>
    <div class="container">
        <router-view transition-mode="out-in"></router-view>
    </div>
    <footer-component></footer-component>
</template>

<script>
    import HeaderComponent from 'components/header'
    import FooterComponent from 'components/footer'

    export default {
        data() {
        },
        components: {
            'header-component': HeaderComponent,
            'footer-component': FooterComponent
        },
        events: {}
    };
</script>
