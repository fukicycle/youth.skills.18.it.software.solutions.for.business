package jp.co.toyota.sato.youth.skills18.models;

import jp.co.toyota.sato.youth.skills18.entities.DeliverySchedule;
import jp.co.toyota.sato.youth.skills18.entities.DeliveryType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class ManagerDeliverymanScheduleView {
    private int id;
    private String office;
    private LocalDate date;
    private List<DeliveryTypeWrapper> deliveryTypes;
    private List<LocalTime> times;
    private List<ManagerDeliverymanScheduleTableView> tableViews;

    public ManagerDeliverymanScheduleView(int id, String office, LocalDate date, List<DeliveryTypeWrapper> deliveryTypes, List<LocalTime> times, List<ManagerDeliverymanScheduleTableView> tableViews) {
        this.id = id;
        this.office = office;
        this.date = date;
        this.deliveryTypes = deliveryTypes;
        this.times = times;
        this.tableViews = tableViews;
    }

    public ManagerDeliverymanScheduleView() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<DeliveryTypeWrapper> getDeliveryTypes() {
        return deliveryTypes;
    }

    public void setDeliveryTypes(List<DeliveryTypeWrapper> deliveryTypes) {
        this.deliveryTypes = deliveryTypes;
    }

    public List<LocalTime> getTimes() {
        return times;
    }

    public void setTimes(List<LocalTime> times) {
        this.times = times;
    }

    public List<ManagerDeliverymanScheduleTableView> getTableViews() {
        return tableViews;
    }

    public void setTableViews(List<ManagerDeliverymanScheduleTableView> tableViews) {
        this.tableViews = tableViews;
    }
}
