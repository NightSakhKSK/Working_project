<template>
  <div class="corp_chpw">
    <div v-if="show.menu" class="app-bar bg-hero fg-white" data-role="appbar">
      <a href="/#/corp" class="brand no-hover">
          <span style="max-width: 200px;  white-space: nowrap;" class="mif-home">
              본사
          </span>
      </a>
      <ul class="app-bar-menu">
        <li><a href="/#/corp"><span class='mif-home'> 본사 메인</span></a></li>
        <li><a href="/#/corp/agency/add"><span class='mif-wrench'> 대리점 등록</span></a></li>
        <li><a href="/#/corp/chpw"><span class='mif-key'> 비밀번호 변경</span></a></li>
        <li><a href="/#/corp/user/list" ><span class='mif-list'> 제품설치목록</span></a></li>
        <li><a href="/"><span class='mif-exit'> 로그아웃</span></a></li>
      </ul>
    </div>

    <div
      class="container-fluid docs-content"
      style="height: auto !important; margin-top: 60px"
    >

      <input type="password" data-role="input" data-prepend="<span class='mif-unlock'> 현재 패스워드</span>"
        placeholder="기존 패스워드를 입력하세요."
        autocomplete='off'
        data-clear-button="false"
        v-model="input.oldPw">
      <div class="space" style="height:30px;"></div>
      <input type="password" data-role="input" data-prepend="<span class='mif-lock'> 신규 패스워드</span>"
        placeholder="변경될 패스워드를 입력하세요."
        autocomplete='off'
        data-clear-button="false"
        v-model="input.newPw">
      <div class="space" style="height:30px;"></div>
      <input type="password" data-role="input" data-prepend="<span class='mif-checkmark'> 신규 패스워드 확인</span>"
        placeholder="변경될 패스워드를 확인하세요."
        autocomplete='off'
        data-clear-button="false"
        v-model="input.confirmPw">
      <div class="space" style="height:30px;"></div>
      <button style="width: 100%;" v-on:click="doChange()"><span class='mif-key'> 비밀번호 변경</span></button>
      <div class="space" style="height:30px;"></div>
    </div>
  </div>
</template>

<style lang="scss">

button {
  background-color: #4A90E2; /* Blue background */
  color: white; /* White text */
  padding: 15px 0; /* Spacing */
  border: none; /* Remove border */
  border-radius: 5px; /* Rounded corners */
  font-size: 1.1em; /* Larger font size */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* Button shadow */
  transition: background-color 0.3s ease, box-shadow 0.3s ease; /* Smooth transition */
}

/* Hover effect for button */
button:hover {
  background-color: #76B3FA; /* Lighter blue on hover */
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.3); /* Larger shadow on hover */
}

/* Active/click effect for button */
button:active {
  background-color: #306ABD; /* Darker blue when active */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2); /* Smaller shadow when clicked */
}

.input .prepend {
  width: 200px !important;
  text-align: left;
}
</style>

<script>
// @ is an alias to /src
import { post } from '@/api/index.js';

export default {
  name: "CorpChpwView",
  components: {},
  mounted : function() {
    this.$data.show.menu = true;
  },
  data() {
    return {
      show : {
        menu : false,
      },
      input : {
        oldPw : "",
        newPw : "",
        confirmPw : "",
      },
    }
  },
  methods: {
    async doChange() {
      post("/api/corp/chpw", this.$data.input)
      .then((res) => this.resultChpw(res.data))
      .catch((error) => {
        alert(error);
      });
    },
    resultChpw(data) {
      console.log(data);
      if(data.cd == "0000") {
        this.$router.push({ path: "/corp" });
        alert('변경되었습니다.');
      } else if(data.cd == "1000") {
        alert('패스워드를 다시 확인하여 주세요.');
      } else if(data.cd == "1001") {
        alert('신규 패스워드가 확인 값과 일치하지 않습니다.');
      } else if(data.cd == "1009") {
        alert('잘못된 입력 값입니다.');
      }
    },
  },
};
</script>
