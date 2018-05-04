package com.av.mojaztask;

/**
 * Created by Mina on 5/3/2018.
 */

public class Item {

   private int id,albumId;
   private String title,url,thumbnailUrl;
   private boolean isSelected;
    public int getId() {
        return id;
    }

    public int getAlbumId() {
        return albumId;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
