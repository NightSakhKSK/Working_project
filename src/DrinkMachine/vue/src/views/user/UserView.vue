<template>
  <div class="user">
    <div v-if="show.menu" class="app-bar bg-hero fg-white" data-role="appbar">
      <a href="/#/user" class="brand no-hover">
          <span style="max-width: 200px;  white-space: nowrap;" class="mif-home ">
            {{ ' ' + titleNm}}
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

    <div
      class="container-fluid docs-content"
      style="height: auto !important; margin-top: 60px"
    >
      <div
        data-role="panel"
        data-title-caption="Total Order <span id='ordercnt'>0</span>"
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
              <div class="d-flex flex-justify-center">{{nonfilters}}</div>
            </div>
            <div class="cell">
              <div class="d-flex flex-justify-center">{{filters}}</div>
            </div>
            <div class="cell">
              <div
                class="d-flex flex-justify-center"
                style="color: red; font-weight: bold"
              >
                {{emergency}}
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="grid" style="padding-top: 20px">
        <div class="row">
          <div class="cell">
            <div class="d-flex flex-justify-center">
              <button
                class="evt-btn"
                v-on:click="gotoOrder"
                :style="{
                  background:
                    'url(' +
                    require('@/assets/order.png') +
                    ') center no-repeat',
                }"
              ></button>
            </div>
          </div>
          <div class="cell">
            <div class="d-flex flex-justify-center">
              <button
                class="evt-btn"
                v-on:click="gotoEmergency"
                :style="{
                  background:
                    'url(' +
                    require('@/assets/Emergency.png') +
                    ') center no-repeat',
                }"
              ></button>
            </div>
          </div>
        </div>
      </div>

      <br />
      <h4>Event List</h4>
      <table class="table">
        <thead>
          <tr>
            <th>Date</th>
            <th>Order</th>
            <th>Qtys</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="order in orderList" v-bind:key="order.seq">
            <td>{{order.date}}</td>
            <td>
              <div class="grid" style="padding-top: 20px">
                <div class="row" v-for="item in order.items" v-bind:key="'nm_' + order.seq + '_' + item.seq">
                  <div class="cell">{{item.nm}}</div>
                </div>
              </div>
            </td>
            <td>
              <div class="grid" style="padding-top: 20px">
                <div class="row" v-for="item in order.items" v-bind:key="'qty_' + order.seq + '_' + item.seq">
                  <div class="cell">{{item.qty}}</div>
                </div>
              </div>
            </td>
          </tr>         
        </tbody>
      </table>
    </div>
  </div>
</template>

<style lang="scss">
.evt-btn {
  transition: transform 0.3s ease;
  background-size: 60px;
  width: 75px;
  height: 100px;
}

.evt-btn:hover {
  transform: scale(1.1);
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

</style>

<script>
// @ is an alias to /src
import { post } from '@/api/index.js';

export default {
  name: "UserView",
  components: {},
  mounted: function () {
    this.$data.show.menu = true;
    this.getInfo();
    this.getOrders();
  },
  data() {
    return {
      titleNm : "",
      filters : 0,
      nonfilters : 0,
      emergency : 0,
      orderList : [],
      show : {
        menu : false,
      },
    }
  },
  methods: {
    async getOrders() {
      post("/api/user/order/list", null)
      .then((res) => this.resultOrders(res.data))
      .catch((error) => {
        alert(error);
      });
    },
    resultOrders(data) {
      console.log(data);
      this.$data.orderList = data;
    },
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
    gotoOrder() {
      this.$router.push({ path: "/user/order" });
    },
    gotoEmergency() {
      this.$router.push({ path: "/user/emergency" });
    },
  },
};
</script>
