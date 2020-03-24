package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

@SpringBootApplication
public class Main {
    public static void main(String[] args) throws Exception {
        String fileName = "res/data-0.2M.xml";
        parseFile(fileName);
        SpringApplication.run(Main.class, args);
    }

    private static void parseFile(String fileName) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        Handler handler = new Handler();
        parser.parse(new File(fileName), handler);
        handler.printResults();
    }
}
