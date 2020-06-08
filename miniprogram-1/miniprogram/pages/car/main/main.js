// pages/car/main/main.js
const app = getApp();
Page({

 
  /**
   * 页面的初始数据
   */
  data: {
    swiperList:[],
    searchData: [{ name: "区域", isSelect:false},
    { name: "品牌", isSelect: false },
      { name: "型号", isSelect: false },
      { name: "年限", isSelect: false },
      { name: "排序", isSelect: false },
    ],
  },
  httpTest:function()
  {
    
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //请求轮播图
     wx-wx.request({
       url: 'https://api.zbztb.cn/api/public/v1/home/swiperdata',
       success: (res)=> {
         this.setData({
           swiperList:res.data.message
         })
       },
       fail: function(res) {},
       complete: function(res) {},
     })
     this.getSellInfo();
    
  },
  getSellInfo:function()
  {
    //请求默认出售数据
    wx - wx.request({
      url: app.globalData.netUrl + 'MainViewController/doPost',
      success: (res) => {
        console.log(res)
        for (var index in res.data) {
          for (var indexT in res.data[index].SourceUrlList) {
            res.data[index].SourceUrlList[indexT] = app.globalData.imageUrl + res.data[index].SourceUrlList[indexT];
          }
        }
        //设置日期显示

        this.setData({
          sellData: res.data
        })
      },
      fail: function (res) { },
      complete: function (res) { },
    })
  },
  //计算与当前的日期差值，换算为天数，不满一天换成小时
  CheckDate: function (startTime) {
    //日期格式化
    var start_date = new Date(parseInt(startTime));
    var end_date = new Date();
    //转成毫秒数，两个日期相减
    var ms = end_date.getTime() - start_date.getTime();
    //转换成天数
    var day = parseInt(ms / (1000 * 60 * 60 * 24));
    //do something
    console.log("********day = ", day);
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    // var start_date = new Date(parseInt("1578912520000"));
    // var end_date = new Date();
    // console.log("********day = ", start_date.getTime(), end_date.getTime());
    this.CheckDate("1568911520000");
  },
 
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    // var age = 'sellData[' + 0 + '].headurl';
    // this.setData({ [age]: app.globalData.avatarurl});
    // console.log("=------------------:" + app.globalData.avatarurl);
     var timestamp = Date.parse(new Date());
     console.log("7777777777:" + timestamp);
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
    this.getSellInfo();
   // wx.showNavigationBarLoading();
    // var that = this;
    // wx.request({
    //   url: 'https://xxx/?page=0',
    //   method: "GET",
    //   header: {
    //     'content-type': 'application/text'
    //   },
    //   success: function (res) {
    //     that.setData({
    //       moment: res.data.data
    //     });
    //     console.log(that.data.moment);
    //     // 隐藏导航栏加载框
    //     wx.hideNavigationBarLoading();
    //     // 停止下拉动作
    //     wx.stopPullDownRefresh();
    //   }
    // })
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    console.log("onReachBottom");
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})