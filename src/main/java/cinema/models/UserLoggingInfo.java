package cinema.models;

import java.time.LocalDateTime;

public class UserLoggingInfo {
    private Long id;
    private Long userId;
    private LocalDateTime timestamp;
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
}
