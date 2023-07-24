package jp.co.toyota.sato.youth.skills18.models;

import java.time.LocalDateTime;
import java.util.List;

public class CustomerDeliveryStatusModel {
    private List<CustomerDeliveryStatusView> items;
    private String id;
    private String status;
    private LocalDateTime scheduleDatetime;

    public CustomerDeliveryStatusModel(List<CustomerDeliveryStatusView> items, String id, String status, LocalDateTime scheduleDatetime) {
        this.items = items;
        this.id = id;
        this.status = status;
        this.scheduleDatetime = scheduleDatetime;
    }

    public CustomerDeliveryStatusModel() {
    }

    public List<CustomerDeliveryStatusView> getItems() {
        return items;
    }

    public void setItems(List<CustomerDeliveryStatusView> items) {
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getScheduleDatetime() {
        return scheduleDatetime;
    }

    public void setScheduleDatetime(LocalDateTime scheduleDatetime) {
        this.scheduleDatetime = scheduleDatetime;
    }
}
