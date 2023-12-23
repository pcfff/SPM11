<template>
  <el-card class="box-card">
    <el-breadcrumb separator="/">
      <el-breadcrumb-item>课程管理</el-breadcrumb-item>
      <el-breadcrumb-item>编辑课程</el-breadcrumb-item>
    </el-breadcrumb>
    <el-form style="margin-top: 20px" ref="dataForm" :model="form" :rules="rule" label-width="80px">
      <el-form-item label="课程名称" prop="name">
        <el-input v-model="form.name" ></el-input>
      </el-form-item>
      <el-form-item label="课时" prop="period">
        <el-input type="number" v-model="form.period" ></el-input>
      </el-form-item>
      <el-form-item label="学分" prop="score">
        <el-input type="number" v-model="form.score" ></el-input>
      </el-form-item>
      <el-form-item label="专业" prop="major">
        <el-input v-model="form.major" ></el-input>
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
        period: '',
        score: '',
        major: '',
      },
      rule: {
        name: [{required: true, message: "请输入课程名称", trigger: 'blur'}],
        period: [{required: true, message: "请输入课时", trigger: 'blur'}],
        score: [{required: true, message: "请输入学分", trigger: 'blur'}],
        major: [{required: true, message: "请输入专业", trigger: 'blur'}],
      }
    }
  },
  methods: {
    onSubmit() {
      this.$refs.dataForm.validate((valid) => {
        if (valid) {
          this.$axios.post('/manage/course/update', this.form).then(res => {
            if (res.success) {
              this.$message({
                message: '修改成功',
                type: 'success'
              });
              this.$router.back();
            } else {
              this.$message.error(res.msg);
            }
          })
        }
      });
    }
  },
  mounted() {
    this.$axios.get('/manage/course/query?id=' + this.$route.query.id).then(res =>{
      this.form = res.data.model
    })
  }
}
</script>

<style scoped>

</style>

