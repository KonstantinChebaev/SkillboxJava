import java.util.Date;
import java.util.TreeSet;

/**
 * Created by Danya on 24.02.2016.
 */
public class WorkTime

{
    private TreeSet<TimePeriod> periods;

    /**
     * Set of TimePeriod objects
     */
    public WorkTime()
    {
        periods = new TreeSet<>();
    } //можно наверно не создавать коллекцию каждый раз а сделать ее статической
    //нет потому что каждая коллекция это время работы отдельного участка
    //или вместо коллекции сделать массив с двумя ячейками и класть туда милисекунды

    public void addVisitTime(Date visit)
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
}
