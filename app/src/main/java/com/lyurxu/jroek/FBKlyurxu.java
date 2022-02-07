package com.lyurxu.jroek;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.applinks.AppLinkData;

public class FBKlyurxu {
    public static String strDeeplyurxu;
    private String fbIdlyurxu;
    private LJ mainActivitylyurxu;

    public FBKlyurxu(String fbIdlyurxu, LJ mainActivitylyurxu) {
        this.fbIdlyurxu = fbIdlyurxu;
        this.mainActivitylyurxu = mainActivitylyurxu;
    }

    public void initlyurxu() {
        initFcbklyurxu();

        AppLinkData.fetchDeferredAppLinkData(mainActivitylyurxu.getApplication(), new AppLinkData.CompletionHandler() {
                    @Override
                    public void onDeferredAppLinkDataFetched(AppLinkData appLinkData) {
                        String deepLinklyurxu = appLinkData.getTargetUri().getQuery();
                        ParseStrlyurxu parserStlyurxur = new ParseStrlyurxu();
                        strDeeplyurxu = parserStlyurxur.parselyurxu(deepLinklyurxu);
                    }
                }
        );

    }
    private void initFcbklyurxu() {
        FacebookSdk.setApplicationId(fbIdlyurxu);
        FacebookSdk.setAdvertiserIDCollectionEnabled(true);
        FacebookSdk.sdkInitialize(mainActivitylyurxu.getApplicationContext());
        FacebookSdk.fullyInitialize();
        FacebookSdk.setAutoInitEnabled(true);
        FacebookSdk.setAutoLogAppEventsEnabled(true);
        AppEventsLogger.activateApp(mainActivitylyurxu.getApplication());
    }
}
