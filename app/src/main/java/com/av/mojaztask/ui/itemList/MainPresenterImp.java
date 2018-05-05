package com.av.mojaztask.ui.itemList;

import android.view.View;

import com.av.mojaztask.R;
import com.av.mojaztask.networkUtilities.GetCallback;
import java.util.ArrayList;

public class MainPresenterImp implements MainContract.MainPresenter,GetCallback.onDataFetched{

    private MainContract.MainView mainView;
    private MainModelImp model;
    private ArrayList<Item> items;

    MainPresenterImp(MainContract.MainView mainView) {
        this.mainView=mainView;
        model=new MainModelImp();
    }

    @Override
    public void fetchData(){
        if(mainView.isNetworkConnected()) {
            mainView.showProgress();
            model.requestItemsListApi(this);
        }
        else{
            mainView.showMsg(R.string.connection_error);
        }
    }

    @Override
    public void onFilterClicked(ArrayList<Item> selected_items){
        if(mainView.isNetworkConnected()) {
            if (selected_items.size() > 0) {
                mainView.setup_recycler(selected_items, View.GONE);
                mainView.changeTitle(R.string.title_after_filter);
                mainView.hideFilterBtn();
                mainView.showBackBtn();
                mainView.hideReloadBtn();
            } else {
                mainView.showMsg(R.string.no_item_selected);
            }
        }
        else{
            mainView.showMsg(R.string.connection_error);
        }
    }

    @Override
    public void onBackClicked() {
                mainView.hideBackBtn();
                mainView.showReloadBtn();
                mainView.showFilterBtn();
                mainView.clearRecyclerView();
                //fetchData();
                loadAllItems();
                mainView.changeTitle(R.string.title_before_filter);
    }

    @Override
    public void onReloadClicked() {
        fetchData();
        mainView.hideFilterBtn();
    }

    @Override
    public void onAttach() {
        fetchData();
    }

    @Override
    public void onDetach() {
        mainView=null;
    }

    @Override
    public void onSuccess(ArrayList<Item> items) {
        this.items=items;
        mainView.setup_recycler(items,View.VISIBLE);
        mainView.showFilterBtn();
        mainView.hideProgress();
    }

    @Override
    public void onFailure(String s) {
        mainView.showMsg(s);
    }

    private void loadAllItems(){
        if(items!=null&&items.size()>0)
        mainView.setup_recycler(items,View.VISIBLE);
    }


}
