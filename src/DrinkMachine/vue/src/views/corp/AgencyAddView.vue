<template>
  <div class="corp_agency_add">
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
    
    <div class="container-fluid docs-content" style="height: auto !important;margin-top: 60px;">
        <div class="space" style="height:30px;"></div>
        <input type="text" data-role="input" data-prepend="<span class='mif-profile'>사업자번호</span>" placeholder="사업자번호를 입력하세요." v-model="input.bsnLcnNo">
        <div class="space" style="height:30px;"></div>
        <input type="text" data-role="input" data-prepend="<span class='mif-room'>주소</span>" placeholder="주소를 입력하세요." v-model="input.addr">
        <div class="space" style="height:30px;"></div>
        <input type="text" data-role="input" data-prepend="<span class='mif-phone'>연락처</span>" placeholder="연락처를 입력하세요." v-model="input.telNo">
        <div class="space" style="height:30px;"></div>
        <input type="text" data-role="input" data-prepend="<span class='mif-user'>담당자</span>" placeholder="담당자명을 입력하세요." v-model="input.mngrNm">
        <div class="space" style="height:30px;"></div>
        <div id="hidden" :style="{display: resultDiplay}">
            <label for="code" class="LargeFont">임시가입번호</label>
            <input id="code" type="text" readonly 
              class="LargeFont"
              v-model="resultCode">
        </div>
        <div class="space" style="height:30px;"></div>
        <button style="width: 100%;" v-on:click="add()"><span class='mif-install'> 대리점 등록</span></button>
        <div class="space" style="height:30px;"></div>
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
  name: "AgencyAddView",
  components: {
    //HelloWorld
  },
  mounted : function() {
    this.$data.show.menu = true;
  },
  data() {
    return {
      resultDiplay : "none",
      resultCode : "",
      input : {
        bsnLcnNo: "",
        addr: "",
        telNo: "",
        mngrNm: "",
      },
      show : {
        menu : false,
      },
    }
  },
  methods: {
    async add() {
      console.log('add');
      post("/api/corp/agency/add", this.$data.input)
      .then((res) => this.result(res.data))
      .catch((error) => {
        console.log(error);
        this.loginError = true;
      });
    },
    result(res) {
      console.log("res", res);
      this.$data.resultDiplay = "block";
      this.$data.resultCode = res.issuedNo;
    },
  }
};
</script>
