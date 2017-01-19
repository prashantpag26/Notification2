package com.indianic.notification2;

import android.app.Application;

/**
 * Created by indianic on 08/08/16.
 */

public class NotificationApp extends Application {
    public static int notificationCount =0;

    public static NotificationApp notificationApp;
    @Override
    public void onCreate() {
        super.onCreate();
        notificationApp = this;

    }

    public static NotificationApp getNotificationApp() {
        return notificationApp;
    }

    public void countIncrement(){
        notificationCount++;
    }

    public int getCount(){
        return notificationCount;
    }

    public void setCountZero(){
        notificationCount =0;
    }
}
