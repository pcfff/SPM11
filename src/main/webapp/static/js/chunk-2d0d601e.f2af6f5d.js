(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d0d601e"],{"715c":function(e,r,t){"use strict";t.r(r);var a=function(){var e=this,r=e._self._c;return r("el-card",{staticClass:"box-card"},[r("el-breadcrumb",{attrs:{separator:"/"}},[r("el-breadcrumb-item",[e._v("题目")]),r("el-breadcrumb-item",[e._v("添加题目")])],1),r("el-form",{ref:"dataForm",staticStyle:{"margin-top":"20px"},attrs:{model:e.form,rules:e.rule,"label-width":"80px"}},[r("el-form-item",{attrs:{label:"问题",prop:"name"}},[r("el-input",{model:{value:e.form.name,callback:function(r){e.$set(e.form,"name",r)},expression:"form.name"}})],1),r("el-form-item",{attrs:{label:"问题类型",prop:"type"}},[r("el-select",{on:{change:function(r){e.form.answer=""}},model:{value:e.form.type,callback:function(r){e.$set(e.form,"type",r)},expression:"form.type"}},[r("el-option",{attrs:{value:1,label:"选择题"}}),r("el-option",{attrs:{value:2,label:"判断题"}}),r("el-option",{attrs:{value:3,label:"填空题"}})],1)],1),r("el-form-item",{directives:[{name:"show",rawName:"v-show",value:1===e.form.type,expression:"form.type === 1"}],attrs:{label:"答案",prop:"answer"}},[r("el-select",{model:{value:e.form.answer,callback:function(r){e.$set(e.form,"answer",r)},expression:"form.answer"}},[r("el-option",{attrs:{value:"A",label:"选项A"}}),r("el-option",{attrs:{value:"B",label:"选项B"}}),r("el-option",{attrs:{value:"C",label:"选项C"}}),r("el-option",{attrs:{value:"D",label:"选项D"}})],1)],1),r("el-form-item",{directives:[{name:"show",rawName:"v-show",value:2===e.form.type,expression:"form.type === 2"}],attrs:{label:"答案",prop:"answer"}},[r("el-select",{model:{value:e.form.answer,callback:function(r){e.$set(e.form,"answer",r)},expression:"form.answer"}},[r("el-option",{attrs:{value:"是",label:"是"}}),r("el-option",{attrs:{value:"否",label:"否"}})],1)],1),r("el-form-item",{directives:[{name:"show",rawName:"v-show",value:3===e.form.type,expression:"form.type === 3"}],attrs:{label:"答案",prop:"answer"}},[r("el-input",{attrs:{type:"textarea"},model:{value:e.form.answer,callback:function(r){e.$set(e.form,"answer",r)},expression:"form.answer"}})],1),r("el-form-item",{directives:[{name:"show",rawName:"v-show",value:1===e.form.type,expression:"form.type === 1"}],attrs:{label:"选项A",prop:"a"}},[r("el-input",{model:{value:e.form.a,callback:function(r){e.$set(e.form,"a",r)},expression:"form.a"}})],1),r("el-form-item",{directives:[{name:"show",rawName:"v-show",value:1===e.form.type,expression:"form.type === 1"}],attrs:{label:"选项B",prop:"b"}},[r("el-input",{model:{value:e.form.b,callback:function(r){e.$set(e.form,"b",r)},expression:"form.b"}})],1),r("el-form-item",{directives:[{name:"show",rawName:"v-show",value:1===e.form.type,expression:"form.type === 1"}],attrs:{label:"选项C",prop:"c"}},[r("el-input",{model:{value:e.form.c,callback:function(r){e.$set(e.form,"c",r)},expression:"form.c"}})],1),r("el-form-item",{directives:[{name:"show",rawName:"v-show",value:1===e.form.type,expression:"form.type === 1"}],attrs:{label:"选项D",prop:"d"}},[r("el-input",{model:{value:e.form.d,callback:function(r){e.$set(e.form,"d",r)},expression:"form.d"}})],1),r("el-form-item",{attrs:{label:"解析",prop:"detail"}},[r("el-input",{attrs:{type:"textarea"},model:{value:e.form.detail,callback:function(r){e.$set(e.form,"detail",r)},expression:"form.detail"}})],1),r("el-form-item",{attrs:{label:"分数",prop:"score"}},[r("el-input",{attrs:{type:"number"},model:{value:e.form.score,callback:function(r){e.$set(e.form,"score",r)},expression:"form.score"}})],1),r("el-form-item",{attrs:{label:"课程",prop:"courseId"}},[r("el-select",{model:{value:e.form.courseId,callback:function(r){e.$set(e.form,"courseId",r)},expression:"form.courseId"}},e._l(e.courseList,(function(e){return r("el-option",{key:e.id,attrs:{value:e.id,label:e.name}})})),1)],1),r("el-form-item",[r("el-button",{attrs:{type:"primary"},on:{click:e.onSubmit}},[e._v("保存")]),r("el-button",{attrs:{type:"primary"},on:{click:function(r){return e.$router.back()}}},[e._v("返回")])],1)],1)],1)},o=[],s={data(){return{form:{name:"",type:"",answer:"",a:"",b:"",c:"",d:"",detail:"",score:"",courseId:""},courseList:[],rule:{name:[{required:!0,message:"请输入问题",trigger:"blur"}],type:[{required:!0,message:"请选择题目类型",trigger:"blur"}],answer:[{required:!0,message:"请输入答案",trigger:"blur"}],score:[{required:!0,message:"请输入分数",trigger:"blur"}],courseId:[{required:!0,message:"请选择课程",trigger:"blur"}]}}},methods:{onSubmit(){this.$refs.dataForm.validate(e=>{if(e){let e={name:this.form.name,type:this.form.type,answer:this.form.answer,detail:this.form.detail,score:this.form.score,courseId:this.form.courseId,options:JSON.stringify([{name:"A",value:this.form.a},{name:"B",value:this.form.b},{name:"C",value:this.form.c},{name:"D",value:this.form.d}])};this.$axios.post("/manage/question/create",e).then(e=>{e.success?(this.$message({message:"添加成功",type:"success"}),this.$router.back()):this.$message.error(e.message)})}})}},mounted(){this.$axios.post("/manage/course/condition-list").then(e=>{this.courseList=e.data})}},l=s,m=t("2877"),i=Object(m["a"])(l,a,o,!1,null,"2a39140a",null);r["default"]=i.exports}}]);
//# sourceMappingURL=chunk-2d0d601e.f2af6f5d.js.map