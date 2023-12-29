<template>
  <div class="agency_user_device">
    <div v-if="show.menu" class="app-bar bg-hero fg-white" data-role="appbar">
        <a href="/#/agency" class="brand no-hover">
            <span style="max-width: 200px;  white-space: nowrap;" class="mif-home">
               {{ params.mcId }}
            </span>
        </a>
        <a v-on:click="move('history')" class="app-bar-item"><span class='mif-history'> History</span></a>
        <a v-on:click="move('emergency')" class="app-bar-item"><span class='mif-help'> Emergency</span></a>
        <ul class="app-bar-menu">
            <li><a href="/#/agency"><span class='mif-home'> 메인</span></a></li>
            <li><a href="/#/agency/user/list"><span class='mif-list'> 제품 설치 목록</span></a></li>
            <li><a href="/#/agency/user/add"><span class='mif-wrench'> 제품 설치 등록</span></a></li>
            <li><a href="/#/agency/chpw"><span class='mif-key'> 비밀번호 변경</span></a></li>
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

      <table class="table">
        <tbody>
          <tr>
            <td>냉각상태</td>
            <td>{{stat.iceStat == "0" ? "정상" : "불량"}}</td>
            <td> </td>
            <td>시럽1잔여량</td>
            <td>{{stat.syrup1Remn > 10 ? stat.syrup1Remn+"%" : "시럽부족"}}</td>
            <td>
              <button class = "button" v-on:click="doAction('INIT', 's1_r')">초기화</button>
            </td>
          </tr>
          <tr>
            <td>냉수상태</td>
            <td>{{stat.cwStat == "0" ? "정상" : "불량"}}</td>
            <td></td>
            <td>시럽2잔여량</td>
            <td>{{stat.syrup2Remn > 10 ? stat.syrup2Remn+"%" : "시럽부족"}}</td>
            <td>
              <button class = "button" v-on:click="doAction('INIT', 's2_r')">초기화</button>
            </td>
          </tr>
          <tr>
            <td>탄산상태</td>
            <td>{{stat.spklStat == "0" ? "정상" : "불량"}}</td>
            <td></td>
            <td>시럽3잔여량</td>
            <td>{{stat.syrup3Remn > 10 ? stat.syrup3Remn+"%" : "시럽부족"}}</td>
            <td>
              <button class = "button" v-on:click="doAction('INIT', 's3_r')">초기화</button>
            </td>
          </tr>
          <tr>
            <td>온수상태</td>
            <td>{{stat.hwStat == "0" ? "정상" : "불량"}}</td>
            <td></td>
            <td>시럽4잔여량</td>
            <td>{{stat.syrup4Remn > 10 ? stat.syrup4Remn+"%" : "시럽부족"}}</td>
            <td>
              <button class = "button" v-on:click="doAction('INIT', 's4_r')">초기화</button>
            </td>
          </tr>
          <tr>
            <td>누수상태</td>
            <td>{{stat.wlStat == "0" ? "정상" : "불량"}}</td>
            <td></td>
            <td>가스 충전 상태</td>
            <td>{{stat.gasRchrgStat != 0 ? stat.gasRchrgStat+"%" : "가스부족" }}</td>
          </tr>
          <tr>
            <td>만수상태</td>
            <td>{{stat.woStat == "0" ? "정상" : "불량"}}</td>
            <td></td>
            <td>냉수 탱크 UVC 수명</td>
            <td>{{stat.cwTankUvcRemn != 0 ? stat.cwTankUvcRemn+"%" : "교체요망"}}</td>
            <td>
              <button class = "button" v-on:click="doAction('INIT', 'u_cold_r')">초기화</button>
            </td>
          </tr>
          <tr>
            <td>히터상태</td>
            <td>{{stat.hitStat == "0" ? "정상" : "불량"}}</td>
            <td></td>
            <td>탄산 탱크 UVC 수명</td>
            <td>{{stat.spklTankUvcRemn != 0 ? stat.spklTankUvcRemn+"%" : "교체요망"}}</td>
            <td>
              <button class = "button" v-on:click="doAction('INIT', 'u_carbon_r')">초기화</button>
            </td>
          </tr>
          <tr>
            <td>콤프레샤상태</td>
            <td>{{stat.compStat == "0" ? "정상" : "불량"}}</td>
            <td> </td>
            <td>얼음 저장고 UVC 수명</td>
            <td>{{stat.iceTankUvcRemn != 0 ? stat.iceTankUvcRemn+"%" : "교체요망"}}</td>
            <td>
              <button class = "button" v-on:click="doAction('INIT', 'u_ice_r')">초기화</button>
            </td>
          </tr>
          <tr>
            <td>부스터펌프상태</td>
            <td>{{stat.boostStat == "0" ? "정상" : "불량"}}</td>
            <td> </td>
            <td>음료 출수구 UVC 수명</td>
            <td>{{stat.drkEoUvcRemn != 0 ? stat.drkEoUvcRemn+"%" : "교체요망"}}</td>
            <td>
              <button class = "button" v-on:click="doAction('INIT', 'u_drink_r')">초기화</button>
            </td>
          </tr>
          <tr>
            <td>탁도1센서</td>
            <td>{{stat.tbdt1Sensr}}NTU</td>
            <td></td>
            <td>얼음 출수구 UVC 수명</td>
            <td>{{stat.iceEoUvcRemn != 0 ? stat.iceEoUvcRemn+"%" : "교체요망"}}</td>
            <td>
              <button class = "button" v-on:click="doAction('INIT', 'u_ice_o_r')">초기화</button>
            </td>
          </tr>
          <tr>
            <td>탁도2센서</td>
            <td>{{stat.tbdt2Sensr}}NTU</td>
            <td> </td>
            <td>소프트웨어 / 펌웨어</td>
            <td>{{stat.swVer}} / {{stat.fwVer}}</td>
            <td>
              <button class = "button" v-on:click="doAction('UPDATE', 'update')">업데이트</button>
            </td>
          </tr>
          <tr>
            <td>PH센서</td>
            <td>{{isFloat(stat.phSensr) ? parseFloat(stat.phSensr) : ""}}PH</td>
            <td> </td>
            <td> </td>
            <td> </td>
            <td> </td>
          </tr>
          <tr>
            <td>수질1센서</td>
            <td>{{stat.wq1Sensr}}ppm</td>
            <td> </td>
            <td> </td>
            <td> </td>
            <td> </td>
          </tr>
          <tr>
            <td>수질2센서</td>
            <td>{{stat.wq2Sensr}}ppm</td>
            <td> </td>
            <td> </td>
            <td> </td>
            <td> </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<style scoped>

