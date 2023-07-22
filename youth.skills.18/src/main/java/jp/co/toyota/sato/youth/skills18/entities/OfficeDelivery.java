package jp.co.toyota.sato.youth.skills18.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "office_deliveries")
public class OfficeDelivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "sender_office_id")
    private int senderOfficeId;
    @Column(name = "destination_office_id")
    private int destinationOfficeId;
    @Column(name = "delivery_time")
    private int deliveryTime;
    @Column(name = "highway_fee")
    private Integer highwayFee;

    public OfficeDelivery(int id, int senderOfficeId, int destinationOfficeId, int deliveryTime, Integer highwayFee) {
        this.id = id;
        this.senderOfficeId = senderOfficeId;
        this.destinationOfficeId = destinationOfficeId;
        this.deliveryTime = deliveryTime;
        this.highwayFee = highwayFee;
    }

    public OfficeDelivery() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderOfficeId() {
        return senderOfficeId;
    }

    public void setSenderOfficeId(int senderOfficeId) {
        this.senderOfficeId = senderOfficeId;
    }

    public int getDestinationOfficeId() {
        return destinationOfficeId;
    }

    public void setDestinationOfficeId(int destinationOfficeId) {
        this.destinationOfficeId = destinationOfficeId;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Integer getHighwayFee() {
        return highwayFee;
    }

    public void setHighwayFee(Integer highwayFee) {
        this.highwayFee = highwayFee;
    }
}
