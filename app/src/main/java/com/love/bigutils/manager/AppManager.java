package com.love.bigutils.manager;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class AppManager {

	private static List<Activity> activityStack;
	private static AppManager instance;

	private AppManager() {
	}

	/**
	 * 单一实例
	 */
	public static AppManager getAppManager() {
		if (instance == null) {
			instance = new AppManager();
		}
		return instance;
	}

	/**
	 * 添加Activity到堆栈
	 */
	public void addActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new ArrayList<Activity>();
		}
		activityStack.add(activity);
	}

	/**
	 * 结束所有Activity
	 */
	public void finishAllActivity() {
		for (int i = 0; i < activityStack.size(); i++) {
			Activity activity = activityStack.get(i);
			if (activity != null && !activity.isFinishing()) {
				activity.finish();
			}
		}
		activityStack.clear();
		// android.os.Process.killProcess(android.os.Process.myPid());
	}

	/**
	 * 退出应用程序
	 */
	public void AppExit(Context context) {
		try {
			finishAllActivity();
			// Intent intent = new Intent(Intent.ACTION_MAIN);
			// intent.addCategory(Intent.CATEGORY_HOME);
			// intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			// context.startActivity(intent);
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
