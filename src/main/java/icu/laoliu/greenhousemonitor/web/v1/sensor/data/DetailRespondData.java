package icu.laoliu.greenhousemonitor.web.v1.sensor.data;


import java.sql.Timestamp;

public class DetailRespondData {
    public Detail[] details;

    public class Detail {
        public Timestamp time;
        public float temp;
        public float humi;
    }

}
