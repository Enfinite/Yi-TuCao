package com.netease.enfinite.usermanager;

import android.os.Environment;

public class Tools {
	/**
	 * ¼ì²éÊÇ·ñ´æÔÚSDCard
	 * @return
	 */
	public static boolean hasSdcard(){
		String state = Environment.getExternalStorageState();
		if(state.equals(Environment.MEDIA_MOUNTED)){
			return true;
		}else{
			return false;
		}
	}
}
