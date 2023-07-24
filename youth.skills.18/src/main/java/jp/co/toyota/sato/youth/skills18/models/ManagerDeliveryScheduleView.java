package jp.co.toyota.sato.youth.skills18.models;

import java.time.LocalDate;
import java.util.List;

public class ManagerDeliveryScheduleView {
    private String office;
    private LocalDate date;
    private List<ManagerDeliveryScheduleTableView> tableViews;
    private int employeeId;

    public ManagerDeliveryScheduleView(String office, LocalDate date, List<ManagerDeliveryScheduleTableView> tableViews, int employeeId) {
        this.office = office;
        this.date = date;
        this.tableViews = tableViews;
        this.employeeId = employeeId;
    }

    public ManagerDeliveryScheduleView() {
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

    public List<ManagerDeliveryScheduleTableView> getTableViews() {
        return tableViews;
    }

    public void setTableViews(List<ManagerDeliveryScheduleTableView> tableViews) {
        this.tableViews = tableViews;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
