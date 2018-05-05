package com.av.mojaztask.networkUtilities;

import com.av.mojaztask.ui.itemList.Item;

import java.util.ArrayList;


public abstract class GetCallback {

    public interface onDataFetched{
        void onSuccess(ArrayList<Item> items);
        void onFailure(String s);
    }
}
