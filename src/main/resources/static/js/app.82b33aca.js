(function(e){function t(t){for(var a,o,i=t[0],l=t[1],c=t[2],m=0,f=[];m<i.length;m++)o=i[m],Object.prototype.hasOwnProperty.call(s,o)&&s[o]&&f.push(s[o][0]),s[o]=0;for(a in l)Object.prototype.hasOwnProperty.call(l,a)&&(e[a]=l[a]);u&&u(t);while(f.length)f.shift()();return n.push.apply(n,c||[]),r()}function r(){for(var e,t=0;t<n.length;t++){for(var r=n[t],a=!0,i=1;i<r.length;i++){var l=r[i];0!==s[l]&&(a=!1)}a&&(n.splice(t--,1),e=o(o.s=r[0]))}return e}var a={},s={app:0},n=[];function o(t){if(a[t])return a[t].exports;var r=a[t]={i:t,l:!1,exports:{}};return e[t].call(r.exports,r,r.exports,o),r.l=!0,r.exports}o.m=e,o.c=a,o.d=function(e,t,r){o.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:r})},o.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},o.t=function(e,t){if(1&t&&(e=o(e)),8&t)return e;if(4&t&&"object"===typeof e&&e&&e.__esModule)return e;var r=Object.create(null);if(o.r(r),Object.defineProperty(r,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var a in e)o.d(r,a,function(t){return e[t]}.bind(null,a));return r},o.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return o.d(t,"a",t),t},o.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},o.p="/";var i=window["webpackJsonp"]=window["webpackJsonp"]||[],l=i.push.bind(i);i.push=t,i=i.slice();for(var c=0;c<i.length;c++)t(i[c]);var u=l;n.push(["56d7","chunk-vendors"]),r()})({"56d7":function(e,t,r){"use strict";r.r(t);r("d3b7"),r("e260"),r("e6cf"),r("cca6"),r("a79d");var a=r("2b0e"),s=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{attrs:{id:"app"}},[r("router-view")],1)},n=[],o={name:"App"},i=o,l=r("2877"),c=Object(l["a"])(i,s,n,!1,null,null,null),u=c.exports,m=r("8c4f"),f=function(){var e=this,t=e.$createElement;e._self._c;return e._m(0)},p=[function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",[r("h1",[e._v("Toilet")])])}],d={name:"LoginPage"},v=d,g=Object(l["a"])(v,f,p,!1,null,null,null),h=g.exports,_=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"container"},[r("div",{staticClass:"row justify-content-center"},[r("div",{staticClass:"register-form"},[e._m(0),r("form",{on:{submit:function(t){return t.preventDefault(),e.submitForm(t)}}},[r("div",{directives:[{name:"show",rawName:"v-show",value:e.errorMessage,expression:"errorMessage"}],staticClass:"alert alert-danger failed"},[e._v(e._s(e.errorMessage))]),r("div",{staticClass:"form-group"},[r("label",{attrs:{for:"username"}},[e._v("Username")]),r("input",{directives:[{name:"model",rawName:"v-model",value:e.form.username,expression:"form.username"}],staticClass:"form-control",attrs:{type:"text",id:"username"},domProps:{value:e.form.username},on:{input:function(t){t.target.composing||e.$set(e.form,"username",t.target.value)}}})]),r("div",{staticClass:"form-group"},[r("label",{attrs:{for:"emailAddress"}},[e._v("Email address")]),r("input",{directives:[{name:"model",rawName:"v-model",value:e.form.emailAddress,expression:"form.emailAddress"}],staticClass:"form-control",attrs:{type:"email",id:"emailAddress"},domProps:{value:e.form.emailAddress},on:{input:function(t){t.target.composing||e.$set(e.form,"emailAddress",t.target.value)}}})]),r("div",{staticClass:"form-group"},[r("label",{attrs:{for:"password"}},[e._v("Password")]),r("input",{directives:[{name:"model",rawName:"v-model",value:e.form.password,expression:"form.password"}],staticClass:"form-control",attrs:{type:"password",id:"password"},domProps:{value:e.form.password},on:{input:function(t){t.target.composing||e.$set(e.form,"password",t.target.value)}}})]),r("button",{staticClass:"btn btn-primary btn-block",attrs:{type:"submit"}},[e._v("Create account")]),e._m(1),e._m(2)])])]),e._m(3)])},b=[function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"logo-wrapper"},[r("img",{staticClass:"logo",attrs:{src:"/static/images/korail-cl.png"}}),r("div",{staticClass:"tagline"},[e._v("Open source 화장실 찾기")])])},function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("p",{staticClass:"accept-terms text-muted"},[e._v("By clicking “Create account”, you agree to our "),r("a",{attrs:{href:"#"}},[e._v("terms of service")]),e._v(" and "),r("a",{attrs:{href:"#"}},[e._v("privacy policy")]),e._v(".")])},function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("p",{staticClass:"text-center text-muted"},[e._v("Already have an account? "),r("a",{attrs:{href:"/login"}},[e._v("Sign in")])])},function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("footer",{staticClass:"footer"},[r("span",{staticClass:"copyright"},[e._v("© 2020 ToiletNearStation.com")]),r("ul",{staticClass:"footer-links list-inline float-right"},[r("li",{staticClass:"list-inline-item"},[r("a",{attrs:{href:"#"}},[e._v("About")])]),r("li",{staticClass:"list-inline-item"},[r("a",{attrs:{href:"#"}},[e._v("Terms of Service")])]),r("li",{staticClass:"list-inline-item"},[r("a",{attrs:{href:"#"}},[e._v("Privacy Policy")])]),r("li",{staticClass:"list-inline-item"},[r("a",{attrs:{href:"https://github.com/taskagile/vuejs.spring-boot.mysql",target:"_blank"}},[e._v("GitHub")])])])])}],w=r("bc3a"),y=r.n(w),C={register:function(e){return new Promise((function(t,r){y.a.post("/registrations",e).then((function(e){var r=e.data;t(r)})).catch((function(e){r(e)}))}))}},P={name:"RegisterPage",data:function(){return{form:{username:"",emailAddress:"",password:""},errorMessage:""}},methods:{submitForm:function(){var e=this;C.register(this.form).then((function(){e.$router.push({name:"LoginPage"})})).catch((function(t){e.errorMessage="Failed to register user. Reason: "+(t.message?t.message:"Unknown")+"."}))}}},j=P,x=(r("6c5a"),Object(l["a"])(j,_,b,!1,null,"4e21e06d",null)),O=x.exports;a["a"].use(m["a"]);var $=new m["a"]({mode:"history",base:"/",routes:[{path:"/login",name:"LoginPage",component:h},{path:"/register",name:"Register",component:O}]}),A=r("2f62");a["a"].use(A["a"]);var k=new A["a"].Store({state:{},mutations:{},actions:{},modules:{}});a["a"].config.productionTip=!1,y.a.defaults.baseURL="/api",y.a.defaults.headers.common.Accept="application/json",y.a.interceptors.response.use((function(e){return e}),(function(e){return Promise.reject(e)})),new a["a"]({router:$,store:k,render:function(e){return e(u)}}).$mount("#app")},"6c5a":function(e,t,r){"use strict";var a=r("b074"),s=r.n(a);s.a},b074:function(e,t,r){}});
//# sourceMappingURL=app.82b33aca.js.map