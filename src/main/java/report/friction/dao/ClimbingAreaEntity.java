package report.friction.dao;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;

@Entity
@Table(name="ClimbingArea")
@NoArgsConstructor
public class ClimbingAreaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String areaName;
    private Double latitude;
    private Double longitude;
    private Instant updatedAt;
    @OneToOne(mappedBy = "climbingArea")
    private CurrentWeather currentWeather;
    @OneToMany(mappedBy = "climbingArea")
    private ArrayList<HourlyWeather> hourlyWeather;
    @OneToMany(mappedBy = "climbingArea")
    private ArrayList<DailyWeather> dailyWeather;

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
    }
}
