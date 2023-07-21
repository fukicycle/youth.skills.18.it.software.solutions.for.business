package jp.co.toyota.sato.youth.skills18.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "delivery_statuses")
public class DeliveryStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "delivery_id")
    private String deliveryId;
    @Column(name = "datetime")
    private LocalDateTime datetime;
    @Column(name = "delivery_status_type_id")
    private int deliveryStatusTypeId;
    @Column(name = "office_id")
    private Integer officeId;

    public DeliveryStatus() {
    }

    public DeliveryStatus(int id, String deliveryId, LocalDateTime datetime, int deliveryStatusTypeId, Integer officeId) {
        this.id = id;
        this.deliveryId = deliveryId;
        this.datetime = datetime;
        this.deliveryStatusTypeId = deliveryStatusTypeId;
        this.officeId = officeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public int getDeliveryStatusTypeId() {
        return deliveryStatusTypeId;
    }

    public void setDeliveryStatusTypeId(int deliveryStatusTypeId) {
        this.deliveryStatusTypeId = deliveryStatusTypeId;
    }

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }
}
