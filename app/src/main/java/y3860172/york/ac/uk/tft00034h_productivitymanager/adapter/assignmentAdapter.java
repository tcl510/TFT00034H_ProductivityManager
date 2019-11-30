package y3860172.york.ac.uk.tft00034h_productivitymanager.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import y3860172.york.ac.uk.tft00034h_productivitymanager.R;
import y3860172.york.ac.uk.tft00034h_productivitymanager.model.Card;
import y3860172.york.ac.uk.tft00034h_productivitymanager.model.assignment_card;
import y3860172.york.ac.uk.tft00034h_productivitymanager.model.tester_card;

public class assignmentAdapter extends RecyclerView.Adapter {
    public List<Card> TempAssignmentList;
    public List<Card> assignmentList;
    Context context;



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
        public assignment_cardViewHolder(View itemView){
            super(itemView);
            //findviewby id
            title = itemView.findViewById(R.id.assignment_title);
            due = itemView.findViewById(R.id.assignment_due);
            empty = itemView.findViewById(R.id.assignment_empty_text);
            button = itemView.findViewById(R.id.assignment_seeMoreButton);
        }


        public void bindView(int position) {
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
                due.setText("10 days");//todo translate time into due date
            }
        }
    }


}
