package report.friction.entities;

import report.friction.config.OpenWeatherRequestConfig;

public class OpenWeatherRequest {

    private String domain;
    private Double latitude;
    private Double longitude;
    private String units;
    private String apiKey;
    private String subAPI;
    private String exclude;

    private OpenWeatherRequest(OpenWeatherRequestBuilder builder){
        this.domain = builder.domain;
        this.apiKey = builder.apiKey;
        this.units = builder.units;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
        this.subAPI = builder.subAPI;
        this.exclude = builder.exclude;
    }

    public static OpenWeatherRequestBuilder newRequest(){
        return new OpenWeatherRequestBuilder();
    }

    @Override
    public String toString() {
        return String.format("%s?%slat=%f&lon=%f%s%s&appid=%s",
                domain, subAPI, latitude, longitude, exclude, units, apiKey);
    }

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


        public OnecallRequestBuilder onecall() {
            return new OnecallRequestBuilder();
        }

        public TimemachineRequestBuilder timemachine() {
            return new TimemachineRequestBuilder();
        }

        public DaySummaryRequestBuilder daySummary() {
            return new DaySummaryRequestBuilder();
        }

//        public OpenWeatherRequestBuilder(ClimbingAreaEntity area){
//            this.latitude = area.getLat();
//            this.longitude = area.getLon();
//        }
//
//        public void metric(){
//            this.units = "&units=metric";
//        }
//
//        public void imperial(){
//            this.units = "&units=imperial";
//        }
//
//        public void exclude(String exclusion){
//            this.exclude = "&exclude=" + exclusion;
//        }
//
        public OpenWeatherRequest build() {
            return new OpenWeatherRequest(this);
        }

        public String buildString() {
            return new OpenWeatherRequest(this).toString();
        }

        private static class OnecallRequestBuilder extends OpenWeatherRequestBuilder {

            private Double latitude;
            private Double longitude;
            private String subAPI = "?";
            private String exclude = "";
            private String units;

            public OnecallRequestBuilder() {}

            public OpenWeatherRequest build() {
                return new OpenWeatherRequest(this);
            }

        }

        private static class TimemachineRequestBuilder extends OpenWeatherRequestBuilder {

            private Double latitude;
            private Double longitude;
            private String subAPI = "/timemachine?";
            private String units;
            private Integer dt;

            public OpenWeatherRequest build() {
                return new OpenWeatherRequest(this);
            }
        }

        private static class DaySummaryRequestBuilder extends OpenWeatherRequestBuilder {

            private Double latitude;
            private Double longitude;
            private String subAPI = "/day_summary?";
            private String units;
            private String date;

            public OpenWeatherRequest build() {
                return new OpenWeatherRequest(this);
            }
        }
    }
}
