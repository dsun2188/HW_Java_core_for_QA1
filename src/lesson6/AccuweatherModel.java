package lesson6;

import lesson6.entity.Weather;
import okhttp3.OkHttpClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

public class AccuweatherModel implements WeatherModel {
    //http://dataservice.accuweather.com/forecasts/v1/daily/1day/295212
    //http://dataservice.accuweather.com/forecasts/v1/daily/5day/295212
    private static final String PROTOKOL = "https";
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECASTS = "forecasts";
    private static final String VERSION = "v1";
    private static final String DAILY = "daily";
    private static final String ONE_DAY = "1day";
    private static final String FIVE_DAYS = "5day";
    private static final String API_KEY = "9A9gGw11kw8mm6qA02L8L2kelTKAJKJo";
    private static final String API_KEY_QUERY_PROPERTY = "apikey";
    private static final String LANGUAGE = "language";
    private static final String LANGUAGE_PARAM = "ru";
    private static final String LOCATIONS = "locations";
    private static final String CITIES = "cities";
    private static final String AUTOCOMPLETE = "autocomplete";

    private static final OkHttpClient okHttpClient = new OkHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void getWeather(String сity, Period period) throws IOException {
        switch (period) {
            case NOW:
                HttpUrl httpUrl1 = new HttpUrl.Builder()
                        .scheme(PROTOKOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(ONE_DAY)
                        .addPathSegment("295212") //(detectCityKey(selectedCity))
                        .addQueryParameter(API_KEY_QUERY_PROPERTY, API_KEY)
                        .addQueryParameter(LANGUAGE, LANGUAGE_PARAM)
                        .build();

                Request request1 = new Request.Builder()
                        .url(httpUrl1)
                        .build();

                Response oneDayForecastResponse = okHttpClient.newCall(request1).execute();
                String weatherResponse = oneDayForecastResponse.body().string();
                System.out.println(weatherResponse);


                //TODO: сделать человекочитаемый вывод погоды. Выбрать параметры для вывода на свое усмотрение
                //Например: Погода в городе Москва - 5 градусов по цельсию Expect showers late Monday night
                //dataBaseRepository.saveWeatherToDataBase(new Weather()) - тут после парсинга добавляем данные в БД

//                String infoMin = WeatherResponse.readTree(weatherResponse).at("/DailyForecasts").get(0).at("/Temperature/Minimum/Velue").asText();
//                String infoMax = WeatherResponse.readTree(weatherResponse).at("/DailyForecasts").get(0).at("/Temperature/Maximum/Velue").asText();
//                String infoDay = WeatherResponse.readTree(weatherResponse).at("/DailyForecasts").get(0).at("/Day/IconPhrase").asText();
//                String infoNight = WeatherResponse.readTree(weatherResponse).at("/DailyForecasts").get(0).at("/Nigth/IconPhrase").asText();

//                System.out.println("Максимальная температура в F" + infoMax + "Минимальная температура в F" + infoMin + "Днем" + infoDay + "Ночью" + infoNight);
                break;


            case FIVE_DAYS:
                //TODO*: реализовать вывод погоды на 5 дней

                HttpUrl httpUrl5 = new HttpUrl.Builder()
                        .scheme(PROTOKOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(FIVE_DAYS)
                        .addPathSegment("295212") //(detectCityKey(selectedCity))
                        .addQueryParameter(API_KEY_QUERY_PROPERTY, API_KEY)
                        .addQueryParameter(LANGUAGE, LANGUAGE_PARAM)
                        .build();

                Request request5 = new Request.Builder()
                        .url(httpUrl5)
                        .build();

                Response fiveDayForecastResponse = okHttpClient.newCall(request5).execute();
                String weatherResponseFive = fiveDayForecastResponse.body().string();

//                String infoMin5 = WeatherResponse.readTree(weatherResponseFive).at("/DailyForecasts").get(0).at("/Temperature/Minimum/Velue").asText();
//                String infoMax5 = WeatherResponse.readTree(weatherResponseFive).at("/DailyForecasts").get(0).at("/Temperature/Maximum/Velue").asText();
//                String infoDay5 = WeatherResponse.readTree(weatherResponseFive).at("/DailyForecasts").get(0).at("/Day/IconPhrase").asText();
//                String infoNight5 = WeatherResponse.readTree(weatherResponseFive).at("/DailyForecasts").get(0).at("/Nigth/IconPhrase").asText();

//                System.out.println("Максимальная температура в F" + infoMax5 + "Минимальная температура в F" + infoMin5 + "Днем" + infoDay5 + "Ночью" + infoNight5);

                break;
        }
    }
//    @Override
//    public List<Weather> getSavedToDBWeather() {
//       // return dataBaseRepository.getSavedToDBWeather();
//    }
    private String detectCityKey(String selectCity) throws IOException {
        //http://dataservice.accuweather.com/locations/v1/cities/autocomplete
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(PROTOKOL)
                .host(BASE_HOST)
                .addPathSegment(LOCATIONS)
                .addPathSegment(VERSION)
                .addPathSegment(CITIES)
                .addPathSegment(AUTOCOMPLETE)
                .addQueryParameter(API_KEY_QUERY_PROPERTY, API_KEY)
                .addQueryParameter("q", selectCity)
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .get()
                .addHeader("accept", "application/json")
                .build();

        Response response = okHttpClient.newCall(request).execute();
        String responseString = response.body().string();

        String cityKey = objectMapper.readTree(responseString).get(0).at("/Key").asText();
        return cityKey;


//    public static void  main(String[] a ) throws IOException {
//        (new AccuweatherModel()).getWeather("Санкт-Петербург", Period.NOW);
//    }
//
//    private String detectCityKey(String city) {
//        return null;
    }
}