package jp.co.toyota.sato.youth.skills18.entities;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "delivery_schedule_details")
public class DeliveryScheduleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "delivery_schedule_id")
    private int deliveryScheduleId;
    @Column(name = "delivery_id")
    private String deliveryId;
    @Column(name = "delivery_order")
    private int deliveryOrder;
    @Column(name = "estimated_time")
    private LocalTime estimatedTime;
    @Column(name = "actual_time")
    private LocalTime actualTime;

    public DeliveryScheduleDetail(int id, int deliveryScheduleId, String deliveryId, int deliveryOrder, LocalTime estimatedTime, LocalTime actualTime) {
        this.id = id;
        this.deliveryScheduleId = deliveryScheduleId;
        this.deliveryId = deliveryId;
        this.deliveryOrder = deliveryOrder;
        this.estimatedTime = estimatedTime;
        this.actualTime = actualTime;
    }

    public DeliveryScheduleDetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeliveryScheduleId() {
        return deliveryScheduleId;
    }

    public void setDeliveryScheduleId(int deliveryScheduleId) {
        this.deliveryScheduleId = deliveryScheduleId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public int getDeliveryOrder() {
        return deliveryOrder;
    }

    public void setDeliveryOrder(int deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
    }

    public LocalTime getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(LocalTime estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public LocalTime getActualTime() {
        return actualTime;
    }

    public void setActualTime(LocalTime actualTime) {
        this.actualTime = actualTime;
    }
}
