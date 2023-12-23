<template>
  <el-container direction="vertical">
    <div>
      <div class="title-wrap">{{ detailInfo.name }} <h2 v-if="showResult" class="green">({{
          showResult && submitStatus === 0 ? '等待批改' : score + '分'
        }})</h2></div>
    </div>
    <el-card v-for="item in questionList" :key="item.id" class="box-card">
      <h5>{{ item.name }}({{ item.score }}分)</h5>
      <div v-if="item.type == 1">
        <el-radio :disabled="showResult" v-model="item.checked" label="A" border>{{ item.a }}</el-radio>
        <el-radio :disabled="showResult" v-model="item.checked" label="B" border>{{ item.b }}</el-radio>
        <el-radio :disabled="showResult" v-model="item.checked" label="C" border>{{ item.c }}</el-radio>
        <el-radio :disabled="showResult" v-model="item.checked" label="D" border>{{ item.d }}</el-radio>
      </div>
      <div v-if="item.type == 2">
        <el-radio :disabled="showResult" v-model="item.checked" label="是" border>是</el-radio>
        <el-radio :disabled="showResult" v-model="item.checked" label="否" border>否</el-radio>
      </div>
      <div v-if="item.type == 3">
        <el-input :disabled="showResult" v-model="item.checked" label="是" border></el-input>
      </div>
      <div v-show="showResult">
        <p v-if="submitStatus === 1">结果：
          <span
              :class="item.correct ? 'green': 'red'">{{
              item.correct ? '正确' : '错误'
            }}</span>
        </p>
        <p v-if="submitStatus === 1">
          得分：<span :class="item.correct ? 'green': 'red'">{{ item.getScore }}</span>
        </p>
        <p style="margin-top: 5px">解析：{{ item.detail }}</p>
        <div v-if="submitStatus === 0">
          结果：
          <el-select v-model="item.correct">
            <el-option :value="1" label="正确"></el-option>
            <el-option :value="0" label="错误"></el-option>
          </el-select>
          分数：
          <el-input-number v-model="item.getScore" :min="0" :max="item.score"/>
        </div>
      </div>
    </el-card>
    <el-button type="primary" @click="saveSubmit" v-if="submitStatus === 0">批改</el-button>
    <el-button type="primary" @click="goback">返回</el-button>
  </el-container>
</template>

<script>

export default {
  data() {
    return {
      studentId: '',
      homeworkId: '',
      questionList: [],
      detailInfo: {},
      showResult: false,
      score: 0,
      submitStatus: 0
    }
  },
  mounted() {
    this.homeworkId = this.$route.query.homeworkId
    this.studentId = this.$route.query.studentId
    this.$axios.get('/manage/homework/query-question?homeworkId=' + this.homeworkId).then(res => {
      this.questionList = res.data.map(item => {
        item.correct = 0
        if (item.options) {
          let json = JSON.parse(item.options)
          item.a = json[0].value
          item.b = json[1].value
          item.c = json[2].value
          item.d = json[3].value
        }
        return item
      })
      this.$axios.get('/manage/homework-submit/detail-list', {
        params: {
          homeworkId: this.homeworkId,
          studentId: this.studentId,
        }
      }).then(res => {
        res.data.forEach(item => {
          let findItem = this.questionList.find(el => el.id === item.questionId)
          if (findItem) {
            findItem.submitId = item.id
            findItem.checked = item.answer
            findItem.getScore = item.score
            findItem.correct = item.correct
            this.score += item.score
          }
        })
        if (res.data.length > 0) {
          this.showResult = true
        }
      })
    })
    this.$axios.get('/manage/homework/query?id=' + this.homeworkId).then(res => {
      this.detailInfo = res.data.model
    })
  },
  methods: {
    goback() {
      this.$router.push('/main/homework-result/list?id=' + this.homeworkId)
    },
    saveSubmit() {
      this.$axios.post('/manage/homework-submit/correct', this.questionList.map(item => {
        return {
          id: item.submitId,
          score: item.getScore,
          correct: item.correct
        }
      })).then(res => {
        if (res.success) {
          this.submitStatus = 1
          this.$message.success('批改成功')
          this.score = this.questionList.map(item => item.getScore).concat([0, 0]).reduce((a, b) => a + b)
        } else {
          this.$message.success('批改失败')
        }
      })
    }
  },
}
</script>

<style scoped lang="scss">
.el-card {
  margin-bottom: 10px;
}

.green {
  color: green;
}

.red {
  color: red;
}

.title-wrap {
  display: flex;
  align-items: center;
  justify-content: flex-start;
}

.title-wrap > h2 {
  margin-left: 10px;
}

.el-button {
  margin: 5px 0;
}
</style>

