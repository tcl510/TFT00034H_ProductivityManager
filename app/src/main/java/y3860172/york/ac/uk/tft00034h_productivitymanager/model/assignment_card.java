package y3860172.york.ac.uk.tft00034h_productivitymanager.model;

import java.sql.Time;

public class assignment_card implements Card { //todo add button add more functionality

    @Override
    public int getType() {
        return Card.CARD_ASSIGNMENT;
    }

    String assignment_title;
    Time assigment_due;

    public assignment_card(String assignment_title, Time assigment_due) {
        this.assignment_title = assignment_title;
        this.assigment_due = assigment_due;
    }

    public String getAssignment_title() {
        return assignment_title;
    }

    public Time getAssigment_due() {
        return assigment_due; //todo parse time
    }

}
