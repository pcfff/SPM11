<template>
  <el-card class="box-card">
    <el-form ref="dataForm" label-width="80px" :model="form" :rules="rule">
      <el-form-item label="用户名" prop="username">
        <el-input disabled readonly v-model="form.username" ></el-input>
      </el-form-item>
      <el-form-item label="姓名" prop="name">
        <el-input v-model="form.name" ></el-input>
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-select v-model="form.sex">
          <el-option :value="1" label="男"></el-option>
          <el-option :value="2" label="女"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="年龄" prop="age">
        <el-input type="number" v-model="form.age" ></el-input>
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input v-model="form.phone" ></el-input>
      </el-form-item>
      <el-form-item label="工号" prop="num">
        <el-input v-model="form.num" ></el-input>
      </el-form-item>
      <el-form-item label="专业" prop="major">
        <el-input v-model="form.major" ></el-input>
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
        username: '',
        name: '',
        sex: '',
        num: '',
        age: '',
        phone: '',
        major: '',
      },
      rule: {
        name: [{required: true, message: "请输入姓名", trigger: 'blur'}],
        sex: [{required: true, message: "请选择性别", trigger: 'blur'}],
        num: [{required: true, message: "请输入工号", trigger: 'blur'}],
        age: [{required: true, message: "请输入年龄", trigger: 'blur'}],
        major: [{required: true, message: "请输入专业", trigger: 'blur'}],
        phone: [{required: true, message: "请输入手机号", trigger: 'blur'}],
        username: [{required: true, message: "请输入用户名", trigger: 'blur'}],
      }
    }
  },
  methods: {
    onSubmit() {
      this.$refs.dataForm.validate((valid) => {
        if (valid) {
          this.$axios.post('/manage/teacher/update-self', this.form).then(res => {
            if (res.success) {
              this.$message({
                message: '修改成功',
                type: 'success'
              })
            } else {
              this.$message.error(res.message);
            }
          })
        }
      });
    }
  },
  mounted() {
    this.$axios.get('/manage/teacher/info').then(res =>{
      Object.keys(this.form).forEach(key =>{
        if (res.data.model && res.data.model[key]){
          this.form[key] = res.data.model[key]
        }
      })
      this.form.username = res.data.username
    })
  }
}
</script>

<style scoped>

</style>

