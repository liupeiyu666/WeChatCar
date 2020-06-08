package com.newedu.onlearn.util;

import java.io.UnsupportedEncodingException;

public class GameUtils
{
   public static String GetUTF(String p_String) throws UnsupportedEncodingException
{
	return   new String(p_String.getBytes("ISO-8859-1"), "UTF-8");
}
}
