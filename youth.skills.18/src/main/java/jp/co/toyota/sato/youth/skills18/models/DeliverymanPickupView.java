package jp.co.toyota.sato.youth.skills18.models;

import jp.co.toyota.sato.youth.skills18.entities.Delivery;
import jp.co.toyota.sato.youth.skills18.entities.DeliveryScheduleDetail;

import java.util.List;

public class DeliverymanPickupView {
    private List<DeliveryScheduleDetail> deliveryScheduleDetails;
    private String truck;
    private String type;
    private Delivery delivery;
    private int total;
    private int finished;

    public DeliverymanPickupView(List<DeliveryScheduleDetail> deliveryScheduleDetails, String truck, String type, Delivery delivery, int total, int finished) {
        this.deliveryScheduleDetails = deliveryScheduleDetails;
        this.truck = truck;
        this.type = type;
        this.delivery = delivery;
        this.total = total;
        this.finished = finished;
    }

    public DeliverymanPickupView() {
    }

    public List<DeliveryScheduleDetail> getDeliveryScheduleDetails() {
        return deliveryScheduleDetails;
    }

    public void setDeliveryScheduleDetails(List<DeliveryScheduleDetail> deliveryScheduleDetails) {
        this.deliveryScheduleDetails = deliveryScheduleDetails;
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
