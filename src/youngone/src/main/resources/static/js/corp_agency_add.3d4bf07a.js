"use strict";(self["webpackChunkvue"]=self["webpackChunkvue"]||[]).push([[437],{2913:function(a,e,s){s.r(e),s.d(e,{default:function(){return V}});var l=s(3396),n=s(9242),t=s(7139);const p={class:"corp_agency_add"},i={key:0,class:"app-bar bg-hero fg-white","data-role":"appbar"},d=(0,l.uE)('<a href="/#/corp" class="brand no-hover"><span style="max-width:200px;white-space:nowrap;" class="mif-home"> 본사 </span></a><ul class="app-bar-menu"><li><a href="/#/corp"><span class="mif-home"> 본사 메인</span></a></li><li><a href="/#/corp/agency/add"><span class="mif-wrench"> 대리점 등록</span></a></li><li><a href="/#/corp/chpw"><span class="mif-key"> 비밀번호 변경</span></a></li><li><a href="/#/corp/user/list"><span class="mif-list"> 제품설치목록</span></a></li><li><a href="/"><span class="mif-exit"> 로그아웃</span></a></li></ul>',2),o=[d],r={class:"container-fluid docs-content",style:{height:"auto !important","margin-top":"60px"}},c=(0,l._)("div",{class:"space",style:{height:"30px"}},null,-1),u=(0,l._)("div",{class:"space",style:{height:"30px"}},null,-1),h=(0,l._)("div",{class:"space",style:{height:"30px"}},null,-1),m=(0,l._)("div",{class:"space",style:{height:"30px"}},null,-1),y=(0,l._)("div",{class:"space",style:{height:"30px"}},null,-1),f=(0,l._)("label",{for:"code",class:"LargeFont"},"임시가입번호",-1),g=(0,l._)("div",{class:"space",style:{height:"30px"}},null,-1),w=(0,l._)("span",{class:"mif-install"}," 대리점 등록",-1),_=[w],v=(0,l._)("div",{class:"space",style:{height:"30px"}},null,-1);function x(a,e,s,d,w,x){return(0,l.wg)(),(0,l.iD)("div",p,[w.show.menu?((0,l.wg)(),(0,l.iD)("div",i,o)):(0,l.kq)("",!0),(0,l._)("div",r,[c,(0,l.wy)((0,l._)("input",{type:"text","data-role":"input","data-prepend":"<span class='mif-profile'>사업자번호</span>",placeholder:"사업자번호를 입력하세요.","onUpdate:modelValue":e[0]||(e[0]=a=>w.input.bsnLcnNo=a)},null,512),[[n.nr,w.input.bsnLcnNo]]),u,(0,l.wy)((0,l._)("input",{type:"text","data-role":"input","data-prepend":"<span class='mif-room'>주소</span>",placeholder:"주소를 입력하세요.","onUpdate:modelValue":e[1]||(e[1]=a=>w.input.addr=a)},null,512),[[n.nr,w.input.addr]]),h,(0,l.wy)((0,l._)("input",{type:"text","data-role":"input","data-prepend":"<span class='mif-phone'>연락처</span>",placeholder:"연락처를 입력하세요.","onUpdate:modelValue":e[2]||(e[2]=a=>w.input.telNo=a)},null,512),[[n.nr,w.input.telNo]]),m,(0,l.wy)((0,l._)("input",{type:"text","data-role":"input","data-prepend":"<span class='mif-user'>담당자</span>",placeholder:"담당자명을 입력하세요.","onUpdate:modelValue":e[3]||(e[3]=a=>w.input.mngrNm=a)},null,512),[[n.nr,w.input.mngrNm]]),y,(0,l._)("div",{id:"hidden",style:(0,t.j5)({display:w.resultDiplay})},[f,(0,l.wy)((0,l._)("input",{id:"code",type:"text",readonly:"",class:"LargeFont","onUpdate:modelValue":e[4]||(e[4]=a=>w.resultCode=a)},null,512),[[n.nr,w.resultCode]])],4),g,(0,l._)("button",{style:{width:"100%"},onClick:e[5]||(e[5]=a=>x.add())},_),v])])}var b=s(7877),N={name:"AgencyAddView",components:{},mounted:function(){this.$data.show.menu=!0},data(){return{resultDiplay:"none",resultCode:"",input:{bsnLcnNo:"",addr:"",telNo:"",mngrNm:""},show:{menu:!1}}},methods:{async add(){console.log("add"),(0,b.v)("/api/corp/agency/add",this.$data.input).then((a=>this.result(a.data))).catch((a=>{console.log(a),this.loginError=!0}))},result(a){console.log("res",a),this.$data.resultDiplay="block",this.$data.resultCode=a.issuedNo}}},k=s(89);const C=(0,k.Z)(N,[["render",x]]);var V=C}}]);
//# sourceMappingURL=corp_agency_add.3d4bf07a.js.map