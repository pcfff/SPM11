(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-3ec4ec7c"],{4225:function(e,t,a){"use strict";a("f663")},a835:function(e,t,a){"use strict";a.r(t);a("14d9");var r=function(){var e=this,t=e._self._c;return t("el-card",{staticClass:"box-card"},[t("el-breadcrumb",{attrs:{separator:"/"}},[t("el-breadcrumb-item",[e._v("课程资料管理")]),t("el-breadcrumb-item",[e._v("数据列表")])],1),t("el-form",{staticClass:"demo-form-inline",attrs:{inline:!0}},[t("el-form-item",{attrs:{label:"课程资料名称"}},[t("el-input",{attrs:{placeholder:"请输入课程资料名称"},model:{value:e.param.name,callback:function(t){e.$set(e.param,"name",t)},expression:"param.name"}})],1),t("el-form-item",[t("el-button",{attrs:{type:"primary"},on:{click:e.searchForm}},[e._v("搜索")])],1)],1),t("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(t){return e.$router.push("/main/course-resource/add")}}},[e._v("添加课程资料")]),t("el-button",{attrs:{type:"danger",size:"mini"},on:{click:e.batchDel}},[e._v("批量删除")]),t("el-table",{ref:"multipleTable",staticStyle:{width:"100%","margin-top":"10px"},attrs:{border:"",data:e.tableList,"tooltip-effect":"dark"},on:{"selection-change":e.handleSelectionChange}},[t("el-table-column",{attrs:{type:"selection",width:"55"}}),t("el-table-column",{attrs:{align:"center",label:"ID",prop:"id",sortable:""}}),t("el-table-column",{attrs:{align:"center",label:"课程资料名称",prop:"name"}}),t("el-table-column",{attrs:{align:"center",label:"附件",prop:"filename"},scopedSlots:e._u([{key:"default",fn:function(a){return[t("el-link",{attrs:{type:"primary",target:"_blank",href:"/api"+a.row.filename}},[e._v(e._s(a.row.filename))])]}}])}),t("el-table-column",{attrs:{align:"center",label:"课程资料内容",prop:"details"}}),t("el-table-column",{attrs:{align:"center",label:"课程名称",prop:"courseName"}}),t("el-table-column",{attrs:{align:"center",label:"发布时间",prop:"createTime"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(" "+e._s(e.$moment(t.row.createTime,"YYYY-MM-DD HH:mm:ss"))+" ")]}}])}),t("el-table-column",{attrs:{align:"left",label:"操作",width:"180"},scopedSlots:e._u([{key:"default",fn:function(a){return[t("el-button",{attrs:{size:"mini",type:"primary"},on:{click:function(t){return e.$router.push("/main/course-resource/modify?id="+a.row.id)}}},[e._v("修改 ")]),t("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(t){return e.removeThis(a.row.id)}}},[e._v("删除")])]}}])})],1),t("el-pagination",{attrs:{background:"",layout:"prev, pager, next",total:e.total},on:{"current-change":e.changePage}})],1)},l=[],n={data(){return{param:{name:""},tableList:[],pageInfo:{pageNum:1,pageSize:10},total:0,multipleSelection:[]}},mounted(){this.getTableList(this.pageInfo)},methods:{getTableList(){this.$axios.get("/manage/course-resource/list",{params:Object.assign(this.param,this.pageInfo)}).then(e=>{this.tableList=e.data.rows,this.total=e.data.count})},searchForm(){this.pageInfo.pageNum=1,this.getTableList()},changePage(e){this.pageInfo.pageNum=e,this.getTableList()},removeThis(e){this.$confirm("是否确定删除？").then(()=>{this.$axios.get("/manage/course-resource/remove?id="+e).then(e=>{e.success?(this.$message.success("删除成功！"),this.getTableList()):this.$message.error("删除失败！")})})},handleSelectionChange(e){this.multipleSelection=e},batchDel(){this.$confirm("是否确定删除？").then(async()=>{for(let e of this.multipleSelection)await this.$axios.get("/manage/course-resource/remove?id="+e.id);this.$message.success("删除成功！"),this.getTableList()})}}},i=n,s=(a("4225"),a("2877")),o=Object(s["a"])(i,r,l,!1,null,"974f1c70",null);t["default"]=o.exports},f663:function(e,t,a){}}]);
//# sourceMappingURL=chunk-3ec4ec7c.e1c493db.js.map