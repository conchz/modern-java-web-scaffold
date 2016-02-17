import HomeComponent from './components/home'
import SignInComponent from './components/sign_in'
import SignUpComponent from './components/sign_up'
import AboutComponent from './components/about.vue'
import NotFoundComponent from './components/not_found'

export function configRouter(router) {
    router.map({
        '/': {
            component: HomeComponent
        },
        '/signIn': {
            component: SignInComponent
        },
        '/signUp': {
            component: SignUpComponent
        },
        '/about': {
            component: AboutComponent
        },
        "*": {
            component: NotFoundComponent
        }
    });

    router.beforeEach((transition) => {
        if (transition.to.path === '/forbidden') {
            router.app.authenticating = true;
            setTimeout(() => {
                router.app.authenticating = false;
                alert('this route is forbidden by a global before hook');
                transition.abort()
            }, 3000)
        } else {
            transition.next()
        }
    })
}
