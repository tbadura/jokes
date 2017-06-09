package badura.tom.jokes.model.bean;

import java.util.Date;

/**
 * Logged entry that records activity
 */
public class LogRecord {

    private java.util.Date activityDate;
    private String ipAddress;

    /*
     * Constructor with arguments
     */
    public LogRecord(String ipAddress) {
        this.ipAddress = ipAddress;
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

    @Override
    public String toString() {
        return "LogRecord{" +
                "activityDate=" + activityDate +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }
}
