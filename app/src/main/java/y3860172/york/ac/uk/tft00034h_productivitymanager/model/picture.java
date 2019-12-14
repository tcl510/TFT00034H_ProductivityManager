package y3860172.york.ac.uk.tft00034h_productivitymanager.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class picture implements Media {

    Bitmap image;
    String image_file_path = null;



    public picture(String image_file){
//        this.image = image;
        this.image_file_path = image_file;
    }
    public picture(Bitmap image){
        this.image = image;
    }

    public Bitmap getImage() {
        return image;
    }
    public String getImage_file_path(){
        return image_file_path;
    }

    public Bitmap makeImage(){
        return BitmapFactory.decodeFile(image_file_path);
    }

    @Override
    public int getType() {
        return MEDIA_PICTURE;
    }
}
