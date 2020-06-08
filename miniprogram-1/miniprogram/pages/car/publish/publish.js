// pages/car/publish/publish.js
const app = getApp();
// 引入SDK核心类
var QQMapWX = require('../../../libs/qqmap-wx-jssdk.js');

// 实例化API核心类
var qqmapsdk = new QQMapWX({
  key: 'CX4BZ-SZ76D-S4Q4Q-HBI4X-WV23H-2KB2O' // 必填
});
Page({
  data: {
    //图片列表
    img_arr: [],
    formdata: '',
    //名称选择器
    namePicker:["发动机","轴承","轮胎","其他"],
    namePickerIndex:0,
    //日期选择器
    date: '2016-09-01',
    //位置
    //位置-经度
    latitude: 23.114155,
    //位置-纬度
    longitude: 113.318977,
    //标记
    markers: [],
    //汉字地址
    locationName:"北京",
    //备注
    notes:"",
    price:"",
  },
  formSubmit: function (e) {
    // var id = e.target.id
    // adds = e.detail.value;
    // adds.program_id = app.jtappid
    // adds.openid = app._openid
    // adds.zx_info_id = this.data.zx_info_id

    this.upload();
  },

  upload: function () {   
    var that = this
    var imgfile;
    //获取本次上传的时间，表明是一次传递
    var timestamp = Date.parse(new Date());
    for (var i = 0; i < this.data.img_arr.length; i++) {//循环遍历图片 
      wx.uploadFile({
        url: app.globalData.netUrl +'PublishController/doPost',//自己的接口地址
        filePath: that.data.img_arr[i],
        name: 'content',
       
        header: {
          "Content-Type": "multipart/form-data",
          'accept': 'application/x-www-form-urlencoded;charset=utf-8'
        },
        formData: ({//上传图片所要携带的参数
          userName: "9999",
          openid: app.globalData.wxOpenId,
          timeRecord: timestamp.toString(),
          productionDate: that.data.date,
          latitude: that.data.latitude,
          longitude: that.data.longitude,
          locationName: that.data.locationName.toString(),
          notes: that.data.notes,
          price: that.data.price,
          namePicker: that.data.namePicker[that.data.namePickerIndex].toString(),
          avatarurl: app.globalData.avatarurl,
          nickName: app.globalData.nickName,
        }),
        success: function (res) {
          console.log(res)
          if (res) {
            console.log("返回的参数信息" + that.data.latitude + "   " + that.data.longitude)
            wx.showToast({
              title: '已提交发布！',
              duration: 3000
            });
          }
        }
      })
    }
    this.setData({
      username: 'CMOS180404',
      password: 'ecb01ff6-2e5c-11e8-b467-0ed5f89f718b'
    })
  },
  upimg: function () {
    var that = this;
    if (this.data.img_arr.length < 6) {
      wx.chooseImage({
        sizeType: ['compressed'], // 可以指定是原图还是压缩图，默认二者都有
        sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
        success: function (res) {
          console.log(res)
          that.setData({
            img_arr: that.data.img_arr.concat(res.tempFilePaths)
          });
        }
      })
    } else {
      wx.showToast({
        title: '最多上传六张图片',
        icon: 'loading',
        duration: 3000
      });
    }
  },
  onTapImage:function(res)
  {
    var that=this;
    console.log(res)
    wx.previewImage({
      current: that.data.img_arr[res.currentTarget.id], // 当前显示图片的http链接
      urls: that.data.img_arr // 需要预览的图片http链接列表
    })
  },
  onTapCloseImage: function (res)
  {
    var that=this;
    var arry = that.data.img_arr;
    that.data.img_arr.splice(res.currentTarget.id,1);
    that.setData({
      img_arr: arry
    })
  },
  chooseLocation: function (e) {
    wx.chooseLocation({
      success: function(res) {
        console.log(res)
      },
    })
   
  },
  initlocation: function()
  {
    var that=this;
    wx.getLocation({
      success: function(res) {
        that.setData({
          latitude: res.latitude,
          longitude: res.longitude
        }),
        // 调用sdk接口
        qqmapsdk.reverseGeocoder({
          location: {
            latitude: res.latitude,
            longitude: res.longitude
          },
          success: function (res) {
            //获取当前地址成功
            console.log(res);
            that.setData({
              locationName: res.result.address_component.province + "." + res.result.address_component.city + "." + res.result.address_component.street,
              

            })

          },
          fail: function (res) {
            console.log('获取当前地址失败');
          }
        });
      },
    })
  },
  onInputNotes:function(res){
    console.log(res)
    this.setData({
      notes: res.detail.value
    });
  },
  onInputPrice: function (res) {
    console.log(res)
    this.setData({
      price: res.detail.value
    });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.initlocation();
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  bindNamePickerChange: function (e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    console.log(this.data.namePicker[e.detail.value])
    this.setData({
      namePickerIndex: e.detail.value
    })
  },

  bindDateChange: function (e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      date: e.detail.value
    })
  },
})