package com.netease.enfinite.usermanager;

import android.graphics.drawable.BitmapDrawable;

/**
 * �û��ľ�����Ϣ�������ǳƣ�ͷ����Ϣ
 * @author ASUS
 *
 */
public  final class UserInfo {
	
	private static String user_name = null ;
	private static BitmapDrawable user_image = null ;
	
	public static String getUser_name() {
		return user_name;
	}
	public static void setUser_name(String user_name) {
		UserInfo.user_name = user_name;
	}
	public static BitmapDrawable getUser_image() {
		return user_image;
	}
	public static void setUser_image(BitmapDrawable user_image) {
		UserInfo.user_image = user_image;
	}
	
}
