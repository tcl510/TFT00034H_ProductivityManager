package y3860172.york.ac.uk.tft00034h_productivitymanager.model;

import android.graphics.Bitmap;

public class picture implements Media {

    Bitmap image;



    public picture(Bitmap image){
        this.image = image;
    }

    public Bitmap getImage() {
        return image;
    }

    @Override
    public int getType() {
        return MEDIA_PICTURE;
    }
}
