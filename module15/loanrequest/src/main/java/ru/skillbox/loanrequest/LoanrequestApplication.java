package ru.skillbox.loanrequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import ru.skillbox.loanrequest.storage.StorageProperties;
import ru.skillbox.loanrequest.storage.StorageService;
import ru.skillbox.loanrequest.visits.BVRepository;
import ru.skillbox.loanrequest.visits.BrowserVisits;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class LoanrequestApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanrequestApplication.class, args);
		
	}

}
