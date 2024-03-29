package y3860172.york.ac.uk.tft00034h_productivitymanager.model;


import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import y3860172.york.ac.uk.tft00034h_productivitymanager.types.Assignment;

public class assignment_card implements Card {

    @Override
    public int getType() {
        return Card.CARD_ASSIGNMENT;
    }

    private String assignment_title;
    private Date assignment_due;
    private int daysLeft;
    private int hoursLeft;
    private Assignment thisAssignment;

    public String daysLeftString(int days){
        if (days < 1){
            return "";
        } else if (days == 1){
            return days + " day";
        } else {
            return days + " days";
        }
    }
    public String hoursLeftString(int hours){
        if (hours == 1){
            return hours + " hour";
        } else {
            return hours + " hours";
        }
    }
    //todo make minutes

    public assignment_card(Assignment assignment) {
        Log.d("assignment", assignment.toString());
        this.assignment_title = assignment.getTitle();
        this.assignment_due = assignment.getDueDate();
        this.daysLeft = getHowManyDays(assignment.getDueDate());
        this.hoursLeft = getHowManyHours(assignment.getDueDate());
        this.thisAssignment = assignment;
    }

    public assignment_card(String assignment_title, Date assignment_due) {
        this.assignment_title = assignment_title;
        this.assignment_due = assignment_due;
        this.daysLeft = getHowManyDays(assignment_due);
        this.hoursLeft = getHowManyHours(assignment_due);
        this.thisAssignment = new Assignment(new ArrayList<String>(), assignment_title, assignment_due, "");
    }

    public String getAssignment_title() {
        return assignment_title;
    }

    public Date getAssignment_due() {
        return assignment_due;
    }

    public String getDaysLeft(){
        return daysLeftString(daysLeft) + " " + hoursLeftString(hoursLeft);
    }

    public int getHowManyHours(Date dueDate){
        Date today = new Date();
        long timeDiff = assignment_due.getTime() - today.getTime() - TimeUnit.MILLISECONDS.convert(daysLeft, TimeUnit.DAYS);
        return (int)TimeUnit.HOURS.convert(timeDiff, TimeUnit.MILLISECONDS);
    }

    public int getHowManyDays(Date dueDate){
        Date today = new Date();
        long timeDiff = assignment_due.getTime() - today.getTime();
        return (int)TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
    }
    //todo make the compare time updatable in sync with timeupdate of mainactiv

    public Assignment getThisAssignment() {
        return thisAssignment;
    }
}