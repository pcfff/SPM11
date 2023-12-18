(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d2311e6"],{eed4:function(e,t,r){"use strict";r.r(t);var o=function(){var e=this,t=e._self._c;return t("el-card",{staticClass:"box-card"},[t("el-breadcrumb",{attrs:{separator:"/"}},[t("el-breadcrumb-item",[e._v("作业")]),t("el-breadcrumb-item",[e._v("编辑作业")])],1),t("el-form",{ref:"dataForm",staticStyle:{"margin-top":"20px"},attrs:{model:e.form,rules:e.rule,"label-width":"120px"}},[t("el-form-item",{attrs:{label:"作业名称",prop:"name"}},[t("el-input",{model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}})],1),t("el-form-item",{attrs:{label:"截止时间",prop:"deadline"}},[t("el-date-picker",{attrs:{type:"datetime","value-format":"yyyy-MM-dd HH:mm:ss",placeholder:"选择日期时间"},model:{value:e.form.deadline,callback:function(t){e.$set(e.form,"deadline",t)},expression:"form.deadline"}})],1),t("el-form-item",{attrs:{label:"作业分数",prop:"score"}},[t("el-input",{attrs:{type:"number",disabled:"",readonly:""},model:{value:e.form.score,callback:function(t){e.$set(e.form,"score",t)},expression:"form.score"}})],1),t("el-form-item",{attrs:{label:"课程",prop:"courseId"}},[t("el-select",{on:{change:e.getQuestionList},model:{value:e.form.courseId,callback:function(t){e.$set(e.form,"courseId",t)},expression:"form.courseId"}},e._l(e.courseList,(function(e){return t("el-option",{key:e.id,attrs:{value:e.id,label:e.name}})})),1)],1),t("el-form-item",{attrs:{label:"批改方式",prop:"correct"}},[t("el-select",{model:{value:e.form.correct,callback:function(t){e.$set(e.form,"correct",t)},expression:"form.correct"}},[t("el-option",{attrs:{value:0,label:"自动批改"}}),t("el-option",{attrs:{value:1,label:"手动批改"}})],1)],1),t("el-form-item",{attrs:{label:"类型",prop:"type"}},[t("el-select",{model:{value:e.form.type,callback:function(t){e.$set(e.form,"type",t)},expression:"form.type"}},[t("el-option",{attrs:{value:0,label:"作业"}}),t("el-option",{attrs:{value:1,label:"考试"}})],1)],1),t("el-form-item",{attrs:{label:"选择题",prop:"questionIds"}},[t("el-select",{attrs:{filterable:"",multiple:""},on:{change:e.updateScore},model:{value:e.form.questionIds[0],callback:function(t){e.$set(e.form.questionIds,0,t)},expression:"form.questionIds[0]"}},e._l(e.questionList.filter(e=>1==e.value),(function(r){return t("el-option-group",{key:r.label,attrs:{label:r.label}},e._l(r.options,(function(e){return t("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})})),1)})),1)],1),t("el-form-item",{attrs:{label:"判断题",prop:"questionIds"}},[t("el-select",{attrs:{filterable:"",multiple:""},on:{change:e.updateScore},model:{value:e.form.questionIds[1],callback:function(t){e.$set(e.form.questionIds,1,t)},expression:"form.questionIds[1]"}},e._l(e.questionList.filter(e=>2==e.value),(function(r){return t("el-option-group",{key:r.label,attrs:{label:r.label}},e._l(r.options,(function(e){return t("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})})),1)})),1)],1),t("el-form-item",{attrs:{label:"填空题",prop:"questionIds"}},[t("el-select",{attrs:{filterable:"",multiple:""},on:{change:e.updateScore},model:{value:e.form.questionIds[2],callback:function(t){e.$set(e.form.questionIds,2,t)},expression:"form.questionIds[2]"}},e._l(e.questionList.filter(e=>3==e.value),(function(r){return t("el-option-group",{key:r.label,attrs:{label:r.label}},e._l(r.options,(function(e){return t("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})})),1)})),1)],1),t("el-form-item",[t("el-button",{attrs:{type:"primary"},on:{click:e.onSubmit}},[e._v("保存")]),t("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.$router.back()}}},[e._v("返回")])],1)],1)],1)},s=[],i=(r("14d9"),{data(){return{form:{name:"",deadline:"",score:"",courseId:"",correct:"",type:"",questionIds:[[],[],[]]},questionList:[],questionListOrigin:[],courseList:[],rule:{name:[{required:!0,message:"请输入作业名称",trigger:"blur"}],deadline:[{required:!0,message:"请选择作业截止时间",trigger:"blur"}],score:[{required:!0,message:"请输入作业分数",trigger:"blur"}],courseId:[{required:!0,message:"请选择课程",trigger:"blur"}],correct:[{required:!0,message:"请选择批改方式",trigger:"blur"}],type:[{required:!0,message:"请选择类型",trigger:"blur"}]}}},methods:{onSubmit(){this.$refs.dataForm.validate(e=>{if(e){let e=JSON.parse(JSON.stringify(this.form)),t=[];if(e.questionIds.forEach(e=>{t.push(...e)}),0===t.length)return void this.$message.warning("请先添加题目");e.questionIds=JSON.stringify(t),this.$axios.post("/manage/homework/update",e).then(e=>{e.success?(this.$message({message:"修改成功",type:"success"}),this.$router.back()):this.$message.error(e.message)})}})},getQuestionList(){return this.form.questionIds=[[],[],[]],this.$axios.get("/manage/question/condition-list",{courseId:this.form.courseId}).then(e=>{this.questionListOrigin=JSON.parse(JSON.stringify(e.data));const t={1:"选择题",2:"判断题",3:"填空题"};let r=new Set;e.data.forEach(e=>r.add(e.type)),this.questionList=Array.from(r).map(r=>({label:t[r],value:r,options:e.data.filter(e=>e.type===r)}))})},updateScore(){let e=0;this.form.questionIds.forEach(t=>{t.forEach(t=>{let r=this.questionListOrigin.find(e=>e.id===t);r&&(e+=r.score)})}),this.form.score=e}},mounted(){this.$axios.post("/manage/course/condition-list").then(e=>{this.courseList=e.data}),this.$axios.get("/manage/homework/query?id="+this.$route.query.id).then(async e=>{Object.keys(e.data.model).forEach(t=>{this.form[t]=e.data.model[t]}),await this.getQuestionList(),this.$axios.get("/manage/homework/query-question?homeworkId="+this.$route.query.id).then(e=>{this.form.questionIds[0]=e.data.filter(e=>1===e.type).map(e=>e.id),this.form.questionIds[1]=e.data.filter(e=>2===e.type).map(e=>e.id),this.form.questionIds[2]=e.data.filter(e=>3===e.type).map(e=>e.id),this.updateScore(),this.$forceUpdate()})})}}),a=i,l=r("2877"),n=Object(l["a"])(a,o,s,!1,null,"5a362298",null);t["default"]=n.exports}}]);
//# sourceMappingURL=chunk-2d2311e6.a9c42136.js.map