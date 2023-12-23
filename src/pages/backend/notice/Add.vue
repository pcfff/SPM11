<template>
  <el-card class="box-card">
    <el-breadcrumb separator="/">
      <el-breadcrumb-item>通知管理</el-breadcrumb-item>
      <el-breadcrumb-item>添加通知</el-breadcrumb-item>
    </el-breadcrumb>
    <el-form style="margin-top: 20px" ref="dataForm" :model="form" :rules="rule" label-width="80px">
      <el-form-item label="通知标题" prop="title">
        <el-input v-model="form.name" ></el-input>
      </el-form-item>
      <el-form-item label="通知内容" prop="content">
        <el-input type="textarea" rows="3" v-model="form.content" ></el-input>
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
        courseId: '',
      },
      courseList: [],
      rule: {
        title: [{required: true, message: "请输入通知标题", trigger: 'blur'}],
        content: [{required: true, message: "请输入通知内容", trigger: 'blur'}],
        courseId: [{required: true, message: "请选择课程", trigger: 'blur'}],
      }
    }
  },
  methods: {
    onSubmit() {
      this.$refs.dataForm.validate((valid) => {
        if (valid) {
          this.$axios.post('/manage/notice/create', this.form).then(res => {
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

