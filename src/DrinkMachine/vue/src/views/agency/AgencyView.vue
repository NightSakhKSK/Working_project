<template xmlns:v-on="http://www.w3.org/1999/xhtml" xmlns:v-bind="http://www.w3.org/1999/xhtml">
  <div class="agency">
    <div v-if="show.menu" class="app-bar bg-hero fg-white" data-role="appbar">
      <a href="/#/agency" class="brand no-hover">
          <span style="max-width: 200px;  white-space: nowrap;" class="mif-home">
            {{ ' ' + titleNm }}
          </span>
      </a>
      <a v-on:click="move('ORDER')" class="app-bar-item text-upper"><span class="mif-cart"> Order</span></a>
      <a v-on:click="move('EMERGENCY')" class="app-bar-item text-upper"><span class="mif-help"> Emergency</span></a>
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
                   :data-total="nonfilters"
                   data-cap="건"
                   data-hole=".6"
                   data-stroke="#e0e0e0"
                   data-fill="#9C27B0"
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
                   data-stroke="#e0e0e0"
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
                   :data-total="filters"
                   data-cap="건"
                   data-hole=".6"
                   data-stroke="#e0e0e0"
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
                   data-stroke="#e0e0e0"
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
                   :data-total="nonfilters"
                   data-cap="건"
                   data-hole=".6"
                   data-stroke="#e0e0e0"
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
                   data-stroke="#e0e0e0"
                   data-fill="#f43232"
                   data-animate="10"
                   data-show-value="true"
              ></div>
            </div>
          </div>
        </div>
      </div>

      <br />
      <h4>Event List</h4>
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
          <tr v-for="order in orderList" v-bind:key="order.seq">
            <td>{{order.date}}</td>
            <td class="move" v-on:click="move(order.tp, order.mcId)">{{order.mcId}}</td>
            <td class="move" v-if="order.tp == 'ORDER'">
              <div class="grid">
                <div class="row" v-for="item in order.items" v-bind:key="'nm_' + order.seq + '_' + item.seq">
                  <div class="cell" v-on:click="move(order.tp, order.mcId, order.seq)">{{item.nm}}</div>
                </div>
              </div>
            </td>
            <td class="move" v-else v-on:click="move(order.tp, order.mcId, order.seq)">Emergency</td>
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
/* General styles for the application */
.app-bar {
  background-color: #4A90E2; /* Consistent blue background for the app bar */
  color: #fff; /* White text for readability */
}

.app-bar .brand {
  color: #fff; /* Ensure brand text is also white for visibility */
}

.app-bar-menu a {
  color: #fff; /* White text for app bar menu items */
  &:hover {
    background-color: #76B3FA; /* Lighter blue on hover for menu items */
  }
}

/* Styles for the panel titles and counts */
.panel {
  background-color: #f2f4f8; /* Light background for panels */
  border: 1px solid #e1e4e8; /* Border to define edges */
  border-radius: 8px; /* Rounded corners for the panel */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* Subtle shadow for depth */
}

.panel [data-title-caption] {
  color: #4A90E2; /* Blue color for the caption */
  font-weight: bold; /* Bold font for emphasis */
}

/* Styles for the table headers and rows */
.table {
  th {
    background-color: #4A90E2; /* Blue background for table headers */
    color: #fff; /* White text for headers */
  }

  td {
    color: #333; /* Dark text for table data for readability */
  }

  thead th {
    font-weight: bold; /* Bold font for table headers */
  }
}

/* Styles for the search input and button */
[data-role="input"] input {
  border: 2px solid #4A90E2; /* Blue border for input fields */
  &:focus {
    border-color: #306ABD; /* Darker blue for focus */
  }
}

button.mif-search {
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

/* Responsive grid */
.grid {
  .row {
    margin: 0; /* Remove default margins */
    padding: 5px 0; /* Add padding for spacing between rows */

    .cell {
      padding: 0 5px; /* Spacing inside cells */
    }
  }
}

/* Additional customizations */
.textLeft {
  text-align: left; /* Align text to the left in the grid */
}

/* Responsive design for smaller screens */
@media (max-width: 768px) {
  .table {
    thead th {
      /* Keep header text size the same, but allow it to wrap */
      white-space: nowrap;
      overflow: hidden;
      text-overflow: ellipsis;
    }

    tbody tr {
      /* Ensure that rows remain the same width, but adjust text size */
      font-size: 0.9em;
    }

    tbody td {
      /* Ensure that cells remain the same width, but adjust text size */
      font-size: 0.9em;
    }
  }
}

/* Soften the edges of the containers */
.panel, .app-bar, .table, .distribute {
  border-radius: 8px;
  box-shadow: 0 4px 10px rgba(0,0,0,0.1); /* Subtle shadow */
  transition: box-shadow 0.3s ease;
}

.donut:hover {
  transform: scale(1.05); /* Slightly increase size on hover */
  box-shadow: 0 12px 30px rgba(0,0,0,0.25); /* Increase shadow on hover */
}

/* Additional styling to make the emergency sections stand out */
.emergency {
  background-color: #ffe5e5; /* Soft red background */
  color: #ff6b6b; /* Brighter red text for contrast */
}

.emergency:hover {
  background-color: #ffc9c9; /* Lighter red on hover */
}

.distribute-table {
  width: 100%;

  .distribute {
    background-color: #fff; /* Default background color */
    cursor: pointer;
    transition: background-color 0.3s ease, box-shadow 0.3s ease;
    margin-bottom: 10px; /* Add space between rows */
    &:hover {
      background-color: #ecf2fa; /* Light blue background on hover */
      box-shadow: 0 6px 15px rgba(0,0,0,0.15); /* More pronounced shadow on hover */
    }
  }
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
    this.getInfo();
    this.getProcess();
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
    async getInfo() {
      post("/api/agency/info", null)
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
      this.$data.total = data.filters + data.nonfilters;
      this.$data.titleNm = data.nm;
      this.$data.show.summary = true;
      
      this.$data.show.donut |= 1 << 0;

    },
    async getProcess() {
      post("/api/agency/process", null)
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
      post("/api/agency/order/list", null)
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
        this.$router.push({ name: "agency_user_emergency", query : { "mcId" : mcId, "seq" : seq } });
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
