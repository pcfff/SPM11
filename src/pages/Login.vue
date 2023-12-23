<template>
  <div class="login-page">
    <div class="login-wrap">
      <h2>在线课程学习网站</h2>
      <div class="form">
        <input type="text" placeholder="用户名" v-model="dataForm.username" name="username" />
        <input type="password" placeholder="密码" v-model="dataForm.password" name="password" />
        <button @click="login"> 登录 </button>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: "Login",
  data() {
    return {
      dataForm: {
        username: '',
        password: '',
        role: '',
      },
      dataRule: {
        username: [
          {required: true, message: '帐号不能为空', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '密码不能为空', trigger: 'blur'}
        ]
      },
    }
  },
  methods: {
    // 提交表单
    login() {
      this.$axios({
        url: '/user/login',
        method: 'POST',
        data: {
          username: this.dataForm.username,
          password: this.dataForm.password
        }
      }).then(res => {
        if (res.success) {
          this.$message.success('登录成功')
          localStorage.setItem('token', res.data.token)
          localStorage.setItem('role', res.data.role)
          localStorage.setItem('username', this.dataForm.username)
          if (res.data.role === 'ROLE_STUDENT'){
            this.$router.push('/portal')
          }else if (res.data.role === 'ROLE_TEACHER' || res.data.role === 'ROLE_ADMIN'){
            this.$router.push('/main')
          }
        } else {
          this.$message.error(res.message || '账号或密码错误')
        }
      })
    },
  }
}
</script>

<style>

.login-page {
  width: 100%;
  height: 100vh;
  background: url(../assets/login-bg.jpg) no-repeat 0/cover;
}
.login-page .login-wrap {
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  margin: 0 auto;
  background: #ecf0f1;
  width: 350px;
  border-radius: 5px;
  box-shadow: 3px 3px 10px #333;
  padding: 15px;
}
.login-page .login-wrap h2 {
  text-align: center;
  font-weight: 200;
  font-size: 2em;
  margin-top: 10px;
  color: #34495e;
}
.login-page .login-wrap .form {
  padding-top: 20px;
}
.login-page .login-wrap .form input[type="text"],
.login-page .login-wrap .form input[type="password"],
.login-page .login-wrap .form button {
  width: 80%;
  margin-left: 10%;
  margin-bottom: 25px;
  height: 40px;
  border-radius: 5px;
  outline: 0;
  -moz-outline-style: none;
}
.login-page .login-wrap .form input[type="text"],
.login-page .login-wrap .form input[type="password"] {
  border: 1px solid #bbb;
  padding: 0 0 0 10px;
  font-size: 14px;
}
.login-page .login-wrap .form input[type="text"]:focus,
.login-page .login-wrap .form input[type="password"]:focus {
  border: 1px solid #3498db;
}
.login-page .login-wrap .form a {
  text-align: center;
  font-size: 10px;
  color: #3498db;
}
.login-page .login-wrap .form a p {
  padding-bottom: 10px;
}
.login-page .login-wrap .form button {
  background: #e74c3c;
  border: none;
  color: white;
  font-size: 18px;
  font-weight: 200;
  cursor: pointer;
  transition: box-shadow .4s ease;
}
.login-page .login-wrap .form button:hover {
  box-shadow: 1px 1px 5px #555;
}
.login-page .login-wrap .form button:active {
  box-shadow: 1px 1px 7px #222;
}
.login-page .login-wrap:after {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  background: -webkit-linear-gradient(left, #27ae60 0%, #27ae60 20%, #8e44ad 20%, #8e44ad 40%, #3498db 40%, #3498db 60%, #e74c3c 60%, #e74c3c 80%, #f1c40f 80%, #f1c40f 100%);
  background: -moz-linear-gradient(left, #27ae60 0%, #27ae60 20%, #8e44ad 20%, #8e44ad 40%, #3498db 40%, #3498db 60%, #e74c3c 60%, #e74c3c 80%, #f1c40f 80%, #f1c40f 100%);
  height: 5px;
  border-radius: 5px 5px 0 0;
}

</style>



