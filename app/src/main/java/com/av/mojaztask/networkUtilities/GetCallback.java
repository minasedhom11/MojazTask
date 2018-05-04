package com.av.mojaztask.networkUtilities;

import android.os.Bundle;

import com.av.mojaztask.Item;

import java.util.ArrayList;

/**
 * Created by Mina on 5/3/2018.
 */

public abstract class GetCallback {

    public interface onDataFetched{
        void onSuccess(ArrayList<Item> items);
        void onFailure(String s);
    }
}
