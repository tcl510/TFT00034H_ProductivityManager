package y3860172.york.ac.uk.tft00034h_productivitymanager.model;

public class Card2 {
    public String weather_state;
    public String location;
    public float temperature_value;
    public String temperature_string;
    public int weather_image;


    public Card2(String weather_state, String location, float temperature_value, int weather_image) {
        this.weather_state = weather_state;
        this.location = location;
        this.temperature_value = temperature_value;
        setTemperature_string(makeWeatherString(temperature_value));
        this.weather_image = weather_image;
    }


    public String makeWeatherString(float tempature_value){
        return tempature_value + "°c";
    }


    public String getWeather_state() {
        return weather_state;
    }

    public void setWeather_state(String weather_state) {
        this.weather_state = weather_state;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getTemperature_value() {
        return temperature_value;
    }

    public void setTemperature_value(float temperature_value) {
        this.temperature_value = temperature_value;
    }

    public String getTemperature_string() {
        return temperature_string;
    }

    public void setTemperature_string(String temperature_string) {
        this.temperature_string = temperature_string;
    }

    public int getWeather_image() {
        return weather_image;
    }

    public void setWeather_image(int weather_image) {
        this.weather_image = weather_image;
    }
}
