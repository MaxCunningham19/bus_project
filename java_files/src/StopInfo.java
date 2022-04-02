import java.util.ArrayList;
import java.util.Queue;

public class StopInfo {
    String name,desc;
    String id,code;
    String latitude,longitude;
    String zone_id,url,location_type;

    StopInfo(String info){
        name = null;
        if(info!=null) {
            String[] arr = info.split(",");
            id = arr[0];
            code = arr[1];
            name = arr[2];
            desc = arr[3];
            latitude = arr[4];
            longitude = arr[5];
            zone_id = arr[6];
            url = arr[7];
            location_type = arr[8];
        }
    }

    /*
     * @brief: This method returns the Info provided to the Object in an aesthetic and understandable way
     *
     * @param: NULL
     *
     * @return: String containing the information or if there is no Information a string saying such
     */
    public String print(){
        if(name == null){
            return "STOP INFO NOT FOUND";
        }
        return "Stop Name: "+name+", Stop ID: "+id+", Stop Code: "+code+", Stop Desc: "+desc+", Lat: "+ latitude+", Lon: "+longitude+
                ", Zone ID: "+zone_id+", URL: "+url+", Loc Type: "+location_type;
    }


}
