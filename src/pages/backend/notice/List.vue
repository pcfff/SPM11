<template>
  <el-card class="box-card">
    <el-breadcrumb separator="/">
      <el-breadcrumb-item>通知管理</el-breadcrumb-item>
      <el-breadcrumb-item>数据列表</el-breadcrumb-item>
    </el-breadcrumb>
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item label="通知标题">
        <el-input placeholder="请输入通知标题" v-model="param.title"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="searchForm">搜索</el-button>
      </el-form-item>
    </el-form>
    <el-button type="primary" size="mini" @click="$router.push('/main/notice/add')">添加通知</el-button>
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
          label="标题"
          prop="title"
      >
      </el-table-column>
      <el-table-column
          align="center"
          label="通知内容"
          prop="content"
      >
      </el-table-column>
      <el-table-column
          align="center"
          label="课程名称"
          prop="courseName"
      >
      </el-table-column>
      <el-table-column
          align="center"
          label="发布时间"
          prop="createTime"
      >
        <template slot-scope="scope">
          {{ $moment(scope.row.createTime, 'YYYY-MM-DD HH:mm:ss') }}
        </template>
      </el-table-column>
      <el-table-column
          align="left"
          label="操作"
          width="180"
      >
        <template v-slot="scope">
          <el-button size="mini" @click="$router.push('/main/notice/modify?id=' + scope.row.id)" type="primary">修改
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
        title: ''
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
      this.$axios.get('/manage/notice/list', {
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
        this.$axios.get('/manage/notice/remove?id=' + id).then(res => {
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
          await this.$axios.get('/manage/notice/remove?id=' + item.id)
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
