package main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import response.WorkTime;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Controller
public class DefaultController {

    @RequestMapping("/")
    public String index (Model model) throws Exception {

        String fileName = "res/data-0.2M.xml";
        ArrayList<WorkTime> results = parseFile(fileName);

        model.addAttribute("count", results.size());
//        я не понял зачем нужен этот код. его исполнение создает таблицу с повторяющимися значениями
//        String rawText = results.stream()
//                .map(row -> String.format("Station %d ", row.getStation()))
//                .collect(Collectors.joining("\n"));
//        model.addAttribute("rawText", rawText);
        return "index";
    }

    private ArrayList<WorkTime> parseFile(String fileName) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        Handler handler = new Handler();
        parser.parse(new File(fileName), handler);
        return handler.getWorkTimesList();
    }
}
