package y3860172.york.ac.uk.tft00034h_productivitymanager.model;


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
    String daysLeft;


    public assignment_card(Assignment assignment) {
        this.assignment_title = assignment.getTitle();
        this.assigment_due = assignment.getDueDate();
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
        Date today = new Date();
        long timeDiff = today.getTime() - assigment_due.getTime();
        float timeDiffConverted = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
        return String.valueOf(timeDiffConverted);
    }
}