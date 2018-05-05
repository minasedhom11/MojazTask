package com.av.mojaztask.ui.itemList;
import android.support.annotation.StringRes;
import com.av.mojaztask.networkUtilities.GetCallback;
import java.util.ArrayList;

interface MainContract {
    interface MainView{
         void showProgress();
         void hideProgress();
         void showMsg(String error);
         void showMsg(@StringRes int resId);
         void changeTitle(@StringRes int resId);
         void showFilterBtn();
         void hideFilterBtn();
         void setup_recycler(ArrayList<Item> list, int checkBox_isVisible);
         boolean isNetworkConnected();
         void clearRecyclerView();
         void showBackBtn();
         void hideBackBtn();
         void showReloadBtn();
         void hideReloadBtn();
    }

    interface MainModel {
        void requestItemsListApi(final GetCallback.onDataFetched listener);
    }

    interface MainPresenter{
        void fetchData();
        void onFilterClicked(ArrayList<Item> selected_items);
        void onBackClicked();
        void onReloadClicked();
        void onAttach();
        void onDetach();
    }
}
