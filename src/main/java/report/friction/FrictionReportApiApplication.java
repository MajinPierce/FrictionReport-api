package report.friction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class FrictionReportApiApplication {

	//TODO write unit tests

	public static void main(String[] args) {
		log.info("Starting Friction Report");
		SpringApplication.run(FrictionReportApiApplication.class, args);
	}

}
