/*** Data of getAirCondition API
 * @author Lao-Liu233
 */

package icu.laoliu.greenhousemonitor.WeatherAPI.Datas;

public class AirConditionData {
    public int code;
    public String updateTime;
    public String fxLink;
    public Daily[] daily;
    public Refer refer;

    public class Daily {
        public String fxDate;
        public int aqi;
        public int level;
        public String catagory;
        public String primary;
    }

    public class Refer {
        public String[] sources;
        public String[] license;
    }
}