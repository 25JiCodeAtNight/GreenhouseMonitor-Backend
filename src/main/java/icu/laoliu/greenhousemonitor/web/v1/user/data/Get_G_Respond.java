package icu.laoliu.greenhousemonitor.web.v1.user.data;

import java.util.ArrayList;
import java.util.List;

public class Get_G_Respond {
    List<Get_G_Record> list=new ArrayList<Get_G_Record>();
    public void add_Record(String n,String g){
        Get_G_Record tmp=new Get_G_Record();
        tmp.name=n;
        tmp.greenhouseID=g;
        list.add(tmp);
    }
    public Get_G_Record[] turn(){
        return list.toArray(new Get_G_Record[list.size()]);
    }
}

