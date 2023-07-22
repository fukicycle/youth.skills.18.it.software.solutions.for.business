package jp.co.toyota.sato.youth.skills18.models;

import java.time.LocalTime;
import java.util.List;

public class ManagerDeliverySchedulePlanView {
    public String office;
    public LocalTime startTime;
    public long cost;
    public int deliveryScheduleId;
    public List<ManagerPlanTableAndMapView> mapItems;
    public List<ManagerPlanTableAndMapView> tableItems;

    public ManagerDeliverySchedulePlanView(String office, LocalTime startTime, long cost, int deliveryScheduleId, List<ManagerPlanTableAndMapView> mapItems, List<ManagerPlanTableAndMapView> tableItems) {
        this.office = office;
        this.startTime = startTime;
        this.cost = cost;
        this.deliveryScheduleId = deliveryScheduleId;
        this.mapItems = mapItems;
        this.tableItems = tableItems;
    }

    public ManagerDeliverySchedulePlanView() {
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
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
}
