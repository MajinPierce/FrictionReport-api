package report.friction.models;

public enum Exclude {
    CURRENT("current"),
    MINUTELY("minutely"),
    HOURLY("hourly"),
    DAILY("daily"),
    ALERTS("alerts");

    public final String value;

    private Exclude(String value){
        this.value = value;
    }
}
