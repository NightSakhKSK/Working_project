<template>
  <div class="user_chpw">
    <div v-if="show.menu" class="app-bar bg-hero fg-white" data-role="appbar">
      <a href="/#/user" class="brand no-hover">
          <span style="max-width: 200px;  white-space: nowrap;" class="mif-home">
            비밀번호 변경
          </span>
      </a>
      <a href="/#/user/order" class="app-bar-item text-upper">
          <span class="mif-cart"> 주문</span>
        </a>
      <ul class="app-bar-menu">
        <li><a href="/#/user/chpw"><span class='mif-key'> 비밀번호 변경</span></a></li>
        <li><a href="/#/user/device"><span class='mif-phonelink-setup'> 디바이스 상태</span></a></li>
        <li><a href="/#/user/emergency/list"><span class='mif-phonelink-setup'> 긴급호출 이력</span></a></li>
        <li><a href="/"><span class='mif-exit'> 로그아웃</span></a></li>
      </ul>
    </div>

    <div class="container-fluid docs-content" style="height: auto !important;margin-top: 60px;">
      <div data-role="panel" data-title-caption="설치 정보" data-title-icon="<span class='mif-news'></span>">
        <div class="grid">
          <div class="row">
            <div class="cell">
              <div class="d-flex flex-justify-center" style="font-weight: bold;">제품 ID</div>
            </div>
            <div class="cell">
              <div class="d-flex flex-justify-left" style="font-weight: bold;">{{mcId}}</div>
            </div>
          </div>
          <div class="row">
            <div class="cell">
              <div class="d-flex flex-justify-center" style="font-weight: bold;">설치일자</div>
            </div>
            <div class="cell">
              <div class="d-flex flex-justify-left" style="font-weight: bold;">{{installDt}}</div>
            </div>
          </div>
          <div class="row">
            <div class="cell">
              <div class="d-flex flex-justify-center" style="font-weight: bold;">관리 대리점</div>
            </div>
            <div class="cell">
              <div class="d-flex flex-justify-left" style="font-weight: bold;">{{mngAgncyNm}}</div>
            </div>
          </div>
          <div class="row">
            <div class="cell">
              <div class="d-flex flex-justify-center" style="font-weight: bold;">연락처</div>
            </div>
            <div class="cell">
              <div class="d-flex flex-justify-left" style="font-weight: bold;">{{mngAgncyTelNo}}</div>
            </div>
          </div>
        </div>
      </div>

      <div class="space" style="height:30px;"></div>
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
    .evt-btn {
      background-size: 60px;
      width: 75px;
      height: 100px;
    }
  
    .evt-btn:hover {
      background-color: rgba(255, 0, 255, 0.2);
    }

    .input .prepend {
      width: 200px;
      /* text-align: right!important; */
    }
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
</style>

<script>
// @ is an alias to /src
import { post } from '@/api/index.js';

export default {
  name: 'ChpwView',
  components: {
  },
  mounted: function () {
    this.$data.show.menu = true;
    this.getInfo();
  },
  data() {
    return {
      mcId : "",
      installDt : "",
      mngAgncyNm : "",
      mngAgncyTelNo : "",
      input : {
        oldPw : "",
        newPw : "",
        confirmPw : "",
      },
      show : {
        menu : false,
      },
    }
  },
  methods: {
    async getInfo() {
      post("/api/user/install", null)
      .then((res) => this.result(res.data))
      .catch((error) => {
        alert(error);
      });
    },
    result(data) {
      console.log(data);
      this.$data.mcId = data.mcId;
      this.$data.installDt = data.installDt;
      this.$data.mngAgncyNm = data.mngAgncyNm;
      this.$data.mngAgncyTelNo = data.mngAgncyTelNo;
    },
    async doChange() {
      post("/api/user/chpw", this.$data.input)
      .then((res) => this.resultChpw(res.data))
      .catch((error) => {
        alert(error);
      });
    },
    resultChpw(data) {
      console.log(data);
      if(data.cd == "0000") {
        this.$router.push({ path: "/user" });
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
}
</script>