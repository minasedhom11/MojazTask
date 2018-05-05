package com.av.mojaztask.ui.itemList;

public class Item {

   private int id,albumId;
   private String title,url,thumbnailUrl;
   private boolean isSelected;
   public int getId() {
        return id;
    }

    int getAlbumId() {
        return albumId;
    }

    String getTitle() {
        return title;
    }

    String getUrl() {
        return url;
    }

    String getThumbnailUrl() {
        return thumbnailUrl;
    }

    boolean isSelected() {
        return isSelected;
    }

    void setSelected(boolean selected) {
        isSelected = selected;
    }
}
