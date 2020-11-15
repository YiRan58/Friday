import Vue from 'vue'
import Router from 'vue-router'
import HomePage from '@/components/HomePage'
import Register from "../components/views/Register"
import Result from "../components/views/Result";


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HomePage',
      component: HomePage
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path: '/Result',
      name: 'Result',
      component: Result
    }
  ]
})
