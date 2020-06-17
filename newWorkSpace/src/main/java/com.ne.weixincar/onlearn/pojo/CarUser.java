package com.ne.weixincar.onlearn.pojo;

import lombok.Data;

@Data
public class CarUser
{
	private String WxOpenId;
	private String Phone;
	private String PersonalNote;
	private Integer VipLevel=0;
}
