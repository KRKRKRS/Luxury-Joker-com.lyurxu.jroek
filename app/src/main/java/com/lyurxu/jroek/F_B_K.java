package com.lyurxu.jroek;

import android.os.Handler;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.applinks.AppLinkData;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import java.io.IOException;

public class F_B_K {
    public static String strDeep;
    public static String AD_ID;

    F_B_K(String fbId, LJ mainActivity) {
        FacebookSdk.setApplicationId(fbId);
        FacebookSdk.setAdvertiserIDCollectionEnabled(true);
        FacebookSdk.sdkInitialize(mainActivity.getApplicationContext());
        FacebookSdk.fullyInitialize();
        FacebookSdk.setAutoInitEnabled(true);
        FacebookSdk.setAutoLogAppEventsEnabled(true);

        AppEventsLogger.activateApp(mainActivity.getApplication());

        AppLinkData.fetchDeferredAppLinkData(mainActivity.getApplication(), new AppLinkData.CompletionHandler() {
            @Override
            public void onDeferredAppLinkDataFetched(@Nullable AppLinkData appLinkData) {
                String deepLink = appLinkData.getTargetUri().getQuery();

                ParseStr parserStr = new ParseStr();
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            AD_ID = AdvertisingIdClient.getAdvertisingIdInfo(mainActivity.getBaseContext()).getId();

                            do {} while (AD_ID == null);
                            strDeep = parserStr.parse(deepLink);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (GooglePlayServicesNotAvailableException e) {
                            e.printStackTrace();
                        } catch (GooglePlayServicesRepairableException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
                }
        );
    }
}
