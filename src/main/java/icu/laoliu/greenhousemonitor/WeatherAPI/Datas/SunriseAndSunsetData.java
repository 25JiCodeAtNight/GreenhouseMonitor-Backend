/*** Data of getSunriseAndSunsetData API
 * @author Lao-Liu233
 */

package icu.laoliu.greenhousemonitor.WeatherAPI.Datas;

public class SunriseAndSunsetData {
    public int code;
    public String updateTime;
    public String fxLink;
    public String sunrise;
    public String sunset;
    public Refer refer;

    public class Refer {
        public String[] sources;
        public String[] license;
    }
}