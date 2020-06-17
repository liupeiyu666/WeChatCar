package com.ne.weixincar.onlearn.pojo;
/*
 * 一个出售的数据结构*/

import java.util.LinkedList;

import com.ne.weixincar.onlearn.pojo.CarSell;
import lombok.Data;

@Data
public class OneCarSell {
    public String WxOpenId;
    public Integer SellId = 0;
    public String Title;
    public String GoodsName;
    public Integer Price = 0;
    public Integer Inventory = 0;

    public LinkedList<String> SourceUrlList = new LinkedList<String>();
    public String Location;
    public String Date;
    public Double Longitude;
    public Double Latitude;
    public String NickName;
    public String AvatarUrl;

    /*
     * 由于数据库存储的数据是特定的数据格式，这里需要进行一次解析*/
    public OneCarSell(CarSell p_CarSell) {
        WxOpenId = p_CarSell.getWxOpenId();
        SellId = p_CarSell.getSellId();
        Title = p_CarSell.getTitle();
        GoodsName = p_CarSell.getGoodsName();
        Price = p_CarSell.getPrice();
        Inventory = p_CarSell.getInventory();
        //资源地址以,号分隔
        if (!p_CarSell.getSourceUrl().equals(null)) {
            String[] t_urlArry = p_CarSell.getSourceUrl().split(",");
            for (int i = 0; i < t_urlArry.length; i++) {
                SourceUrlList.add(t_urlArry[i]);
            }
        }


        Location = p_CarSell.getLocation();
        Date = p_CarSell.getDate();
        Longitude = p_CarSell.getLongitude();
        Latitude = p_CarSell.getLatitude();
        NickName = p_CarSell.getNickName();
        AvatarUrl = p_CarSell.getAvatarUrl();
    }

    public CarSell ToCarSell() {
        CarSell t_CarSell = new CarSell();
        t_CarSell.setWxOpenId(this.WxOpenId);
        t_CarSell.setSellId(this.SellId);
        t_CarSell.setTitle(this.Title);
        t_CarSell.setGoodsName(this.GoodsName);
        t_CarSell.setPrice(this.Price);
        t_CarSell.setInventory(this.Inventory);
        t_CarSell.setLocation(this.Location);
        t_CarSell.setDate(this.Date);
        t_CarSell.setLongitude(this.Longitude);
        t_CarSell.setLatitude(this.Latitude);
        t_CarSell.setNickName(this.NickName);
        t_CarSell.setAvatarUrl(this.AvatarUrl);

        //设置图片地址
        String t_url = "";
        for (int i = 0; i < SourceUrlList.size(); i++) {
            t_url += SourceUrlList.get(i);
            if (i < SourceUrlList.size() - 1) {
                t_url += ",";
            }
        }
        t_CarSell.setSourceUrl(t_url);
        return t_CarSell;
    }
}