table {
  width: 100%;
  border-collapse: collapse;
}
th, td {
  text-align: center;
  border-bottom: 1px solid #ddd;
}
tr:nth-child(even) {
  background-color: #f2f2f2;
}

.button, .button * {
  background-color: #4A90E2; /* Blue background for search button */
  color: #fff !important; /* White icon */
  padding: 5px 10px;
  text-align: center;
  display: inline-block;
  font-size: 14px;
  margin: 4px 2px;
  transition-duration: 0.2s;
  cursor: pointer;
  border-radius: 4px;
  border: none;

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
  name: "AgencyUserDeviceView",
  components: {
    //HelloWorld
  },
  mounted: function () {
    this.$data.params.mcId = this.$route.query.mcId;
    this.$data.show.menu = true;
    this.getInfo();
    this.getDeviceStat();
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
      },
      stat : {
        iceStat        :"",
        cwStat         :"",
        hwStat         :"",
        spklStat       :"",
        wlStat         :"",
        woStat         :"",
        tbdt1Sensr     :"",
        tbdt2Sensr     :"",
        phSensr        :"",
        wq1Sensr       :"",
        wq2Sensr       :"",
        syrup1Remn     :"",
        syrup2Remn     :"",
        syrup3Remn     :"",
        syrup4Remn     :"",
        gasRchrgStat   :"",
        cwTankUvcRemn  :"",
        spklTankUvcRemn:"",
        iceTankUvcRemn :"",
        drkEoUvcRemn   :"",
        iceEoUvcRemn   :"",
        swVer          :"",
        fwVer          :"",
      },
      show : {
        menu : false,
      },
    };
  },
  methods: {
    move(tp) {
      if(tp == 'emergency') {
        this.$router.push({ name: "agency_user_emergency", query : { "mcId" : this.$data.params.mcId } }); //parameter전달시 path이용불가
      } else if(tp == 'history') {
        this.$router.push({ name: "agency_user_history", query : { "mcId" : this.$data.params.mcId } }); //parameter전달시 path이용불가
      } else if(tp == 'device') {
        this.$router.push({ name: "agency_user_device", query : { "mcId" : this.$data.params.mcId } }); //parameter전달시 path이용불가
      }
    },
    isFloat(val) {
      const regex = new RegExp('^[0-9]+(\\.[0-9]+)?$');
      return regex.test(val);
    },
    async getInfo() {
      post("/api/agency/user/info", this.$data.params)
      .then((res) => this.result(res.data))
      .catch((error) => {
        alert(error);
      });
    },
    result(data) {
      this.$data.info.filters = data.filters;
      this.$data.info.nonfilters = data.nonfilters;
      this.$data.info.emergency = data.emergency;
      this.$data.info.total = data.filters + data.nonfilters;
      this.$data.info.show = true;
    },
    async getDeviceStat() {
      post("/api/agency/user/device/stat", this.$data.params)
      .then((res) => this.resultDeviceStat(res.data))
      .catch((error) => {
        alert(error);
      });
    },
    resultDeviceStat(data) {
      console.log(data);
      this.$data.stat.iceStat         = data.iceStat        ;
      this.$data.stat.cwStat          = data.cwStat         ;
      this.$data.stat.hwStat          = data.hwStat         ;
      this.$data.stat.spklStat        = data.spklStat       ;
      this.$data.stat.wlStat          = data.wlStat         ;
      this.$data.stat.woStat          = data.woStat         ;
      this.$data.stat.hitStat         = data.hitStat        ;
      this.$data.stat.compStat        = data.compStat       ;
      this.$data.stat.boostStat       = data.boostStat      ;
      this.$data.stat.tbdt1Sensr      = data.tbdt1Sensr     ;
      this.$data.stat.tbdt2Sensr      = data.tbdt2Sensr     ;
      this.$data.stat.phSensr         = data.phSensr        ;
      this.$data.stat.wq1Sensr        = data.wq1Sensr       ;
      this.$data.stat.wq2Sensr        = data.wq2Sensr       ;
      this.$data.stat.syrup1Remn      = data.syrup1Remn     ;
      this.$data.stat.syrup2Remn      = data.syrup2Remn     ;
      this.$data.stat.syrup3Remn      = data.syrup3Remn     ;
      this.$data.stat.syrup4Remn      = data.syrup4Remn     ;
      this.$data.stat.gasRchrgStat    = data.gasRchrgStat   ;
      this.$data.stat.cwTankUvcRemn   = data.cwTankUvcRemn  ;
      this.$data.stat.spklTankUvcRemn = data.spklTankUvcRemn;
      this.$data.stat.iceTankUvcRemn  = data.iceTankUvcRemn ;
      this.$data.stat.drkEoUvcRemn    = data.drkEoUvcRemn   ;
      this.$data.stat.iceEoUvcRemn    = data.iceEoUvcRemn   ;
      this.$data.stat.swVer           = data.swVer          ;
      this.$data.stat.fwVer           = data.fwVer          ;
    },
    async doAction(action, value) {
      console.log('action', action, 'value', value);

      if (confirm("설정하시겠습니까?")) {
        post("/api/agency/user/device/command", { "action" : action, "value" : value, "mcId": this.$data.params.mcId })
        .then((res) => this.resultAction(res.data, action, value))
        .catch((error) => {
          alert(error);
        });
      }
    },
    resultAction(data, action, value) {
      console.log('resultAction', data, action, value);

      if (data.cd == '0000' && action == 'INIT') {
        let initValue = 100;
        switch (value) {
          case 's1_r': 
            {{this.$data.stat.syrup1Remn = initValue}}
            break;
          case 's2_r': 
            {{this.$data.stat.syrup2Remn = initValue}}
            break;
          case 's3_r': 
            {{this.$data.stat.syrup3Remn = initValue}}
            break;
          case 's4_r': 
            {{this.$data.stat.syrup4Remn = initValue}}
            break;
          case 'u_cold_r': 
            {{this.$data.stat.cwTankUvcRemn = initValue}}
            break;
          case 'u_ice_r': 
            {{this.$data.stat.iceTankUvcRemn = initValue}}
            break;
          case 'u_carbon_r': 
            {{this.$data.stat.spklTankUvcRemn = initValue}}
            break;
          case 'u_drink_r': 
            {{this.$data.stat.drkEoUvcRemn = initValue}}
            break;
          case 'u_ice_o_r': 
            {{this.$data.stat.iceEoUvcRemn = initValue}}
            break;
        }
      }
      /*
      if(data.cd == "0000") {
        this.$router.push({ path: "/user" });
        alert('변경되었습니다.');
      } else if(data.cd == "1000") {
        alert('패스워드를 다시 확인하여 주세요.');
      } else if(data.cd == "1001") {
        alert('신규 패스워드가 확인 값과 일치하지 않습니다.');
      } else if(data.cd == "1009") {
        alert('잘못된 입력 값입니다.');
      }
      */
    },
  },
};
</script>
