package y3860172.york.ac.uk.tft00034h_productivitymanager.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import y3860172.york.ac.uk.tft00034h_productivitymanager.AddAssignment;
import y3860172.york.ac.uk.tft00034h_productivitymanager.R;
import y3860172.york.ac.uk.tft00034h_productivitymanager.model.Card;
import y3860172.york.ac.uk.tft00034h_productivitymanager.model.assignment_card;

//import android.widget.ImageView;
//import y3860172.york.ac.uk.tft00034h_productivitymanager.model.tester_card;

public class assignmentAdapter extends RecyclerView.Adapter {
    private List<Card> TempAssignmentList = new ArrayList<>();
    private List<Card> assignmentList;
    private Context context;



    public assignmentAdapter(List<Card> assignmentList, Context context) {
        this.assignmentList = assignmentList;
        this.context = context;
        Log.d("something", "adapter invoked");
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int ViewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.assignment,parent,false);
        return new assignmentAdapter.assignment_cardViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((assignment_cardViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        if (assignmentList == null){
            return 0;
        } else {
            if (assignmentList.size() == 0){
                TempAssignmentList = new ArrayList<>();
                TempAssignmentList.add(new assignment_card("",new Time(1)));
                return 1;
            } else{
                TempAssignmentList = new ArrayList<>();
                TempAssignmentList = assignmentList;
                return assignmentList.size();
            }
        }
    }

    @Override
    public int getItemViewType(int position){
        return TempAssignmentList.get(position).getType();
    }

    public void setInfoList(List<? extends Card> thisinfoList){
        if (TempAssignmentList == null){
            TempAssignmentList = new ArrayList<>();
        }
        TempAssignmentList.clear();
        TempAssignmentList.addAll(thisinfoList);
        notifyDataSetChanged();
    }

    class assignment_cardViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView due;
        TextView empty;
        Button button;
        private assignment_cardViewHolder(View itemView){
            super(itemView);
            //done impliment the position in get index, basically fix getadapterposition (solved adaptor side)
            //findviewby id
            title = itemView.findViewById(R.id.assignment_title);
            due = itemView.findViewById(R.id.assignment_due);
            empty = itemView.findViewById(R.id.assignment_empty_text);
            button = itemView.findViewById(R.id.assignment_seeMoreButton);

            button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int pos = getAdapterPosition();
                    Log.d("adaptor", String.valueOf(pos));
                   /* if (pos != RecyclerView.NO_POSITION){
                        Movie clickedDataItem = movieList.get(pos);
                        Intent intent = new Intent(mContext, DetailActivity.class);
                        intent.putExtra("movies", clickedDataItem );
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getOriginalTitle(), Toast.LENGTH_SHORT).show();
                    }*/
                    Intent i = new Intent(context, AddAssignment.class);
                    assignment_card card = (assignment_card) TempAssignmentList.get(pos);
                    Bundle extras = new Bundle();

                    i.putExtra("index", pos);
                    i.putExtra("assignment", card.getThisAssignment());
                    ((Activity) context).startActivityForResult(i, 420);
                }
            });
        }


        private void bindView(int position) {
            assignment_card card = (assignment_card) TempAssignmentList.get(position);

            //bind data to views
            //textView.setText()..
            if (card.getAssignment_title() == ""){
                button.setVisibility(View.INVISIBLE);
                title.setVisibility(View.INVISIBLE);
                due.setVisibility(View.INVISIBLE);
                empty.setText("Yay, no assignments");
            } else {
                empty.setVisibility(View.INVISIBLE);
                title.setText(card.getAssignment_title());
                due.setText(card.getDaysLeft());//done translate time into due date
            }
        }
    }
}
