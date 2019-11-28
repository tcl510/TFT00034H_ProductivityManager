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

public class cardAdapter2 {

    public class card2Adapter extends RecyclerView.Adapter<card2Adapter.card2ViewHolder>{
        private List<Card> infoList;
        Context context;

        public card2Adapter(List<Card> infoList, Context context ){
            this.infoList = infoList;
            this.context = context;
        }

        @Override
        public card2ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            //inflating the file
            View cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card2, parent, false);
            card2ViewHolder gvh = new card2ViewHolder(cardView);
            return gvh;
        }
        @Override
        public void onBindViewHolder(card2ViewHolder holder, final int position){
            holder.txtLocation.setText(infoList.get(position).getTitle());
            holder.txtWeather.setText(infoList.get(position).getSubtitle());
            holder.txtTemperature.setText(infoList.get(position).getSupporting());
            holder.imgWeather.setImageResource(infoList.get(position).getMedia());
        }

        @Override
        public int getItemCount(){
            return infoList.size();
        }


        public class card2ViewHolder extends RecyclerView.ViewHolder {
            TextView txtLocation;
            TextView txtWeather;
            TextView txtTemperature;
            ImageView imgWeather;

            public card2ViewHolder (View view){
                super(view);
                txtLocation = view.findViewById(R.id.location_text);
                txtWeather = view.findViewById(R.id.event_title);
                txtTemperature = view.findViewById(R.id.supporting_text);
                imgWeather = view.findViewById(R.id.media_image);
            }
        }
    }

}
