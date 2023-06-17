package report.friction.services;

import com.opencsv.CSVReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import report.friction.dao.ClimbingAreaEntity;
import report.friction.repositories.ClimbingAreaRepository;

import java.io.FileReader;

@Component
public class StartupService implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private ClimbingAreaRepository climbingAreaRepository;

    private static final String CONFIG_FILE = "./src/main/resources/coordinates.config";

    //TODO switch from csv to yaml or something
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
                String areaName = nextRecord[0];
                String state = nextRecord[1];
                Double latitude = Double.parseDouble(nextRecord[2]);
                Double longitude = Double.parseDouble(nextRecord[3]);
                String mountainProjectUrl = nextRecord[4];
                area.setAreaName(areaName);
                area.setState(state);
                area.setLat(latitude);
                area.setLon(longitude);
                area.setMountainProjectUrl(mountainProjectUrl);
                climbingAreaRepository.save(area);
                System.out.println(String.format(
                        "%s, %s | %f, %f | %s", areaName, state, latitude, longitude, mountainProjectUrl
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}