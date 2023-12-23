<template>
  <el-card class="box-card">
    <el-breadcrumb separator="/">
      <el-breadcrumb-item>课程视频</el-breadcrumb-item>
      <el-breadcrumb-item>添加课程视频</el-breadcrumb-item>
    </el-breadcrumb>
    <el-form style="margin-top: 20px" ref="dataForm" :model="form" :rules="rule" label-width="80px">
      <el-form-item label="课程视频名称" prop="name">
        <el-input v-model="form.name" ></el-input>
      </el-form-item>
      <el-form-item label="课程视频内容" prop="content">
        <el-input type="textarea" rows="3" v-model="form.content" ></el-input>
      </el-form-item>
      <el-form-item label="附件" prop="fileUrl">
        <el-upload
            class="image-uploader"
            action="/api/upload"
            accept="video/*"
            :limit="1"
            :auto-upload="true"
            :show-file-list="false"
            :on-success="onUploadSuccess"
            :on-error="onUploadError"
        >
          <img v-if="form.fileUrl" src="../../../assets/fileicon.png" class="image">
          <i v-else class="el-icon-plus image-uploader-icon"></i>
        </el-upload>
      </el-form-item>
      <el-form-item label="课程" prop="courseId">
        <el-select v-model="form.courseId">
          <el-option v-for="item in courseList" :value="item.id" :label="item.name" :key="item.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">保存</el-button>
        <el-button type="primary" @click="$router.back()">返回</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
export default {
  data() {
    return {
      form: {
        name: '',
        content: '',
        fileUrl: '',
        courseId: '',
      },
      courseList: [],
      rule: {
        name: [{required: true, message: "请输入课程视频名称", trigger: 'blur'}],
        content: [{required: true, message: "请输入课程视频内容", trigger: 'blur'}],
        fileUrl: [{required: true, message: "请上传附件", trigger: 'blur'}],
        courseId: [{required: true, message: "请选择课程", trigger: 'blur'}],
      }
    }
  },
  methods: {
    onSubmit() {
      this.$refs.dataForm.validate((valid) => {
        if (valid) {
          this.$axios.post('/manage/experiment/create', this.form).then(res => {
            if (res.success) {
              this.$message({
                message: '添加成功',
                type: 'success'
              });
              this.$router.back();
            } else {
              this.$message.error(res.message);
            }
          })
        }
      });
    },
    onUploadSuccess(response, file, fileList) {
      console.log(response)
      this.form.fileUrl = response.data.filename
      this.$message.success('上传成功')
    },
    onUploadError(err, file, fileList) {
      this.$message.error('上传失败')
    }
  },
  mounted() {
    this.$axios.post('/manage/course/condition-list').then(res =>{
      this.courseList = res.data
    })
  }
}
</script>

<style scoped>
.image-uploader ::v-deep .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.image-uploader ::v-deep .el-upload:hover {
  border-color: #409EFF;
}

.image-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.image {
  width: 178px;
  height: 178px;
  display: block;
}
</style>

