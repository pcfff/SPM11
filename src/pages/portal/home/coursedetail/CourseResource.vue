<template>
  <div class="list-wrapper">
    <el-card class="data-item" v-for="item in tableList" :key="item.id">
      <div class="header">
        <svg t="1702807166430" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="9376" width="32" height="32"><path d="M914.850909 62.045091H340.759273A62.231273 62.231273 0 0 0 278.714182 124.090182v155.136h-46.545455v-23.272727a31.045818 31.045818 0 0 0-62.045091 0v-116.363637a31.045818 31.045818 0 1 0-62.045091 0V899.863273h-31.045818a31.045818 31.045818 0 1 0 0 62.045091h124.136728a31.045818 31.045818 0 1 0 0-62.045091h-31.045819V364.590545a31.045818 31.045818 0 1 0 62.045091 0V341.317818h46.545455v248.226909a62.231273 62.231273 0 0 0 62.045091 62.045091h574.091636A62.231273 62.231273 0 0 0 976.896 589.544727v-465.454545A62.231273 62.231273 0 0 0 914.850909 62.045091z m31.045818 522.658909a31.138909 31.138909 0 0 1-31.045818 31.045818H346.018909a31.138909 31.138909 0 0 1-31.045818-31.045818v-458.798545a31.138909 31.138909 0 0 1 31.045818-31.045819h568.832a31.138909 31.138909 0 0 1 31.045818 31.045819z" fill="#5BBCC9" p-id="9377"></path><path d="M325.818182 109.381818m23.272727 0l558.545455 0q23.272727 0 23.272727 23.272727l0 446.836364q0 23.272727-23.272727 23.272727l-558.545455 0q-23.272727 0-23.272727-23.272727l0-446.836364q0-23.272727 23.272727-23.272727Z" fill="#5BBCC9" p-id="9378"></path></svg>
        <div class="header-info-wrapper">
          <p class="title">{{ item.name }}</p>
        </div>
      </div>
      <div class="info-wrapper">
        <div class="info-item">
          <span>介绍：</span>
          <span>{{ item.details }}</span>
        </div>
        <div class="info-item">
          <span>下载：</span>
          <span>
            <el-link type="primary" target="_blank" :href="$baseURL + item.filename">{{ item.name }}</el-link>
          </span>
        </div>
        <div class="info-item">
          <span>发布时间：</span>
          <span>{{ $moment(item.createTime, 'yyyy.MM.DD HH:mm:ss') }}</span>
        </div>
      </div>
    </el-card>

    <el-pagination
        background
        layout="prev, pager, next"
        :total="total"
        @current-change="changePage"
    >
    </el-pagination>
  </div>
</template>

<script>


export default {
  name: "Index",
  data() {
    return {
      tableList: [],
      pageInfo: {
        pageNum: 1,
        pageSize: 10,
      },
      total: 0,
    }
  },
  methods: {
    changePage(e) {
      this.pageInfo.pageNum = e
      this.getTableList()
    },
    getTableList() {
      this.$axios.get('/course-resource/list', {
        params: Object.assign({
          courseId: this.$route.query.courseId
        }, this.pageInfo)
      }).then(res => {
        this.tableList = res.data.rows;
        this.total = res.data.count;
      })
    },
  },
  mounted() {
    this.getTableList()
  }
}
</script>

<style scoped lang="scss">
.data-item {
  margin-bottom: 10px;

  .header {
    display: flex;
    align-items: center;
    justify-content: flex-start;

    .header-info-wrapper {
      margin-left: 20px;

      .title {
        font-size: 20px;
        font-weight: bold;
      }

      p {
        margin: 0;
      }
    }
  }

  .info-wrapper {
    margin: 10px 0;

    .info-item {
      display: flex;
      align-items: center;
      justify-content: flex-start;
      padding: 5px 0;

      span:first-child {
        color: #999;
        margin-right: 10px;
      }
    }
  }
}

.el-pagination {
  margin-top: 10px;
}
</style>
