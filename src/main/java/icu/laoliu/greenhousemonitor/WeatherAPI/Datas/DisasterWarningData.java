/** Data of getDisasterWarning API
 * @author Lao-Liu233
 */

package icu.laoliu.greenhousemonitor.WeatherAPI.Datas;

import com.fasterxml.jackson.annotation.JsonInclude;

public class DisasterWarningData {
    public int code;
    public String updateTime;
    public String fxLink;
    public Warning[] warning;
    public Refer refer;



    public class Warning{
       public String id;
        String sender;
        String pubTime;
       public String title;
        String startTime;
        String endTime;
        String status;
        String level;
        String severity;
        String severityColor;
        String type;
        String typeName;
        String urgency;
        String certainty;
        String text;
        String related;
    }

    public class Refer {
        public String[] sources;
        public String[] license;
    }
}