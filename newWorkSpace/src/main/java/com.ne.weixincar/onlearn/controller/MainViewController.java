package com.ne.weixincar.onlearn.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ne.weixincar.onlearn.pojo.OneCarSell;
import com.ne.weixincar.onlearn.runtimeData.CarSellMananger;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainViewController{

    @RequestMapping("/MainViewController/doPost")
    public void MainView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost--------------------");
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("catch-control", "no-catch");
        PrintWriter out = response.getWriter();
        //回复界面显示的数据
        List<OneCarSell> t_carSellList= CarSellMananger.get_instance().GetUsersByDefault();
        ObjectMapper t_JsonObj = new ObjectMapper();
        out.write(t_JsonObj.writeValueAsString(t_carSellList));
        out.flush();
        out.close();
    }
}
