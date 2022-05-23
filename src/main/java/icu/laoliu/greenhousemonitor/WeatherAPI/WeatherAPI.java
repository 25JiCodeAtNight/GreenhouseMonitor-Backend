/*** QWeather API
 * @author Lao-liu233
 */

package icu.laoliu.greenhousemonitor.WeatherAPI;

import com.google.gson.Gson;
import icu.laoliu.greenhousemonitor.HTTP.HTTP;
import icu.laoliu.greenhousemonitor.WeatherAPI.Datas.RealtimeWeatherData;

public class WeatherAPI {
    private final String KEY;
    private final String location;

    /*** Initial WeatherAPI
     * @param KEY QWeather Private KEY
     * @param location Request location
     */
    public WeatherAPI(String KEY, String location) {
        this.KEY = KEY;
        this.location = location;
    }

    /*** Get Realtime Weather
     * @return Current weather information
     */
    public RealtimeWeatherData getRealtimeWeather() {
        String url = "https://devapi.qweather.com/v7/weather/now?key=" + KEY + "&location=" + location + "&lang=zh&unit=m";
        HTTP http = new HTTP(url);
        RealtimeWeatherData data;
        Gson gson = new Gson();
        data = gson.fromJson(http.get(), RealtimeWeatherData.class);
        return data;
    }
}