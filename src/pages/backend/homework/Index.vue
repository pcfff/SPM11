<template>
  <el-card class="box-card">
    <el-breadcrumb separator="/">
      <el-breadcrumb-item>作业管理</el-breadcrumb-item>
      <el-breadcrumb-item>数据列表</el-breadcrumb-item>
    </el-breadcrumb>
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item label="作业名称">
        <el-input placeholder="请输入作业名称" v-model="param.name"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="searchForm">搜索</el-button>
      </el-form-item>
    </el-form>
    <el-button type="primary" size="mini" @click="$router.push('/main/homework/add')">添加作业</el-button>
    <el-button type="danger" size="mini" @click="batchDel">批量删除</el-button>
    <el-table
        border
        ref="multipleTable"
        :data="tableList"
        tooltip-effect="dark"
        style="width: 100%; margin-top: 10px"
        @selection-change="handleSelectionChange"
    >
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column
          align="center"
          label="ID"
          prop="id"
          sortable
      >
      </el-table-column>
      <el-table-column
          align="center"
          label="作业名称"
          prop="name"
      >
      </el-table-column>
      <el-table-column
          align="center"
          label="发布时间"
          prop="createTime"
      >
        <template slot-scope="scope">
          <span>{{$moment(scope.row.createTime, 'YYYY-MM-DD HH:mm:ss')}}</span>
        </template>
      </el-table-column>
      <el-table-column
          align="center"
          label="截止时间"
          prop="deadline"
      >
        <template slot-scope="scope">
          <span>{{$moment(scope.row.deadline, 'YYYY-MM-DD HH:mm:ss')}}</span>
        </template>
      </el-table-column>
      <el-table-column
          align="center"
          label="课程"
          prop="courseName"
      >
      </el-table-column>
      <el-table-column
          align="center"
          label="批改方式"
          prop="correct"
      >
        <template slot-scope="scope">
          <span v-if="scope.row.correct === 0">自动批改</span>
          <span v-if="scope.row.correct === 1">手动批改</span>
        </template>
      </el-table-column>
      <el-table-column
          align="center"
          label="类型"
          prop="type"
      >
        <template slot-scope="scope">
          <span v-if="scope.row.type === 0">作业</span>
          <span v-if="scope.row.type === 1">考试</span>
        </template>
      </el-table-column>
      <el-table-column
          align="left"
          label="操作"
          width="280"
      >
        <template v-slot="scope">
          <el-button size="mini" @click="$router.push('/main/homework-result/list?id=' + scope.row.id)" type="primary">查看提交情况
          </el-button>
          <el-button size="mini" @click="$router.push('/main/homework/modify?id=' + scope.row.id)" type="primary">修改
          </el-button>
          <el-button size="mini" @click="removeThis(scope.row.id)" type="danger">删除</el-button>
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
        name: '',
        type: 0
      },
      tableList: [],
      pageInfo: {
        pageNum: 1,
        pageSize: 10,
      },
      total: 0,
      multipleSelection: []
    }
  },
  mounted() {
    this.getTableList(this.pageInfo)
  },
  methods: {
    getTableList() {
      this.$axios.get('/manage/homework/list', {
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
    removeThis(id) {
      this.$confirm('是否确定删除？').then(() => {
        this.$axios.get('/manage/homework/remove?id=' + id).then(res => {
          if (res.success) {
            this.$message.success('删除成功！')
            this.getTableList()
          } else {
            this.$message.error('删除失败！')
          }
        })
      })
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    batchDel() {
      this.$confirm('是否确定删除？').then(async () => {
        for (let item of this.multipleSelection) {
          await this.$axios.get('/manage/homework/remove?id=' + item.id)
        }
        this.$message.success('删除成功！')
        this.getTableList()
      })
    }
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

