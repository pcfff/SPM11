<template>
  <div class="list-wrapper">
    <el-card class="data-item" v-for="item in tableList" :key="item.id">
      <div class="header">
        <svg t="1702796598531" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
             p-id="5536" width="32" height="32">
          <path d="M145 127.7h737v598.7H145z" fill="#5BBCC9" p-id="5537"></path>
          <path
              d="M944 111.7H83c-8.8 0-16 7.2-16 16s7.2 16 16 16h46v582.7c0 8.8 7.2 16 16 16h162.8L164.7 884.7c-6.3 6.2-6.3 16.4-0.1 22.6 3.1 3.1 7.2 4.7 11.3 4.7 4.1 0 8.2-1.5 11.3-4.7l166-165h317.6l165.9 164.9c3.1 3.1 7.2 4.7 11.3 4.7 4.1 0 8.2-1.6 11.3-4.7 6.2-6.3 6.2-16.4-0.1-22.6l-143-142.2H882c8.8 0 16-7.2 16-16V143.7h46c8.8 0 16-7.2 16-16s-7.2-16-16-16z m-78 598.7H161V143.7h705v566.7z"
              fill="#5BBCC9" p-id="5538"></path>
          <path
              d="M252.7 227v400h521.6V227H252.7z m258.9 296.2H345.3c-17.5 0-31.7-14.2-31.7-31.7s14.2-31.7 31.7-31.7h166.3c17.5 0 31.7 14.2 31.7 31.7s-14.2 31.7-31.7 31.7z m170.1-135.9H345.3c-17.5 0-31.7-14.2-31.7-31.7s14.2-31.7 31.7-31.7h336.4c17.5 0 31.7 14.2 31.7 31.7s-14.2 31.7-31.7 31.7z"
              fill="#5BBCC9" p-id="5539"></path>
        </svg>
        <div class="header-info-wrapper">
          <p class="title">{{ item.name }}</p>
          <p>创建时间：{{ $moment(item.createTime, 'yyyy.MM.DD') }}</p>
        </div>
      </div>
      <div class="info-wrapper">
        <div class="info-item">
          <span>课时：</span>
          <span>{{ item.period }}</span>
        </div>
        <div class="info-item">
          <span>学分：</span>
          <span>{{ item.score }}</span>
        </div>
        <div class="info-item">
          <span>专业：</span>
          <span>{{ item.major }}</span>
        </div>
      </div>
      <div>
        <el-button size="mini" @click="$router.push('/portal/home/course-detail?courseId=' + item.id)" type="primary">查看详情
        </el-button>
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
      this.$axios.get('/course/list', {
        params: Object.assign({}, this.pageInfo)
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
