package y3860172.york.ac.uk.tft00034h_productivitymanager.types;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Assignment implements Serializable {
    public List<Bitmap> photos;
    public String title;
    public Date dueDate;
    public String notes;

    public Assignment(List<Bitmap> photos, String title, Date dueDate, String notes) {
        this.photos = photos;
        this.title = title;
        this.dueDate = dueDate;
        this.notes = notes;
    }

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
}
