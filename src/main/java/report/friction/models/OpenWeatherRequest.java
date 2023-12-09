package report.friction.models;

import report.friction.config.OpenWeatherRequestConfig;
import report.friction.entities.ClimbingAreaEntity;

public class OpenWeatherRequest {

    private final String domain;
    private final Double latitude;
    private final Double longitude;
    private final String units;
    private final String apiKey;
    private final String subAPI;
    private final String exclude;

    public OpenWeatherRequest(OpenWeatherRequestBuilder builder){
        this.domain = builder.domain;
        this.apiKey = builder.apiKey;
        this.units = builder.units;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
        this.subAPI = builder.subAPI;
        this.exclude = builder.exclude;
    }

    public static OpenWeatherRequestBuilder newRequest(ClimbingAreaEntity area){
        return new OpenWeatherRequestBuilder(area);
    }

    @Override
    public String toString() {
        return String.format("%s%slat=%f&lon=%f%s&units=%s&appid=%s",
                domain, subAPI, latitude, longitude, exclude, units, apiKey);
    }

    //TODO: Redo this builder
    public static class OpenWeatherRequestBuilder {

        public static final String domain = OpenWeatherRequestConfig.domain;
        public static final String apiKey = OpenWeatherRequestConfig.apiKey;
        private String units = OpenWeatherRequestConfig.units;
        private Double latitude;
        private Double longitude;
        private String subAPI;
        private String exclude = "";
        private String dt = "";
        private String date = "";

        public OpenWeatherRequestBuilder(ClimbingAreaEntity area){
            this.latitude = area.getLat();
            this.longitude = area.getLon();
        }

        public OpenWeatherRequestBuilder onecall() {
            this.subAPI = "?";
            return this;
        }

        public OpenWeatherRequestBuilder timemachine() {
            this.subAPI = "/timemachine?";
            return this;
        }

        public OpenWeatherRequestBuilder daySummary() {
            this.subAPI = "/day_summary?";
            return this;
        }

        public OpenWeatherRequestBuilder metric(){
            this.units = "&units=metric";
            return this;
        }

        public OpenWeatherRequestBuilder imperial(){
            this.units = "&units=imperial";
            return this;
        }

        public OpenWeatherRequestBuilder exclude(Exclude exclude){
            this.exclude = "&exclude=" + exclude.value;
            return this;
        }

        //For timemachine calls
        //unix timestamp
        public OpenWeatherRequestBuilder dt(String dt){
            this.dt = dt;
            return this;
        }

        //for daily summary aggregations
        //YYYY-MM-DD
        public OpenWeatherRequestBuilder date(String date){
            this.date = date;
            return this;
        }

        public String build() {
            return new OpenWeatherRequest(this).toString();
        }

    }
}
