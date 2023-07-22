package jp.co.toyota.sato.youth.skills18.models;

import java.time.LocalTime;

public class ManagerPlanTableAndMapView {
    public int sequence;
    public String address;
    public LocalTime estimatedTime;
    public LocalTime desiredTime;
    public double x;
    public double y;
    public double prevX;
    public double prevY;
    public String office;

    public ManagerPlanTableAndMapView(int sequence, String address, LocalTime estimatedTime, LocalTime desiredTime, double x, double y, double prevX, double prevY, String office) {
        this.sequence = sequence;
        this.address = address;
        this.estimatedTime = estimatedTime;
        this.desiredTime = desiredTime;
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

    public LocalTime getDesiredTime() {
        return desiredTime;
    }

    public void setDesiredTime(LocalTime desiredTime) {
        this.desiredTime = desiredTime;
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

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }
}
