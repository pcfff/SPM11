<template>
  <el-container direction="vertical">
    <div>
      <div class="title-wrap">{{ detailInfo.name }}
        <h2 v-if="showResult" class="green">({{
            showResult && submitStatus === 0 ? '等待批改' : score + '分'
          }})</h2>
        <h2 v-if="!showResult" class="green">
          {{ countdownStr }}
        </h2>
      </div>
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
      <div v-show="showResult && submitStatus === 1">
        <p>结果：<span
            :class="item.correct ? 'green': 'red'">{{
            item.correct ? '正确' : '错误'
          }}</span>
        </p>
        <p>得分：<span
            :class="item.correct ? 'green': 'red'">{{ item.getScore }}</span>
        </p>
        <p style="margin-top: 0">解析：{{ item.detail }}</p>
      </div>
    </el-card>
    <el-button type="primary" v-if="!showResult" @click="submitAnswer">交卷</el-button>
    <el-button type="primary" v-else @click="goback">返回</el-button>
  </el-container>
</template>

<script>

export default {
  data() {
    return {
      homeworkId: '',
      questionList: [],
      detailInfo: {},
      showResult: false,
      score: 0,
      submitStatus: 0,
      countdownStr: ''
    }
  },
  mounted() {
    this.homeworkId = this.$route.query.homeworkId
    this.$axios.get('/homework/query-question?homeworkId=' + this.homeworkId).then(res => {
      this.questionList = res.data.map(item => {
        if (item.options) {
          let json = JSON.parse(item.options)
          item.a = json[0].value
          item.b = json[1].value
          item.c = json[2].value
          item.d = json[3].value
        }
        return item
      })
      this.$axios.get('/homework-submit/list', {
        params: {
          homeworkId: this.homeworkId
        }
      }).then(res => {
        res.data.forEach(item => {
          let findItem = this.questionList.find(el => el.id === item.questionId)
          if (findItem) {
            findItem.checked = item.answer
            findItem.getScore = item.score
            findItem.correct = item.correct
            this.score += item.score
          }
        })
        if (res.data.length > 0) {
          this.showResult = true
          this.submitStatus = res.data[0].status
        }
      })
    })
    this.$axios.get('/homework/query?id=' + this.homeworkId).then(res => {
      this.detailInfo = res.data.model

      const syncCountDown = () =>{
        let diff = this.detailInfo.deadline - new Date().getTime()
        if (diff > 0) {
          this.countdownStr = this.formatMillisecondsToTime(diff)
        } else {
          this.countdownStr = '00:00:00'
        }
      }
      syncCountDown()
      setInterval(() => {
        syncCountDown()
      }, 1000)
    })
  },
  methods: {
    submitAnswer() {
      this.score = 0
      let answerList = this.questionList.map(item => item.checked).filter(item => !item)
      if (answerList.length > 0) {
        this.$message.error('请先完成所有题目')
        return
      }
      this.questionList.forEach(item => {
        if (item.checked === item.answer) {
          this.score += item.score
          item.getScore = item.score
          item.correct = 1
        } else {
          item.getScore = 0
          item.correct = 0
        }
      })
      this.$axios.post('/homework-submit/add', {
        homeworkId: this.homeworkId,
        list: this.questionList.map(item => {
          return {
            questionId: item.id,
            answer: item.checked,
            score: item.getScore,
            correct: item.correct
          }
        })
      }).then(res => {
        if (res.success) {
          this.$message.success('提交成功')
          this.showResult = true
        } else {
          this.$message.error(res.message)
        }
      })
    },
    goback() {
      this.$router.push('/portal/home/course')
    },
    formatMillisecondsToTime(milliseconds) {
      const hours = Math.floor(milliseconds / 3600000);
      const minutes = Math.floor((milliseconds % 3600000) / 60000);
      const seconds = Math.floor((milliseconds % 60000) / 1000);

      const formattedHours = hours.toString().padStart(2, '0');
      const formattedMinutes = minutes.toString().padStart(2, '0');
      const formattedSeconds = seconds.toString().padStart(2, '0');

      return `${formattedHours}:${formattedMinutes}:${formattedSeconds}`;
    }
  },
}
</script>

<style scoped lang="scss">
.el-container {
  width: 100%;
  padding: 10px 0;
  box-sizing: border-box;
}

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
  margin-bottom: 10px;
}

.title-wrap > h2 {
  margin: 0;
  margin-left: 10px;
}
</style>

