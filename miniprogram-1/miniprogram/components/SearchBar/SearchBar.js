// components/SearchBar/SearchBar.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
     searchData:{
       type:Array,
       value:[
         {name:"11",isSelect:false},
         { name: "22", isSelect: false }
         ],
     }
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
    onTap:function(e)
    {
      for (var i = 0; i < this.properties.searchData.length; i++)
      {
        if (this.properties.searchData[i].name == e.currentTarget.dataset.name)
        {
          console.log(e.currentTarget.dataset.name);
         // this.properties.searchData[i].isSelect=true;
          var age = 'searchData[' + i + '].isSelect';
          this.setData({[age] : true});
        }
        else
        {
         // this.properties.searchData[i].isSelect = false;
          var age = 'searchData[' + i + '].isSelect';
          this.setData({ [age]: false });
        }
      }
     // console.log(e.currentTarget.dataset.name);
    //  console.log(this.properties.searchData);
    }
  }
})
