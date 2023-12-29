<!--<template>-->
<!--  <div class="login">-->
<!--    <div class="protected" v-if="loginSuccess">-->
<!--          <h1>-->
<!--              <span variant="success">보안 사이트에 대한 액세스가 허용되었습니다</span>-->
<!--          </h1>-->
<!--          <h5>로그인 성공!</h5>-->
<!--      </div>-->
<!--      <div class="unprotected" v-else-if="loginError">-->
<!--          <h1>-->
<!--              <span variant="danger">이 페이지에 대한 접근 권한이 없습니다.</span>-->
<!--          </h1>-->
<!--          <h5>로그인 실패!</h5>-->
<!--      </div>-->
<!--      <div class="unprotected" v-else>-->
<!--          <h1>-->
<!--              <span variant="info">로그인해주세요</span>-->
<!--          </h1>-->
<!--          <h5>로그인 하지 않았습니다. 로그인을 해주세요</h5>-->

<!--          <form @submit.prevent="login()">-->
<!--              <label>-->
<!--                  <input type="text" placeholder="username" v-model="user">-->
<!--              </label>-->
<!--              <br/>-->
<!--              <br/>-->
<!--              <label>-->
<!--                  <input type="password" placeholder="password" v-model="password">-->
<!--              </label>-->
<!--              <br/>-->
<!--              <br/>-->
<!--              <button variant="success" type="submit">Login</button>-->
<!--              <p v-if="error" class="error">Bad login information</p>-->
<!--          </form>-->
<!--      </div>-->
<!--  </div>-->
<!--</template>-->

<template>
  <div class="login">
    <div class="protected" v-if="loginSuccess">
      <h1>
        <span class="success-message">보안 사이트에 대한 액세스가 허용되었습니다</span>
      </h1>
      <h5>로그인 성공!</h5>
    </div>
    <div class="unprotected" v-else-if="loginError">
      <h1>
        <span class="error-message">이 페이지에 대한 접근 <br> 권한이 없습니다.</span>
      </h1>
      <h5>로그인 실패!</h5>
    </div>
    <div class="unprotected" v-else>
      <h1>
        <span class="info-message">로그인해주세요</span>
      </h1>
      <h5>로그인 하지 않았습니다.</h5>
      <h5> 로그인을 해주세요</h5>
      <form @submit.prevent="login()" class="login-form">
        <label>
          <input type="text" placeholder="username" v-model="user">
        </label>
        <br/>
        <label>
          <input type="password" placeholder="password" v-model="password">
        </label>
        <br/>
        <button class="login-button" type="submit">Login</button>
        <p v-if="error" class="error">Bad login information</p>
      </form>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
//import { post } from '@/api/index.js';

export default {
  name: "LoginView",
  components: {
    //HelloWorld
  },
  data() {
    return {
        loginSuccess: false,
        loginError: false,
        user: '',
        password: '',
        error: false
    }
  },
  methods: {
    async login() {
      console.log('login');
      this.$store
          .dispatch("LOGIN", { user : this.user, password : this.password })
          .then(() => this.redirect())
          .catch((error) => {
            console.log(error);
            this.loginError = true;
          });
    },
    redirect() {
      console.log("redirect", this.$store.state.role);
      switch(this.$store.state.role) {
        case 'USER' :
          this.$router.push({ path: "/user" });
          break;
        case 'AGENCY' :
          this.$router.push({ path: "/agency" });
          break;
        case 'CORP' :
          this.$router.push({ path: "/corp" });
          break;
      }
    },
  }
};
</script>

<style scoped>
.login {
  max-width: 600px;
  margin: 50px auto;
  padding: 20px;
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
  border-radius: 10px;
  background: #fff;
}

.success-message, .error-message, .info-message {
  display: block;
  text-align: center;
  font-weight: bold;
}

.success-message {
  color: #4CAF50;
}

.error-message {
  color: #F44336;
}

.info-message {
  color: #2196F3;
}

.login-form {
  display: flex;
  flex-direction: column;
}

.login-form input {
  margin-bottom: 10px;
  padding: 10px;
  border: 2px solid #ccc;
  border-radius: 5px;
}

.login-button {
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  background-color: #4CAF50;
  color: white;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.login-button:hover {
  background-color: #45a049;
}

.error {
  color: #F44336;
  text-align: center;
}
</style>