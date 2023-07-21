package jp.co.toyota.sato.youth.skills18.models;

import jp.co.toyota.sato.youth.skills18.entities.Delivery;

import java.util.List;

public class DeliverymanPickupAndDeliveryView {
    private int deliveryScheduleId;
    private List<DeliverymanScheduleView> items;
    private String truck;
    private String type;
    private Delivery delivery;
    private int total;
    private int finished;

    public DeliverymanPickupAndDeliveryView(int deliveryScheduleId, List<DeliverymanScheduleView> items, String truck, String type, Delivery delivery, int total, int finished) {
        this.deliveryScheduleId = deliveryScheduleId;
        this.items = items;
        this.truck = truck;
        this.type = type;
        this.delivery = delivery;
        this.total = total;
        this.finished = finished;
    }

    public DeliverymanPickupAndDeliveryView() {
    }

    public int getDeliveryScheduleId() {
        return deliveryScheduleId;
    }

    public void setDeliveryScheduleId(int deliveryScheduleId) {
        this.deliveryScheduleId = deliveryScheduleId;
    }

    public List<DeliverymanScheduleView> getItems() {
        return items;
    }

    public void setItems(List<DeliverymanScheduleView> items) {
        this.items = items;
    }

    public String getTruck() {
        return truck;
    }

    public void setTruck(String truck) {
        this.truck = truck;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }
}
