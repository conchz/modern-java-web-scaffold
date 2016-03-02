import { router } from './main'

const API_URL = 'http://localhost:8081/api',
    LOGIN_URL = API_URL + '/user/login',
    SIGNUP_URL = API_URL + '/user/signUp';

export default {
    user: {
        authenticated: false
    },
    login(context, creds, redirect) {
        context.$http.post(LOGIN_URL, creds, data => {
            localStorage.setItem('id_token', data.id_token);

            this.user.authenticated = true;

            if (redirect) {
                router.go(redirect)
            }
        }).error(err => {
            context.error = err
        })
    },
    signUp(context, creds, redirect) {
        context.$http.post(SIGNUP_URL, creds, data => {
            localStorage.setItem('id_token', data.id_token);

            this.user.authenticated = true;

            if (redirect) {
                router.go(redirect)
            }
        }).error(err => {
            context.error = err
        })
    },
    logout() {
        localStorage.removeItem('id_token');
        this.user.authenticated = false
    },
    checkAuth() {
        const jwt = localStorage.getItem('id_token');
        this.user.authenticated = !!jwt
    },
    getAuthHeader() {
        return {
            'Authorization': 'Bearer ' + localStorage.getItem('id_token')
        }
    }
}