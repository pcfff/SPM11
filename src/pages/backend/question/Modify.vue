<template>
  <el-card class="box-card">
    <el-breadcrumb separator="/">
      <el-breadcrumb-item>题目</el-breadcrumb-item>
      <el-breadcrumb-item>编辑题目</el-breadcrumb-item>
    </el-breadcrumb>
    <el-form style="margin-top: 20px" ref="dataForm" :model="form" :rules="rule" label-width="80px">
      <el-form-item label="问题" prop="name">
        <el-input v-model="form.name" ></el-input>
      </el-form-item>
      <el-form-item label="问题类型" prop="type">
        <el-select v-model="form.type" @change="form.answer = ''" >
          <el-option :value="1" label="选择题"></el-option>
          <el-option :value="2" label="判断题"></el-option>
          <el-option :value="3" label="填空题"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item v-show="form.type === 1" label="答案" prop="answer">
        <el-select v-model="form.answer" >
          <el-option value="A" label="选项A"></el-option>
          <el-option value="B" label="选项B"></el-option>
          <el-option value="C" label="选项C"></el-option>
          <el-option value="D" label="选项D"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item v-show="form.type === 2" label="答案" prop="answer">
        <el-select v-model="form.answer" >
          <el-option value="是" label="是"></el-option>
          <el-option value="否" label="否"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item v-show="form.type === 3" label="答案" prop="answer">
        <el-input type="textarea" v-model="form.answer" ></el-input>
      </el-form-item>
      <el-form-item v-show="form.type === 1" label="选项A" prop="a">
        <el-input v-model="form.a" ></el-input>
      </el-form-item>
      <el-form-item v-show="form.type === 1" label="选项B" prop="b">
        <el-input v-model="form.b" ></el-input>
      </el-form-item>
      <el-form-item v-show="form.type === 1" label="选项C" prop="c">
        <el-input v-model="form.c" ></el-input>
      </el-form-item>
      <el-form-item v-show="form.type === 1" label="选项D" prop="d">
        <el-input v-model="form.d" ></el-input>
      </el-form-item>
      <el-form-item label="解析" prop="detail">
        <el-input type="textarea" v-model="form.detail" ></el-input>
      </el-form-item>
      <el-form-item label="分数" prop="score">
        <el-input type="number" v-model="form.score" ></el-input>
      </el-form-item>
      <el-form-item label="课程" prop="courseId">
        <el-select v-model="form.courseId" >
          <el-option v-for="item in courseList" :key="item.id" :value="item.id" :label="item.name"></el-option>
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
        id: '',
        name: '',
        type: '',
        answer: '',
        a: '',
        b: '',
        c: '',
        d: '',
        detail: '',
        score: '',
        courseId: ''
      },
      courseList: [],
      rule: {
        name: [{required: true, message: "请输入问题", trigger: 'blur'}],
        type: [{required: true, message: "请选择题目类型", trigger: 'blur'}],
        answer: [{required: true, message: "请输入答案", trigger: 'blur'}],
        score: [{required: true, message: "请输入分数", trigger: 'blur'}],
        courseId: [{required: true, message: "请选择课程", trigger: 'blur'}],
      }
    }
  },
  methods: {
    onSubmit() {
      this.$refs.dataForm.validate((valid) => {
        if (valid) {
          let params = {
            id: this.form.id,
            name: this.form.name,
            type: this.form.type,
            answer: this.form.answer,
            detail: this.form.detail,
            score: this.form.score,
            courseId: this.form.courseId,
            options: JSON.stringify([{name: 'A', value: this.form.a}, {name: 'B', value: this.form.b}, {name: 'C', value: this.form.c}, {name: 'D', value: this.form.d}])
          }
          this.$axios.post('/manage/question/update', params).then(res => {
            if (res.success) {
              this.$message({
                message: '修改成功',
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
    this.$axios.get('/manage/question/query?id=' + this.$route.query.id).then(res =>{
      Object.keys(this.form).forEach(key =>{
        if (res.data.model[key]){
          this.form[key] = res.data.model[key]
        }
      })
      if (res.data.model.options){
        let json = JSON.parse(res.data.model.options)
        this.form.a = json[0].value
        this.form.b = json[1].value
        this.form.c = json[2].value
        this.form.d = json[3].value
      }
    })
    this.$axios.post('/manage/course/condition-list').then(res =>{
      this.courseList = res.data
    })
  }
}
</script>

<style scoped>

</style>

