package y3860172.york.ac.uk.tft00034h_productivitymanager.model;

import android.media.Image;
import android.view.View;

import y3860172.york.ac.uk.tft00034h_productivitymanager.R;


public interface Card {
//    int CARD_WEATHER = R.layout.card2;
//    int CARD_TESTER_CARD = R.layout.card;
//    int CARD_FIRST_THING = R.layout.list_of;

    int CARD_WEATHER = R.layout.card2;
    int CARD_TESTER_CARD = R.layout.card;
    int CARD_FIRST_THING = R.layout.list_of;
    int CARD_ASSIGNMENTS = R.layout.list_of;
    int CARD_ASSIGNMENT = R.layout.assignment;
    int CARD_CALENDER_EVENT = R.layout.calender_event;

    int getType();
}

