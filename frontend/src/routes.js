import Home from './components/home'
import SecretQuote from './components/secret_quote'
import SignIn from './components/sign_in'
import SignUp from './components/sign_up'
import About from './components/about'
import PageNotFound from './components/not_found'

export default router => {
    router.map({
        '/': {
            component: Home
        },
        '/secretQuote': {
            component: SecretQuote
        },
        '/login': {
            component: SignIn
        },
        '/signUp': {
            component: SignUp
        },
        '/about': {
            component: About
        },
        "*": {
            component: PageNotFound
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
