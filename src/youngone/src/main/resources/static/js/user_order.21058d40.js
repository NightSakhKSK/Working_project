"use strict";(self["webpackChunkvue"]=self["webpackChunkvue"]||[]).push([[322],{554:function(e,l,s){s.r(l),s.d(l,{default:function(){return q}});var t=s(3396),a=s(7139),i=s(9242);const r={class:"user_order"},d={key:0,class:"app-bar bg-hero fg-white","data-role":"appbar"},n={href:"/#/user",class:"brand no-hover"},o={style:{"max-width":"200px","white-space":"nowrap"},class:"mif-home"},c=(0,t.uE)('<ul class="app-bar-menu"><li><a href="/#/user/chpw"><span class="mif-key"> 비밀번호 변경</span></a></li><li><a href="/#/user/device"><span class="mif-phonelink-setup"> 디바이스 상태</span></a></li><li><a href="/#/user/emergency/list"><span class="mif-phonelink-setup"> 긴급호출 이력</span></a></li><li><a href="/"><span class="mif-exit"> 로그아웃</span></a></li></ul>',1),u={class:"container-fluid docs-content",style:{height:"auto !important","margin-top":"60px"}},f={"data-role":"panel","data-title-caption":"Total Order <span id='ordercnt'>0</span>","data-title-icon":"<span class='mif-news'></span>"},p={class:"grid"},v=(0,t.uE)('<div class="row"><div class="cell"><div class="d-flex flex-justify-center" style="font-weight:bold;"> Cylinder &amp; Syrups </div></div><div class="cell"><div class="d-flex flex-justify-center" style="font-weight:bold;"> Filters </div></div><div class="cell"><div class="d-flex flex-justify-center" style="color:red;font-weight:bold;"> Emergency </div></div></div>',1),h={class:"row"},m={class:"cell"},y={class:"d-flex flex-justify-center"},g={class:"cell"},w={class:"d-flex flex-justify-center"},x={class:"cell"},_={class:"d-flex flex-justify-center",style:{color:"red","font-weight":"bold"}},b={class:"grid orderlist",style:{"padding-top":"20px"}},C=(0,t._)("div",{class:"row"},[(0,t._)("div",{class:"cell"},[(0,t._)("div",{class:"d-flex flex-justify-start",style:{"font-weight":"bold","font-size":"20px"}}," ORDER ")])],-1),k={class:"cell"},O={class:"vname d-flex flex-justify-start","data-name":"{{item.name}}"},j={class:"cell"},$={class:"d-flex flex-justify-end"},E=["onClick"],z=["onUpdate:modelValue"],I=["onClick"],S={class:"grid",style:{"padding-top":"10px"}},B=(0,t._)("div",{class:"row"},[(0,t._)("div",{class:"cell-2"},[(0,t._)("div",{class:"d-flex flex-justify-start",style:{"font-weight":"bold","font-size":"20px"}}," TOTAL ")])],-1),D={class:"row"},N=(0,t._)("div",{class:"cell"},[(0,t._)("div",{id:"resultBox",style:{"font-weight":"bold"}})],-1),R={class:"cell",style:{padding:"0px 0px"}},T={class:"d-flex flex-justify-end"};function F(e,l,s,F,V,A){return(0,t.wg)(),(0,t.iD)("div",r,[V.show.menu?((0,t.wg)(),(0,t.iD)("div",d,[(0,t._)("a",n,[(0,t._)("span",o,(0,a.zw)(" "+V.titleNm),1)]),c])):(0,t.kq)("",!0),(0,t._)("div",u,[(0,t._)("div",f,[(0,t._)("div",p,[v,(0,t._)("div",h,[(0,t._)("div",m,[(0,t._)("div",y,(0,a.zw)(V.nonfilters),1)]),(0,t._)("div",g,[(0,t._)("div",w,(0,a.zw)(V.filters),1)]),(0,t._)("div",x,[(0,t._)("div",_,(0,a.zw)(V.emergency),1)])])])]),(0,t._)("div",b,[C,((0,t.wg)(!0),(0,t.iD)(t.HY,null,(0,t.Ko)(V.items,(e=>((0,t.wg)(),(0,t.iD)("div",{class:"row",key:e.id},[(0,t._)("div",k,[(0,t._)("div",O,(0,a.zw)(e.name),1)]),(0,t._)("div",j,[(0,t._)("div",$,[(0,t._)("button",{class:"vchange",onClick:l=>A.minus(e)},"-",8,E),(0,t.wy)((0,t._)("input",{"onUpdate:modelValue":l=>e.value=l,class:"valuebox",type:"number"},null,8,z),[[i.nr,e.value]]),(0,t._)("button",{class:"vchange",onClick:l=>A.plus(e)},"+",8,I)])])])))),128))]),(0,t._)("div",S,[B,(0,t._)("div",D,[N,(0,t._)("div",R,[(0,t._)("div",T,[(0,t._)("button",{id:"btnOrder",onClick:l[0]||(l[0]=e=>A.order()),disabled:""},"주문")])])])])])])}var V=s(7877),A={name:"OrdeView",components:{},mounted:function(){this.$data.show.menu=!0,this.getInfo()},data(){return{titleNm:"",filters:0,nonfilters:0,emergency:0,items:[{id:"Cylinder",category:"Cylinder",name:"Cylinder",value:0},{id:"Strawberry",category:"Syrup",name:"딸기",value:0},{id:"Mint",category:"Syrup",name:"민트",value:0},{id:"Herb",category:"Syrup",name:"허브",value:0},{id:"Cola",category:"Syrup",name:"콜라",value:0},{id:"Filter",category:"Filter",name:"필터",value:0}],orders:[],show:{menu:!1}}},methods:{async getInfo(){(0,V.v)("/api/user/info",null).then((e=>this.result(e.data))).catch((e=>{alert(e)}))},result(e){console.log(e),this.$data.filters=e.filters,this.$data.nonfilters=e.nonfilters,this.$data.emergency=e.emergency,this.$data.titleNm=e.mcId;let l=document.getElementById("ordercnt");l.textContent=e.filters+e.nonfilters},gotoOrder(){this.$router.push({path:"/user/order"})},gotoEmergency(){this.$router.push({path:"/user/emergency"})},plus(e){console.log("plus",e),e.value=e.value+1,this.calcOrder()},minus(e){console.log("minus",e),e.value>0&&(e.value=e.value-1),this.calcOrder()},getOrders(){let e=[];for(let l in this.items){let s=this.items[l];s.value>0&&e.push(s)}return e},order(){let e=this.getOrders();e.length<1?alert("주문내역이 없습니다."):(0,V.v)("/api/user/order",this.$data.orders).then((e=>this.orderResult(e.data))).catch((e=>{alert(e)}))},orderResult(e){console.log(e),this.$router.push({path:"/user"})},calcOrder(){let e=this.getOrders(),l=document.getElementById("resultBox");this.$data.orders=[];while(l.firstChild)l.removeChild(l.firstChild);document.getElementById("btnOrder").disabled=e.length<1;for(let s=0;s<e.length;s++){let t=document.createElement("div");t.appendChild(document.createTextNode(e[s].name+" "+e[s].value+"EA")),l.appendChild(t),this.$data.orders.push(e[s])}}}},H=s(89);const U=(0,H.Z)(A,[["render",F]]);var q=U}}]);
//# sourceMappingURL=user_order.21058d40.js.map