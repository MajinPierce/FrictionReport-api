package report.friction.entities;

import report.friction.config.OpenWeatherRequestConfig;

public class OpenWeatherRequest {

    private String domain;
    private Double latitude;
    private Double longitude;
    private String units;
    private String apiKey;

    private OpenWeatherRequest(OpenWeatherRequestBuilder builder){
        this.domain = builder.domain;
        this.apiKey = builder.apiKey;
        this.units = builder.units;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setLatitudeLongitude(ClimbingAreaEntity area){
        this.latitude = area.getLat();
        this.longitude = area.getLon();
    }

    public static OpenWeatherRequestBuilder newRequest(ClimbingAreaEntity area){
        return new OpenWeatherRequestBuilder(area);
    }

    @Override
    public String toString() {
        return String.format("%slat=%f&lon=%f&exclude=minutely&units=%s&appid=%s",
                domain , latitude, longitude, units, apiKey);
    }

    public static class OpenWeatherRequestBuilder {

        public String domain = OpenWeatherRequestConfig.domain;
        public String apiKey = OpenWeatherRequestConfig.apiKey;
        private String units = OpenWeatherRequestConfig.units;
        private Double latitude;
        private Double longitude;

        public OpenWeatherRequestBuilder(ClimbingAreaEntity area){
            this.latitude = area.getLat();
            this.longitude = area.getLon();
        }

        public void isMetric(){
            this.units = "metric";
        }

        public void isImperial(){
            this.units = "imperial";
        }

        public OpenWeatherRequest build() {
            return new OpenWeatherRequest(this);
        }

        public String buildString() {
            return new OpenWeatherRequest(this).toString();
        }

    }
}
