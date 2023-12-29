<template>
  <div class="agency">
    <div v-if="show.menu" class="app-bar bg-hero fg-white" data-role="appbar">
      <a href="/#/agency" class="brand no-hover">
          <span style="max-width: 200px;  white-space: nowrap;" class="mif-home">
            {{ ' ' + titleNm }}
          </span>
      </a>
      <a v-on:click="move('ORDER')" class="app-bar-item text-upper"><span class="mif-cart"> Order</span></a>
      <a v-if='!this.$route.query.mcId' v-on:click="move('EMERGENCY')" class="app-bar-item text-upper"><span class="mif-help"> Emergency</span></a>
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
        :data-title-caption="'Total Orders ' + total"
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
              <div class="d-flex flex-justify-center">{{ nonfilters }}</div>
            </div>
            <div class="cell">
              <div class="d-flex flex-justify-center">{{ filters }}</div>
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

      <br />
      <h4>History List</h4>
      <table class="table table-border cell-border">
        <thead>
          <tr>
            <th>Date</th>
            <th>ID</th>
            <th>Order</th>
            <th>Qty</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="order in orderList.filter((c) => c.tp == 'ORDER')" v-bind:key="order.seq">
            <td>{{order.date}}</td>
            <td class="move" v-on:click="move(order.tp, order.mcId ? order.mcId : this.$route.query.mcId)">{{order.mcId ? order.mcId : this.$route.query.mcId}}</td>
            <td class="move" v-if="order.tp == 'ORDER'">
              <div class="grid">
                <div class="row" v-for="item in order.items" v-bind:key="'nm_' + order.seq + '_' + item.seq">
                  <div class="cell" v-on:click="move(order.tp, order.mcId ? order.mcId : this.$route.query.mcId, order.seq)">{{item.nm}}</div>
                </div>
              </div>
            </td>
            <td class="move" v-else v-on:click="move(order.tp, order.mcId ? order.mcId : this.$route.query.mcId, order.seq)">Emergency</td>
            <td class="move" v-if="order.tp == 'ORDER'">
              <div class="grid">
                <div class="row" v-for="item in order.items" v-bind:key="'qty_' + order.seq + '_' + item.seq">
                  <div class="cell">{{item.qty}}</div>
                </div>
              </div>
            </td>
            <td v-else></td>
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
.move {
  cursor: pointer;
}
</style>

<script>
// @ is an alias to /src
import { post } from '@/api/index.js';

export default {
  name: "AgencyView",
  components: {},
  mounted: function () {
    this.$data.show.menu = true;
    if (this.$route.query.mcId)
      this.getUserInfo();
    else
      this.getInfo();
    this.getOrderList();
  },
  data() {
    return {
      titleNm : "",
      total : 0,
      filters : 0,
      nonfilters : 0,
      emergency : 0,
      process : {
        filters : 0,
        nonfilters : 0,
        emergency : 0,
      },
      show : {
        menu : false,
        summary : false,
        donut : 0,
      },
      orderList : [],
    }
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
      this.$data.filters = data.filters;
      this.$data.nonfilters = data.nonfilters;
      this.$data.emergency = data.emergency;
      this.$data.total = data.filters + data.nonfilters;
      this.$data.titleNm = data.mcId;
      this.$data.show.summary = true;
      
      this.$data.show.donut |= 1 << 0;

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
      this.$data.filters = data.filters;
      this.$data.nonfilters = data.nonfilters;
      this.$data.emergency = data.emergency;
      this.$data.total = data.filters + data.nonfilters;
      this.$data.titleNm = data.nm;
      this.$data.show.summary = true;
      
      this.$data.show.donut |= 1 << 0;
    },
    async getOrderList() {
      post("/api/agency/user/history/order/list", {"mcId": this.$route.query.mcId})
      .then((res) => this.resultOrderList(res.data))
      .catch((error) => {
        alert(error);
      });
    },
    resultOrderList(data) {
      console.log(data);
      this.$data.orderList = data;
    },
    move(tp, mcId, seq) {
      if(tp == "EMERGENCY") {
        this.$router.push({ name: "agency_user_emergency", query : { "mcId" : mcId ? mcId : this.$route.query.mcId, "seq" : seq } });
      } else if(tp == "ORDER") {
        mcId || this.$route.query.mcId
        ? 
        this.$router.push({ name: "agency_user_order", query : { "mcId" : mcId ? mcId : this.$route.query.mcId, "seq" : seq } })
        :
        this.$router.push({ name: "agency_user_history_order", query : { "seq" : seq } });
      }
    },  
  },
};
</script>
