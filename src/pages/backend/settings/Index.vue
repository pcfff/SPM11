<template>
  <el-card class="box-card">
    <el-breadcrumb separator="/">
      <el-breadcrumb-item>系统设置</el-breadcrumb-item>
    </el-breadcrumb>
    <el-form style="margin-top: 20px" ref="dataForm" :model="form" label-width="80px">
      <el-form-item label="网站登录" prop="title">
        <el-radio v-model="form.dictValue" label="1" border>开启</el-radio>
        <el-radio v-model="form.dictValue" label="0" border>关闭</el-radio>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">保存</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
export default {
  data() {
    return {
      form: {
        dictKey: 'enable_login',
        dictValue: '',
      },
    }
  },
  methods: {
    onSubmit() {
      this.$refs.dataForm.validate((valid) => {
        if (valid) {
          this.$axios.post('/manage/dict/update', this.form).then(res => {
            if (res.success) {
              this.$message({
                message: '修改成功',
                type: 'success'
              });
            } else {
              this.$message.error(res.message);
            }
          })
        }
      });
    },
  },
  mounted() {
    this.$axios.get('/manage/dict/query?id=' + this.form.dictKey).then(res =>{
      this.form = res.data
    })
  }
}
</script>

<style scoped>


</style>

