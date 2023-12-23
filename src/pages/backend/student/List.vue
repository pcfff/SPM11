<template>
  <el-card class="box-card">
    <el-breadcrumb separator="/">
      <el-breadcrumb-item>学生管理</el-breadcrumb-item>
      <el-breadcrumb-item>数据列表</el-breadcrumb-item>
    </el-breadcrumb>
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item label="姓名">
        <el-input placeholder="请输入学生姓名" v-model="param.name"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="searchForm">搜索</el-button>
      </el-form-item>
    </el-form>
    <el-button type="primary" size="mini" @click="$router.push('/main/student/add')">添加学生</el-button>
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
          prop="userId"
          sortable
      >
      </el-table-column>
      <el-table-column
          align="center"
          label="用户名"
          prop="username"
      >
      </el-table-column>
      <el-table-column
          align="center"
          label="学号"
          prop="num"
      >
      </el-table-column>
      <el-table-column
          align="center"
          label="姓名"
          prop="name"
      >
      </el-table-column>
      <el-table-column
          align="center"
          label="手机号"
          prop="phone"
      >
      </el-table-column>
      <el-table-column
          align="center"
          label="专业"
          prop="major"
      >
      </el-table-column>
      <el-table-column
          align="center"
          label="性别"
          prop="gender"
      >
        <template v-slot="scope">
          {{ scope.row.sex === 1 ? '男' : '女' }}
        </template>
      </el-table-column>
      <el-table-column
          align="center"
          label="年级"
          prop="grade"
      >
      </el-table-column>
      <el-table-column
          align="center"
          label="年龄"
          prop="age"
      >
      </el-table-column>
      <el-table-column
          align="left"
          label="操作"
          width="280"
      >
        <template v-slot="scope">
          <el-button size="mini" @click="userId = scope.row.userId; username = scope.row.username; dialogFormVisible = true"
                     type="primary">修改密码
          </el-button>
          <el-button size="mini" @click="$router.push('/main/student/modify?id=' + scope.row.userId)" type="primary">修改
          </el-button>
          <el-button size="mini" @click="removeThis(scope.row.userId)" type="danger">删除</el-button>
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

    <el-dialog title="修改密码" :visible.sync="dialogFormVisible">
      <el-form>
        <el-form-item label="用户名" label-width="80px">
          <el-input v-model="username" readonly disabled autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" label-width="80px">
          <el-input v-model="password" type="password" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="setPassword">确 定</el-button>
      </div>
    </el-dialog>

  </el-card>
</template>

<script>

export default {
  data() {
    return {
      dialogFormVisible: false,
      username: '',
      password: '',
      userId: '',
      param: {
        name: ''
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
      this.$axios.get('/manage/student/list', {
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
        this.$axios.get('/manage/student/remove?id=' + id).then(res => {
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
          await this.$axios.get('/manage/student/remove?id=' + item.id)
        }
        this.$message.success('删除成功！')
        this.getTableList()
      })
    },
    setPassword() {
      this.$axios.post('/manage/user/update-pwd', {id: this.userId, password: this.password}).then(res => {
         if (res.success){
           this.$message.success('设置成功')
           this.dialogFormVisible = false
           this.getTableList()
         }else {
           this.$message.error('设置失败')
         }
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

