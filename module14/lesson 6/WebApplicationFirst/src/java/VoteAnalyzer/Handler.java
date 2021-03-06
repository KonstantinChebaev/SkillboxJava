package VoteAnalyzer;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Handler extends DefaultHandler {
    private static SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    private HashMap<Integer, WorkTime> voteStationWorkTimes = new HashMap<>();
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("voter")) {
            String name = attributes.getValue("name");
            String birthDay = attributes.getValue("birthDay");
            try {
                DBConnection.countVoter(name, birthDay);
            } catch (SQLException e) {
                e.printStackTrace();
            }
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

    @Override
    public void endDocument() throws SAXException {
        try {
            DBConnection.flushCounter();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void printResults() {
        System.out.println("Voting station work times: ");
        for (Integer votingStation : voteStationWorkTimes.keySet()) {
            WorkTime workTime = voteStationWorkTimes.get(votingStation);
            System.out.println("\t" + votingStation + " - " + workTime);
        }
        System.out.println("Duplicated voters: ");
        try {
            DBConnection.printVoterCounts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public HashMap getWorkTimes (){
        return voteStationWorkTimes;
    }

}
