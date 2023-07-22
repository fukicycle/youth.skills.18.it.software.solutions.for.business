package jp.co.toyota.sato.youth.skills18.models;

import java.time.LocalTime;

public class ManagerDeliveryScheduleTableView {

    private LocalTime time;
    private String type;
    private String color;
    private int deliveryScheduleId;

    public ManagerDeliveryScheduleTableView(LocalTime time, String type, String color, int deliveryScheduleId) {
        this.time = time;
        this.type = type;
        this.color = color;
        this.deliveryScheduleId = deliveryScheduleId;
    }

    public ManagerDeliveryScheduleTableView() {
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getDeliveryScheduleId() {
        return deliveryScheduleId;
    }

    public void setDeliveryScheduleId(int deliveryScheduleId) {
        this.deliveryScheduleId = deliveryScheduleId;
    }
}
