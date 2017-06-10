package badura.tom.jokes.model.bean;

import java.util.Date;

/**
 * Logged entry that records activity
 */
public class LogRecord {

    private java.util.Date activityDate;
    private String ipAddress;
    private String joke;

    /*
     * Constructor with arguments
     */
    public LogRecord(String ipAddress, String joke) {
        this.ipAddress = ipAddress;
        this.joke = joke;
    }

    public LogRecord() {
        // in case you want set instance variables later
    }


    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    @Override
    public String toString() {
        return "LogRecord{" +
                "activityDate=" + activityDate +
                ", ipAddress='" + ipAddress + '\'' +
                ", joke='" + joke + '\'' +
                '}';
    }
}
