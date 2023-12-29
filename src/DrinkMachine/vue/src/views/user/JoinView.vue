<template>
  <div class="user_join">
    <div class="container" style="margin-top: 40px">
      <div
        data-role="panel"
        data-title-caption="사용자 회원가입"
        data-title-icon="<span class='mif-users'></span>"
      >
        <div class="space" style="height: 30px"></div>
        <input
          autocomplete='one-time-code'
          type="text"
          data-role="input"
          data-prepend="<span class='mif-barcode'>사용자 가입코드</span>"
          placeholder="가입코드를 입력하세요."
          v-model="input.issuedNo"
          data-clear-button="false"
        />
        <div class="space" style="height: 10px"></div>
        <div class="grid">
          <div class="row">
            <div class="cell">
              <input
                autocomplete='off'
                @keydown="user_check_change()"
                @keydown.enter="user_check()"
                type="text"
                data-role="input"
                data-prepend="<span class='mif-user'>사용자 아이디</span>"
                placeholder="사용할 아이디를 입력하세요."
                data-search-button="true"
                data-search-button-click="custom"
                data-search-button-icon=""
                v-model="input.id"
                data-clear-button="false"
              />
              <button
                id="user_check"
                class="check-button"
                :class="{'mif-user-check': isChecked, 'mif-user': !isChecked}"
                :style="{'color': (isChecked ? 'green' : 'red')}"
                v-on:click="user_check();"
              ></button>
            </div>
          </div>
        </div>
        <div class="space" style="height: 10px"></div>
        <input
          autocomplete='new-password'
          @keydown.enter="user_check()"
          type="password"
          data-role="input"
          data-prepend="<span class='mif-lock'>패스워드</span>"
          placeholder="사용할 패스워드를 입력하세요."
          v-model="input.pw"
          data-clear-button="false"
        />
        <div class="space" style="height: 30px"></div>
        <button style="width: 100%" v-on:click="join()">
          <span class="mif-user-plus"> 회원 가입</span>
        </button>
        <div class="space" style="height: 30px"></div>
      </div>
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
  width: 160px !important;
  text-align: left;
}
.check-button {
  position: absolute;
  top: 10px;
  right: 10px;
  width: 30px;
  height: 28px;
  background-color: transparent;
  border: none;
}
.check-button:hover {
  cursor: pointer;
}
.check-button.pass {
  background-color: green;
}
.check-button.nonpass {
  background-color: red;
}
</style>

<script>
// @ is an alias to /src
import { post } from '@/api/index.js';
export default {
  name: "JoinView",
  components: {},
  data() {
    return {
      isChecked : false,
      input : {
        id:"",
        pw:"",
        nm:"",
        issuedNo:"",
      },
      username: "",
    };
  },
  mounted: function () {
    //Metro.init();
    console.log("join mounted");
    console.log(document.getElementById("user_check"));
  },
  methods: {
    user_check() {
      post("/api/join/checkid", {'id':this.$data.input.id})
      .then(() => {
        this.$data.isChecked = true;
      })
      .catch((error) => {
        console.log(error);
      });
    },
    user_check_change() {
      this.$data.isChecked = false;
    },
    async join() {
      if(!this.$data.isChecked) {
        alert('id 가능 여부를 확인해주세요.');
        return;
      } else if(this.$data.input.pw == "") {
        alert('패스워드를 입력해주세요.');
        return;
      } else if(this.$data.input.issuedNo.length != 6) {
        alert('6자리 사용자 가입코드를 입력해주세요.');
        return;
      }
      console.log('add');
      post("/api/join/user", this.$data.input)
      .then((res) => this.result(res.data))
      .catch((error) => {
        console.log(error);
        alert(error);
      });
    },
    result(data) {
      if(data.cd == "0000") {
        this.$router.push({ path: "/" });
        alert('등록되었습니다.');
      } else if(data.cd == "1000") {
        alert('잘못된 사용자 가입코드');
      }
    },
  },
};
</script>
