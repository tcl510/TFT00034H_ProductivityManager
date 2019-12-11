package y3860172.york.ac.uk.tft00034h_productivitymanager.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class time_card implements Card {

    public String timeString;
    public String dateString;
    public String dayOfWeek;
    public String dayString;
    public String monthString;
    public String fullLowerString;

    public time_card() {

    }

    @Override
    public int getType() {
        return Card.CARD_TIME;
    }

    public String getTimeString() {

        return getTime("hh:mm:ss");
    }

    public String getDateString() {
        return getTime("dd-MM");
    }

    public String getDayOfWeek() {
        return getTime("EEEE");
    }

    public String getDayString() {
        return getTime("aa");
    }

    public String getMonthString() {
        return getTime("MMMM");
    }
    public String getFullLowerString(){
        return getTime("EEEE, dd MMMM");
    }

    public String getTime(String pattern){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        return(simpleDateFormat.format(date));
    }
}
