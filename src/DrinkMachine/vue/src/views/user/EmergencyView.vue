<template>
  <div class="user_emergency">
    <div v-if="show.menu" class="app-bar bg-hero fg-white" data-role="appbar">
      <a href="/#/user" class="brand no-hover">
          <span style="max-width: 200px;  white-space: nowrap;" class="mif-home">
            {{ ' ' + titleNm }}
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
      <div data-role="panel" data-title-caption="Total Order <span id='ordercnt'>0</span>"
           data-title-icon="<span class='mif-news'></span>">
        <div class="grid">
          <div class="row">
            <div class="cell">
              <div class="d-flex flex-justify-center" style="font-weight: bold;">Cylinder & Syrups</div>
            </div>
            <div class="cell">
              <div class="d-flex flex-justify-center" style="font-weight: bold;">Filters</div>
            </div>
            <div class="cell">
              <div class="d-flex flex-justify-center" style="color:red;font-weight: bold;">Emergency</div>
            </div>
          </div>
          <div class="row">
            <div class="cell">
              <div class="d-flex flex-justify-center">{{ nonfilters }}</div>
            </div>
            <div class="cell">
              <div class="d-flex flex-justify-center">{{ filters }}</div>
            </div>
            <div class="cell">
              <div class="d-flex flex-justify-center" style="color:red;font-weight: bold;">{{ emergency }}</div>
            </div>
          </div>
        </div>
      </div>
      <div class="grid" style="padding-top: 20px;">
        <div class="row">
          <div class="cell">
            <div class="d-flex flex-justify-center">
              <div style="height: 200px; width: 80%;">
                <font size="20" color="red">긴급 요청<br>하시겠습니까?</font>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="cell">
            <div class="d-flex flex-justify-center">
              <button class = "Yes-button" v-on:click="doOk">확인</button>
            </div>
          </div>
          <div class="cell">
            <div class="d-flex flex-justify-center">
              <button class = "No-button" v-on:click="doCancel">취소</button>
            </div>
          </div>
        </div>
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
</style>

<style scoped>
.Yes-button {
  background-color: #e81818; /* Red background */
  color: white; /* White text */
  padding: 5px 3px; /* Spacing */
  border: none; /* Remove border */
  border-radius: 5px; /* Rounded corners */
  font-size: 1em; /* Text size */
  cursor: pointer; /* Mouse pointer on hover */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* Button shadow */
  transition: background-color 0.3s ease, box-shadow 0.3s ease; /* Smooth transition */
}

.No-button {
  background-color: #28a745; /* Green background */
  color: white; /* White text */
  padding: 5px 3px; /* Spacing */
  border: none; /* Remove border */
  border-radius: 5px; /* Rounded corners */
  font-size: 1em; /* Text size */
  cursor: pointer; /* Mouse pointer on hover */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* Button shadow */
  transition: background-color 0.3s ease, box-shadow 0.3s ease; /* Smooth transition */
}

/* Hover effect for Yes-button */
.Yes-button:hover {
  background-color: #f34242; /* Lighter red on hover */
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.3); /* Larger shadow on hover */
}

/* Hover effect for No-button */
.No-button:hover {
  background-color: #34c759; /* Lighter green on hover */
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.3); /* Larger shadow on hover */
}
</style>


<script>
// @ is an alias to /src
import {post} from '@/api/index.js';

export default {
  name: 'EmergencyView',
  components: {},
  mounted: function () {
    this.$data.show.menu = true;
    this.getInfo();
  },
  data() {
    return {
      titleNm: "",
      filters: 0,
      nonfilters: 0,
      emergency: 0,
      show: {
        menu: false,
      },
    }
  },
  methods: {
    async getInfo() {
      post("/api/user/info", null)
          .then((res) => this.result(res.data))
          .catch((error) => {
            alert(error);
          });
    },
    result(data) {
      console.log(data);
      this.$data.filters = data.filters;
      this.$data.nonfilters = data.nonfilters;
      this.$data.emergency = data.emergency;
      this.$data.titleNm = data.mcId;
      let ord = document.getElementById('ordercnt');
      ord.textContent = data.filters + data.nonfilters;

    },
    doOk() {
      post("/api/user/emergency", null)
          .then((res) => this.resultEmergency(res.data))
          .catch((error) => {
            alert(error);
          });
    },
    resultEmergency(data) {
      console.log(data);
      this.$router.push({path: '/user'});
    },
    doCancel() {
      this.$router.push({path: '/user'});
    }
  }
}
</script>