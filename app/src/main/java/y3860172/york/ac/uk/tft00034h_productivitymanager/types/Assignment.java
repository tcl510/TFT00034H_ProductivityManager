package y3860172.york.ac.uk.tft00034h_productivitymanager.types;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

public class Assignment implements Parcelable {
    public List<Bitmap> photos;
    public String title;
    public Date dueDate;
    public String notes;
    //constructor
    public Assignment(List<Bitmap> photos, String title, Date dueDate, String notes) {
        this.photos = photos;
        this.title = title;
        this.dueDate = dueDate;
        this.notes = notes;
    }



    //get and set
    public List<Bitmap> getPhotos() {
        return photos;
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
        photos = in.createTypedArrayList(Bitmap.CREATOR);
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
        dest.writeTypedList(photos);
        dest.writeLong(dueDate.getTime());
        dest.writeString(title);
        dest.writeString(notes);
    }
}
