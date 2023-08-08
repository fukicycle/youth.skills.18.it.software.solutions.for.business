package jp.co.toyota.sato.youth.skills18.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class CustomerDeliveryChangeModel {
    private String deliveryId;
    private LocalDate date;
    private LocalTime time;
    private String zipcode;
    private String address;
    private String building;

    public CustomerDeliveryChangeModel(String deliveryId, LocalDate date, LocalTime time, String zipcode, String address, String building) {
        this.deliveryId = deliveryId;
        this.date = date;
        this.time = time;
        this.zipcode = zipcode;
        this.address = address;
        this.building = building;
    }

    public CustomerDeliveryChangeModel() {
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
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
}
