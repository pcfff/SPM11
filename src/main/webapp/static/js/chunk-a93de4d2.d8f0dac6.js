(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-a93de4d2"],{1707:function(e,r,t){e.exports=t.p+"static/img/fileicon.bd9e1224.png"},"9ef4":function(e,r,t){"use strict";t.r(r);var s=function(){var e=this,r=e._self._c;return r("el-card",{staticClass:"box-card"},[r("el-breadcrumb",{attrs:{separator:"/"}},[r("el-breadcrumb-item",[e._v("课程资料")]),r("el-breadcrumb-item",[e._v("编辑课程资料")])],1),r("el-form",{ref:"dataForm",staticStyle:{"margin-top":"20px"},attrs:{model:e.form,rules:e.rule,"label-width":"80px"}},[r("el-form-item",{attrs:{label:"课程资料名称",prop:"name"}},[r("el-input",{model:{value:e.form.name,callback:function(r){e.$set(e.form,"name",r)},expression:"form.name"}})],1),r("el-form-item",{attrs:{label:"课程资料内容",prop:"details"}},[r("el-input",{attrs:{type:"textarea",rows:"3"},model:{value:e.form.details,callback:function(r){e.$set(e.form,"details",r)},expression:"form.details"}})],1),r("el-form-item",{attrs:{label:"附件",prop:"filename"}},[r("el-upload",{staticClass:"image-uploader",attrs:{limit:1,"auto-upload":!0,"show-file-list":!1,"on-success":e.onUploadSuccess,"on-error":e.onUploadError}},[e.form.filename?r("img",{staticClass:"image",attrs:{src:t("1707")}}):r("i",{staticClass:"el-icon-plus image-uploader-icon"})])],1),r("el-form-item",{attrs:{label:"课程",prop:"courseId"}},[r("el-select",{model:{value:e.form.courseId,callback:function(r){e.$set(e.form,"courseId",r)},expression:"form.courseId"}},e._l(e.courseList,(function(e){return r("el-option",{key:e.id,attrs:{value:e.id,label:e.name}})})),1)],1),r("el-form-item",[r("el-button",{attrs:{type:"primary"},on:{click:e.onSubmit}},[e._v("保存")]),r("el-button",{attrs:{type:"primary"},on:{click:function(r){return e.$router.back()}}},[e._v("返回")])],1)],1)],1)},a=[],o={data(){return{form:{name:"",details:"",filename:"",courseId:""},courseList:[],rule:{name:[{required:!0,message:"请输入课程资料名称",trigger:"blur"}],details:[{required:!0,message:"请输入课程资料内容",trigger:"blur"}],filename:[{required:!0,message:"请上传附件",trigger:"blur"}],courseId:[{required:!0,message:"请选择课程",trigger:"blur"}]}}},methods:{onSubmit(){this.$refs.dataForm.validate(e=>{e&&this.$axios.post("/manage/course-resource/update",this.form).then(e=>{e.success?(this.$message({message:"添加成功",type:"success"}),this.$router.back()):this.$message.error(e.message)})})},onUploadSuccess(e,r,t){console.log(e),this.form.filename=e.data.filename,this.$message.success("上传成功")},onUploadError(e,r,t){this.$message.error("上传失败")}},mounted(){this.$axios.post("/manage/course/condition-list").then(e=>{this.courseList=e.data}),this.$axios.get("/manage/course-resource/query?id="+this.$route.query.id).then(e=>{this.form=e.data})}},i=o,l=(t("c052"),t("2877")),u=Object(l["a"])(i,s,a,!1,null,"be8d14da",null);r["default"]=u.exports},c052:function(e,r,t){"use strict";t("ec86")},ec86:function(e,r,t){}}]);
//# sourceMappingURL=chunk-a93de4d2.d8f0dac6.js.map