package report.friction.services;

import com.opencsv.CSVReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import report.friction.models.ClimbingAreaEntity;
import report.friction.repositories.ClimbingAreaRepository;

import java.io.FileReader;

@Component
public class StartupService implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private ClimbingAreaRepository climbingAreaRepository;

    private static final String CONFIG_FILE = "./src/main/resources/coordinates.config";

    /**
     * Initialize climbing area data (name and coordinates) based on config file.
     * This data is then used to query weather info from open weather map.
     * @param event     Application Ready Event
     */
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        try (FileReader fileReader = new FileReader(CONFIG_FILE);
             CSVReader csvReader = new CSVReader(fileReader);){

            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                ClimbingAreaEntity area = new ClimbingAreaEntity();
                area.setAreaName(nextRecord[0]);
                area.setLatitude(Double.parseDouble(nextRecord[1]));
                area.setLongitude(Double.parseDouble(nextRecord[2]));
                climbingAreaRepository.save(area);
                System.out.println(nextRecord[0] + " " + nextRecord[1] + " " + nextRecord[2]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}