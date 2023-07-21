package jp.co.toyota.sato.youth.skills18.models;

import java.time.LocalTime;

public class DeliverymanMenuView {
    private int deliveryScheduleId;
    private int deliveryTypeId;
    private String type;
    private LocalTime minTime;
    private String displayTimes;
    private int count;
    private String href;
    private boolean isValid;

    public DeliverymanMenuView(int deliveryScheduleId, int deliveryTypeId, String type, LocalTime minTime, String displayTimes, int count, String href, boolean isValid) {
        this.deliveryScheduleId = deliveryScheduleId;
        this.deliveryTypeId = deliveryTypeId;
        this.type = type;
        this.minTime = minTime;
        this.displayTimes = displayTimes;
        this.count = count;
        this.href = href;
        this.isValid = isValid;
    }

    public DeliverymanMenuView() {
    }

    public int getDeliveryScheduleId() {
        return deliveryScheduleId;
    }

    public void setDeliveryScheduleId(int deliveryScheduleId) {
        this.deliveryScheduleId = deliveryScheduleId;
    }

    public int getDeliveryTypeId() {
        return deliveryTypeId;
    }

    public void setDeliveryTypeId(int deliveryTypeId) {
        this.deliveryTypeId = deliveryTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalTime getMinTime() {
        return minTime;
    }

    public void setMinTime(LocalTime minTime) {
        this.minTime = minTime;
    }

    public String getDisplayTimes() {
        return displayTimes;
    }

    public void setDisplayTimes(String displayTimes) {
        this.displayTimes = displayTimes;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}

