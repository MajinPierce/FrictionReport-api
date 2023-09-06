package report.friction.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import report.friction.entities.ClimbingAreaEntity;
import report.friction.repositories.ClimbingAreaRepository;

import java.util.List;

@Slf4j
@Component
public class StartupService implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private ClimbingAreaRepository climbingAreaRepository;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        List<ClimbingAreaEntity> areas = climbingAreaRepository.findAll();
        for (ClimbingAreaEntity area: areas) {
            log.info("{}, {} | {}, {} | {}",
                    area.getFullName(),
                    area.getState(),
                    area.getLat(),
                    area.getLon(),
                    area.getMountainProjectUrl());
        }
    }
}