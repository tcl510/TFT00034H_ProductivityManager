package y3860172.york.ac.uk.tft00034h_productivitymanager.model;

public class picture implements Media {

    int image;



    public picture(int image){
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    @Override
    public int getType() {
        return MEDIA_PICTURE;
    }
}
