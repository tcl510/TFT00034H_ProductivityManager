package y3860172.york.ac.uk.tft00034h_productivitymanager.model;

import android.media.Image;
import android.view.View;

import y3860172.york.ac.uk.tft00034h_productivitymanager.R;

public class Card {


    public String title = "Default Title";
    public String subtitle = "Default Subtitle goes here, more words, words and more words";
    public String supporting = "a subtitle to behold!";
    public int avatar = R.drawable.tedted;
    public int media = R.drawable.tedted;
    public String type;

    public Card(String type, String title, String subtitle, String supporting, int avatar, int media) {
        this.type = type;
        this.title = title;
        this.subtitle = subtitle;
        this.supporting = supporting;
        this.avatar = avatar;
        this.media = media;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getSupporting() {
        return supporting;
    }

    public void setSupporting(String supporting) {
        this.supporting = supporting;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public int getMedia() {
        return media;
    }

    public void setMedia(int media) {
        this.media = media;
    }


}

