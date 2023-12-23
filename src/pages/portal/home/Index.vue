<template>
  <div class="list-wrapper">
    <el-card class="data-item" v-for="item in tableList" :key="item.id">
      <div class="header">
        <svg t="1702795434676" class="icon" viewBox="0 0 1086 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4368" width="32" height="32"><path d="M728.613228 489.65248a24.839868 24.839868 0 0 1-7.220892 17.482159l-31.847934 31.771924-84.111989-84.066383 31.923943-31.923943a24.809464 24.809464 0 0 1 34.842704 0l49.269285 49.254083a24.839868 24.839868 0 0 1 7.144883 17.48216z m-158.023519 0l84.11199 84.066383-198.536523 198.536523a24.186187 24.186187 0 0 1-17.40615 7.296901h-49.269285A24.687849 24.687849 0 0 1 364.847497 754.925245V705.671161a24.505427 24.505427 0 0 1 7.220892-17.482159zM166.767031 1023.99848a45.42321 45.42321 0 0 1-45.788055-46.06169v-491.020648a45.772854 45.772854 0 0 1 91.545707 0v445.567034h653.452711V486.916142a45.803257 45.803257 0 0 1 91.591313 0v491.020648a45.362403 45.362403 0 0 1-45.605633 46.06169H166.767031z m873.393478-552.892291a45.240788 45.240788 0 0 1-32.684037-14.289765L542.299015 109.757557 78.231296 457.27248a44.328675 44.328675 0 0 1-32.030357 13.833709 45.605633 45.605633 0 0 1-32.729642-14.289765 43.538178 43.538178 0 0 1-13.332047-31.467887 45.468816 45.468816 0 0 1 13.377652-32.684037L510.420678 13.833709a44.20706 44.20706 0 0 1 31.923943-13.833709 44.967154 44.967154 0 0 1 32.456009 14.289765L1072.494903 392.360463a45.757652 45.757652 0 0 1 13.68169 32.988074 44.28307 44.28307 0 0 1-13.210432 31.619906 46.046487 46.046487 0 0 1-32.805652 14.137746z" fill="#5BBCC9" p-id="4369"></path></svg>
        <div class="header-info-wrapper">
          <p class="title">作业发布</p>
          <p>{{$moment(item.createTime, 'yyyy.MM.DD')}}</p>
        </div>
      </div>
      <p class="content">课程{{item.courseName}}于{{$moment(item.createTime, 'yyyy.MM.DD HH:mm:ss')}}发布了【{{item.name}}】作业，作业截止时间{{$moment(item.deadline, 'yyyy.MM.DD HH:mm:ss')}}</p>
      <div>
        <el-tag style="margin-right: 10px">{{ item.courseName }}</el-tag>
        <el-button v-if="new Date(item.deadline).getTime() > new Date().getTime()" size="mini" @click="$router.push('/portal/home/course-detail/homework-detail?courseId=' + item.courseId + '&homeworkId=' + item.id)" type="primary">查看详情
        </el-button>
        <el-link v-else type="danger">已截止</el-link>
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
  data(){
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
      this.$axios.get('/homework/list', {
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

  .content {
    font-size: 18px;
  }
}

.el-pagination {
  margin-top: 10px;
}
</style>
