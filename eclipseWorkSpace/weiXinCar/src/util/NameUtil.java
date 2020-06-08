package com.neuedu.onlearn.util;

public class NameUtil {
	/**
	 * 转换变量命名风格
	 * @param varName
	 * @return
	 */
	public static String convert2Caml(String varName) {
		//根据下划线进行分割
		String[] temp = varName.split("_");
		StringBuilder varSb = new StringBuilder();
		for(int i = 0;i< temp.length;i++) {
			if(i == 0) {
				//第一个词，大小写不变
				varSb.append(temp[i]);
			}else {
				varSb.append(firstUpper(temp[i]));
			}
		}
		return varSb.toString();
	}
	/**
	 * 首字母转大写
	 * @param varName
	 * @return
	 */
	public static String firstUpper(String varName) {
		StringBuilder varSb = new StringBuilder();
		String firstAlp = varName.substring(0, 1).toUpperCase();
		varSb.append(firstAlp);
		//追加其他字母
		varSb.append(varName.substring(1, varName.length()));
		return varSb.toString();
	}
	public static void main(String[] args) {
		String varName = "text_kjsfh_dlkfj";
		String rs = convert2Caml(varName);
		System.out.println(rs);
		Class clz = NameUtil.class;
		System.out.println(clz);
	}
}
