<template>
  <div class="home" >

    <Layout class="wrap">
      <Layout>
        <Header>
          <h2>登录</h2>
        </Header>

        <div class="form-wrap">
          <Form ref="formInline" :model="formInline" :rules="ruleInline">

            <FormItem prop="user">
              <Input type="text" v-model="formInline.user" placeholder="用户名">
                <Icon type="ios-person-outline" slot="prepend"></Icon>
              </Input>
            </FormItem>

            <FormItem prop="password">
              <Input type="password" v-model="formInline.password" placeholder="密码">
                <Icon type="ios-lock-outline" slot="prepend"></Icon>
              </Input>
            </FormItem>

            <FormItem prop="checkcode">
              <Input type="text" v-model="formInline.checkcode" placeholder="验证码" ></Input>
            </FormItem>

            <div class="checkArea">
              <img id="checkImg" alt="验证码">
              <a href="#" @click.prevent="changeCheck(getUuid())" class="tips">看不清？换一张</a>
            </div>


            <FormItem>
              <Button type="primary" long @click="handleSubmit(formInline)">立即登录</Button>

              <router-link to="/Register">
                <a href="#" class="registerTip">立即注册</a>
              </router-link>

            </FormItem>
          </Form>
        </div>
      </Layout>
    </Layout>

  </div>
</template>

<script>
  import  axios from 'axios'

export default {
  name: 'HomePage',
  data () {
    return {
      formInline: {
        user: '',
        password: '',
        checkcode:'',
        uuid:''
      },
      ruleInline: {
        user: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { type: 'string', min: 4, message: '密码长度不小于4位', trigger: 'blur' }
        ],
        checkcode:[
          {required:true,message:'请输入验证码',trigger:'blur'}
        ]
      }
    }
  },
  created() {
    var uuid = this.getUuid()

    this.changeCheck(uuid)
  },
  methods: {

    getUuid() {
        var s = [];
        var hexDigits = "0123456789abcdef";
        for (var i = 0; i < 36; i++) {
          s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
        }
        s[14] = "4"; // bits 12-15 of the time_hi_and_version field to 0010
        s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1); // bits 6-7 of the clock_seq_hi_and_reserved to 01
        s[8] = s[13] = s[18] = s[23] = "-";

        var uuid = s.join("");

        this.uuid = uuid

        return uuid;
    },

    handleSubmit(data) {
      // this.$refs[data].validate((valid) => {
      //   if (valid) {
      //     this.$Message.success('Success!');
      //   } else {
      //     this.$Message.error('Fail!');
      //   }
      // })

      // console.log(data)
      // console.log(this.uuid)

      let postData = {
        username: data.user,
        password: data.password,
        uuid: this.uuid,
        text: data.checkcode
      }

      // 输入验证，表单的准确性
      if(postData.username == "" || postData.password == "" || postData.text == "")
      {
        // alert('表单数据不准为空')
        this.$Message.error('表单数据请填写完整 !');
        return;
      }

      if(postData.password.length <4)
      {
        // alert('密码长度不小于4位数')
        this.$Message.error('密码长度不小于4位数 !');
        return;
      }

      let url = 'http://192.168.1.59:9003/login'

      let that = this;
      axios.post(url,postData,{
      },{
        headers: {
          "Content-Type": "application/x-www-form-urlencoded"
          // "Content-Type": "text/plain"
        },

      }).then(function (response) {

        console.log(response.data)

        var status = response.data.code
        if(status === 0)
        {
          that.$Message.success('登录成功 !');
          // 成功登录后带参跳转页面
          that.$router.push({name:'Result',params:{username:postData.username}})
        }
        else
        {
          that.$Message.error(response.data.errMsg);
          that.changeCheck(that.getUuid())
        }
          // alert(response.data.errMsg)
      })

    },

    createMiniQrcode (blob) {
      let img = document.createElement('img')
      img.onload = function (e) {
        // 元素的onload 事件触发后将销毁URL对象, 释放内存。
        window.URL.revokeObjectURL(img.src)
      }
      // 浏览器允许使用URL.createObjectURL()方法，针对 Blob 对象生成一个临时 URL。
      // 这个 URL 以blob://开头,表明对应一个 Blob 对象。
      img.src = window.URL.createObjectURL(blob)
      document.querySelector('#checkImg').src = img.src
    },

    changeCheck(uuid) {
      var that = this;

      let url = 'http://192.168.1.59:9003/captcha/'+uuid
      axios.get(url,{
        responseType: 'blob'
      },{
        headers: {
          "Content-Type": "application/x-www-form-urlencoded"
          // "Content-Type": "text/plain"
        }
      }).then(function (response) {
        console.log(response)
        that.createMiniQrcode(response.data)
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h2 {
    color: #ffffff;
  }

  .wrap {
    width: 40%;
    margin: 0 auto;
    min-width: 500px;
  }

  .form-wrap {
    width: 50%;
    margin: 20px auto;
  }

  .checkArea {
    /*border: 1px solid #3e3e3e;*/
    height: 40px;
    /*padding-top: 5px;*/
  }

  #checkImg {
    width: 100px;
    height: 30px;
    border: none;
  }

  .tips {
    font-size: 12px;
    display: block;
    height: 30px;
    float: right;
    line-height: 30px;
  }

  .registerTip {
    font-size: 12px;
    color: #3e3e3e;
    float: right;
  }
</style>
