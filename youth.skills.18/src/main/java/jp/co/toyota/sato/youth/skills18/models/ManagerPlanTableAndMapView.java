package jp.co.toyota.sato.youth.skills18.models;

import jp.co.toyota.sato.youth.skills18.entities.Office;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class ManagerPlanTableAndMapView {
    private int sequence;
    private String address;
    private LocalTime estimatedTime;
    private LocalDateTime desiredDatetime;
    private double x;
    private double y;
    private double prevX;
    private double prevY;
    private Office office;

    public ManagerPlanTableAndMapView(int sequence, String address, LocalTime estimatedTime, LocalDateTime desiredDatetime, double x, double y, double prevX, double prevY, Office office) {
        this.sequence = sequence;
        this.address = address;
        this.estimatedTime = estimatedTime;
        this.desiredDatetime = desiredDatetime;
        this.x = x;
        this.y = y;
        this.prevX = prevX;
        this.prevY = prevY;
        this.office = office;
    }

    public ManagerPlanTableAndMapView() {
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalTime getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(LocalTime estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public LocalDateTime getDesiredDatetime() {
        return desiredDatetime;
    }

    public void setDesiredDatetime(LocalDateTime desiredDatetime) {
        this.desiredDatetime = desiredDatetime;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getPrevX() {
        return prevX;
    }

    public void setPrevX(double prevX) {
        this.prevX = prevX;
    }

    public double getPrevY() {
        return prevY;
    }

    public void setPrevY(double prevY) {
        this.prevY = prevY;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }
}
