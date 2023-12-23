<template>
  <el-card class="box-card">
    <el-breadcrumb separator="/">
      <el-breadcrumb-item>作业详情</el-breadcrumb-item>
      <el-breadcrumb-item>提交情况</el-breadcrumb-item>
    </el-breadcrumb>
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item label="学生用户名">
        <el-input placeholder="请输入学生用户名" v-model="param.studentName"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="searchForm">搜索</el-button>
      </el-form-item>
    </el-form>
    <el-button type="primary" size="mini" @click="$router.push('/main/homework/list')">返回</el-button>
    <el-table
        border
        ref="multipleTable"
        :data="tableList"
        tooltip-effect="dark"
        style="width: 100%; margin-top: 10px"
    >
      <el-table-column
          align="center"
          label="姓名"
          prop="studentName"
      >
      </el-table-column>
      <el-table-column
          align="center"
          label="分数"
          prop="score"
      >
        <template slot-scope="scope">
          <span v-if="scope.row.score">{{scope.row.status === 0 ? '等待批改' : scope.row.score}}</span>
        </template>
      </el-table-column>
      <el-table-column
          align="center"
          label="提交时间"
          prop="createTime"
      >
        <template slot-scope="scope">
          <span v-if="scope.row.createTime">{{$moment(scope.row.createTime, 'YYYY-MM-DD HH:mm:ss')}}</span>
        </template>
      </el-table-column>
      <el-table-column
          align="center"
          label="操作"
      >
        <template v-slot="scope">
          <el-button v-if="scope.row.createTime !== null" size="mini" type="primary" @click="$router.push('/main/homework-submit/detail?homeworkId=' + param.homeworkId + '&studentId=' + scope.row.userId)">查看提交详情</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        background
        layout="prev, pager, next"
        :total="total"
        @current-change="changePage"
    >
    </el-pagination>
  </el-card>
</template>

<script>

export default {
  data() {
    return {
      param: {
        studentName: '',
        homeworkId: ''
      },
      tableList: [],
      pageInfo: {
        pageNum: 1,
        pageSize: 10,
      },
      total: 0,
    }
  },
  mounted() {
    this.param.homeworkId = this.$route.query.id
    this.getTableList(this.pageInfo)
  },
  methods: {
    getTableList() {
      this.$axios.get('/manage/homework-submit/list', {
        params: Object.assign(this.param, this.pageInfo)
      }).then(res => {
        this.tableList = res.data.rows;
        this.total = res.data.count;
      })
    },
    searchForm() {
      this.pageInfo.pageNum = 1;
      this.getTableList();
    },
    changePage(e) {
      this.pageInfo.pageNum = e
      this.getTableList()
    },
  },
}
</script>

<style scoped lang="scss">
.el-pagination {
  margin-top: 10px;
}

.demo-form-inline {
  margin-top: 20px;
}
</style>

