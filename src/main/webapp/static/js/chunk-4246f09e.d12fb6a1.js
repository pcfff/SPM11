(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-4246f09e"],{"06d8":function(e,t,r){"use strict";r("ceba")},1707:function(e,t,r){e.exports=r.p+"static/img/fileicon.bd9e1224.png"},"473f":function(e,t,r){"use strict";r.r(t);var s=function(){var e=this,t=e._self._c;return t("el-card",{staticClass:"box-card"},[t("el-breadcrumb",{attrs:{separator:"/"}},[t("el-breadcrumb-item",[e._v("课程视频")]),t("el-breadcrumb-item",[e._v("编辑课程视频")])],1),t("el-form",{ref:"dataForm",staticStyle:{"margin-top":"20px"},attrs:{model:e.form,rules:e.rule,"label-width":"80px"}},[t("el-form-item",{attrs:{label:"课程视频名称",prop:"name"}},[t("el-input",{model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}})],1),t("el-form-item",{attrs:{label:"课程视频内容",prop:"content"}},[t("el-input",{attrs:{type:"textarea",rows:"3"},model:{value:e.form.content,callback:function(t){e.$set(e.form,"content",t)},expression:"form.content"}})],1),t("el-form-item",{attrs:{label:"附件",prop:"fileUrl"}},[t("el-upload",{staticClass:"image-uploader",attrs:{action:"/api/upload",accept:"video/*",limit:1,"auto-upload":!0,"show-file-list":!1,"on-success":e.onUploadSuccess,"on-error":e.onUploadError}},[e.form.fileUrl?t("img",{staticClass:"image",attrs:{src:r("1707")}}):t("i",{staticClass:"el-icon-plus image-uploader-icon"})])],1),t("el-form-item",{attrs:{label:"课程",prop:"courseId"}},[t("el-select",{model:{value:e.form.courseId,callback:function(t){e.$set(e.form,"courseId",t)},expression:"form.courseId"}},e._l(e.courseList,(function(e){return t("el-option",{key:e.id,attrs:{value:e.id,label:e.name}})})),1)],1),t("el-form-item",[t("el-button",{attrs:{type:"primary"},on:{click:e.onSubmit}},[e._v("保存")]),t("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.$router.back()}}},[e._v("返回")])],1)],1)],1)},o=[],a={data(){return{form:{name:"",content:"",fileUrl:"",courseId:""},courseList:[],rule:{name:[{required:!0,message:"请输入课程视频名称",trigger:"blur"}],content:[{required:!0,message:"请输入课程视频内容",trigger:"blur"}],fileUrl:[{required:!0,message:"请上传附件",trigger:"blur"}],courseId:[{required:!0,message:"请选择课程",trigger:"blur"}]}}},methods:{onSubmit(){this.$refs.dataForm.validate(e=>{e&&this.$axios.post("/manage/experiment/update",this.form).then(e=>{e.success?(this.$message({message:"添加成功",type:"success"}),this.$router.back()):this.$message.error(e.message)})})},onUploadSuccess(e,t,r){console.log(e),this.form.fileUrl=e.data.filename,this.$message.success("上传成功")},onUploadError(e,t,r){this.$message.error("上传失败")}},mounted(){this.$axios.post("/manage/course/condition-list").then(e=>{this.courseList=e.data}),this.$axios.get("/manage/experiment/query?id="+this.$route.query.id).then(e=>{this.form=e.data.model})}},i=a,l=(r("06d8"),r("2877")),n=Object(l["a"])(i,s,o,!1,null,"02a99f04",null);t["default"]=n.exports},ceba:function(e,t,r){}}]);
//# sourceMappingURL=chunk-4246f09e.d12fb6a1.js.map