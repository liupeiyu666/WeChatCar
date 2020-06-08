//index.js
//获取应用实例
const app = getApp()
Page({
  data: {
    //判断小程序的API，回调，参数，组件等是否在当前版本可用。
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    avatarurl: app.globalData.avatarurl
  },
  /**
  * 生命周期函数--监听页面加载
  */
  onLoad: function (options) {
    console.log("onLoad")
    var that=this
    wx.getUserInfo({
      success: function (res) {

        app.globalData.avatarurl = res.userInfo.avatarUrl;
        that.setData({ avatarurl: res.userInfo.avatarUrl})
      }
    })
  },
  //登录获取code
  login: function () {
    //读取用户信息
    //获取用户基本信息
    wx.getUserInfo({
      success: function (res) {
        console.log(res);
        app.globalData.avatarurl = res.userInfo.avatarUrl;
        app.globalData.nickName = res.userInfo.nickName;
        //
        //获取用户信息--唯一标识
        wx.login({
          success: function (res) {
            // console.log( res)      
            //发送请求--获取唯一id并注册
            wx.request({
              url: app.globalData.netUrl + "WechatServlet/doPost", //改成自己的服务器地址
              data: {
                code: res.code,//上面wx.login()成功获取到的code
                operFlag: 'getOpenid',
              },
              header: {
                'content-type': 'application/json' //默认值
              },
              success: function (res) {
                console.log(res)
                app.globalData.wxOpenId = res.data.openid;
                wx.switchTab({
                  url: '../main/main',
                })
              }
            })
          }
        })
      }
    })
    
  },
  refuse:function()
  {
    wx - wx.request({
      url: app.globalData.netUrl + 'TestInfo/doPost',
      data: {      
        operFlag: '这是汉语',
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded;charset=utf-8',
        'accept': 'application/x-www-form-urlencoded;charset=utf-8'
      },
      success: (res) => {
        console.log(res)
        
      },
      fail: function (res) { },
      complete: function (res) { },
    })
    wx.showToast({
      title: '亲~~拒绝授权将无法登陆！！请授权登录哦',
      icon: 'none',
    })
  }
 
})