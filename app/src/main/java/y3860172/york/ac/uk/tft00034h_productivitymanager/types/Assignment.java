package y3860172.york.ac.uk.tft00034h_productivitymanager.types;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Assignment implements Parcelable {
    public List<String> photos;
    public String title;
    public Date dueDate;
    public String notes;
    //constructor
    public Assignment(List<String> photos, String title, Date dueDate, String notes) {
        this.photos = photos;
        this.title = title;
        this.dueDate = dueDate;
        this.notes = notes;
    }
    public Assignment(){

    }


    //get and set
    public List<Bitmap> getPhotosBitmaps() {
        List<Bitmap> photolist = new ArrayList<>();
        for (String path: photos) {
            if (path == null){
                Log.d("storage", "path null");
            }
            photolist.add(makeImage(path));
        }
        return photolist;
    }
    public List<String> getPhotos() {
        List<String> photolist = new ArrayList<>();
        for (String path: photos) {
            if (path == null){
                Log.d("storage", "path null");
            }
            photolist.add(path);
        }
        return photolist;
    }


    public Bitmap makeImage(String image_file_path){
        return BitmapFactory.decodeFile(image_file_path);
    }

    public String getTitle() {

        return title;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public String getNotes() {
        return notes;
    }

    //parcel
    protected Assignment(Parcel in) {
//        photos = in.createTypedArrayList(Bitmap.CREATOR);
        photos = in.createStringArrayList();
        dueDate = new Date(in.readLong());
        title = in.readString();
        notes = in.readString();
    }

    public static final Creator<Assignment> CREATOR = new Creator<Assignment>() {
        @Override
        public Assignment createFromParcel(Parcel in) {
            return new Assignment(in);
        }

        @Override
        public Assignment[] newArray(int size) {
            return new Assignment[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeTypedList(photos);
        dest.writeStringList(photos);
        dest.writeLong(dueDate.getTime());
        dest.writeString(title);
        dest.writeString(notes);
    }
}
