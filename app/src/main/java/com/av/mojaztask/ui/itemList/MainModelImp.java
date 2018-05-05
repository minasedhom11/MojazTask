package com.av.mojaztask.ui.itemList;

import android.support.annotation.NonNull;

import com.av.mojaztask.networkUtilities.ApiClient;
import com.av.mojaztask.networkUtilities.ApiInterface;
import com.av.mojaztask.networkUtilities.GetCallback;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainModelImp implements MainContract.MainModel {

    private final ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);

    @Override
    public void requestItemsListApi(final GetCallback.onDataFetched listener){
        Call<ArrayList<Item>> call=apiInterface.getItemsListApi();
        call.enqueue(new Callback<ArrayList<Item>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Item>> call, @NonNull Response<ArrayList<Item>> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        listener.onSuccess(response.body());
                    }
                }
                else listener.onFailure("Error.");
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Item>> call, @NonNull Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });

    }
}
