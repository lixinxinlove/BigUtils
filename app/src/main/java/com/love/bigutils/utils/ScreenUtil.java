package com.love.bigutils.utils;

import java.util.UUID;

import com.eventmosh.evente.EventApp;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;

public class ScreenUtil {
	/**
	 * 获取屏幕dpi
	 * 
	 * @param context
	 * @return
	 */
	public static float getDensity(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		dm = context.getApplicationContext().getResources().getDisplayMetrics();
		float density = dm.density;
		return density;
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 设备唯一标识
	 * 
	 * @param context
	 * @return
	 */
	public static String getDeviceId(Context context) {
		StringBuilder deviceId = new StringBuilder();
		try {

			// IMEI（imei）
			TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			String imei = tm.getDeviceId();
			if (StringUtils.isNotNull(imei)) {
				deviceId.append(imei);
				Log.e("getDeviceId : ", deviceId.toString());
				return deviceId.toString();
			}
			// 序列号（sn）
			String sn = tm.getSimSerialNumber();
			if (StringUtils.isNotNull(sn)) {
				deviceId.append(sn);
				Log.e("getDeviceId : ", deviceId.toString());
				return deviceId.toString();
			}
			// wifi mac地址
			WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
			WifiInfo info = wifi.getConnectionInfo();
			String wifiMac = info.getMacAddress();
			if (StringUtils.isNotNull(wifiMac)) {
				deviceId.append(wifiMac);
				Log.e("getDeviceId : ", deviceId.toString());
				return deviceId.toString();
			}
			// 如果上面都没有， 则生成一个id：随机码
			String uuid = getUUID(context);
			if (StringUtils.isNotNull(uuid)) {
				deviceId.append(uuid);
				Log.e("getDeviceId : ", deviceId.toString());
				return deviceId.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.e("getDeviceId : ", deviceId.toString());
		return deviceId.toString();
	}

	/**
	 * 得到全局唯一UUID
	 */
	private static String getUUID(Context context) {
		String uuid = "";
		SharedPreferences mShare = EventApp.mPref;
		if (mShare != null) {
			uuid = mShare.getString("uuid", "");
		}

		if (StringUtils.isNull(uuid)) {
			uuid = UUID.randomUUID().toString();
			mShare.edit().putString("uuid", uuid).commit();
		}
		Log.e("UUID", "getUUID : " + uuid);
		return uuid;
	}

}
