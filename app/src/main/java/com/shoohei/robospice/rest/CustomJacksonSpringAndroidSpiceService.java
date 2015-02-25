package com.shoohei.robospice.rest;

import android.app.Notification;
import android.util.Log;

import com.octo.android.robospice.JacksonSpringAndroidSpiceService;

/**
 * Created by trouillard on 24/02/2015.
 */
public class CustomJacksonSpringAndroidSpiceService extends JacksonSpringAndroidSpiceService {

    @Override
    public Notification createDefaultNotification() {
        return null;
    }


    @Override
    public void onCreate() {
        try {
            super.onCreate();
        } catch (Exception e) {
            Log.d("CustomJacksonSpringAndroidSpiceService", e.getMessage());
        }
    }
}
