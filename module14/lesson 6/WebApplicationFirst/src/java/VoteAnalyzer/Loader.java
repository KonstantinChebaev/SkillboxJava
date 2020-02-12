package VoteAnalyzer;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class Loader {

    public static void main(String[] args) throws Exception {
        String fileName = "res/data-0.2M.xml";
        long t = System.currentTimeMillis();
        parseFile(fileName);
        System.out.println((System.currentTimeMillis()-t)+" ms");
    }

    public static Handler parseFile(String fileName) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        Handler handler = new Handler();
        parser.parse(new File(fileName), handler);
        return handler;
        //handler.printResults();
    }


}