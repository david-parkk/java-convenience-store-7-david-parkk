package store.io;

import java.time.LocalDateTime;

public class PromotionData {

    private String name;
    private int buy;
    private int get;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public PromotionData(String name, int buy, int get, LocalDateTime startTime, LocalDateTime endTime) {
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public int getBuy() {
        return buy;
    }

    public int getGet() {
        return get;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}
