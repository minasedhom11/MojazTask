package com.av.mojaztask.networkUtilities;


import com.av.mojaztask.Item;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by Mina on 1/10/2018.
 */

public interface ApiInterface {

    @GET("photos")
    Call<ArrayList<Item>> getItemsListApi();
}
