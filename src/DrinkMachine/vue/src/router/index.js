import { createRouter, createWebHashHistory } from 'vue-router'
import LoginView from '../views/LoginView.vue'
import store from '../store'

const requireAuth = (role) => (from, to, next) => {
  if (store.state.role == role) return next()
  next(to)
}

const routes = [
  {
    path: '/',
    name: 'login',
    component: LoginView
  },
  /*
   * 사용자
   */
  {
    path: '/user',
    name: 'user',
    component: () => import(/* webpackChunkName: "user" */ '../views/user/UserView.vue'),
    beforeEnter: requireAuth('USER'),
  },
  {
    path: '/user/chpw',
    name: 'user_chpw',
    component: () => import(/* webpackChunkName: "user_chpw" */ '../views/user/ChpwView.vue'),
    beforeEnter: requireAuth('USER'),
  },
  {
    path: '/user/order',
    name: 'user_order',
    component: () => import(/* webpackChunkName: "user_order" */ '../views/user/OrderView.vue'),
    beforeEnter: requireAuth('USER'),
  },
  {
    path: '/user/emergency',
    name: 'user_emergency',
    component: () => import(/* webpackChunkName: "user_emergency" */ '../views/user/EmergencyView.vue'),
    beforeEnter: requireAuth('USER'),
  },
  {
    path: '/user/emergency/list',
    name: 'user_emergency_list',
    component: () => import(/* webpackChunkName: "user_emergency" */ '../views/user/EmergencyListView.vue'),
    beforeEnter: requireAuth('USER'),
  },
  {
    path: '/user/device',
    name: 'user_device',
    component: () => import(/* webpackChunkName: "user_device" */ '../views/user/DeviceView.vue'),
    beforeEnter: requireAuth('USER'),
  },
  {
    path: '/user/join',
    name: 'user_join',
    component: () => import(/* webpackChunkName: "user_join" */ '../views/user/JoinView.vue')
  },
  /*
   * 대리점
   */
  {
    path: '/agency',
    name: 'agency',
    component: () => import(/* webpackChunkName: "agency" */ '../views/agency/AgencyView.vue'),
    beforeEnter: requireAuth('AGENCY'),
  },
  {
    path: '/agency/chpw',
    name: 'agency_chpw',
    component: () => import(/* webpackChunkName: "agency_chpw" */ '../views/agency/ChpwView.vue'),
    beforeEnter: requireAuth('AGENCY'),
  },
  {
    path: '/agency/join',
    name: 'agency_join',
    component: () => import(/* webpackChunkName: "agency_join" */ '../views/agency/JoinView.vue')
  },
  {
    path: '/agency/user/add',
    name: 'agency_user_add',
    component: () => import(/* webpackChunkName: "agency_user_add" */ '../views/agency/UserAddView.vue'),
    beforeEnter: requireAuth('AGENCY'),
  },
  {
    path: '/agency/user/device',
    name: 'agency_user_device',
    component: () => import(/* webpackChunkName: "agency_user_device" */ '../views/agency/UserDeviceView.vue'),
    beforeEnter: requireAuth('AGENCY'),
  },
  {
    path: '/agency/user/emergency',
    name: 'agency_user_emergency',
    component: () => import(/* webpackChunkName: "agency_user_emergency" */ '../views/agency/UserEmergencyView.vue'),
    beforeEnter: requireAuth('AGENCY'),
  },
  {
    path: '/agency/user/history',
    name: 'agency_user_history',
    component: () => import(/* webpackChunkName: "agency_user_history" */ '../views/agency/UserHistoryView.vue'),
    beforeEnter: requireAuth('AGENCY'),
  },
  {
    path: '/agency/user/history/order',
    name: 'agency_user_history_order',
    component: () => import(/* webpackChunkName: "agency_user_history_order" */ '../views/agency/UserHistoryOrderView.vue'),
    beforeEnter: requireAuth('AGENCY'),
  },

  {
    path: '/agency/user/list',
    name: 'agency_user_list',
    component: () => import(/* webpackChunkName: "agency_user_list" */ '../views/agency/UserListView.vue'),
    beforeEnter: requireAuth('AGENCY'),
  },
  {
    path: '/agency/user/order',
    name: 'agency_user_order',
    component: () => import(/* webpackChunkName: "agency_user_order" */ '../views/agency/UserOrderView.vue'),
    beforeEnter: requireAuth('AGENCY'),
  },
  /*
   * 본사
   */
  {
    path: '/corp',
    name: 'corp',
    component: () => import(/* webpackChunkName: "corp" */ '../views/corp/CorpView.vue'),
    beforeEnter: requireAuth('CORP'),
  },
  {
    path: '/corp/chpw',
    name: 'corp_chpw',
    component: () => import(/* webpackChunkName: "corp_chpw" */ '../views/corp/ChpwView.vue'),
    beforeEnter: requireAuth('CORP'),
  },
  {
    path: '/corp/agency/add',
    name: 'corp_agency_add',
    component: () => import(/* webpackChunkName: "corp_agency_add" */ '../views/corp/AgencyAddView.vue'),
    beforeEnter: requireAuth('CORP'),
  },
  {
    path: '/corp/agency/emergency',
    name: 'corp_agency_emergency',
    component: () => import(/* webpackChunkName: "corp_agency_emergency" */ '../views/corp/AgencyEmergencyView.vue'),
    beforeEnter: requireAuth('CORP'),
  },
  {
    path: '/corp/agency/order',
    name: 'corp_agency_order',
    component: () => import(/* webpackChunkName: "corp_agency_order" */ '../views/corp/AgencyOrderView.vue'),
    beforeEnter: requireAuth('CORP'),
  },
  {
    path: '/corp/agency',
    name: 'corp_agency',
    component: () => import(/* webpackChunkName: "corp_agency" */ '../views/corp/AgencyView.vue'),
    beforeEnter: requireAuth('CORP'),
  },
  {
    path: '/corp/user/list',
    name: 'corp_user_list',
    component: () => import(/* webpackChunkName: "corp_user_list" */ '../views/corp/UserListView.vue'),
    beforeEnter: requireAuth('CORP'),
  },
]

const router = createRouter({
  history: createWebHashHistory(),
  scrollBehavior() {
    // always scroll to top
    return { top: 0 }
  },
  routes
})

export default router
