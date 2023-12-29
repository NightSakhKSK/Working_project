<template>
  <div class="corp_user_list">
    <div v-if="show.menu" class="app-bar bg-hero fg-white" data-role="appbar">
      <a v-if="!isCorp" v-on:click="move('summary')" class="brand no-hover">
          <span style="max-width: 200px;  white-space: nowrap;" class="mif-home">
            {{' ' + titleNm }}
          </span>
      </a>
      <a v-if="isCorp" href="/#/corp" class="brand no-hover">
          <span style="max-width: 200px;  white-space: nowrap;" class="mif-home">
            제품설치목록
          </span>
      </a>
      <ul class="app-bar-menu">
        <li><a href="/#/corp"><span class='mif-home'> 본사 메인</span></a></li>
        <li><a href="/#/corp/agency/add"><span class='mif-wrench'> 대리점 등록</span></a></li>
        <li><a href="/#/corp/chpw"><span class='mif-key'> 비밀번호 변경</span></a></li>
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
    </div>

    <div class="space" style="height:30px;"></div>
    <table class="table">
      <tbody>
        <tr>
          <td>
            <input type="text" data-role="input" data-prepend="<span class='mif-qrcode'> 검색 </span>" placeholder="제품ID, 구매자명, 장소, 연락처 등으로 검색하세요." v-model="search.text" @keyup.enter="searchUser">
          </td>
          <td style="width: 50px;">
            <button v-on:click="searchUser" class="mif-search"></button>
          </td>
        </tr>
      </tbody>
    </table>
    
    <div class="space" style="height:30px;"></div>
    <table class="table distribute-table table-bubble">
      <thead>
          <tr>
              <th>ID</th>
              <th>설치정보</th>
          </tr>
      </thead>
      <tbody>
        <tr v-for="user in users" v-bind:key="user.mcId">
          <td>{{user.mcId}}</td>
          <td>
            <div class="grid textLeft">
              <div class="row text-right">
                <div class="cell">
                  {{ user.buyerNm }} {{ user.telNo }}
                </div>
              </div>
              <div class="row text-right">
                <div class="cell">
                  {{ user.instlLocDesc }}
                </div>
              </div>
            </div>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped>

.table-bubble {
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

  tbody tr {
    transition: background-color 0.2s; /* Smooth transition for hover effect */
  }

  tbody tr:hover {
    background-color: #ecf2fa; /* Light blue background on row hover */
  }
}
</style>

<script>
// @ is an alias to /src
import { post } from '@/api/index.js';

export default {
  name: "UserListView",
  components: {
    //HelloWorld
  },
  mounted : function() {
    this.$data.params.agncyId = this.$route.query.agncyId;
    if(this.$data.params.agncyId == undefined) this.$data.isCorp = true;
    this.$data.show.menu = true;
    this.getInfo();
    this.searchUser();
  },
  data() {
    return {
      titleNm : "",
      isCorp : false,
      info : {
        total : 0,
        filters : 0,
        nonfilters : 0,
        emergency : 0,
        devices : 0,
      },
      show : {
        menu : false,
        summary : false,
        donut : 0,
      },
      params : {
        agncyId : null,
      },
      search : {
        text : "",
      },
      users : [],
    }
  },
  methods: {
    move(tp) {
      console.log(tp);
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
      if(this.$data.params.agncyId) {
        post("/api/corp/agency/info", this.$data.params)
        .then((res) => this.result(res.data))
        .catch((error) => {
          alert(error);
        });
      } else {
        post("/api/corp/info", null)
        .then((res) => this.result(res.data))
        .catch((error) => {
          alert(error);
        });
      }
      
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
    },
    async searchUser() {
      post("/api/corp/user/search", { "text" : this.$data.search.text, "agncyId" : this.$data.params.agncyId })
      .then((res) => this.resultSearch(res.data))
      .catch((error) => {
        alert(error);
      });
    },
    resultSearch(data) {
      this.$data.users = data;
    },
  },
};
</script>
