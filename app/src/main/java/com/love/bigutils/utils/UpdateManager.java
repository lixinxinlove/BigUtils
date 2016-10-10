package com.love.bigutils.utils;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.eventmosh.evente.EventApp;
import com.eventmosh.evente.receiver.CompleteReceiver;

/**
 * Created by lixinxin on 2016/7/5.
 */
public class UpdateManager {


    private Context mContext;

    public UpdateManager(Context context) {
        this.mContext = context;
    }

    public void checkUpdate(final boolean isToast, String apkUrl) {
        // 检查是否更新
        showNoticeDialog("有新版本立即更新", apkUrl);// 显示更新提示对话框
    }

    /**
     * 显示软件更新对话框
     */
    public void showNoticeDialog(String version_info, final String apkUrl) {
        // 构造对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("更新");
        builder.setMessage(version_info);
        // 更新
        builder.setPositiveButton("马上更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                ToastUtils.showToast(mContext, "程序正在后台下载，可在通知栏查看下载进度");
                // 首先注册一个广播接收者，用于在下载完成后安装apk
                IntentFilter filter = new IntentFilter();
                filter.addAction("android.intent.action.DOWNLOAD_COMPLETE");
                filter.addAction("android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED");
                BroadcastReceiver receiver = new CompleteReceiver();
                EventApp.mContext.registerReceiver(receiver, filter);
                // 调用系统的下载功能去下载应用

                DownloadManager downloadManager = (DownloadManager)
                        EventApp.mContext.getSystemService(EventApp.mContext.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse(apkUrl);//
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                request.setVisibleInDownloadsUi(false);
                request.setTitle("活动易");
                // 这个id要保存起来，在广播接收者里面会根据它判断是否成功下载
                long id = downloadManager.enqueue(request);
                if (PrefUtils.getString(mContext, "UPDATE_ID", "0").equals("0")) {
                    PrefUtils.setString(mContext, "UPDATE_ID", String.valueOf(id));
                } else {
                    return;
                }
            }
        });

        // 稍后更新
        builder.setNegativeButton("稍后更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog noticeDialog = builder.create();
        noticeDialog.show();
    }

    /**
     * 获取app 版本号
     *
     * @param context
     * @return
     */
    public int getVersionCode(Context context) {
        int versionCode = 0;
        PackageManager pm = context.getPackageManager();
        PackageInfo info = null;
        try {
            info = pm.getPackageInfo(context.getApplicationContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (info != null) {
            versionCode = info.versionCode;
        }
        return versionCode;
    }
}
