package com.av.mojaztask.ui;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.av.mojaztask.Item;
import com.av.mojaztask.R;
import com.av.mojaztask.networkUtilities.ApiClient;
import com.av.mojaztask.networkUtilities.ApiInterface;
import com.av.mojaztask.networkUtilities.GetCallback;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements GetCallback.onDataFetched {

    private ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
    RecyclerView recyclerView;
    ItemsListAdapter adapter;
    Button filter_btn;
    ProgressBar progressBar;
    ArrayList<Item> items,selected_items;
    TextView toolBarTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar_top=findViewById(R.id.toolbar_top);
        toolBarTitle=toolbar_top.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar_top);
        if(getSupportActionBar()!=null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);// Display title name

        items=new ArrayList<>();
        selected_items=new ArrayList<>();
        progressBar=findViewById(R.id.progress_bar);
        recyclerView=findViewById(R.id.recycler_viw);

        getItemsList(this);

        filter_btn=findViewById(R.id.filter_btn);
        filter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(adapter.get_selected_items().size()>0){
                    selected_items=adapter.get_selected_items();
                    setup_recycler(adapter.get_selected_items(),View.GONE);
                    toolBarTitle.setText(getResources().getString(R.string.title_after_filter));
                    filter_btn.setVisibility(View.GONE);

                }
                else
                    Toast.makeText(MainActivity.this, "Please select some items first!", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void setup_recycler(ArrayList<Item> list,int vis){
        if(getApplicationContext()!=null){
            adapter=new ItemsListAdapter(this,list,vis);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView .setAdapter(adapter);
        }
    }


    public void getItemsList(final GetCallback.onDataFetched listener){
        showProgress();
        Call<ArrayList<Item>> call=apiInterface.getItemsListApi();
        call.enqueue(new Callback<ArrayList<Item>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Item>> call, @NonNull Response<ArrayList<Item>> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        items=response.body();
                        listener.onSuccess(items);
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

    @Override
    public void onSuccess(ArrayList<Item> items) {
     //   toolBarTitle.setText(getResources().getString(R.string.title_before_filter));
        setup_recycler(items,View.VISIBLE);
        hideProgress();
    }

    @Override
    public void onFailure(String s) {
        hideProgress();
    }

    @Override
    public void onBackPressed() {
       if( selected_items.size()<=10&&selected_items.size()>0){
           recyclerView.removeAllViewsInLayout();
           getItemsList(this);
           toolBarTitle.setText(getResources().getString(R.string.title_before_filter));
           filter_btn.setVisibility(View.VISIBLE);
       }
       else
        super.onBackPressed();
    }

    public void showProgress(){progressBar.setVisibility(View.VISIBLE);}
    public void hideProgress(){progressBar.setVisibility(View.GONE);}
}
