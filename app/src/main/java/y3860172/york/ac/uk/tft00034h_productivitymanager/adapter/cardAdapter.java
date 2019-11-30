package y3860172.york.ac.uk.tft00034h_productivitymanager.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import y3860172.york.ac.uk.tft00034h_productivitymanager.R;
import y3860172.york.ac.uk.tft00034h_productivitymanager.model.Card;
import y3860172.york.ac.uk.tft00034h_productivitymanager.model.assignments_card;
import y3860172.york.ac.uk.tft00034h_productivitymanager.model.tester_card;
import y3860172.york.ac.uk.tft00034h_productivitymanager.model.weather_card;

public class cardAdapter extends RecyclerView.Adapter{
    public List<Card> infoList;
    Context context;
    private assignmentAdapter massignmentAdapter;//todo add assignmentAdapter

    public cardAdapter(List<Card> infoList, Context context){
        this.infoList = infoList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        switch(viewType){
            case Card.CARD_TESTER_CARD:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
                return new tester_cardViewHolder(itemView);
            case Card.CARD_WEATHER:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card2,parent,false);
                return new weather_cardViewHolder(itemView);
            case Card.CARD_ASSIGNMENTS:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_of,parent,false);
                return new assignments_cardViewHolder(itemView);
            default:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_of,parent,false);
                return new tester_cardViewHolder(itemView);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case Card.CARD_TESTER_CARD:
                ((tester_cardViewHolder) holder).bindView(position);
                break;
            case Card.CARD_WEATHER:
                ((weather_cardViewHolder) holder).bindView(position);
                break;
            case Card.CARD_ASSIGNMENTS:
                ((assignments_cardViewHolder) holder).bindView(position);
            default:
                ((assignments_cardViewHolder) holder).bindView(position);
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (infoList == null){
            return 0;
        } else {
            return infoList.size();
        }
    }

    @Override
    public int getItemViewType(int position){
        return infoList.get(position).getType();
    }

    public void setInfoList(List<? extends Card> thisinfoList){
        if (infoList == null){
            infoList = new ArrayList<>();
        }
        infoList.clear();
        infoList.addAll(thisinfoList);
        notifyDataSetChanged();
    }




    class tester_cardViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        TextView txtSubtitle;
        TextView txtSupporting;
        ImageView imgAvatar;
        ImageView imgMedia;
        public tester_cardViewHolder(View itemView){
            super(itemView);
            //findviewby id
            txtTitle = itemView.findViewById(R.id.tester_card_title_text);
            txtSubtitle = itemView.findViewById(R.id.tester_card_subtitle_text);
            txtSupporting = itemView.findViewById(R.id.tester_card_supporting_text);
            imgAvatar = itemView.findViewById(R.id.tester_card_avatar_image);
            imgMedia = itemView.findViewById(R.id.tester_card_media_image);
        }


        public void bindView(int position) {
            tester_card card = (tester_card) infoList.get(position);
            Log.d("something", card.getSubtitle());
            //bind data to views
            //textView.setText()..
            txtTitle.setText(card.getTitle());
            txtSubtitle.setText(card.getSubtitle());
            txtSupporting.setText(card.supporting);
            imgMedia.setImageResource(card.getMedia());
            imgAvatar.setImageResource(card.getAvatar());
        }
    }

    class weather_cardViewHolder extends RecyclerView.ViewHolder {
        TextView weatherCondition;
        TextView weatherLocation;
        TextView weatherTempature;
        ImageView weatherImage;
        public weather_cardViewHolder(View itemView){

            super(itemView);
            //fineviewby id
            weatherCondition = itemView.findViewById(R.id.weather_condition);
            weatherLocation = itemView.findViewById(R.id.weather_location);
            weatherTempature = itemView.findViewById(R.id.weather_tempature);
            weatherImage = itemView.findViewById(R.id.weather_image);
        }

        public void bindView(int position){
            weather_card card = (weather_card) infoList.get(position);
            weatherCondition.setText(card.getCondition());
            weatherLocation.setText(card.getLocation());
            weatherTempature.setText(card.getTemperature_string());

//            weatherImage.setImageResource(card.getWeather_image());
            //14:36 https://www.youtube.com/watch?v=Vyqz_-sJGFk

            Glide.with(context).load(card.weather_image).into(weatherImage);

        }
    }

    class assignments_cardViewHolder extends RecyclerView.ViewHolder{
        RecyclerView assignment_list;
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);

        TextView title;

        public assignments_cardViewHolder(View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.first_thing_title);
            assignment_list = itemView.findViewById(R.id.list_of_events_list);
            assignment_list.setLayoutManager(layoutManager);

        }
        public void bindView(int position){
            assignments_card card = (assignments_card) infoList.get(position);
            title.setText(card.getTitle());
//            Log.d("something", "adapter invoked");


            massignmentAdapter = new assignmentAdapter(card.getAssignments(), context);


            assignment_list.setAdapter(massignmentAdapter);


        }
    }
}




//credits to https://iteritory.com/android-cardview-tutorial-with-example/
//cards system based on android materials

//inspired by https://gist.github.com/keinix/46848bd52117f533c26b20300ba6abda
