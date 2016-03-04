import Home from 'views/home'
import SecretQuote from 'views/secret_quote'
import SignIn from 'views/sign_in'
import SignUp from 'views/sign_up'
import About from 'views/about'
import PageNotFound from 'views/not_found'

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
