package jp.co.toyota.sato.youth.skills18.models;

import jp.co.toyota.sato.youth.skills18.entities.Office;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ManagerDeliverySchedulePlanModel {
    private int id;
    private Office office;
    private LocalTime startTime;
    private long cost;
    private int deliveryScheduleId;
    private List<ManagerPlanTableAndMapView> mapItems;
    private List<ManagerPlanTableAndMapView> tableItems;
    private boolean isCost;
    private double maxWidth;
    private double maxHeight;
    private double officeX;
    private double officeY;
    private int employeeId;
    private LocalDate date;

    public ManagerDeliverySchedulePlanModel(int id, Office office, LocalTime startTime, long cost, int deliveryScheduleId, List<ManagerPlanTableAndMapView> mapItems, List<ManagerPlanTableAndMapView> tableItems, boolean isCost, double maxWidth, double maxHeight, double officeX, double officeY, int employeeId, LocalDate date) {
        this.id = id;
        this.office = office;
        this.startTime = startTime;
        this.cost = cost;
        this.deliveryScheduleId = deliveryScheduleId;
        this.mapItems = mapItems;
        this.tableItems = tableItems;
        this.isCost = isCost;
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.officeX = officeX;
        this.officeY = officeY;
        this.employeeId = employeeId;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ManagerDeliverySchedulePlanModel() {
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public int getDeliveryScheduleId() {
        return deliveryScheduleId;
    }

    public void setDeliveryScheduleId(int deliveryScheduleId) {
        this.deliveryScheduleId = deliveryScheduleId;
    }

    public List<ManagerPlanTableAndMapView> getMapItems() {
        return mapItems;
    }

    public void setMapItems(List<ManagerPlanTableAndMapView> mapItems) {
        this.mapItems = mapItems;
    }

    public List<ManagerPlanTableAndMapView> getTableItems() {
        return tableItems;
    }

    public void setTableItems(List<ManagerPlanTableAndMapView> tableItems) {
        this.tableItems = tableItems;
    }

    public boolean isCost() {
        return isCost;
    }

    public void setCost(boolean cost) {
        isCost = cost;
    }

    public double getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(double maxWidth) {
        this.maxWidth = maxWidth;
    }

    public double getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(double maxHeight) {
        this.maxHeight = maxHeight;
    }

    public double getOfficeX() {
        return officeX;
    }

    public void setOfficeX(double officeX) {
        this.officeX = officeX;
    }

    public double getOfficeY() {
        return officeY;
    }

    public void setOfficeY(double officeY) {
        this.officeY = officeY;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
