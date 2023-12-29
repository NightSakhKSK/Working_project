<template>
  <div class="corp_agency">
    <div v-if="show.menu" class="app-bar bg-hero fg-white" data-role="appbar">
      <a v-on:click="move('summary')" class="brand no-hover">
          <span style="max-width: 200px;  white-space: nowrap;" class="mif-home">
            {{' ' + titleNm }}
          </span>
      </a>
      <a v-on:click="move('order')" class="app-bar-item text-upper"><span class="mif-cart"> Order</span></a>
      <a v-on:click="move('emergency')" class="app-bar-item text-upper"><span class="mif-help"> Emergency</span></a>

      <ul class="app-bar-menu">
        <li><a href="/#/corp"><span class='mif-home'> 본사 메인</span></a></li>
        <li><a v-on:click="move('user')" ><span class='mif-list'> 제품설치목록</span></a></li>
        <li><a href="/"><span class='mif-exit'> 로그아웃</span></a></li>
      </ul>
    </div>

    <div
      class="container-fluid docs-content"
      style="height: auto !important; margin-top: 60px"
    >
      <div v-if="show.summary"
        data-role="panel"
        :data-title-caption="'Total Devices ' + info.devices"
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

      <br />
      <h4>Process</h4>
      <div class="grid" style="padding-top: 20px">
        <div class="row">
          <div class="cell">
            <div class="d-flex flex-justify-center">Cyilnder &amp; Syrup</div>
          </div>
          <div class="cell">
            <div class="d-flex flex-justify-center">Filter</div>
          </div>
          <div class="cell">
            <div class="d-flex flex-justify-center">Emergency</div>
          </div>
        </div>
        <div class="row">
          <div class="cell">
            <div class="d-flex flex-justify-center">
              <div v-if="show.donut == 3"
                id="donutNonfilter"
                data-role="donut"
                :data-value="process.nonfilters"
                :data-total="info.nonfilters"
                data-cap="건"
                data-hole=".6"
                data-stroke="#f5f5f5"
                data-fill="#9C27B0"
                data-animate="10"
                data-show-value="true"
              ></div>
              <div v-else
                id="donutNonfilter"
                data-role="donut"
                data-value="0"
                data-total="0"
                data-cap="건"
                data-hole=".6"
                data-stroke="#f5f5f5"
                data-fill="#9C27B0"
                data-animate="10"
                data-show-value="true"
              ></div>
            </div>
          </div>
          <div class="cell">
            <div class="d-flex flex-justify-center">
              <div v-if="show.donut == 3"
                id="donutFilter"
                data-role="donut"
                :data-value="process.filters"
                :data-total="info.filters"
                data-cap="건"
                data-hole=".6"
                data-stroke="#f5f5f5"
                data-fill="#f6855c"
                data-animate="10"
                data-show-value="true"
              ></div>
              <div v-else
                id="donutFilter"
                data-role="donut"
                data-value="0"
                data-total="0"
                data-cap="건"
                data-hole=".6"
                data-stroke="#f5f5f5"
                data-fill="#f6855c"
                data-animate="10"
                data-show-value="true"
              ></div>
            </div>
          </div>
          <div class="cell">
            <div class="d-flex flex-justify-center">
              <div v-if="show.donut == 3"
                id="donutEmergency"
                data-role="donut"
                :data-value="process.emergency"
                :data-total="info.emergency"
                data-cap="건"
                data-hole=".6"
                data-stroke="#f5f5f5"
                data-fill="#f43232"
                data-animate="10"
                data-show-value="true"
              ></div>
              <div v-else
                id="donutEmergency"
                data-role="donut"
                data-value="0"
                data-total="0"
                data-cap="건"
                data-hole=".6"
                data-stroke="#f5f5f5"
                data-fill="#f43232"
                data-animate="10"
                data-show-value="true"
              ></div>
            </div>
          </div>
        </div>
      </div>

      <br />
      <h4>Today's Event List</h4>
      <table class="table">
        <thead>
          <tr>
            <th>Date</th>
            <th>ID</th>
            <th>Order</th>
            <th>Qty</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="order in orderList" v-bind:key="'order' + order.seq">
            <td>{{order.date}}</td>
            <td class="move" v-on:click="move(order.tp, order.mcId)">{{order.mcId}}</td>
            <td class="move">
              <div class="grid">
                <div class="row" v-for="item in order.items" v-bind:key="'nm_' + order.seq + '_' + item.seq">
                  <div class="cell" v-on:click="move(order.tp, order.mcId, order.seq)">{{item.nm}}</div>
                </div>
              </div>
            </td>
            <td class="move">
              <div class="grid">
                <div class="row" v-for="item in order.items" v-bind:key="'qty_' + order.seq + '_' + item.seq">
                  <div class="cell">{{item.qty}}</div>
                </div>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
      <br />
      <h4>Today's Emergency</h4>
      <table class="table table-border cell-border">
        <thead>
          <tr>
            <th>Date</th>
            <th>ID</th>
            <th>Message</th>
            <th>Status</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="emergency in emergencies" v-bind:key="'emergency' + emergency.seq">
            <td>{{emergency.date}}</td>
            <td>{{emergency.mcId}}</td>
            <td>{{ this.$getEmergencyMessageFromCode((emergency.emgcyTpCd)) }}</td>
            <td>{{emergency.statCd}}</td>
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
  name: "AgencyView",
  components: {},
  mounted : function() {
    this.$data.params.agncyId = this.$route.query.agncyId;
    this.$data.show.menu = true;
    this.getInfo();
    this.getProcess();
    this.getOrderList();
    this.getEmergencyList();
  },
  data() {
    return {
      titleNm : "",
      info : {
        total : 0,
        filters : 0,
        nonfilters : 0,
        emergency : 0,
        devices : 0,
      },
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
      params : {
        agncyId : null,
      },
      orderList : [],
      emergencies : [],
    }
  },
  methods: {
    move(tp) {
      if(tp == 'emergency') {
        this.$router.push({ name: "corp_agency_emergency", query : { "agncyId" : this.$data.params.agncyId } }); //parameter전달시 path이용불가
      } else if(tp == 'order') {
        this.$router.push({ name: "corp_agency_order", query : { "agncyId" : this.$data.params.agncyId } }); //parameter전달시 path이용불가
      } else if(tp == 'user') {
        this.$router.push({ name: "corp_user_list", query : { "agncyId" : this.$data.params.agncyId } }); //parameter전달시 path이용불가
      } else if(tp == 'summary') {
        this.$router.push({ name: "corp_agency", query : { "agncyId" : this.$data.params.agncyId } }); //parameter전달시 path이용불가
      }
    },
    async getInfo() {
      post("/api/corp/agency/info", this.$data.params)
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
      this.$data.info.devices = data.devices;
      this.$data.info.total = data.filters + data.nonfilters;
      this.$data.titleNm = data.nm;
      this.$data.show.summary = true;
      
      this.$data.show.donut |= 1 << 0;
    },
    async getProcess() {
      post("/api/corp/agency/process", this.$data.params)
      .then((res) => this.resultProcess(res.data))
      .catch((error) => {
        alert(error);
      });
    },
    resultProcess(data) {
      console.log(data);
      this.$data.process.filters = data.filters;
      this.$data.process.nonfilters = data.nonfilters;
      this.$data.process.emergency = data.emergency;
      this.$data.show.donut |= 1 << 1;
    },
    async getOrderList() {
      post("/api/corp/agency/order/today", this.$data.params)
      .then((res) => this.resultOrderList(res.data))
      .catch((error) => {
        alert(error);
      });
    },
    resultOrderList(data) {
      console.log(data);
      this.$data.orderList = data;
    },
    async getEmergencyList() {
      post("/api/corp/agency/emergency/today", this.$data.params)
      .then((res) => this.resultEmergencyList(res.data))
      .catch((error) => {
        alert(error);
      });
    },
    resultEmergencyList(data) {
      console.log(data);
      this.$data.emergencies = data;
    },
  },
};
</script>
<script>
</script>