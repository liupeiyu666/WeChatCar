package com.ne.weixincar.onlearn.pojo;

import lombok.Data;

@Data
public class CarSell {
    private String WxOpenId;
    private Integer SellId = 0;
    private String Title;
    private String GoodsName;
    private Integer Price = 0;
    private Integer Inventory;
    private String SourceUrl;
    private String Location;
    private String Date;
    private Double Longitude;
    private Double Latitude;
    private String NickName;
    private String AvatarUrl;
}
