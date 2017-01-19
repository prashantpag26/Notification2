package com.indianic.notification2;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.indianic.notification.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import me.leolin.shortcutbadger.ShortcutBadger;


public class FirebaseNoti extends FirebaseMessagingService {

    boolean success = false;


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        try {
            NotificationApp.getNotificationApp().countIncrement();
            success = ShortcutBadger.applyCount(this, NotificationApp.getNotificationApp().getCount());
            final Bitmap bitmap = Glide.
                    with(this).
                    load(remoteMessage.getData().get("image")).
                    asBitmap().
                    into(500, 200). // Width and height
                    get();
//            final Bitmap bitmap = getBitmapFromURL(remoteMessage.getData().get("image"));
            final String message = remoteMessage.getData().get("message");
            sendNotification(message, bitmap);
            Log.e("Set count=" + NotificationApp.getNotificationApp().getCount(), message + success);
            Log.e("Set count=" + NotificationApp.getNotificationApp().getCount(), "abc" + success);
        } catch (Exception e) {
            Log.e("TAG", e.getMessage());
        }
    }

    //This method is generating a notification and displaying the notification
    private void sendNotification(final String messageBody, Bitmap image) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        int requestCode = 0;
        PendingIntent pendingIntent = PendingIntent.getActivity(this, requestCode, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder noBuilder = new NotificationCompat.Builder(this)
                .setLargeIcon(image)/*Notification icon image*/
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(messageBody)
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(image))/*Notification with Image*/
                .setAutoCancel(true)
                .setSound(sound)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, noBuilder.build()); //0 = ID of notification
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }


}
