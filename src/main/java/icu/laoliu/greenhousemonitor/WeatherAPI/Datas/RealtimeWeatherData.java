/*** Data of getRealtimeWeather API
 * @author Lao-Liu233
 */


package icu.laoliu.greenhousemonitor.WeatherAPI.Datas;

public class RealtimeWeatherData {
    public int code;
    public String updateTime;
    public String fxLink;
    public Now now = new Now();
    public Refer refer;

    public class Now {
        public String obsTime;
        public int temp;
        public int feelsLike;
        public int icon;
        public String text;
        public int wind360;
        public String windDir;
        public int windScale;
        public int windSpeed;
        public int humidity;
        public float precip;
        public int pressure;
        public int vis;
        public int cloud;
        public int dew;
    }
    public class Refer {
        public String[] sources;
        public String[] license;
    }
}
