package response;


import java.util.TreeSet;

public class WorkTime
{
    private int station;
    private String time;
    private TreeSet<TimePeriod> periods;

    public WorkTime()
    {
        station = 0;
        time = "null";
        periods = new TreeSet<TimePeriod>();
    }

    public void addVisitTime(long visit)
    {
        TimePeriod newPeriod = new TimePeriod(visit, visit);
        for(TimePeriod period : periods)
        {
            if(period.compareTo(newPeriod) == 0)
            {
                period.appendTime(visit);
                return;
            }
        }
        periods.add(new TimePeriod(visit, visit));
    }

    public String toString()
    {
        String line = "";
        for(TimePeriod period : periods)
        {
            if(!line.isEmpty()) {
                line += ", ";
            }
            line += period;
        }
        return line;
    }

    public void setData (int st){
        station = st;
        time = this.toString();
    }

    public int getStation() {
        return station;
    }

    public void setStation(int station) {
        this.station = station;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public TreeSet<TimePeriod> getPeriods(){
        return periods;
    }

}
