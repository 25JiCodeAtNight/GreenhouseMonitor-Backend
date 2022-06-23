package icu.laoliu.greenhousemonitor.web.v1.greenhouse.data;

public class WarningRespondData {
    public WarningData[] warningData;

    public  WarningRespondData(int a){
        warningData = new WarningData[a];
        for (int i = 0; i < a; i++) {
            warningData[i] = new WarningData();
        }
    }

    public class WarningData {
        public String id;
        public String name;
    }
}
