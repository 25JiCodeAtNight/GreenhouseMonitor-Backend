/*** Data of getMinuteRain API
 * @author Lao-Liu233
 */

package icu.laoliu.greenhousemonitor.WeatherAPI.Datas;

public class MinuteRainData {
    public int code;
    public String updateTime;
    public String fxLink;
    public String summary;
    public Minutely[] minutel;
    public Refer refer;

    public class Minutely {
        public String fxTime;
        public float precip;
        public String type;
    }

    public class Refer {
        public String[] sources;
        public String[] license;
    }
}