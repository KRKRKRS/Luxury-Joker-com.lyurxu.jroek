package com.lyurxu.jroek;

import static com.lyurxu.jroek.ParseStrlyurxu.decodlyurxue;

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

public class ApplClsslyurxu extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        OnSIntlyurxu();
        ApsFlIntlyurxu();
        new Thread(new Runnable() {
            public void run() {
                try {
                    LJ.AD_IDlyurxu = AdvertisingIdClient.getAdvertisingIdInfo(getBaseContext()).getId();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.i("MyApp", e.toString());
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                    Log.i("MyApp", e.toString());
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                    Log.i("MyApp", e.toString());
                }
            }
        }).start();
    }

    private void OnSIntlyurxu() {
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(this);
        OneSignal.setAppId(decodlyurxue(CNSTNlyurxu.OneSignalIdlyurxu));
    }

    private void ApsFlIntlyurxu() {
        MyAppsFlyerListener MAF = new MyAppsFlyerListener();
        AppsFlyerLib.getInstance().init(decodlyurxue(CNSTNlyurxu.AFKeylyurxu), MAF, this);
        AppsFlyerLib.getInstance().start(this);
        LJ.AppsFl_Idlyurxu = AppsFlyerLib.getInstance().getAppsFlyerUID(this);
    }


    static class MyAppsFlyerListener implements AppsFlyerConversionListener {
        @Override
        public void onConversionDataSuccess(Map<String, Object> map) {
            for (String attrName : map.keySet())
                LJ.statusAppsFlyerlyurxu = Objects.requireNonNull(map.get(decodlyurxue("YWZfc3RhdHVz"))).toString();
            if (LJ.statusAppsFlyerlyurxu.equals(decodlyurxue("Tm9uLW9yZ2FuaWM="))) {
                ParseStrlyurxu parserStr = new ParseStrlyurxu();
                LJ.strAppsFlyerlyurxu = parserStr.parselyurxu(Objects.requireNonNull(map.get(decodlyurxue("Y2FtcGFpZ24="))).toString());
            }
        }

        @Override
        public void onConversionDataFail(String s) {
        }

        @Override
        public void onAppOpenAttribution(Map<String, String> map) {
        }

        @Override
        public void onAttributionFailure(String s) {
        }
    }
}
