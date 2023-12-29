<template>
  <div class="agency_user_emergency">
    <div v-if="show.menu" class="app-bar bg-hero fg-white" data-role="appbar">
        <a href="/#/agency" class="brand no-hover">
            <span style="max-width: 200px;  white-space: nowrap;" class="mif-home">
               {{ ' ' +info.titleNm }}
            </span>
        </a>
        <a v-if='this.$route.query.mcId' v-on:click="move('HISTORY')" class="app-bar-item"><span class='mif-history'> History</span></a>
        <a v-if='this.$route.query.mcId' v-on:click="move('DEVICE')" class="app-bar-item"><span class='mif-devices'> Device Status</span></a>
        <a v-if='!this.$route.query.mcId' v-on:click="move('ORDER')" class="app-bar-item"><span class='mif-cart'> Order</span></a>
        <a v-if='!this.$route.query.mcId' v-on:click="move('EMERGENCY')" class="app-bar-item text-upper"><span class="mif-help"> Emergency</span></a>
        <ul class="app-bar-menu">
            <li><a href="/#/agency"><span class='mif-home'> 메인</span></a></li>
            <li><a href="/#/agency/user/list"><span class='mif-list'> 제품 설치 목록</span></a></li>
            <li><a href="/#/agency/user/add"><span class='mif-wrench'> 제품 설치 등록</span></a></li>
            <li><a href="/#/agency/chpw"><span class='mif-key'> 비밀번호 변경</span></a></li>
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
                    <th v-if='this.$route.query.mcId'>select</th>
                    <th>Date</th>
                    <th v-if='!this.$route.query.mcId'>ID</th>
                    <th>Message</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
              <tr v-for="emergency in emergencies" v-bind:key="emergency.seq">
                <td v-if='this.$route.query.mcId'><input type="radio" data-role="radio" :checked="emergency.seq == params.seq" name="seq" @change="seqChange(emergency.seq)"/></td>
                <td>{{ emergency.date }}</td>
                <td class="move" v-if='!this.$route.query.mcId' v-on:click="move('HISTORY', emergency.mcId)">{{ emergency.mcId }}</td>
                <td>{{ this.$getEmergencyMessageFromCode((emergency.emgcyTpCd)) }}</td>
                <td>{{ emergency.statCd }}</td>
              </tr>
            </tbody>
        </table>
        <br>
        <div v-if="this.$route.query.mcId">
          <h4>Visit</h4>
          <div class="space" style="height:30px;"></div>
          <input type="text" autocomplete='off' data-clear-button="false" data-role="input" data-prepend="<span class='mif-ambulance'>방문일정</span>" placeholder="방문일정" v-model="visit.plan">
          <div class="space" style="height:30px;"></div> 
          <button style="width: 100%;" v-on:click="processVisit"><span class='mif-user-md'> 입력</span></button>
          <div class="space" style="height:30px;"></div>
          <br>
          <h4>Process</h4>
          <div class="space" style="height:30px;"></div>
          <input type="text" autocomplete='off' data-clear-button="false" data-role="input" data-prepend="<span class='mif-ambulance'>증상</span>" placeholder="증상" v-model="emergency.symptCntn">
          <div class="space" style="height:10px;"></div>
          <input type="text" autocomplete='off' data-clear-button="false" data-role="input" data-prepend="<span class='mif-stethoscope'>원인</span>"  placeholder="원인" v-model="emergency.causeDesc">
          <div class="space" style="height:10px;"></div>
          <input type="text" autocomplete='off' data-clear-button="false" data-role="input" data-prepend="<span class='mif-medkit'>조치</span>" placeholder="조치" v-model="emergency.actnCntn">
          <div class="space" style="height:30px;"></div>
          <button style="width: 100%;" v-on:click="process"><span class='mif-user-md'> 입력</span></button>
          <div class="space" style="height:30px;"></div>
        </div>
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
  name: "UserEmergencyView",
  components: {
  },
  mounted: function () {
    this.$data.params.mcId = this.$route.query.mcId;
    this.$data.params.seq = this.$route.query.seq;
    this.$data.emergency.mcId = this.$route.query.mcId;
    this.$data.visit.mcId = this.$route.query.mcId;
    this.$data.show.menu = true;
    
    if (this.$route.query.mcId) {
      this.getUserInfo();
      this.getUserEmergency();
    } else {
      this.getInfo();
      this.getEmergency();
    }
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
    async getUserInfo() {
      post("/api/agency/user/info", this.$data.params)
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
      this.$data.info.titleNm = data.mcId;
      this.$data.info.total = data.filters + data.nonfilters;
      this.$data.show.summary = true;
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
      this.$data.show.summary = true;
      
      this.$data.show.donut |= 1 << 0;
    },
    async getUserEmergency() {
      post("/api/agency/user/emergency/list", this.$data.params)
      .then((res) => this.resultUserEmergency(res.data))
      .catch((error) => {
        alert(error);
      });
    },
    resultUserEmergency(data) {
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
    async getEmergency() {
      post("/api/agency/emergency/list", this.$data.params)
      .then((res) => this.resultUserEmergency(res.data))
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
    move(tp, mcId) {
      console.log(tp);
      if(tp == 'EMERGENCY') {
        this.$router.push({ name: "agency_user_emergency", query : { "mcId" : this.$data.params.mcId } }); //parameter전달시 path이용불가
      } else if(tp == 'HISTORY') {
        this.$router.push({ name: "agency_user_history", query : { "mcId" : mcId ? mcId : this.$data.params.mcId } }); //parameter전달시 path이용불가
      } else if(tp == 'DEVICE') {
        this.$router.push({ name: "agency_user_device", query : { "mcId" : this.$data.params.mcId } }); //parameter전달시 path이용불가
      } else if(tp == 'ORDER') {
        this.$route.query.mcId
        ? 
        this.$router.push({ name: "agency_user_order", query : { "mcId" : this.$route.query.mcId } })
        :
        this.$router.push({ name: "agency_user_history_order" });
      }
    },
    seqChange(seq) {
      this.$data.emergency.seq = seq;
      this.$data.visit.seq = seq;
    },
    async processVisit() {
      console.log(this.$data.emergency);
      if(this.$data.emergency.seq < 0) {
        alert('처리할 긴급호출을 선택해주세요.');
        return;
      }
      if(this.$data.emergency.plan == "") {
        alert('방문일정을 입력해주세요.');
        return;
      }
      post("/api/agency/emergency/visit", this.$data.visit)
      .then((res) => this.resultProcess(res.data))
      .catch((error) => {
        alert(error);
      });
    },
    resultProcessVisit(data) {
      if(data.cd == "1000") {
        alert('처리 중 오류가 발생하였습니다.');
      } else {
        this.$router.push({ name: "agency"});
        // this.getEmergency();
        // this.$data.visit.plan = "";
      }
    },
    async process() {
      console.log(this.$data.emergency);
      if(this.$data.emergency.seq < 0) {
        alert('처리할 긴급호출을 선택해주세요.');
        return;
      }
      if(this.$data.emergency.symptCntn == "") {
        alert('증상을 입력해주세요.');
        return;
      }
      if(this.$data.emergency.causeDesc == "") {
        alert('원인을 입력해주세요.');
        return;
      }
      if(this.$data.emergency.actnCntn == "") {
        alert('조치사항을 입력해주세요.');
        return;
      }
      post("/api/agency/emergency/process", this.$data.emergency)
      .then((res) => this.resultProcess(res.data))
      .catch((error) => {
        alert(error);
      });
    },
    resultProcess(data) {
      if(data.cd == "1000") {
        alert('처리 중 오류가 발생하였습니다.');
      } else {
        this.$router.push({ name: "agency"});
        // this.getEmergency();
        // this.$data.emergency.symptCntn = "";        
        // this.$data.emergency.causeDesc = "";
        // this.$data.emergency.actnCntn = "";
      }
    },
  },
};
</script>
