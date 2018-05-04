package com.av.mojaztask.ui;

import com.av.mojaztask.Item;
import com.av.mojaztask.networkUtilities.ApiClient;
import com.av.mojaztask.networkUtilities.ApiInterface;
import com.av.mojaztask.networkUtilities.GetCallback;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mina on 5/4/2018.
 */

public class Model {

    private static ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);

    public static void getItemsList(final GetCallback.onDataFetched listener){
        //showProgress();
        Call<ArrayList<Item>> call=apiInterface.getItemsListApi();
        call.enqueue(new Callback<ArrayList<Item>>() {
            @Override
            public void onResponse(Call<ArrayList<Item>> call, Response<ArrayList<Item>> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                       // items=response.body();
                        listener.onSuccess(response.body());
                    }
                }
                else listener.onFailure("Error.");
            }

            @Override
            public void onFailure(Call<ArrayList<Item>> call, Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });

    }
}
