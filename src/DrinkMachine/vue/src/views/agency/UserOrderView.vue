<template>
  <div class="agency_user_order">
    <div v-if="show.menu" class="app-bar bg-hero fg-white" data-role="appbar">
      <a href="/#/agency" class="brand no-hover">
          <span style="max-width: 200px;  white-space: nowrap;" class="mif-home">
            {{ ' ' + info.titleNm }}
          </span>
      </a>
      <a v-if='this.$route.query.mcId' v-on:click="move('HISTORY')" class="app-bar-item"><span class='mif-history'> History</span></a>
      <a v-if='!this.$route.query.mcId' v-on:click="move('ORDER')" class="app-bar-item"><span class='mif-cart'> Order</span></a>
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

        <h4>Order List</h4>
        <table class="table table-border cell-border">
        <thead>
          <tr>
            <th>Date</th>
            <th>Order</th>
            <th>Qty</th>
            <th>ID</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="order in orderList.filter((c) => c.tp == 'ORDER')" v-bind:key="order.seq">
            <td>{{order.date}}</td>
            <td>
              <div class="grid">
                <div class="row" v-for="item in order.items" v-bind:key="'nm_' + order.seq + '_' + item.seq">
                  <div class="cell" v-on:click="move(order.tp, order.mcId, order.seq)">{{item.nm}}</div>
                </div>
              </div>
            </td>
            <td>
              <div class="grid">
                <div class="row" v-for="item in order.items" v-bind:key="'qty_' + order.seq + '_' + item.seq">
                  <div class="cell">{{item.qty}}</div>
                </div>
              </div>
            </td>
            <td><button class="blue-button" v-on:click="delivery(order.seq)">배송</button></td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<style scoped>
button.blue-button {
  background-color: #4A90E2; /* Blue background for search button */
  color: #fff; /* White icon */
  border: none; /* Remove border */
  padding: 10px; /* Spacing around the icon */
  border-radius: 5px; /* Rounded corners */
  cursor: pointer; /* Pointer cursor on hover */

  &:hover {
    background-color: #76B3FA; /* Lighter blue on hover */
  }

  &:active {
    background-color: #306ABD; /* Darker blue when active */
  }
}
</style>

<script>
// @ is an alias to /src
import { post } from '@/api/index.js';

export default {
  name: "UserOrderView",
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
    this.getOrderList();
  },
  data() {
    return {
      params : {
        mcId : "",
      },
      info : {
        show : false,
        total : 0,
        filters : 0,
        nonfilters : 0,
        emergency : 0,
        titleNm : "",
      },
      orderList : [],
      show : {
        menu : false,
      },
    }
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
      this.$data.info.filters = data.filters;
      this.$data.info.nonfilters = data.nonfilters;
      this.$data.info.emergency = data.emergency;
      this.$data.info.total = data.filters + data.nonfilters;
      this.$data.info.show = true;

      this.$data.info.titleNm = data.mcId;
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
      
      this.$data.show.donut |= 1 << 0;
    },
    async getOrderList() {
      post("/api/agency/user/order/list", this.$data.params)
      .then((res) => this.resultOrderList(res.data))
      .catch((error) => {
        alert(error);
      });
    },
    resultOrderList(data) {
      console.log(data);
      this.$data.orderList = data;
    },
    async delivery(seq) {
      var find = null;
      for(let i in this.$data.orderList) {
        let v = this.$data.orderList[i];
        if(v.seq == seq) {
          find = v;
        }
      }
      if(find == null) {
        alert('잘못된 명령입니다.');
        return;
      }
      var check='';
      for(let i=0;i<find.items.length;i++) {
          check += find.items[i].nm + " : " + find.items[i].qty + "EA\n";
      }
      check += '\n배송완료처리 하시겠습니까?';
      if(confirm(check)) {
        post("/api/agency/user/order/delivery", { "mcId" : this.$data.params.mcId, "seq" : seq } )
        .then((res) => this.resultDelivery(res.data))
        .catch((error) => {
          alert(error);
        });
      }
    },
    resultDelivery(data) {
      if(data.cd == "0000") this.getOrderList();
      else                  alert('처리중 오류가 발생하였습니다');
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
      } else if(tp == "HISTORY") {
        this.$router.push({ name: "agency_user_history_order", query : { "mcId" : mcId ? mcId : this.$route.query.mcId, "seq" : seq } });
      }
    },  
  }
};
</script>
