package main;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import response.WorkTime;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Handler extends DefaultHandler {
    private static SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    private static HashMap<Integer, WorkTime> voteStationWorkTimes = new HashMap<Integer, WorkTime>();
    private static ArrayList<WorkTime> times = new ArrayList<WorkTime>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("voter")) {
//            String name = attributes.getValue("name");
//            String birthDay = attributes.getValue("birthDay");
//            try {
//                DBConnection.countVoter(name, birthDay);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
        } else if (qName.equals("visit")) {
            Integer station = Integer.parseInt(attributes.getValue("station"));
            Date time = null;
            try {
                time = visitDateFormat.parse(attributes.getValue("time"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            WorkTime workTime = voteStationWorkTimes.get(station);
            if (workTime == null) {
                workTime = new WorkTime();
                voteStationWorkTimes.put(station, workTime);
            }
            workTime.addVisitTime(time.getTime());
        }
    }

//    @Override
//    public void endDocument() throws SAXException {
//        try {
//            DBConnection.flushCounter();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public static ArrayList<WorkTime> getWorkTimesList () {
        for (Integer votingStation : voteStationWorkTimes.keySet()) {
            WorkTime workTime = voteStationWorkTimes.get(votingStation);
            workTime.setData(votingStation);
        }
        times.addAll(voteStationWorkTimes.values());
        return times;
    }

//        System.out.println("Duplicated voters: ");
//        try {
//            DBConnection.printVoterCounts();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    public static ArrayList<WorkTime> getWorkTimes() {
    return times;
}
    public static WorkTime getOneWorkTime(int id) {
         return voteStationWorkTimes.get(id);
    }

}
