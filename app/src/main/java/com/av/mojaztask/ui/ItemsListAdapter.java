package com.av.mojaztask.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.av.mojaztask.Item;
import com.av.mojaztask.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

/**
 * Created by Mina on 5/3/2018.
 */

public class ItemsListAdapter  extends RecyclerView.Adapter<ItemsListAdapter.ViewHolder> {

    ArrayList<Item> items;
    Context context;
    ArrayList<Item> selected_items;
    int counter=0;
    int checkBox_visible;

    public ItemsListAdapter(Context context,ArrayList<Item> items,int checkBox_visible){
        this.items=items;
        this.context=context;
        this.checkBox_visible=checkBox_visible;
        selected_items=new ArrayList<>();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }

        ImageView photo=itemView.findViewById(R.id.photo_iv);
        TextView title=itemView.findViewById(R.id.title_tv);
        TextView album_id=itemView.findViewById(R.id.album_id_tv);
        CheckBox checkBox=itemView.findViewById(R.id.checkBox);
    }


    @NonNull
    @Override
    public ItemsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_items_list, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemsListAdapter.ViewHolder holder, final int position) {
        holder.checkBox.setVisibility(checkBox_visible);
        final Item item=items.get(position);
        holder.title.setText(item.getTitle());
        holder.album_id.setText(String.valueOf(item.getAlbumId()));

        Glide.with(context)
                .applyDefaultRequestOptions(new RequestOptions()
                        .fitCenter().transform(new CircleCrop()))
                .load(item.getUrl())
                .into(holder.photo);
        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setChecked(items.get(position).isSelected());

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    if(b){
                        if(selected_items.size()<10) {
                            if(!selected_items.contains(items.get(position))) {
                                selected_items.add(items.get(position));
                                items.get(position).setSelected(true);
                            }

                        }else{
                            Toast.makeText(context, "You have selected 10 items.", Toast.LENGTH_SHORT).show();
                            items.get(position).setSelected(false);
                            compoundButton.setChecked(items.get(position).isSelected());
                        }
                    }
                    else{
                        selected_items.remove(items.get(position));
                        items.get(position).setSelected(false);
                    }

            }

        });

       //if(items.get(position).isSelected())

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, String.valueOf(item.isSelected() + " "+position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public ArrayList<Item> get_selected_items(){
        return selected_items;
    }
}
