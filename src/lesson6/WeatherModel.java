package lesson6;

import lesson6.entity.Weather;

import java.io.IOException;
import java.util.List;

public interface WeatherModel {
    void getWeather(String city, Period period) throws IOException;


    // public List<Weather> getSavedToDBWeather();

}
