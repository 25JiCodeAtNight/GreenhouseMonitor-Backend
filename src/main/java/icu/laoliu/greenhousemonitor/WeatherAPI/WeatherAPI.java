/*** QWeather API
 * @author Lao-liu233
 */

package icu.laoliu.greenhousemonitor.WeatherAPI;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import icu.laoliu.greenhousemonitor.HTTP.HTTP;
import icu.laoliu.greenhousemonitor.WeatherAPI.Datas.*;

public class WeatherAPI {
    private final String KEY;
    private final String location;

    /***
     * Initial WeatherAPI
     * 
     * @param KEY      QWeather Private KEY
     * @param location Request location
     */
    public WeatherAPI(String KEY, String location) {
        this.KEY = KEY;
        this.location = location;
    }

    /***
     * Get Realtime Weather
     * 
     * @return Current weather data
     */
    public RealtimeWeatherData getRealtimeWeather() {
        String url = "https://devapi.qweather.com/v7/weather/now?key=" + KEY + "&location=" + location
                + "&lang=zh&unit=m";
        HTTP http = new HTTP(url);
        RealtimeWeatherData data;
        Gson gson = new Gson();
        data = gson.fromJson(http.get(), RealtimeWeatherData.class);
        return data;
    }

    public DisasterWarningData getDisasterWarning() {
        String url = "https://devapi.qweather.com/v7/warning/now?key=" + KEY + "&location=" + location + "&lang=zh";
        HTTP http = new HTTP(url);
        DisasterWarningData data;
        Gson gson = new Gson();
        data = gson.fromJson(http.get(), DisasterWarningData.class);
        return data;
    }

    public MinuteRainData getMinuteRain() {
        String url = "https://devapi.qweather.com/v7/minutely/5m?key=" + KEY + "&location=" + location + "&lang=zh";
        HTTP http = new HTTP(url);
        MinuteRainData data;
        Gson gson = new Gson();
        data = gson.fromJson(http.get(), MinuteRainData.class);
        return data;
    }

    public AirConditionData getAirCondition() {
        String url = "https://devapi.qweather.com/v7/air/5d?key=" + KEY + "&location=" + location + "&lang=zh";
        HTTP http = new HTTP(url);
        AirConditionData data;
        Gson gson = new Gson();
        data = gson.fromJson(http.get(), AirConditionData.class);
        return data;
    }

    public SunriseAndSunsetData getSunriseAndSunset() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat();
        dateFormatter.applyPattern("yyyyMMdd");
        Date date = new Date();
        String url = "https://devapi.qweather.com/v7/astronomy/sun?key=" + KEY + "&location=" + location +
                "&date=" + dateFormatter.format(date) + "&lang=zh";
        HTTP http = new HTTP(url);
        SunriseAndSunsetData data;
        Gson gson = new Gson();
        data = gson.fromJson(http.get(), SunriseAndSunsetData.class);
        return data;
    }
}