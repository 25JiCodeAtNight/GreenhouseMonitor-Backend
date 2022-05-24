/** Data of getDisasterWarning API
 * @author Lao-Liu233
 */

package icu.laoliu.greenhousemonitor.WeatherAPI.Datas;

public class DisasterWarningData {
    public int code;
    public String updateTime;
    public String fxLink;
    public String[] warning;
    public Refer refer;

    public class Refer {
        public String[] sources;
        public String[] license;
    }
}