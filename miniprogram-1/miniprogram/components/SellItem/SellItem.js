
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    oneItem:
    {
      type:Object,
      value: { bossUuid: "", AvatarUrl: "", NickName: "", GoodsName: "", Price: "", SourceUrlList: "", Location: "", Date: ""}
    },
    //唯一标识
    bossUuid: {
      type: String,
      value: null
    },
    //头像路径
    headurl:{
      type: String,
      value:null
    },
    //卖家名称
    bossName:
    {
      type: String,
      value: null
    },
    //商品描述
    goodInfo:
    {
      type: String,
      value:null
    },
    //商品图片
    goodsPictrue:
    {
      type: Array,
      value: null
    },

    //地理位置
    goodsPositon:
    {
      type: String,
      value: null
    },
    //发布时间
    publishTime:
    {
      type: String,
      value: null
    },

  },

  /**
   * 组件的初始数据
   */
  data: {

  },

  /**
   * 组件的方法列表
   */
  methods: {
     onTapPhone:function(e)
     {

     },

     onTapChat:function(e)
     {

     }
  }
})
