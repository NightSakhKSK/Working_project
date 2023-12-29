<template>
  <div class="agency_user_list">
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
    </div>

    <div class="space" style="height:30px;"></div>
    <table class="table">
      <tbody>
        <tr>
          <td>
            <input type="text" data-role="input" data-prepend="<span class='mif-qrcode'> 검색 </span>" placeholder="제품ID, 구매자명, 장소, 연락처 등으로 검색하세요." v-model="search.text" @keyup.enter="searchUser">
          </td>
          <td style="width: 50px;">
            <button v-on:click="searchUser" class="mif-search"></button>
          </td>
        </tr>
      </tbody>
    </table>
    
    <div class="space" style="height:30px;"></div>
    <table class="table">
      <thead>
          <tr>
              <th>ID</th>
              <th>설치정보</th>
          </tr>
      </thead>
      <tbody>
        <tr v-for="user in users" v-bind:key="user.mcId">
          <td>{{user.mcId}}</td>
          <td>
            <div class="grid textLeft">
              <div class="row text-right">
                <div class="cell">
                  {{ user.buyerNm }} {{ user.telNo }}
                </div>
              </div>
              <div class="row text-right">
                <div class="cell">
                  {{ user.instlLocDesc }}
                </div>
              </div>
            </div>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<style lang="scss">
  .textLeft {
    text-align: left;
  }
</style>

<script>
// @ is an alias to /src
import { post } from '@/api/index.js';

export default {
  name: "UserListView",
  components: {
    //HelloWorld
  },
  mounted: function() {
    this.$data.show.menu = true;
    this.getInfo();
    this.searchUser();
  },
  data() {
    return {
      device : 0,
      titleNm : "",
      orders : 0,
      emergency : 0,
      search : {
        text : "",
      },
      users : [],
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
      this.$data.orders = data.filters + data.nonfilters;
      this.$data.emergency = data.emergency;
      this.$data.titleNm = data.nm;
      this.$data.device = data.devices;
      this.$data.show.summary = true;
      console.log(data);
    },
    async searchUser() {
      post("/api/agency/user/search", this.$data.search)
      .then((res) => this.resultSearch(res.data))
      .catch((error) => {
        alert(error);
      });
    },
    resultSearch(data) {
      this.$data.users = data;
    },
  },
};
</script>
