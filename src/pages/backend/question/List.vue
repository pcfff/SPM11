<template>
  <el-card class="box-card">
    <el-breadcrumb separator="/">
      <el-breadcrumb-item>问题管理</el-breadcrumb-item>
      <el-breadcrumb-item>数据列表</el-breadcrumb-item>
    </el-breadcrumb>
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item label="问题类型">
        <el-select placeholder="请选择问题类型" clearable v-model="param.type">
          <el-option :value="1" label="选择题"></el-option>
          <el-option :value="2" label="判断题"></el-option>
          <el-option :value="3" label="填空题"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="课程" prop="courseId">
        <el-select v-model="param.courseId" clearable>
          <el-option v-for="item in courseList" :key="item.id" :value="item.id" :label="item.name"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="searchForm">搜索</el-button>
      </el-form-item>
    </el-form>
    <el-button type="primary" size="mini" @click="$router.push('/main/question/add')">添加问题</el-button>
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
          label="问题"
          prop="name"
      >
        <template v-slot="scope">
          <el-tooltip class="item" effect="dark" :content="scope.row.name" placement="top">
            <span>{{scope.row.name.substring(0, 10)}}</span>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column
          align="center"
          label="题目类型"
          prop="gender"
      >
        <template v-slot="scope">
          {{ ['选择题', '判断题', '填空题'][scope.row.type - 1] }}
        </template>
      </el-table-column>
      <el-table-column
          align="center"
          label="答案"
          prop="answer"
      >
      </el-table-column>
      <el-table-column
          align="center"
          label="解析"
          prop="detail"
      >
        <template v-slot="scope">
          <el-tooltip class="item" effect="dark" :content="scope.row.detail" placement="top">
            <span>{{scope.row.detail.substring(0, 10)}}</span>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column
          align="center"
          label="分数"
          prop="score"
          sortable
      >
      </el-table-column>
      <el-table-column
          align="center"
          label="课程名称"
          prop="courseName"
      >
      </el-table-column>
      <el-table-column
          align="left"
          label="操作"
          width="180"
      >
        <template v-slot="scope">
          <el-button size="mini" @click="$router.push('/main/question/modify?id=' + scope.row.id)" type="primary">修改
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
        type: '',
        courseId: ''
      },
      courseList: [],
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
    this.$axios.post('/manage/course/condition-list').then(res =>{
      this.courseList = res.data
    })
  },
  methods: {
    getTableList() {
      this.$axios.get('/manage/question/list', {
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
        this.$axios.post('/manage/question/remove?id=' + id, ).then(res => {
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
          await this.$axios.get('/manage/question/remove?id=' + item.id)
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

