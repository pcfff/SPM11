<template>
  <div class="page">
    <el-header style="background-color: #3C445B; color: #fff; padding: 0;">
      <el-row type="flex" justify="space-between">
        <!-- 左侧菜单栏 -->
        <el-col :span="8">
          <el-menu mode="horizontal" default-active="1" style="background-color: #3C445B; border: none;"
                   text-color="rbga(255, 255, 255, .5)"
                   active-text-color="#fff"
                   active-background-color="#3C445B"
          >
            <el-menu-item index="1" style="opacity: 0;">首页</el-menu-item>
          </el-menu>
        </el-col>

        <!-- 右侧用户头像和下拉框 -->
        <el-col :span="4" style="text-align: right;">
          <el-dropdown trigger="click">
            <span class="el-dropdown-link">
              <el-avatar>
                <img src="../assets/avatar.jpg" alt="">
              </el-avatar>
              <span>{{ user.info.name }}</span>
              <i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item >
                <p style="margin: 0"  @click="$router.push('/portal/user/info')">个人信息</p>
              </el-dropdown-item>
              <el-dropdown-item >
                <p style="margin: 0"  @click="$router.push('/portal/user/updatepwd')">修改密码</p>
              </el-dropdown-item>
              <el-dropdown-item divided>
                <p style="margin: 0; text-align: center" @click="logout">退出</p>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-col>
      </el-row>
    </el-header>
    <!-- 主内容区域 -->
    <el-main>
      <router-view />
    </el-main>
  </div>
</template>

<script>
export default {
  data(){
    return {
      user: {}
    }
  },
  mounted() {
    this.$axios.get("/user/info").then(res => {
      // 重新赋值后台的最新User数据
      this.user = res.data
    })
  },
  methods: {
    logout() {
      localStorage.clear()
      this.$message.success("退出成功")
      this.$router.push('/login')
    }
  }
}
</script>

<style scoped lang="scss">
.page {
  width: 100vw;
  height: 100vh;
  background: #F6F6F6;

  .el-header {
    padding: 0 80px !important;
    box-sizing: border-box;

    .el-menu-item {
      background: none !important;
    }

    .el-dropdown {
      width: 138px;
      height: 100%;
      display: flex;
      align-items: center;
      justify-content: center;
    }

    .el-dropdown-link {
      display: flex;
      align-items: center;
      justify-content: center;
      color: #fff;
      cursor: pointer;

      .el-avatar {
        margin-right: 10px;
      }
    }
  }

  .el-main {
    width: 80%;
    max-width: 1400px;
    margin: 10px auto;
  }
}
</style>
