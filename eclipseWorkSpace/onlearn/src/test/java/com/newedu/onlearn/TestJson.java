package com.newedu.onlearn;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newedu.onlearn.mapper.UsersMapper;
import com.newedu.onlearn.po.CarUser;
import com.newedu.onlearn.util.D;

public class TestJson
{
	@Test
	public  void TestJson() throws JsonProcessingException
	{
		ObjectMapper name = new ObjectMapper();
		Temp t_temp = new Temp();
		List<Temp> t_mm=new LinkedList<Temp>();
		t_mm.add(new Temp());
		t_mm.add(new Temp());
		t_mm.add(new Temp());
		t_mm.add(new Temp());
		//123
		System.out.println(name.writeValueAsString(t_mm));
		
		
	}
	@Test
	public void TestSql() throws IOException
	{
//		SqlSession t_Session=D.Connect();
//		UsersMapper t_Mapper=t_Session.getMapper(UsersMapper.class);
//		CarUser t_User=t_Mapper.GetUserById(2);
//		System.out.println(t_User);
	}
}
class Temp
{
  public Integer haha=10;
  public String mm="Ygg";
}