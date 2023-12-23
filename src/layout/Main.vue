<template>
  <div class="index">
    <div id="left-nav">
      <div class="logo-wrap">
        <h5>后台管理</h5>
      </div>
      <el-menu
          default-active="0-0"
          class="el-menu-vertical-demo"
          :background-color="skinColor"
          text-color="#fff"
          active-text-color="#ffd04b"
          :collapse="isCollapse"
      >
        <div v-for="(item, index) in menuList" :key="index">
          <el-submenu v-if="item.childList.length > 0" :index="index + ''">
            <template slot="title">
              <i :class="item.icon"></i>
              <span slot="title">{{ item.title }}</span>
            </template>
            <el-menu-item v-for="(el, idx) in item.childList" :key="idx" :index="index + '-' + idx"
                          @click="clickMenuItem(el)">
            <span slot="title">
              {{ el.title }}
            </span>
            </el-menu-item>
          </el-submenu>
          <el-menu-item v-else :index="index + ''"
                        @click="clickMenuItem(item)">
            <template slot="title">
              <i :class="item.icon"></i>
              <span slot="title">{{ item.title }}</span>
            </template>
          </el-menu-item>
        </div>
      </el-menu>
    </div>
    <div class="right-body">
      <div class="header">
        <el-dropdown class="user-dropdown" trigger="click">
          <div class="user-info">
            <el-avatar class="el-dropdown-link" :src="require('../assets/avatar1.png')"></el-avatar>
            <span>{{username}}</span>
          </div>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item v-if="role === 'ROLE_TEACHER'">
              <div @click="$router.push('/main/user/info')">个人信息</div>
            </el-dropdown-item>
            <el-dropdown-item>
              <div @click="$router.push('/main/user/updatepwd')">修改密码</div>
            </el-dropdown-item>
            <el-dropdown-item>
              <div @click="logout">退出登录</div>
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
      <div class="main-body">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script>

/* eslint-disable */

export default {
  name: 'index',
  components: {},
  data() {
    return {
      isCollapse: false,
      menuList: [
        {
          title: '学生管理',
          icon: 'el-icon-user',
          role: ['ROLE_TEACHER', 'ROLE_ADMIN'],
          childList: [
            {
              title: '学生列表',
              path: '/main/student/list',
              role: ['ROLE_TEACHER', 'ROLE_ADMIN'],
            },
          ]
        },
        {
          title: '课程管理',
          icon: 'el-icon-takeaway-box',
          role: ['ROLE_TEACHER'],
          childList: [
            {
              title: '课程列表',
              path: '/main/course/list',
              role: ['ROLE_TEACHER'],
            },
          ]
        },
        {
          title: '题目管理',
          icon: 'el-icon-reading',
          role: ['ROLE_TEACHER'],
          childList: [
            {
              title: '题目列表',
              path: '/main/question/list',
              role: ['ROLE_TEACHER'],
            },
          ],
        },
        {
          title: '作业管理',
          icon: 'el-icon-thumb',
          role: ['ROLE_TEACHER'],
          childList: [
            {
              title: '作业列表',
              path: '/main/homework/list',
              role: ['ROLE_TEACHER'],
            },
          ],
        },
        {
          title: '课程视频管理',
          icon: 'el-icon-video-camera',
          role: ['ROLE_TEACHER'],
          childList: [
            {
              title: '课程视频列表',
              path: '/main/experiment/list',
              role: ['ROLE_TEACHER'],
            },
          ],
        },
        {
          title: '通知管理',
          icon: 'el-icon-bell',
          role: ['ROLE_TEACHER'],
          childList: [
            {
              title: '通知列表',
              path: '/main/notice/list',
              role: ['ROLE_TEACHER'],
            },
          ],
        },
        {
          title: '课程资源管理',
          icon: 'el-icon-wind-power',
          role: ['ROLE_TEACHER'],
          childList: [
            {
              title: '课程资源列表',
              path: '/main/course-resource/list',
              role: ['ROLE_TEACHER'],
            },
          ],
        },
        {
          title: '评论管理',
          icon: 'el-icon-chat-dot-round',
          role: ['ROLE_ADMIN'],
          childList: [
            {
              title: '评论列表',
              path: '/main/experiment-comment/list',
              role: ['ROLE_ADMIN'],
            },
          ],
        },
        {
          title: '系统设置',
          icon: 'el-icon-setting',
          role: ['ROLE_ADMIN'],
          childList: [
            {
              title: '系统设置',
              path: '/main/settins/index',
              role: ['ROLE_ADMIN'],
            },
          ],
        },
      ],
      username: '',
      skinColor: '#196CD2'
    }
  },
  mounted() {
    let role = localStorage.getItem('role')
    this.username = localStorage.getItem('username')

    this.menuList = this.menuList.filter(el => {
      return el.role.indexOf(role) !== -1
    })
    this.menuList.forEach(item => {
      item.childList = item.childList.filter(el => {
        return el.role.indexOf(role) !== -1
      })
    })
  },
  methods: {
    clickMenuItem(el) {
      this.$router.push(el.path)
    },
    logout() {
      localStorage.clear()
      this.$router.replace('/login')
    }
  },
  watch: {
    skinColor(newVal){
      localStorage.setItem('skinColor', newVal)
    }
  }
}
</script>


<style scoped >
.index {
  width: 100%;
  height: 100vh;
  display: flex;
}

#left-nav {
  height: 100%;
}

#left-nav .el-menu-vertical-demo {
  border: none;
  height: calc(100% - 50px);
  width: 200px;
}

#left-nav .el-submenu__title > span,
#left-nav .el-menu-item span {
  margin-left: 10px;
}

.right-body {
  width: 100%;
  height: 100%;
  overflow-y: auto;
  display: flex;
  flex-direction: column;
}

.right-body .main-body {
  box-sizing: border-box;
  padding: 10px;
}

.right-body .el-menu-demo {
  box-sizing: border-box;
  padding-left: 20px;
}

.skin-wrap {
  width: 100%;
  height: 100%;
  position: relative;
}

.skin-wrap .el-color-picker {
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  opacity: 0;
}

.el-menu ::v-deep i {
  color: #fff;
}

.header{
  width: 100%;
  height: 60px;
  box-shadow: 0 2px 12px 0 rgba(0,0,0,.1);
  background: #fff;
  display: flex;
  align-items: center;
  justify-content: flex-end;
  box-sizing: border-box;
  padding: 0 20px;
}

.user-info{
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: 500;
  color: #999;
  font-size: 17px;
  cursor: pointer;
}
.el-avatar{
  margin-right: 5px;
}

.logo-wrap{
  width: 100%;
  height: 50px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 20px;
  background: #347fda;
  color: #fff;
}
</style>

