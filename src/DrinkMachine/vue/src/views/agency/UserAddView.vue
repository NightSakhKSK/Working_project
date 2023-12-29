<template>
  <div class="agency_user_add">
    <div v-if="show.menu" class="app-bar bg-hero fg-white" data-role="appbar">
      <a href="/#/agency" class="brand no-hover">
        <span style="max-width: 200px;  white-space: nowrap;" class="mif-home">
            {{ ' ' + titleNm }}
          </span>
      </a>
      <a v-on:click="move('order')" class="app-bar-item text-upper"><span class="mif-cart"> Order</span></a>
      <a v-on:click="move('emergency')" class="app-bar-item text-upper"><span class="mif-help"> Emergency</span></a>
      <ul class="app-bar-menu">
        <li>
            <a href="/#/agency">
              <span class="mif-home"> 메인</span>
            </a>
          </li>
          <li>
            <a href="/#/agency/user/list">
              <span class="mif-list"> 제품 설치 목록</span>
            </a>
          </li>
          <li>
            <a href="/#/agency/user/add">
              <span class="mif-wrench"> 제품 설치 등록</span>
            </a>
          </li>
          <li>
            <a href="/#/agency/chpw">
              <span class="mif-key"> 비밀번호 변경</span>
            </a>
          </li>
          <li><a href="/"><span class='mif-exit'> 로그아웃</span></a></li>
      </ul>
    </div>

    <div
      class="container-fluid docs-content"
      style="height: auto !important; margin-top: 60px"
    >
      <div v-if="show.summary"
        data-role="panel"
        :data-title-caption="'Total Device ' + device"
        data-title-icon="<span class='mif-news'></span>"
      >
        <div class="grid">
          <div class="row">
            <div class="cell">
              <div class="d-flex flex-justify-center" style="font-weight: bold">
                Order
              </div>
            </div>
            <div class="cell">
              <div
                class="d-flex flex-justify-center"
                style="color: red; font-weight: bold"
              >
                Emergency
              </div>
            </div>
          </div>
          <div class="row">
            <div class="cell">
              <div class="d-flex flex-justify-center">{{ orders }}</div>
            </div>
            <div class="cell">
              <div
                class="d-flex flex-justify-center"
                style="color: red; font-weight: bold"
              >
                {{ emergency }}
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="space" style="height: 30px"></div>
      <input
        type="text"
        data-role="input"
        data-prepend="<span class='mif-qrcode'> 제품ID</span>"
        placeholder="제품ID를 입력하세요."
        v-model="input.mcId"
      />
      <div class="space" style="height: 30px"></div>
      <input
        type="text"
        data-role="input"
        data-prepend="<span class='mif-user'> 구매자명</span>"
        placeholder="구매자명을 입력하세요."
        v-model="input.buyerNm"
      />
      <div class="space" style="height: 30px"></div>
      <input
        type="text"
        data-role="input"
        data-prepend="<span class='mif-phone'> 연락처</span>"
        placeholder="연락처를 입력하세요."
        v-model="input.telNo"
      />
      <div class="space" style="height: 30px"></div>
      <input
        type="text"
        data-role="input"
        data-prepend="<span class='mif-room'> 설치주소</span>"
        placeholder="설치주소를 입력하세요."
        v-model="input.instLocDesc"
      />
      <div class="space" style="height: 30px"></div>
      <div id="hidden" :style="{display: resultDiplay}">
        <label for="code" class="LargeFont">임시가입번호</label>
        <input id="code" type="text" readonly 
          class="LargeFont"
          v-model="resultCode">
      </div>
      <div class="space" style="height: 30px"></div>
      <button style="width: 100%" v-on:click="add()">
        <span class="mif-install"> 설치 등록</span>
      </button>
      <div class="space" style="height: 30px"></div>
    </div>
  </div>
</template>

<style lang="scss">
.LargeFont {
  font-size: xx-large;
  font-weight: bold;
}

/* New styles */
/* Style for the input labels */
.mif-profile, .mif-room, .mif-phone, .mif-user {
  color: #000000; /* Blue color */
}

/* General style for the input fields */
[data-role="input"] input {
  border: 2px solid #4A90E2; /* Blue border */
  border-radius: 5px; /* Rounded corners */
  padding: 10px 15px; /* Spacing inside input */
  font-size: 1.1em; /* Larger font size */
  color: #333; /* Dark text for readability */
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2); /* Subtle shadow for depth */
}

/* Hover effect for input fields */
[data-role="input"] input:hover {
  border-color: #76B3FA; /* Lighter blue on hover */
}

/* Focus effect for input fields */
[data-role="input"] input:focus {
  border-color: #306ABD; /* Darker blue for focus */
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3); /* Stronger shadow for focus */
}

/* Style for the registration button */
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
  name: "UserAddView",
  components: {
    //HelloWorld
  },
  mounted: function () {
    this.$data.show.menu = true;
    this.getInfo();
  },
  data() {
    return {
      resultDiplay : "none",
      resultCode : "",
      input : {
        mcId : "",
        buyerNm : "",
        telNo : "",
        instLocDesc : "",
        awsId : "",
        awsKey : "",
      },
      device : 0,
      titleNm : "",
      orders : 0,
      emergency : 0,
      show : {
        menu : false,
        summary : false,
      },
    }
  },
  methods: {
    async getInfo() {
      post("/api/agency/info", null)
      .then((res) => this.result(res.data))
      .catch((error) => {
        alert(error);
      });
    },
    result(data) {
      console.log(data);
      this.$data.orders = data.filters + data.nonfilters;
      this.$data.emergency = data.emergency;
      this.$data.titleNm = data.nm;
      this.$data.device = data.devices;
      this.$data.show.summary = true;
    },
    async add() {
      post("/api/agency/user/add", this.$data.input)
      .then((res) => this.addResult(res.data))
      .catch((error) => {
        console.log(error);
        this.loginError = true;
      });
    },
    addResult(data) {
      console.log(data);
      this.$data.resultDiplay = "block";
      this.$data.resultCode = data.issuedNo;
    },
  },
};
</script>
