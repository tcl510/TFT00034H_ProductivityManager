package y3860172.york.ac.uk.tft00034h_productivitymanager.model;

import android.graphics.Color;

import java.net.URL;

public class weather_card implements Card  {

    @Override
    public int getType(){
        return Card.CARD_WEATHER;
    }
    public String location;
    public String condition;
    public String temperature_string;
    public float temperature;
    public String weather_image = "";
    public URL url;
    public String night_day;

    public weather_card(String location, String condition, float temperature) {
        this.location = location;
        this.condition = condition;
        this.temperature_string = makeTemperatureString(temperature);
//        this.weather_image = weather_image;
    }
    public weather_card(){
        this.location = "--";
        this.condition = "Connect to the internet for weather data";//todo not show weather card unless theres info
        this.temperature_string = null;
        this.weather_image = null;
    }
    //DONE add day night color difference

    public String makeTemperatureString(float temperature){
        return String.valueOf(temperature) + "°C";
    }

    public String getLocation() {
        return location;
    }

    public String getCondition() {
        return condition;
    }

    public String getTemperature_string() {
        return temperature_string;
    }

    public String getWeather_image() {
        return weather_image;
    }

    public int getDayNight(){
        if (night_day == "d"){
            return Color.parseColor("#FFCC80");
        } else {
            return Color.parseColor("#9FA8DA");
        }
    }
}