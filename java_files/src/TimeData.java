public class TimeData {
    int hour,min,sec;
    StopInfo info;

    TimeData(int hour, int min, int sec, String info){
        this.hour = hour;
        this.min = min;
        this.sec = sec;
        this.info = new StopInfo(info);
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

    public int getHour() {
        return hour;
    }

    public int getMin() {
        return min;
    }

    public int getSec() {
        return sec;
    }
}

