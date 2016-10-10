package com.love.bigutils.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;

/**
 *
 * 软键盘 工具类
 */
public class InputMethodUtils {

	/**
	 * 隐藏软键盘
	 * 
	 * @param context
	 */
	public static void hideInput(Context context) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
		// imm.hideSoftInputFromWindow(context.getCurrentFocus().getApplicationWindowToken(),
		// 0);
		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
	}

}
