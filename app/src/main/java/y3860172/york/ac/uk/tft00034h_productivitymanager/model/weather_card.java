package y3860172.york.ac.uk.tft00034h_productivitymanager.model;

public class weather_card implements Card  {

    @Override
    public int getType(){
        return Card.CARD_WEATHER;
    }
    public String location;
    public String condition;
    public String temperature_string;
    public float temperature;
    public int weather_image;

    public weather_card(String location, String condition, float temperature, int weather_image) {
        this.location = location;
        this.condition = condition;
        this.temperature_string = makeTemperatureString(temperature);
        this.weather_image = weather_image;
    }

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

    public int getWeather_image() {
        return weather_image;
    }
}