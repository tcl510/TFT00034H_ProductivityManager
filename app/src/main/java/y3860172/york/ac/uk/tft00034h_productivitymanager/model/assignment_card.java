package y3860172.york.ac.uk.tft00034h_productivitymanager.model;


import android.util.Log;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import y3860172.york.ac.uk.tft00034h_productivitymanager.types.Assignment;

public class assignment_card implements Card { //todo add button add more functionality

    @Override
    public int getType() {
        return Card.CARD_ASSIGNMENT;
    }

    String assignment_title;
    Date assigment_due;
    int daysLeft;
    public String daysLeftString(int days){
        if (days == 1){
            return days + " day";
        } else {
            return days + " days";
        }
    }


    public assignment_card(Assignment assignment) {
        Log.d("assignment", assignment.toString());
        this.assignment_title = assignment.getTitle();
        this.assigment_due = assignment.getDueDate();
        this.daysLeft = getHowManyDays(assignment.getDueDate());
    }

    public assignment_card(String assignment_title, Date assigment_due) {
        this.assignment_title = assignment_title;
        this.assigment_due = assigment_due;
    }

    public String getAssignment_title() {
        return assignment_title;
    }

    public Date getAssigment_due() {
        return assigment_due; //todo parse time

    }
    public String getDaysLeft(){


        //todo maybe make this countdown?
        return daysLeftString(daysLeft);
    }
    public int getHowManyDays(Date dueDate){
        Date today = new Date();
        long timeDiff = assigment_due.getTime() - today.getTime();
        return (int)TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
    }

}