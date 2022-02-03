package com.lyurxu.jroek;

import android.app.Application;
import android.util.Log;

import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;
import com.onesignal.OneSignal;

import java.util.Map;
import java.util.Objects;

public class ApplClss extends Application {
    public static boolean afLoaded;

    @Override
    public void onCreate() {
        super.onCreate();
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(this);
        OneSignal.setAppId(CNSTN.decode(CNSTN.OneSignalId));

        MyAppsFlyerListener MAF = new MyAppsFlyerListener();
        AppsFlyerLib.getInstance().init(CNSTN.decode(CNSTN.AFKey), MAF, this);
        AppsFlyerLib.getInstance().start(this);
        LJ.AppsFl_Id = AppsFlyerLib.getInstance().getAppsFlyerUID(this);
    }


    static class MyAppsFlyerListener implements AppsFlyerConversionListener {
        @Override
        public void onConversionDataSuccess(Map<String, Object> map) {
            for (String attrName : map.keySet())
                LJ.statusAppsFlyer = Objects.requireNonNull(map.get(CNSTN.decode("YWZfc3RhdHVz"))).toString();
            if (LJ.statusAppsFlyer.equals(CNSTN.decode("Tm9uLW9yZ2FuaWM="))) {
                String campaignStr = Objects.requireNonNull(map.get(CNSTN.decode("Y2FtcGFpZ24="))).toString();
                ParseStr parserStr = new ParseStr();
                LJ.strAppsFlyeretgpy = parserStr.parse(campaignStr);
            }
            afLoaded = true;
        }

        @Override
        public void onConversionDataFail(String s) {
            Log.i("MyApp", "AppsFl onConversionDataFail " + s);
            afLoaded = true;
        }

        @Override
        public void onAppOpenAttribution(Map<String, String> map) {
            Log.i("MyApp", "AppsFl onAppOpenAttribution");
            afLoaded = true;
        }

        @Override
        public void onAttributionFailure(String s) {
            Log.i("MyApp", "AppsFl onAttributionFailure" + s);
            afLoaded = true;
        }
    }
}
