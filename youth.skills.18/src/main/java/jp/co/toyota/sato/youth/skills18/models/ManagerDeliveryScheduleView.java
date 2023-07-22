package jp.co.toyota.sato.youth.skills18.models;

import java.time.LocalDate;
import java.util.List;

public class ManagerDeliveryScheduleView {
    private String office;
    private int id;
    private LocalDate date;
    private List<ManagerDeliveryScheduleTableView> tableViews;

    public ManagerDeliveryScheduleView(String office, int id, LocalDate date, List<ManagerDeliveryScheduleTableView> tableViews) {
        this.office = office;
        this.id = id;
        this.date = date;
        this.tableViews = tableViews;
    }

    public ManagerDeliveryScheduleView() {
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<ManagerDeliveryScheduleTableView> getTableViews() {
        return tableViews;
    }

    public void setTableViews(List<ManagerDeliveryScheduleTableView> tableViews) {
        this.tableViews = tableViews;
    }
}
