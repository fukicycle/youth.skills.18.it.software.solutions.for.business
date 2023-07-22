package jp.co.toyota.sato.youth.skills18.models;

import java.time.LocalTime;

public class DeliverymanOtherView {
    private int deliveryScheduleId;
    private String truck;
    private String zipcode;
    private String address;
    private String name;
    private LocalTime estimatedTime;

    public DeliverymanOtherView(int deliveryScheduleId, String truck, String zipcode, String address, String name, LocalTime estimatedTime) {
        this.deliveryScheduleId = deliveryScheduleId;
        this.truck = truck;
        this.zipcode = zipcode;
        this.address = address;
        this.name = name;
        this.estimatedTime = estimatedTime;
    }

    public DeliverymanOtherView() {
    }

    public int getDeliveryScheduleId() {
        return deliveryScheduleId;
    }

    public void setDeliveryScheduleId(int deliveryScheduleId) {
        this.deliveryScheduleId = deliveryScheduleId;
    }

    public String getTruck() {
        return truck;
    }

    public void setTruck(String truck) {
        this.truck = truck;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(LocalTime estimatedTime) {
        this.estimatedTime = estimatedTime;
    }
}
