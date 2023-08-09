package cinema.models;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Comparator;
import java.util.Locale;

public class UserLoggingInfo implements Comparable<UserLoggingInfo> {
    private Long id;
    private Long userId;
    private LocalDateTime timestamp;
    private String Month;
    private int dayOfMonth;
    private String hour;
    private String minute;
    private int year;
    private String IP;

    public UserLoggingInfo() {
    }

    public UserLoggingInfo(Long id, Long userId, LocalDateTime timestamp, String IP) {
        this.id = id;
        this.userId = userId;
        this.timestamp = timestamp;
        this.IP = IP;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        this.Month =  this.timestamp.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        this.dayOfMonth = this.timestamp.getDayOfMonth();
        this.hour = new DecimalFormat("00").format(this.timestamp.getHour());
        this.minute = new DecimalFormat("00").format(this.timestamp.getMinute());
        this.year = this.timestamp.getYear();
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    @Override
    public String toString() {
        return "UserLoggingInfo{" +
                "id=" + id +
                ", userId=" + userId +
                ", timestamp=" + timestamp +
                ", IP='" + IP + '\'' +
                '}';
    }

    public String getMonth() {
        return Month;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public String getHour() {
        return hour;
    }

    public String getMinute() {
        return minute;
    }

    public int getYear() {
        return year;
    }

    @Override
    public int compareTo(UserLoggingInfo o) {
        return this.timestamp.compareTo(o.getTimestamp());
    }
}
