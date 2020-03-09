package com.ldc.wandroid.jg;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.ldc.wandroid.R;
import com.ldc.wandroid.activitys.ShowMessageActivity;

import java.util.Objects;

import cn.jpush.android.api.CustomMessage;
import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.api.NotificationMessage;
import cn.jpush.android.service.JPushMessageReceiver;

import static android.content.Context.NOTIFICATION_SERVICE;

public class JgReceived extends JPushMessageReceiver {
    private static final String TAG = "JgReceived";

    /**
     * 连接极光服务器
     *
     * @param context
     * @param b
     */
    @Override
    public void onConnected(Context context, boolean b) {
        super.onConnected(context, b);
        Log.i(TAG, "onConnected: 连接极光服务器");
    }

    /**
     * 注册极光时的回调
     *
     * @param context
     * @param s
     */
    @Override
    public void onRegister(Context context, String s) {
        super.onRegister(context, s);
        Log.i(TAG, "onRegister: 注册极光时的回调");
    }


    /**
     * 注册和注销别名回调
     *
     * @param context
     * @param jPushMessage
     */
    @Override
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onAliasOperatorResult(context, jPushMessage);
        Log.i(TAG, "onAliasOperatorResult: 注册和注销别名回调");
    }

    /**
     * 接收到推送信息
     *
     * @param context
     * @param notificationMessage
     */
    @Override
    public void onNotifyMessageArrived(Context context, NotificationMessage notificationMessage) {
        super.onNotifyMessageArrived(context, notificationMessage);
        Log.i(TAG, "onNotifyMessageArrived: 接收到推送信息");
    }


    /**
     * 打开推送信息
     *
     * @param context
     * @param notificationMessage
     */
    @Override
    public void onNotifyMessageOpened(Context context, NotificationMessage notificationMessage) {
        super.onNotifyMessageOpened(context, notificationMessage);
        Log.i(TAG, "onNotifyMessageOpened: 打开推送信息");
    }

    /**
     * 接受到自定义信息
     *
     * @param context
     * @param customMessage
     */
    @Override
    public void onMessage(Context context, CustomMessage customMessage) {
        super.onMessage(context, customMessage);
        handleMessage(context, customMessage);
        Log.i(TAG, String.format("onMessage: %s", customMessage.toString()));
        Log.i(TAG, String.format("onMessage: 接受到自定义信息 %s", customMessage.extra));
    }

    //处理自定义信息
    final void handleMessage(final Context context, CustomMessage customMessage) {
        try {
            final String channelID = String.format("%s", customMessage.appId);
            final String channelName = "channel_name";
            //跳转
            Intent intent = new Intent(context, ShowMessageActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("m_title", customMessage.title);
            intent.putExtra("m_message", customMessage.message);
            intent.putExtra("m_extra", customMessage.extra);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            //
            final NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            //适配安卓8.0的消息渠道
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);
                Objects.requireNonNull(notificationManager).createNotificationChannel(channel);
            }
            final NotificationCompat.Builder notification = new NotificationCompat.Builder(context, channelID);

            notification.setAutoCancel(true)
                    .setContentText(customMessage.message)
                    .setContentTitle("您有新消息")
                    .setSmallIcon(R.drawable.icon_icon)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setContentIntent(pendingIntent);

            Objects.requireNonNull(notificationManager).notify((int) (System.currentTimeMillis() / 1000), notification.build());
        } catch (Exception e) {
            Log.e(TAG, String.format("handleMessage: %s", e.getMessage()));
            e.printStackTrace();
        }

    }


}
