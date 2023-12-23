<template>
  <el-row :gutter="10">
    <el-col :span="6">
      <el-card>
        <div class="user-info-wrapper">
          <el-avatar class="el-avatar--large" size="large">
            <img src="../../../assets/avatar.jpg"/>
          </el-avatar>
          <p>{{ user.info.name }}</p>
          <div class="el-row--flex">
            <el-tag>学生</el-tag>
            <el-tag style="margin-left: 10px">{{ user.info.major }}</el-tag>
          </div>
        </div>
        <el-divider/>
        <div>
          <span>我的学习</span>
          <div class="menu-item-wrapper">
            <div class="menu-item" :class="{'active': index === selected}" @click="changeMenu(index)" v-for="(item, index) in menuList" :key="index" >
              <i :class="item.icon"></i>
              <span>{{ item.name }}</span>
            </div>
          </div>
        </div>
      </el-card>
    </el-col>
    <el-col :span="18">
      <router-view />
    </el-col>
  </el-row>
</template>

<script>
import index from "@/pages/portal/home/Index.vue";

export default {
  name: "Home",
  data() {
    return {
      menuList: [
        {
          name: "首页",
          icon: 'el-icon-s-home',
          path: "/portal/home/index"
        },
        {
          name: "我的课程",
          icon: 'el-icon-s-management',
          path: "/portal/home/course"
        },
        {
          name: "公告",
          icon: 'el-icon-message-solid',
          path: "/portal/home/notice"
        }
      ],
      selected: 0,
      user: {}
    }
  },
  methods: {
    index() {
      return index
    },
    changeMenu(index) {
      this.selected = index;
      this.$router.push(this.menuList[index].path);
    }
  },
  mounted() {
    this.$axios.get("/user/info").then(res => {
      // 重新赋值后台的最新User数据
      this.user = res.data
    })
  }
}
</script>

<style scoped lang="scss">

.user-info-wrapper {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  .el-avatar--large {
    width: 80px;
    height: 80px;
  }
}

.menu-item-wrapper {
  width: 100%;
  margin-top: 12px;

  .menu-item {
    width: 100%;
    padding: 12px 15px;
    box-sizing: border-box;
    border-radius: 22px;
    display: flex;
    align-items: center;
    justify-content: flex-start;
    cursor: pointer;
    font-size: 18px;

    span {
      margin-left: 10px;
    }
  }

  .menu-item.active {
    color: #fff;
    background: #5BBDC9;
  }
}
</style>
