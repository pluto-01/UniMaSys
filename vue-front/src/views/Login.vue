<template>
  <div class="login">
    <div class="container" id="container">
      <h2>登录</h2>
      <div>
        <div class="input-container">
          <label class="login_label">账号</label>
          <input
            type="text"
            required
            name="username"
            class="login_input"
            placeholder="请输入用户名或学号"
            v-model="userInfo.username"
          />
        </div>
        <div class="input-container">
          <label class="login_label">密码</label>
          <input
            type="password"
            required
            name="password"
            class="login_input"
            placeholder="请输入密码"
            v-model="userInfo.password"
          />
        </div>
        <div class="btn-container">
          <div class="identity-container">
            <a href="#" class="forget_pwd">忘记密码？</a>
          </div>
          <div>
            <button type="submit" @click="login">登录</button>
            <button type="submit">注册</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "login",
  data() {
    return {
      userInfo: {},
    };
  },
  methods: {
    login() {
      this.request.post("/user/login", this.userInfo).then((res) => {
        console.log(res);
        if (res.code === "200") {
          localStorage.setItem("user", JSON.stringify(res.data)); //  存储用户信息到浏览器
          this.$router.push("/");
          this.$message.success("登录成功");
        } else {
          this.$message.error(res.$message);
        }
      });
    },
  },
};
</script>

<style scoped>
.login {
  height: 100vh;
  display: flex;
  align-items: center;
  background-image: url("../assets/images/01.png");
}
.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin: 0 auto;
  width: 350px;
  height: 380px;
  border-radius: 20px;
  border-top: 1px solid rgba(255, 255, 255, 0.5);
  border-left: 1px solid rgba(255, 255, 255, 0.5);
  border-bottom: 1px solid rgba(255, 255, 255, 0.2);
  border-right: 1px solid rgba(255, 255, 255, 0.2);
  backdrop-filter: blur(10px);
  background: rgba(50, 50, 50, 0.2);
}
body {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
  /*background: url(../images/01.png) no-repeat;*/
  background-size: cover;
}
h2 {
  color: rgba(255, 255, 255, 0.9);
  margin-bottom: 20px;
}

.container .input-container {
  display: flex;
  flex-direction: column;
  margin-bottom: 10px;
}

.login_label {
  margin-bottom: 5px;
  color: rgba(255, 255, 255, 0.9);
  font-size: 13px;
}

.login_input {
  box-sizing: border-box;
  color: rgba(255, 255, 255, 0.9);
  font-size: 14px;
  height: 35px;
  width: 250px;
  background-color: rgba(255, 255, 255, 0.3);
  border: solid 1px rgba(255, 255, 255, 0.5);
  border-radius: 5px;
  transition: 0.2s;
  outline: none;
  padding: 0 10px;
  letter-spacing: 1px;
}

input:focus {
  border: solid 1px rgba(255, 255, 255, 0.8);
}

.container .btn-container {
  width: 250px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
}

.forget_pwd {
  font-size: 14px;
  text-decoration: none;
  color: rgba(255, 255, 255, 0.9);
  transition: 0.2s;
  width: 70px;
  text-align: end;
}

.identity_label {
  display: flex;
  font-size: 14px;
  color: rgba(255, 255, 255, 0.9);
  margin-left: 8px;
}

.forget_pwd:hover {
  color: rgb(104, 103, 103);
}

.container .btn-container > div {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: flex-start;
  margin-top: 20px;
}

.container .btn-container > div > button {
  width: 120px;
  height: 35px;
  border: solid 1px rgba(45, 88, 83, 0.8);
  background: rgba(45, 88, 83, 0.5);
  color: rgba(255, 255, 255, 0.9);
  border-radius: 5px;
  transition: 0.2s;
  cursor: pointer;
}

.container .btn-container > div > button:nth-of-type(2) {
  margin-left: 10px;
}

.container .btn-container > div > button:hover {
  border: solid 1px rgba(109, 144, 58, 0.8);
  background: rgba(109, 144, 58, 0.5);
}
</style>