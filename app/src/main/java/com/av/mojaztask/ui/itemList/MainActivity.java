package com.av.mojaztask.ui.itemList;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;
import com.av.mojaztask.R;
import com.av.mojaztask.databinding.ActivityMainBinding;
import com.av.mojaztask.networkUtilities.CheckNetwork;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity implements MainContract.MainView {

    private ItemsListAdapter adapter;
    private MainPresenterImp mainPresenter;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.toolbarTop);
        if(getSupportActionBar()!=null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        mainPresenter=new MainPresenterImp(this);
        mainPresenter.onAttach();
        binding.filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresenter.onFilterClicked(adapter.get_selected_items());
            }
        });

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresenter.onBackClicked();
            }
        });

        binding.reloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresenter.onReloadClicked();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.onDetach();
    }

    @Override
    public void setup_recycler( ArrayList<Item> list, int visibility){
        if(getApplicationContext()!=null){
            adapter=new ItemsListAdapter(this,list,visibility);
            binding.recyclerViw.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
            binding.recyclerViw.setScrollbarFadingEnabled(true);
            binding.recyclerViw .setAdapter(adapter);
        }
    }

    @Override
    public boolean isNetworkConnected() {
        return CheckNetwork.isNetworkConnected(this);
    }

    @Override
    public void clearRecyclerView() {
        binding.recyclerViw.removeAllViewsInLayout();
    }

    @Override
    public void onBackPressed() {
        if( adapter.getItemCount()<=10 && adapter.getItemCount()>0) {
            mainPresenter.onBackClicked();
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    public void showProgress(){
        binding.progressBar.setVisibility(View.VISIBLE);}

    @Override
    public void hideProgress(){
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showMsg(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMsg(int resId) {
        Toast.makeText(this, getResources().getString(resId), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void changeTitle(int resId) {
        binding.toolbarTitle.setText(resId);
    }

    @Override
    public void showFilterBtn() {
        binding.filterBtn.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideFilterBtn() {
        binding.filterBtn.setVisibility(View.GONE);
    }

    @Override
    public void showBackBtn(){binding.backBtn.setVisibility(View.VISIBLE);}

    @Override
    public void hideBackBtn(){binding.backBtn.setVisibility(View.GONE);}

    @Override
    public void showReloadBtn() {
        binding.reloadBtn.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideReloadBtn() {
        binding.reloadBtn.setVisibility(View.GONE);
    }

}
