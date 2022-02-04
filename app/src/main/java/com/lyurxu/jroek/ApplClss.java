package com.lyurxu.jroek;

import static com.lyurxu.jroek.ParseStr.decode;

import android.app.Application;
import android.util.Log;

import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.onesignal.OneSignal;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class ApplClss extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        OnSInt();
        ApsFlInt();
    }

    private void OnSInt() {
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(this);
        OneSignal.setAppId(decode(CNSTN.OneSignalId));
    }

    private void ApsFlInt() {
        MyAppsFlyerListener MAF = new MyAppsFlyerListener();
        AppsFlyerLib.getInstance().init(decode(CNSTN.AFKey), MAF, this);
        AppsFlyerLib.getInstance().start(this);
        LJ.AppsFl_Id = AppsFlyerLib.getInstance().getAppsFlyerUID(this);
    }


    static class MyAppsFlyerListener implements AppsFlyerConversionListener {
        @Override
        public void onConversionDataSuccess(Map<String, Object> map) {
            for (String attrName : map.keySet())
                LJ.statusAppsFlyer = Objects.requireNonNull(map.get(decode("YWZfc3RhdHVz"))).toString();
            if (LJ.statusAppsFlyer.equals(decode("Tm9uLW9yZ2FuaWM="))) {
                ParseStr parserStr = new ParseStr();
                LJ.strAppsFlyer = parserStr.parse(Objects.requireNonNull(map.get(decode("Y2FtcGFpZ24="))).toString());
            }
            LJ.afLoaded = true;
        }

        @Override
        public void onConversionDataFail(String s) {
            Log.i("MyApp", "AppsFl onConversionDataFail " + s);
            LJ.afLoaded = true;
        }

        @Override
        public void onAppOpenAttribution(Map<String, String> map) {
            Log.i("MyApp", "AppsFl onAppOpenAttribution");
            LJ.afLoaded = true;
        }

        @Override
        public void onAttributionFailure(String s) {
            Log.i("MyApp", "AppsFl onAttributionFailure" + s);
            LJ.afLoaded = true;
        }
    }
}
