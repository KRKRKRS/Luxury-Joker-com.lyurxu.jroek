package com.lyurxu.jroek;

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

    private static final String OAI = CNSTN.decode(CNSTN.NTJM_NZ_RI);
    public static final String AFK = CNSTN.decode(CNSTN.LA_ED_RSEVQ_NFJGUERDYW);
    public static String AFId;
    public static boolean afLoaded;

    @Override
    public void onCreate() {
        super.onCreate();
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(this);
        OneSignal.setAppId(OAI);

        new Thread(new Runnable() {
            public void run() {
                try {
                    F_B_K.AIDetgpy = AdvertisingIdClient.getAdvertisingIdInfo(getBaseContext()).getId();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                }

                // todo catches
            }
        }).start();
        MyAppsFlConversoList MAF = new MyAppsFlConversoList();
        AppsFlyerLib.getInstance().init(AFK, MAF, this);
        AppsFlyerLib.getInstance().start(this);
        AFId = AppsFlyerLib.getInstance().getAppsFlyerUID(this);
    }

    static class MyAppsFlConversoList implements AppsFlyerConversionListener {
        @Override
        public void onConversionDataSuccess(Map<String, Object> map) {

            for (String attrName : map.keySet())
                LJ.statusAppsFlyeretgpy = Objects.requireNonNull(map.get(CNSTN.decode("YWZfc3RhdHVz"))).toString();
            if (LJ.statusAppsFlyeretgpy.equals(CNSTN.decode("Tm9uLW9yZ2FuaWM="))) {
                String campaignStr = Objects.requireNonNull(map.get(CNSTN.decode("Y2FtcGFpZ24="))).toString();
                Log.i("MyApp", "campaignStr " + campaignStr);
                ParseStr parserStr = new ParseStr();
                LJ.strAppsFlyeretgpy = parserStr.parse(campaignStr);
            }
            afLoaded = true;
        }

        @Override
        public void onConversionDataFail(String s) {
            Log.i("MyApp", "AppsFl - " +s);
            afLoaded = true; }

        @Override
        public void onAppOpenAttribution(Map<String, String> map) {
            Log.i("MyApp", "AppsFl - ___");
            afLoaded = true;
        }

        @Override
        public void onAttributionFailure(String s) {
            Log.i("MyApp", "AppsFl - " +s);
            afLoaded = true;
        }
    }


}
