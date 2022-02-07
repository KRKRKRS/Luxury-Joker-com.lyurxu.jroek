package com.lyurxu.jroek;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GistApilyurxu {

    @GET(CNSTNlyurxu.Gistklyurxu)
    Call<ResponseBody> getStringUrllyurxu();

}
