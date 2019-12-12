package y3860172.york.ac.uk.tft00034h_productivitymanager.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import y3860172.york.ac.uk.tft00034h_productivitymanager.R;
import y3860172.york.ac.uk.tft00034h_productivitymanager.model.Media;
import y3860172.york.ac.uk.tft00034h_productivitymanager.model.picture;

public class imageAdaptor extends RecyclerView.Adapter {

    public List<Media> PhotoList;
    Context context;

    public imageAdaptor(List<Media> photoList, Context context) {
        PhotoList = photoList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        switch (viewType) {
            case Media.MEDIA_PICTURE:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.picture,parent,false);
                return new pictureViewHolder (itemView);
            case Media.MEDIA_ADD:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_picture, parent, false);
                Log.d("camera", "camera binded");
                return new add_pictureViewHolder(itemView);
            default:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.picture,parent,false);
                return new pictureViewHolder (itemView);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case Media.MEDIA_PICTURE:
                ((pictureViewHolder) holder).bindView(position);
                break;
            case Media.MEDIA_ADD:
                ((add_pictureViewHolder) holder).bindView(position);
                break;
            default:
                ((pictureViewHolder) holder).bindView(position);
                break;

        }
    }

    @Override
    public int getItemCount() {
        if (PhotoList == null) {
            return 0;
        } else {
            return PhotoList.size();
        }
    }

    @Override
    public int getItemViewType(int position){
        return PhotoList.get(position).getType();
    }

    class pictureViewHolder extends RecyclerView.ViewHolder {

        ImageView imgMedia;
        public pictureViewHolder(View itemView){
            super(itemView);
            imgMedia = itemView.findViewById(R.id.thumbnail);
        }


        public void bindView(int position) {
            picture picture = (picture) PhotoList.get(position);
            //bind data to views
            //textView.setText()..
            imgMedia.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imgMedia.setImageBitmap(picture.getImage());


        }
    }
    class add_pictureViewHolder extends RecyclerView.ViewHolder {


        public add_pictureViewHolder(View itemView){
            super(itemView);

        }
        public void bindView(int position){

        }
    }
}
