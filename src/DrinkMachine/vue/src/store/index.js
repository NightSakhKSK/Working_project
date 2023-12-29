import { createStore } from 'vuex'
import { post } from '@/api/index.js';
import VueJwtDecode from 'vue-jwt-decode'

export default createStore({
  state: {
    token: null,
    role: null,
  },
  getters: {
  },
  mutations: {
    LOGIN(state, { token }) {
      let parseToken = VueJwtDecode.decode(token);
      //console.log(parseToken);
      state.token = token;
      state.role = parseToken.ROLE;
    },
    LOGOUT(state) {
      state.token = null;
      state.role = null;
    },
  },
  actions: {
    LOGIN({ commit }, { user, password }) {
      return post("/authenticate", { id : user, pw : password })
        .then(({ data }) => commit("LOGIN", data));
    },
    LOGOUT({ commit }) {
      commit("LOGOUT");
    },
  },
  modules: {
  }
})
