<template>
  <el-card class="box-card">
    <el-breadcrumb separator="/">
      <el-breadcrumb-item>作业</el-breadcrumb-item>
      <el-breadcrumb-item>编辑作业</el-breadcrumb-item>
    </el-breadcrumb>
    <el-form style="margin-top: 20px" ref="dataForm" :model="form" :rules="rule" label-width="120px">
      <el-form-item label="作业名称" prop="name">
        <el-input v-model="form.name" ></el-input>
      </el-form-item>
      <el-form-item label="截止时间" prop="deadline">
        <el-date-picker
            v-model="form.deadline"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="选择日期时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="作业分数" prop="score">
        <el-input type="number" disabled readonly v-model="form.score" ></el-input>
      </el-form-item>
      <el-form-item label="课程" prop="courseId">
        <el-select v-model="form.courseId" @change="getQuestionList">
          <el-option v-for="item in courseList" :value="item.id" :label="item.name" :key="item.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="批改方式" prop="correct">
        <el-select v-model="form.correct" >
          <el-option :value="0" label="自动批改"></el-option>
          <el-option :value="1" label="手动批改"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="类型" prop="type">
        <el-select v-model="form.type" >
          <el-option :value="0" label="作业"></el-option>
          <el-option :value="1" label="考试"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="选择题" prop="questionIds">
        <el-select v-model="form.questionIds[0]" @change="updateScore" filterable multiple>
          <el-option-group
              v-for="group in questionList.filter(item => item.value == 1)"
              :key="group.label"
              :label="group.label">
            <el-option
                v-for="item in group.options"
                :key="item.id"
                :label="item.name"
                :value="item.id">
            </el-option>
          </el-option-group>
        </el-select>
      </el-form-item>

      <el-form-item label="判断题" prop="questionIds">
        <el-select v-model="form.questionIds[1]" @change="updateScore" filterable multiple>
          <el-option-group
              v-for="group in questionList.filter(item => item.value == 2)"
              :key="group.label"
              :label="group.label">
            <el-option
                v-for="item in group.options"
                :key="item.id"
                :label="item.name"
                :value="item.id">
            </el-option>
          </el-option-group>
        </el-select>
      </el-form-item>

      <el-form-item label="填空题" prop="questionIds">
        <el-select v-model="form.questionIds[2]" @change="updateScore" filterable multiple>
          <el-option-group
              v-for="group in questionList.filter(item => item.value == 3)"
              :key="group.label"
              :label="group.label">
            <el-option
                v-for="item in group.options"
                :key="item.id"
                :label="item.name"
                :value="item.id">
            </el-option>
          </el-option-group>
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

/* eslint-disable */

export default {
  data() {
    return {
      form: {
        name: '',
        deadline: '',
        score: '',
        courseId: '',
        correct: '',
        type: '',
        questionIds: [
            [], [], []
        ],
      },
      questionList: [],
      questionListOrigin: [],
      courseList: [],
      rule: {
        name: [{required: true, message: "请输入作业名称", trigger: 'blur'}],
        deadline: [{required: true, message: "请选择作业截止时间", trigger: 'blur'}],
        score: [{required: true, message: "请输入作业分数", trigger: 'blur'}],
        courseId: [{required: true, message: "请选择课程", trigger: 'blur'}],
        correct: [{required: true, message: "请选择批改方式", trigger: 'blur'}],
        type: [{required: true, message: "请选择类型", trigger: 'blur'}],
        // questionIds: [{required: true, message: "请选择作业题目", trigger: 'blur'}],
      }
    }
  },
  methods: {
    onSubmit() {
      this.$refs.dataForm.validate((valid) => {
        if (valid) {
          let param = JSON.parse(JSON.stringify(this.form))
          let questionIds = []
          param.questionIds.forEach(item =>{
            questionIds.push(...item)
          })
          if (questionIds.length === 0){
            this.$message.warning('请先添加题目')
            return
          }
          param.questionIds = JSON.stringify(questionIds)
          this.$axios.post('/manage/homework/update', param).then(res => {
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
    },
    getQuestionList(){
      this.form.questionIds = [
          [], [], []
      ]
      return this.$axios.get('/manage/question/condition-list', {
        courseId: this.form.courseId
      }).then(res =>{
        this.questionListOrigin = JSON.parse(JSON.stringify(res.data))
        const typeNameList = {
          1: '选择题',
          2: '判断题',
          3: '填空题',
        }
        let typeSet = new Set()
        res.data.forEach(item => typeSet.add(item.type))
        this.questionList = Array.from(typeSet).map(item =>{
          return {
            label: typeNameList[item],
            value: item,
            options: res.data.filter(el => el.type === item)
          }
        })
      })
    },
    updateScore() {
      let score = 0
      this.form.questionIds.forEach(arr => {
        arr.forEach(item =>{
          let find = this.questionListOrigin.find(el => el.id === item)
          if (find) {
            score += find.score
          }
        })
      })
      this.form.score = score
    }
  },
  mounted() {
    this.$axios.post('/manage/course/condition-list').then(res =>{
      this.courseList = res.data
    })
    this.$axios.get('/manage/homework/query?id=' + this.$route.query.id).then(async res =>{
      Object.keys(res.data.model).forEach(key =>{
        this.form[key] = res.data.model[key]
      })
      await this.getQuestionList()
      this.$axios.get('/manage/homework/query-question?homeworkId=' + this.$route.query.id).then(res =>{
        this.form.questionIds[0] = res.data.filter(item => item.type === 1).map(item => item.id)
        this.form.questionIds[1] = res.data.filter(item => item.type === 2).map(item => item.id)
        this.form.questionIds[2] = res.data.filter(item => item.type === 3).map(item => item.id)
        this.updateScore()
        this.$forceUpdate()
      })
    })
  }
}
</script>

<style scoped>

</style>

