package jp.co.toyota.sato.youth.skills18.models;

import java.time.LocalTime;

public class DeliverymanScheduleView {
    private int id;
    private String address;
    private int order;
    private LocalTime estimatedTime;

    public DeliverymanScheduleView(int id, String address, int order, LocalTime estimatedTime) {
        this.id = id;
        this.address = address;
        this.order = order;
        this.estimatedTime = estimatedTime;
    }

    public DeliverymanScheduleView() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public LocalTime getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(LocalTime estimatedTime) {
        this.estimatedTime = estimatedTime;
    }
}
