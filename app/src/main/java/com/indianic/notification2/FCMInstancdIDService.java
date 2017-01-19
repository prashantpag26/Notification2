package com.indianic.notification2;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by indianic on 5/30/16.
 */
public class FCMInstancdIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e("", "Refreshed token: " + refreshedToken);
        System.out.println("FCMInstancdIDService :: Token :: " + refreshedToken);

        // TODO: Implement this method to send any registration to your app's servers.
       // sendRegistrationToServer(refreshedToken);
    }
}
