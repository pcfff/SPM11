(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-6bcf173c"],{1599:function(t,a,e){},"6e2c":function(t,a,e){"use strict";e("1599")},"8efd":function(t,a,e){"use strict";e.r(a);var s=function(){var t=this,a=t._self._c;return a("div",{staticClass:"list-wrapper"},[t._l(t.tableList,(function(e){return a("el-card",{key:e.id,staticClass:"data-item"},[a("div",{staticClass:"header"},[a("svg",{staticClass:"icon",attrs:{t:"1702805494389",viewBox:"0 0 1024 1024",version:"1.1",xmlns:"http://www.w3.org/2000/svg","p-id":"8025",width:"32",height:"32"}},[a("path",{attrs:{d:"M61.139079 310.392465q0-26.58137 18.913667-44.983856t45.495036-18.402487l64.408703 0 0 287.283263-64.408703 0q-26.58137 0-45.495036-18.402487t-18.913667-44.983856l0-160.510577zM776.791335 46.623491q43.961496-41.916775 79.744109-46.006216t63.386343 18.913667 47.028577 67.475784 31.693171 99.168955 18.402487 114.504361 6.134162 113.993181-7.667703 108.370199-22.491928 101.724856-34.760252 83.32237-44.472676 53.162739-52.651559 12.268324-58.785721-39.872054q-29.648451-34.760252-80.255289-57.763361t-110.414919-38.849694-121.149703-25.559009-112.45964-17.380126-85.367091-14.824225-38.338514-17.380126l0-319.998794q4.089441-14.313045 32.204352-23.514288t71.565226-17.380126 98.146595-17.891306 111.43728-26.58137 110.9261-42.939135 98.146595-66.964604zM408.741603 673.330395q5.111802 12.268324 12.268324 26.58137 6.134162 13.290685 16.357766 30.670811t24.536649 37.827334q17.380126 24.536649 35.271433 51.629198t27.09255 49.073298 5.622982 36.293793-29.13727 14.313045l-58.274541 0q-20.447207 0-37.827334-7.667703t-33.226712-23.514288-32.715532-40.383234-36.293793-58.274541q-23.514288-38.849694-32.715532-67.986964t-12.268324-48.562117q-4.089441-22.491928 0-38.849694 8.178883 1.02236 19.424847 4.089441 9.201243 2.044721 22.491928 5.111802t30.670811 6.134162q17.380126 4.089441 31.181991 7.667703t25.047829 7.667703q12.268324 4.089441 22.491928 8.178883z","p-id":"8026",fill:"#5BBCC9"}})]),a("div",{staticClass:"header-info-wrapper"},[a("p",{staticClass:"title"},[t._v(t._s(e.title))])])]),a("div",{staticClass:"info-wrapper"},[a("div",{staticClass:"info-item"},[a("span",[t._v("内容：")]),a("span",[t._v(t._s(e.content))])]),a("div",{staticClass:"info-item"},[a("span",[t._v("发布时间：")]),a("span",[t._v(t._s(t.$moment(e.createTime,"yyyy.MM.DD")))])]),a("el-tag",{attrs:{type:"primary"}},[t._v(t._s(e.courseName))])],1)])})),a("el-pagination",{attrs:{background:"",layout:"prev, pager, next",total:t.total},on:{"current-change":t.changePage}})],2)},i=[],n={name:"Index",data(){return{tableList:[],pageInfo:{pageNum:1,pageSize:10},total:0}},methods:{changePage(t){this.pageInfo.pageNum=t,this.getTableList()},getTableList(){this.$axios.get("/notice/list",{params:Object.assign({},this.pageInfo)}).then(t=>{this.tableList=t.data.rows,this.total=t.data.count})}},mounted(){this.getTableList()}},l=n,c=(e("6e2c"),e("2877")),o=Object(c["a"])(l,s,i,!1,null,"31e0e1c6",null);a["default"]=o.exports}}]);
//# sourceMappingURL=chunk-6bcf173c.969bba6d.js.map