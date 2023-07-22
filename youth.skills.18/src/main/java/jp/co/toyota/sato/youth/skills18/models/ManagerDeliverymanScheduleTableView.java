package jp.co.toyota.sato.youth.skills18.models;

import java.util.List;

public class ManagerDeliverymanScheduleTableView {
    private String employee;
    private List<String> items;

    public ManagerDeliverymanScheduleTableView(String employee, List<String> items) {
        this.employee = employee;
        this.items = items;
    }

    public ManagerDeliverymanScheduleTableView() {
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}
