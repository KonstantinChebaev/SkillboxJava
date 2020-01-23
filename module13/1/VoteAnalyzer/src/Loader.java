import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;


/*
можно сделать следующие потоки
1 - делает тему со временем рабочим этих сраных пунктов
2 - делает тему с повторяющимися пидорами
 */


public class Loader {

    public static void main(String[] args) throws Exception {
        String fileName = "res/data-18M.xml";
        long t = System.currentTimeMillis();
        parseFile(fileName);
        System.out.println((System.currentTimeMillis()-t)+" ms");
    }

    private static void parseFile(String fileName) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        Handler handler = new Handler();
        parser.parse(new File(fileName), handler);
        handler.printResults();
    }


}