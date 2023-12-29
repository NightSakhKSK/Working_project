<template>
  <div class="agency_user_history">
    <div v-if="show.menu" class="app-bar bg-hero fg-white" data-role="appbar">
        <a href="/#/agency" class="brand no-hover">
            <span style="max-width: 200px;  white-space: nowrap;" class="mif-home">
              {{ ' ' + info.titleNm }}
            </span>
        </a>
        <a v-if='!this.$route.query.mcId' v-on:click="move('ORDER')" class="app-bar-item"><span class='mif-cart'> Order</span></a>
        <a v-on:click="move('EMERGENCY')" class="app-bar-item text-upper"><span class="mif-help"> Emergency</span></a>
        <a v-if='this.$route.query.mcId' v-on:click="move('DEVICE')" class="app-bar-item"><span class='mif-devices'> Device Status</span></a>
        <ul class="app-bar-menu">
            <li><a href="/#/agency"><span class='mif-home'> 메인</span></a></li>
            <li><a href="/#/agency/user/list"><span class='mif-list'> 제품 설치 목록</span></a></li>
            <li><a href="/#/agency/user/add"><span class='mif-wrench'> 제품 설치 등록</span></a></li>
            <li><a href="/#/agency/chpw"><span class='mif-key'> 비밀번호 변경</span></a></li>
            <li><a href="/"><span class='mif-exit'> 로그아웃</span></a></li>
        </ul>
    </div>
    <div class="container-fluid docs-content" style="height: auto !important;margin-top: 60px;">
        <div v-if="info.show" data-role="panel" :data-title-caption="'Total Order ' + info.total" data-title-icon="<span class='mif-news'></span>">
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
                        <div class="d-flex flex-justify-center">{{ info.nonfilters }}</div>
                    </div>
                    <div class="cell">
                        <div class="d-flex flex-justify-center">{{ info.filters }}</div>
                    </div>
                    <div class="cell">
                        <div class="d-flex flex-justify-center" style="color:red;font-weight: bold;">{{ info.emergency }}</div>
                    </div>
                </div>
            </div>
        </div>

        <br>
        <h4>History</h4>
        <table class="table table-border cell-border">
            <thead>
                <tr>
                    <th>Date</th>
                    <th>증상</th>
                    <th>원인</th>
                    <th>조치</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="history in histories" v-bind:key="history.seq">
                    <td>{{history.date}}</td>
                    <td>{{history.symptCntn}}</td>
                    <td>{{history.causeDesc}}</td>
                    <td>{{history.actnCntn}}</td>
                </tr>
            </tbody>
        </table>
    </div>
  </div>
</template>

<style lang="scss">
  .app-bar a {
    cursor: pointer;
  }
</style>

<script>
// @ is an alias to /src
import { post } from '@/api/index.js';

export default {
  name: "UserHistoryView",
  components: {
    //HelloWorld
  },
  mounted: function () {
    this.$data.params.mcId = this.$route.query.mcId;
    this.$data.show.menu = true;
    if (this.$route.query.mcId)
      this.getUserInfo();
    else
      this.getInfo();
    this.getHistory();
  },
  data() {
    return {
      params : {
        mcId : "",
      },
      info : {
        titleNm : "",
        show : false,
        total : 0,
        filters : 0,
        nonfilters : 0,
        emergency : 0,
      },
      histories : [],
      show : {
        menu : false,
      },
    };
  },
  methods: {
    async getUserInfo() {
      post("/api/agency/user/info", {"mcId": this.$route.query.mcId})
      .then((res) => this.resultUserInfo(res.data))
      .catch((error) => {
        alert(error);
      });
    },
    resultUserInfo(data) {
      console.log(data);
      this.$data.info.filters = data.filters;
      this.$data.info.nonfilters = data.nonfilters;
      this.$data.info.emergency = data.emergency;
      this.$data.info.total = data.filters + data.nonfilters;
      this.$data.info.titleNm = data.mcId;
      this.$data.info.show = true;
    },
    async getInfo() {
      post("/api/agency/info", null)
      .then((res) => this.result(res.data))
      .catch((error) => {
        alert(error);
      });
    },
    result(data) {
      console.log("getInfo", data);
      this.$data.info.filters = data.filters;
      this.$data.info.nonfilters = data.nonfilters;
      this.$data.info.emergency = data.emergency;
      this.$data.info.total = data.filters + data.nonfilters;
      this.$data.info.titleNm = data.nm;
      this.$data.info.show = true;
    },
    move(tp) {
      console.log(tp);
      if(tp == 'EMERGENCY') {
        this.$router.push({ name: "agency_user_emergency", query : { "mcId" : this.$data.params.mcId } }); //parameter전달시 path이용불가
      } else if(tp == 'ORDER') {
        this.$router.push({ name: "agency_user_order", query : { "mcId" : this.$data.params.mcId } }); //parameter전달시 path이용불가
      } else if(tp == 'DEVICE') {
        this.$router.push({ name: "agency_user_device", query : { "mcId" : this.$data.params.mcId } }); //parameter전달시 path이용불가
      }
    },
    async getHistory() {
      post("/api/agency/user/history/list", this.$data.params)
      .then((res) => this.resultHistory(res.data))
      .catch((error) => {
        alert(error);
      });
    },
    resultHistory(data) {
      console.log('resultHistory', data);
      this.$data.histories = data;
      //this.$forceUpdate();
    },
  },
};
</script>
