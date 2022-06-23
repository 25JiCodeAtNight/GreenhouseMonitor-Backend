package icu.laoliu.greenhousemonitor.web.v1.user.data;

import java.util.ArrayList;
import java.util.List;

public class Get_S_Respond {
    List<Get_S_Record> list=new ArrayList<Get_S_Record>();
    public void add_Record(String n,String g){
        Get_S_Record tmp=new Get_S_Record();
        tmp.name=n;
        tmp.sensorID=g;
        list.add(tmp);
    }
    public Get_S_Record[] turn(){
        return list.toArray(new Get_S_Record[list.size()]);
    }
}
