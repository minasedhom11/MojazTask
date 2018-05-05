package com.av.mojaztask.networkUtilities;


import com.av.mojaztask.ui.itemList.Item;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("photos")
    Call<ArrayList<Item>> getItemsListApi();
}
