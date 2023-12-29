<template>
  <div class="user_order">
    <div v-if="show.menu" class="app-bar bg-hero fg-white" data-role="appbar">
      <a href="/#/user" class="brand no-hover">
          <span style="max-width: 200px;  white-space: nowrap;" class="mif-home">
            {{ ' ' + titleNm }}
          </span>
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
      <div class="grid orderlist" style="padding-top: 20px">
        <div class="row">
          <div class="cell">
            <div
              class="d-flex flex-justify-start"
              style="font-weight: bold; font-size: 20px"
            >
              ORDER
            </div>
          </div>
        </div>
        <div class="row" v-for="item in items" v-bind:key="item.id">
          <div class="cell">
            <div
              class="vname d-flex flex-justify-start"
              data-name="{{item.name}}"
            >
              {{ item.name }}
            </div>
          </div>
          <div class="cell">
            <div class="d-flex flex-justify-end">
              <button class="vchange" v-on:click="minus(item)">-</button>
              <input v-model="item.value" class="valuebox" type="number" />
              <button class="vchange" v-on:click="plus(item)">+</button>
            </div>
          </div>
        </div>
      </div>

      <div class="grid" style="padding-top: 10px">
        <div class="row">
          <div class="cell-2">
            <div
              class="d-flex flex-justify-start"
              style="font-weight: bold; font-size: 20px"
            >
              TOTAL
            </div>
          </div>
        </div>
        <div class="row">
          <div class="cell">
            <div id="resultBox" style = "font-weight : bold"></div>
          </div>
          <div class="cell" style ="padding: 0px 0px">
            <div class="d-flex flex-justify-end">
              <button id="btnOrder" v-on:click="order()" disabled>주문</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss">

.cell-user {
 padding-top: 3px;
}
// Existing button styles
.evt-btn {
  background-size: 60px;
  width: 50px;
  height: 50px;
  border: none;
  cursor: pointer;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.evt-btn:hover {
  background-color: rgba(255, 0, 255, 0.2);
  transform: scale(1.05); // subtle pop effect
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2); // adds depth
}

// Style for the value buttons and input
.vchange {
  background-color: #4A90E2; // Theme color
  color: #fff; // White text

  margin: 0 3px;
  border: none;
  border-radius: 3px;
  cursor: pointer;
  transition: background-color 0.2s;
}

.vchange:hover {
  background-color: #76B3FA; // Lighter on hover
}

.valuebox {
  text-align: center;
  width: 27px; // fixed width for alignment
  border: 1px solid #ccc;
  border-radius: 4px;
  padding: 2px;
  margin: 0 5px;
}

// Style for the order box
.orderlist {
  background: #f8f8f8; // Light background for the order list
  border-radius: 4px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  padding: 1rem;
  margin-top: 1rem;
}


#resultBox {
  padding: 0.5rem;
  background: #fff;
  border-radius: 4px;
  min-height: 50px; // Ensures box is visible even when empty
}

#btnOrder {
  background-color: #4CAF50; // Green background
  color: #fff;
  padding: 10px 20px;
  border-radius: 4px;
  cursor: pointer;
  border: none;
  transition: background-color 0.2s;
}

#btnOrder:disabled {
  background-color: #9E9E9E; // Grey out when disabled
}

#btnOrder:not(:disabled):hover {
  background-color: #66BB6A; // Lighter green on hover
}

// Brand styling
.brand {
  text-overflow: ellipsis;
  overflow: hidden;
  max-width: 200px; // Restricts the brand to avoid overflow
  white-space: nowrap;
}

// General text enhancements
.d-flex {
  font-weight: bold;
}

// Responsive table adjustments (if necessary)
@media (max-width: 768px) {
  .cell {
    flex-direction: column;
    align-items: flex-start;
  }

  .cell > div {
    width: 100%;
    margin-bottom: 0.5rem;
  }

  .valuebox {
    width: 50%;
  }
}
</style>



<script>
// @ is an alias to /src
import { post } from '@/api/index.js';

export default {
  name: "OrdeView",
  components: {},
  mounted: function () {
    this.$data.show.menu = true;
    this.getInfo();
  },
  data() {
    return {
      titleNm : "",
      filters : 0,
      nonfilters : 0,
      emergency : 0,
      items: [
        {
          id: "Cylinder",
          category: "Cylinder",
          name: "Cylinder",
          value: 0,
        },
        {
          id: "Strawberry",
          category: "Syrup",
          name: "딸기",
          value: 0,
        },
        {
          id: "Mint",
          category: "Syrup",
          name: "민트",
          value: 0,
        },
        {
          id: "Herb",
          category: "Syrup",
          name: "허브",
          value: 0,
        },
        {
          id: "Cola",
          category: "Syrup",
          name: "콜라",
          value: 0,
        },
        {
          id: "Filter",
          category: "Filter",
          name: "필터",
          value: 0,
        },
      ],
      orders : [],
      show : {
        menu : false,
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
    gotoOrder() {
      this.$router.push({ path: "/user/order" });
    },
    gotoEmergency() {
      this.$router.push({ path: "/user/emergency" });
    },

    plus(item) {
      console.log("plus", item);
      item.value = item.value + 1;
      this.calcOrder();
    },
    minus(item) {
      console.log("minus", item);
      if (item.value > 0) item.value = item.value - 1;
      this.calcOrder();
    },
    getOrders() {
      let lstOrders = [];
      for (let i in this.items) {
        let item = this.items[i];
        if (item.value > 0) {
          lstOrders.push(item);
        }
      }
      return lstOrders;
    },
    order() {
      let lstOrders = this.getOrders();
      if (lstOrders.length < 1) {
        alert("주문내역이 없습니다.");
      } else {
        post("/api/user/order", this.$data.orders)
        .then((res) => this.orderResult(res.data))
        .catch((error) => {
          alert(error);
        });
      }
    },
    orderResult(data) {
      console.log(data);
      this.$router.push({ path: "/user" });
    },
    calcOrder() {
      let lstOrders = this.getOrders();
      let rb = document.getElementById("resultBox");
      this.$data.orders = [];
      while (rb.firstChild) {
        rb.removeChild(rb.firstChild);
      }
      document.getElementById("btnOrder").disabled = lstOrders.length < 1;
      for (let i = 0; i < lstOrders.length; i++) {
        let row = document.createElement("div");
        row.appendChild(
          document.createTextNode(
            lstOrders[i].name + " " + lstOrders[i].value + "EA"
          )
        );
        rb.appendChild(row);
        this.$data.orders.push(lstOrders[i]);
      }
    },
  },
};
</script>
