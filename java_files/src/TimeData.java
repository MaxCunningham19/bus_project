public class TimeData {
    int hour,min,sec;
    String info;

    TimeData(int hour, int min, int sec, String info){
        if(!isValidTime(hour, min, sec)) {
            throw  new IllegalArgumentException("An invalid time was passed to TimeData");
        }
            this.hour = hour;
            this.min = min;
            this.sec = sec;
            this.info = info;

    }

    TimeData(String time, String info){
        String[] times = time.split(":");
        int hour = Integer.parseInt(times[0]);
        int min = Integer.parseInt(times[1]);
        int sec = Integer.parseInt(times[2]);
        if(!isValidTime(hour, min, sec)) {
            throw  new IllegalArgumentException("An invalid time was passed to TimeData");
        }
        this.hour = hour;
        this.min = min;
        this.sec = sec;
        this.info = info;

    }

    public boolean isGreater(TimeData t){
        if(hour > t.getHour()) return true;
        if(hour == t.getHour() && min > t.getMin()) return true;
        return hour == t.getHour() && min == t.getMin() && sec > t.getSec();
    }

    public boolean isValidTime(int hour, int min, int sec){
        if(hour>23) return false;
        if(hour == 23 && min>59) return false;
        return hour != 23 || min != 59 || sec <= 59;

    }

    public boolean isEqual(TimeData t){
        return hour==t.getHour() && min == t.getMin() && sec == t.getSec();
    }

    public boolean isLess(TimeData t){
        if(hour < t.getHour()) return true;
        if(hour == t.getHour() && min < t.getMin()) return true;
        return hour == t.getHour() && min == t.getMin() && sec < t.getSec();
    }

    public int getHour() {
        return hour;
    }

    public int getMin() {
        return min;
    }

    public int getSec() {
        return sec;
    }

    public String getInfo(){
        if(info == null) return "No Info";
        String[] arr = info.split(",");
        return "Trip ID: "+arr[0]+ ", Arrival Time: "+arr[1] + ", stop ID: "+arr[3] +", Shape Dist Travelled: "+arr[arr.length-1];
    }
}

