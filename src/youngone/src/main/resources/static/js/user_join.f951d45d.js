"use strict";(self["webpackChunkvue"]=self["webpackChunkvue"]||[]).push([[794],{9597:function(e,t,a){a.r(t),a.d(t,{default:function(){return x}});var s=a(3396),n=a(9242),c=a(7139);const l={class:"user_join"},i={class:"container",style:{"margin-top":"40px"}},o={"data-role":"panel","data-title-caption":"사용자 회원가입","data-title-icon":"<span class='mif-users'></span>"},d=(0,s._)("div",{class:"space",style:{height:"30px"}},null,-1),u=(0,s._)("div",{class:"space",style:{height:"10px"}},null,-1),p={class:"grid"},r={class:"row"},h={class:"cell"},_=(0,s._)("div",{class:"space",style:{height:"10px"}},null,-1),k=(0,s._)("div",{class:"space",style:{height:"30px"}},null,-1),m=(0,s._)("span",{class:"mif-user-plus"}," 회원 가입",-1),f=[m],g=(0,s._)("div",{class:"space",style:{height:"30px"}},null,-1);function v(e,t,a,m,v,w){return(0,s.wg)(),(0,s.iD)("div",l,[(0,s._)("div",i,[(0,s._)("div",o,[d,(0,s.wy)((0,s._)("input",{autocomplete:"one-time-code",type:"text","data-role":"input","data-prepend":"<span class='mif-barcode'>사용자 가입코드</span>",placeholder:"가입코드를 입력하세요.","onUpdate:modelValue":t[0]||(t[0]=e=>v.input.issuedNo=e),"data-clear-button":"false"},null,512),[[n.nr,v.input.issuedNo]]),u,(0,s._)("div",p,[(0,s._)("div",r,[(0,s._)("div",h,[(0,s.wy)((0,s._)("input",{autocomplete:"off",onKeydown:[t[1]||(t[1]=e=>w.user_check_change()),t[2]||(t[2]=(0,n.D2)((e=>w.user_check()),["enter"]))],type:"text","data-role":"input","data-prepend":"<span class='mif-user'>사용자 아이디</span>",placeholder:"사용할 아이디를 입력하세요.","data-search-button":"true","data-search-button-click":"custom","data-search-button-icon":"","onUpdate:modelValue":t[3]||(t[3]=e=>v.input.id=e),"data-clear-button":"false"},null,544),[[n.nr,v.input.id]]),(0,s._)("button",{id:"user_check",class:(0,c.C_)(["check-button",{"mif-user-check":v.isChecked,"mif-user":!v.isChecked}]),style:(0,c.j5)({color:v.isChecked?"green":"red"}),onClick:t[4]||(t[4]=e=>{w.user_check()})},null,6)])])]),_,(0,s.wy)((0,s._)("input",{autocomplete:"new-password",onKeydown:t[5]||(t[5]=(0,n.D2)((e=>w.user_check()),["enter"])),type:"password","data-role":"input","data-prepend":"<span class='mif-lock'>패스워드</span>",placeholder:"사용할 패스워드를 입력하세요.","onUpdate:modelValue":t[6]||(t[6]=e=>v.input.pw=e),"data-clear-button":"false"},null,544),[[n.nr,v.input.pw]]),k,(0,s._)("button",{style:{width:"100%"},onClick:t[7]||(t[7]=e=>w.join())},f),g])])])}var w=a(7877),y={name:"JoinView",components:{},data(){return{isChecked:!1,input:{id:"",pw:"",nm:"",issuedNo:""},username:""}},mounted:function(){console.log("join mounted"),console.log(document.getElementById("user_check"))},methods:{user_check(){(0,w.v)("/api/join/checkid",{id:this.$data.input.id}).then((()=>{this.$data.isChecked=!0})).catch((e=>{console.log(e)}))},user_check_change(){this.$data.isChecked=!1},async join(){this.$data.isChecked?""!=this.$data.input.pw?6==this.$data.input.issuedNo.length?(console.log("add"),(0,w.v)("/api/join/user",this.$data.input).then((e=>this.result(e.data))).catch((e=>{console.log(e),alert(e)}))):alert("6자리 사용자 가입코드를 입력해주세요."):alert("패스워드를 입력해주세요."):alert("id 가능 여부를 확인해주세요.")},result(e){"0000"==e.cd?(this.$router.push({path:"/"}),alert("등록되었습니다.")):"1000"==e.cd&&alert("잘못된 사용자 가입코드")}}},b=a(89);const C=(0,b.Z)(y,[["render",v]]);var x=C}}]);
//# sourceMappingURL=user_join.f951d45d.js.map