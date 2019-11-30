package y3860172.york.ac.uk.tft00034h_productivitymanager.model;

import java.util.List;

public class assignments_card implements Card {

    public List<Card> Assignments;
    public String title = "Assignments";

    @Override
    public int getType() {
        return Card.CARD_ASSIGNMENTS;
    }

    public assignments_card(List<Card> assignments) {
        this.Assignments = assignments;
    }

    public List<Card> getAssignments() {
        return Assignments;
    }

    public void setAssignments(List<Card> assignments) {
        Assignments = assignments;
    }

    public String getTitle() {
        return title;
    }
}
