package report.friction.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import report.friction.entities.ClimbingAreaEntity;
import report.friction.repositories.ClimbingAreaRepository;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class StartupService {

    private final ClimbingAreaRepository climbingAreaRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void logInitialization() {
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