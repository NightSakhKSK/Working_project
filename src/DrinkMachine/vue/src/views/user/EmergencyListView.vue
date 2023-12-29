<template>
  <div class="user">
    <div v-if="show.menu" class="app-bar bg-hero fg-white" data-role="appbar">
      <a href="/#/user" class="brand no-hover">
          <span style="max-width: 200px;  white-space: nowrap;" class="mif-home">
            {{' ' + info.titleNm }}
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
      <div v-if='show.summary'
        data-role="panel"
        :data-title-caption="'Total Orders ' + info.total"
        data-title-icon="<span class='mif-news'></span>"
      >
        <div class="grid">
          <div class="row">
            <div class="cell">
              <div class="d-flex flex-justify-center" style="font-weight: bold">
                Cylinder & Syrups
              </div>
            </div>
            <div class="cell">
              <div class="d-flex flex-justify-center" style="font-weight: bold">
                Filters
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
              <div class="d-flex flex-justify-center">{{ info.nonfilters }}</div>
            </div>
            <div class="cell">
              <div class="d-flex flex-justify-center">{{ info.filters }}</div>
            </div>
            <div class="cell">
              <div
                class="d-flex flex-justify-center"
                style="color: red; font-weight: bold"
              >
              {{ info.emergency }}
              </div>
            </div>
          </div>
        </div>
      </div>
        <br>
        <h4>Emergency</h4>
        <table class="table table-border cell-border">
            <thead>
                <tr>
                    <th>날짜</th>
                    <th>방문일정</th>
                    <th>조치</th>
                    <th>상태</th>
                </tr>
            </thead>
            <tbody>
              <tr v-for="emergency in emergencies" v-bind:key="emergency.seq">
                <td>{{ emergency.date }}</td>
                <td>{{ emergency.visitCntn }}</td>
                <td>{{ emergency.actnCntn }}</td>
                <td>{{ emergency.statCd }}</td>
              </tr>
            </tbody>
        </table>
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

<script>
// @ is an alias to /src
import { post } from '@/api/index.js';

export default {
  name: "EmergencyListView",
  components: {
    //HelloWorld
  },
  mounted: function () {
    this.$data.params.mcId = this.$route.query.mcId;
    this.$data.params.seq = this.$route.query.seq;
    this.$data.emergency.mcId = this.$route.query.mcId;
    this.$data.visit.mcId = this.$route.query.mcId;
    this.$data.show.menu = true;
    
    this.getInfo();
    this.getEmergency();
  },
  data() {
    return {
      params : {
        mcId : "",
        seq  : -1,
      },
      info : {
        titleNm : "",
        total : 0,
        filters : 0,
        nonfilters : 0,
        emergency : 0,
      },
      show : {
        menu : false,
        summary : false,
      },
      visit : {
        mcId : "",
        seq  : -1,
        plan : "",
      },
      emergency : {
        mcId : "",
        seq  : -1,
        symptCntn : "",
        causeDesc : "",
        actnCntn : "",
      }, 
      emergencies : [],
    };
  },
  methods: {
    async getInfo() {
      post("/api/user/info", this.$data.params)
      .then((res) => this.result(res.data))
      .catch((error) => {
        alert(error);
      });
    },
    result(data) {
      console.log(data);
      this.$data.info.filters = data.filters;
      this.$data.info.nonfilters = data.nonfilters;
      this.$data.info.emergency = data.emergency;
      this.$data.info.titleNm = data.mcId;
      this.$data.info.total = data.filters + data.nonfilters;
      this.$data.show.summary = true;
    },
    async getEmergency() {
      post("/api/user/emergency/list", this.$data.params)
      .then((res) => this.resultEmergency(res.data))
      .catch((error) => {
        alert(error);
      });
    },
    resultEmergency(data) {
      console.log('resultEmergency', data);
      this.$data.emergencies = data;
      if(this.$data.params.seq != undefined) {
        for(var i in data) {
          let v = data[i];
          if(v.seq == this.$data.params.seq) {
            this.$data.emergency.seq = v.seq;
            this.$data.visit.seq = v.seq;
            break;
          }
        }
      }
      //this.$forceUpdate();
    },
  },
};
</script>
