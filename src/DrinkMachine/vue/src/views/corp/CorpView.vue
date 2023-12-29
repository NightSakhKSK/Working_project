<template>
  <div class="corp">

    <div v-if="show.menu" class="app-bar bg-hero fg-white" data-role="appbar">
      <a href="/#/corp" class="brand no-hover">
          <span style="max-width: 200px;  white-space: nowrap;" class="mif-home">
              본사
          </span>
      </a>
      <ul class="app-bar-menu">
        <li><a href="/#/corp"><span class='mif-home'> 본사 메인</span></a></li>
        <li><a href="/#/corp/agency/add"><span class='mif-wrench'> 대리점 등록</span></a></li>
        <li><a href="/#/corp/chpw"><span class='mif-key'> 비밀번호 변경</span></a></li>
        <li><a href="/#/corp/user/list" ><span class='mif-list'> 제품설치목록</span></a></li>
        <li><a href="/"><span class='mif-exit'> 로그아웃</span></a></li>
      </ul>
    </div>
    
    <div class="container-fluid docs-content">
        <div v-if="show.summary" data-role="panel" :data-title-caption="'Total Device ' + info.devices" data-title-icon="<span class='mif-news bold-text'></span>">
            <div class="grid">
                <div class="row">
                    <div class="cell">
                        <div class="d-flex flex-justify-center" style="font-weight: bold;">Cyilnder & Syrup</div>
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
                :data-total="info.filters"
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
                :data-total="info.emergency"
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

        <br>
        <h4>Distribute List</h4>
        <table class="table distribute-table">
            <thead>
                <tr>
                    <th width="50%"></th>
                    <th width="50%"></th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="distributepair in distributes" v-bind:key="distributepair.id">
                    <td>
                        <div class="distribute move bold-text" data-role="panel" :data-title-caption="distributepair.left.agncyNm" v-on:click="move(distributepair.left.agncyId)">
                            Device : {{distributepair.left.devices}}<br>
                            Order : {{distributepair.left.nonfilters}}<br>
                            Filter : {{distributepair.left.filters}}<br>
                            Emergency : {{distributepair.left.emergency}}<br>
                        </div>
                    </td>
                    <td>
                        <div v-if="distributepair.right" class="distribute move" data-role="panel" :data-title-caption="distributepair.right.agncyNm" v-on:click="move(distributepair.right.agncyId)">
                            Device : {{distributepair.right.devices}}<br>
                            Order : {{distributepair.right.nonfilters}}<br>
                            Filter : {{distributepair.right.filters}}<br>
                            Emergency : {{distributepair.right.emergency}}<br>
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
  name: "CorpView",
  components: {},
  mounted : function() {
    this.$data.show.menu = true;
    this.getInfo();
    this.getProcess();
    this.getDistribute();
  },
  data() {
    return {
      info : {
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
        distribute : false,
      },
      distributes : [],
    }
  },
  methods: {
    async getInfo() {
      post("/api/corp/info", null)
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
      this.$data.show.summary = true;
      this.$data.show.donut |= 1 << 0;
    },
    async getProcess() {
      post("/api/corp/process", null)
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
    async getDistribute() {
      post("/api/corp/distribute", null)
      .then((res) => this.resultDistribute(res.data))
      .catch((error) => {
        alert(error);
      });
    },
    resultDistribute(data) {
      console.log(data);
      this.$data.distributes = [];
      if(data) {
        for(let i=0;i<data.length;i+=2) {
            let pair = {"id" : "distpair" + i, "left" : data[i] };
            if(i+1<data.length) {
                pair['right'] = data[i+1];
            }
            this.$data.distributes.push(pair);
        }
      }
      this.$data.show.distribute = true;
    },
    move(agncyId) {
      this.$router.push({ name: "corp_agency", query : { "agncyId" : agncyId } }); //parameter전달시 path이용불가
    }
  },
};
</script>
