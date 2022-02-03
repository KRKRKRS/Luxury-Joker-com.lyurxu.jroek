package com.lyurxu.jroek;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GistApi {

    @GET(CNSTN.GistLink)
    Call<ResponseBody> getStringUrl();

}
