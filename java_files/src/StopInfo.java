import java.util.ArrayList;
import java.util.Queue;

public class StopInfo {
    String name,desc;
    int id,code;
    double latitude,longitude;
    String zone_id,url,location_type;

    StopInfo(String info){
        name = null;
        if(info!=null) {
            String[] arr = info.split(",");
            id = Integer.parseInt(arr[0]);
            code = Integer.parseInt(arr[1]);
            name = arr[2];
            desc = arr[3];
            latitude = Double.parseDouble(arr[4]);
            longitude = Double.parseDouble(arr[5]);
            zone_id = arr[6];
            url = arr[7];
            location_type = arr[8];
        }
    }

    public String print(){
        if(name == null){
            return "STOP INFO NOT FOUND";
        }
        return "Stop ID: "+id+", Stop Code: "+code+", Stop Name: "+name+", Stop Desc: "+desc+", Lat: "+ latitude+", Lon: "+longitude+", Zone ID: "+zone_id+", URL: "+url+
                ", Loc Type: "+location_type;
    }


}
