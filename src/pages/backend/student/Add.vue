<template>
  <el-card class="box-card">
    <el-breadcrumb separator="/">
      <el-breadcrumb-item>学生</el-breadcrumb-item>
      <el-breadcrumb-item>添加学生</el-breadcrumb-item>
    </el-breadcrumb>
    <el-form style="margin-top: 20px" ref="dataForm" :model="form" :rules="rule" label-width="80px">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="form.username" ></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input type="password" v-model="form.password" ></el-input>
      </el-form-item>
      <el-form-item label="姓名" prop="name">
        <el-input v-model="form.name" ></el-input>
      </el-form-item>
      <el-form-item label="学号" prop="num">
        <el-input v-model="form.num" ></el-input>
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-select v-model="form.sex">
          <el-option :value="1" label="男"></el-option>
          <el-option :value="2" label="女"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input v-model="form.phone" ></el-input>
      </el-form-item>
      <el-form-item label="年级" prop="grade">
        <el-input  v-model="form.grade" ></el-input>
      </el-form-item>
      <el-form-item label="年龄" prop="age">
        <el-input type="number" v-model="form.age" ></el-input>
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
        age: '',
        sex: '',
        grade: '',
        username: '',
        password: '',
        phone: '',
        major: '',
        num: ''
      },
      rule: {
        name: [{required: true, message: "请输入姓名", trigger: 'blur'}],
        sex: [{required: true, message: "请选择性别", trigger: 'blur'}],
        num: [{required: true, message: "请输入学号", trigger: 'blur'}],
        age: [{required: true, message: "请输入年龄", trigger: 'blur'}],
        grade: [{required: true, message: "请输入年级", trigger: 'blur'}],
        phone: [{required: true, message: "请输入手机号", trigger: 'blur'}],
        major: [{required: true, message: "请输入专业", trigger: 'blur'}],
        username: [{required: true, message: "请输入用户名", trigger: 'blur'}],
        password: [{required: true, message: "请输入密码", trigger: 'blur'}],
      }
    }
  },
  methods: {
    onSubmit() {
      this.$refs.dataForm.validate((valid) => {
        if (valid) {
          this.$axios.post('/manage/student/create', this.form).then(res => {
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
    }
  },
  mounted() {

  }
}
</script>

<style scoped>

</style>

