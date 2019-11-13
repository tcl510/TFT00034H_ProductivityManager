package y3860172.york.ac.uk.tft00034h_productivitymanager.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import y3860172.york.ac.uk.tft00034h_productivitymanager.R;
import y3860172.york.ac.uk.tft00034h_productivitymanager.model.Card;
import y3860172.york.ac.uk.tft00034h_productivitymanager.model.Card2;

public class cardAdapter extends RecyclerView.Adapter<cardAdapter.cardViewHolder>{
    private List<Card> infoList;
    Context context;

    public cardAdapter(List<Card> infoList, Context context ){
        this.infoList = infoList;
        this.context = context;
    }

    @Override
    public cardViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        cardViewHolder gvh = null;
        View cardView = null;
        //inflating the file
        switch (viewType) {
            case 0:
                cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
                gvh = new cardViewHolder(cardView);
                break;
            case 1:
                cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card2, parent, false);
                gvh = new cardViewHolder(cardView);
                break;
        }
        return gvh;
    }
    @Override
    public void onBindViewHolder(cardViewHolder holder, final int position){
//        int viewType = getItemViewType(position);
        if (getItemViewType(position) == 0) {
            holder.txtTitle.setText(infoList.get(position).getTitle());
            holder.txtSubtitle.setText(infoList.get(position).getSubtitle());
            holder.txtSupporting.setText(infoList.get(position).getSupporting());
            holder.imgAvatar.setImageResource(infoList.get(position).getAvatar());
            holder.imgMedia.setImageResource(infoList.get(position).getMedia());
        }
        if (getItemViewType(position) == 1){
            holder.txtTitle.setText(infoList.get(position).getTitle());
            holder.txtSubtitle.setText(infoList.get(position).getSubtitle());
            holder.txtSupporting.setText(infoList.get(position).getSupporting());
//            holder.imgAvatar.setImageResource(infoList.get(position).getAvatar());
            holder.imgMedia.setImageResource(infoList.get(position).getMedia());
        }
    }

    @Override
    public int getItemCount(){
        return infoList.size();
    }

    @Override
    public int getItemViewType(int position) {
        String type = infoList.get(position).getType();
        if (type == "weather"){
            return 1;
        } else {
            return 0;
        }
    }

    public class cardViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        TextView txtSubtitle;
        TextView txtSupporting;
        ImageView imgAvatar;
        ImageView imgMedia;
        public cardViewHolder (View view){
            super(view);
            txtTitle = view.findViewById(R.id.title_text);
            txtSubtitle = view.findViewById(R.id.subtitle_text);
            txtSupporting = view.findViewById(R.id.supporting_text);
            imgAvatar = view.findViewById(R.id.avatar_image);
            imgMedia = view.findViewById(R.id.media_image);
        }
    }
}




//credits to https://iteritory.com/android-cardview-tutorial-with-example/
//cards system based on android materials
