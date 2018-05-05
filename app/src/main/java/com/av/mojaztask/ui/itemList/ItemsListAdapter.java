package com.av.mojaztask.ui.itemList;

import android.annotation.SuppressLint;
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
import com.av.mojaztask.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import java.util.ArrayList;

@SuppressWarnings("CanBeFinal")
public class ItemsListAdapter  extends RecyclerView.Adapter<ItemsListAdapter.ViewHolder> {

    private ArrayList<Item> items;
    private Context context;
    private int checkBox_visible;

    ItemsListAdapter(Context context, ArrayList<Item> items, int checkBox_visible){
        this.items=items;
        this.context=context;
        this.checkBox_visible=checkBox_visible;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View itemView) {
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
    public void onBindViewHolder(@NonNull final ItemsListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.checkBox.setVisibility(checkBox_visible);
        final Item item=items.get(position);
        holder.title.setText(item.getTitle());
        holder.album_id.setText(String.valueOf(context.getResources().getString(R.string.album_id)+" "+item.getAlbumId()));
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
                        if(get_selected_items().size()<10) {
                            items.get(position).setSelected(true);
                        }
                        else{
                              Toast.makeText(context, context.getResources().getString(R.string.ten_items_selected), Toast.LENGTH_SHORT).show();
                              items.get(position).setSelected(false);
                              compoundButton.setChecked(items.get(position).isSelected());
                        }
                    }
                    else{
                        items.get(position).setSelected(false);
                    }
            }

        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    ArrayList<Item> get_selected_items(){
        ArrayList<Item> selected_items=new ArrayList<>();
        for(Item item:items){
            if(item.isSelected()&&!selected_items.contains(item)) {
                selected_items.add(item);
            }
            else selected_items.remove(item);
        }
        return selected_items;
    }
}
