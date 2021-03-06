package com.google.firebase.codelab.friendlychat.utilities;

import android.app.Application;
import android.content.Context;

/**
 * Created by patelkev on 11/10/16.
 */


public class ChatApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        ChatApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return ChatApplication.context;
    }

    public static AutoReplyClient getAutoReplyClient() {
        return AutoReplyClient.getInstance();
    }

    public static FirebaseClient getFirebaseClient() {
        return (FirebaseClient) FirebaseClient.getInstance();
    }

}