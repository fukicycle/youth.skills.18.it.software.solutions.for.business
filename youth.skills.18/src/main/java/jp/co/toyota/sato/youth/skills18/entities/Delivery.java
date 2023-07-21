package jp.co.toyota.sato.youth.skills18.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "deliveries")
public class Delivery {
    @Id
    private String id;
    @Column(name = "amount")
    private int amount;
    @Column(name = "sender_name")
    private String senderName;
    @Column(name = "sender_name_kana")
    private String senderNameKana;
    @Column(name = "sender_birth_date")
    private LocalDate senderBirthDate;
    @Column(name = "sender_zipcode")
    private String senderZipcode;
    @Column(name = "sender_address")
    private String senderAddress;
    @Column(name = "collection_datetime")
    private LocalDateTime collectionDatetime;
    @Column(name = "destination_name")
    private String destinationName;
    @Column(name = "destination_name_kana")
    private String destinationNameKana;
    @Column(name = "destination_zipcode")
    private String destinationZipcode;
    @Column(name = "destination_address")
    private String destinationAddress;
    @Column(name = "delivery_datetime")
    private LocalDateTime deliveryDatetime;

    public Delivery() {

    }

    public Delivery(String id, int amount, String senderName, String senderNameKana, LocalDate senderBirthDate, String senderZipcode, String senderAddress, LocalDateTime collectionDatetime, String destinationName, String destinationNameKana, String destinationZipcode, String destinationAddress, LocalDateTime deliveryDatetime) {
        this.id = id;
        this.amount = amount;
        this.senderName = senderName;
        this.senderNameKana = senderNameKana;
        this.senderBirthDate = senderBirthDate;
        this.senderZipcode = senderZipcode;
        this.senderAddress = senderAddress;
        this.collectionDatetime = collectionDatetime;
        this.destinationName = destinationName;
        this.destinationNameKana = destinationNameKana;
        this.destinationZipcode = destinationZipcode;
        this.destinationAddress = destinationAddress;
        this.deliveryDatetime = deliveryDatetime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderNameKana() {
        return senderNameKana;
    }

    public void setSenderNameKana(String senderNameKana) {
        this.senderNameKana = senderNameKana;
    }

    public LocalDate getSenderBirthDate() {
        return senderBirthDate;
    }

    public void setSenderBirthDate(LocalDate senderBirthDate) {
        this.senderBirthDate = senderBirthDate;
    }

    public String getSenderZipcode() {
        return senderZipcode;
    }

    public void setSenderZipcode(String senderZipcode) {
        this.senderZipcode = senderZipcode;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public LocalDateTime getCollectionDatetime() {
        return collectionDatetime;
    }

    public void setCollectionDatetime(LocalDateTime collectionDatetime) {
        this.collectionDatetime = collectionDatetime;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getDestinationNameKana() {
        return destinationNameKana;
    }

    public void setDestinationNameKana(String destinationNameKana) {
        this.destinationNameKana = destinationNameKana;
    }

    public String getDestinationZipcode() {
        return destinationZipcode;
    }

    public void setDestinationZipcode(String destinationZipcode) {
        this.destinationZipcode = destinationZipcode;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public LocalDateTime getDeliveryDatetime() {
        return deliveryDatetime;
    }

    public void setDeliveryDatetime(LocalDateTime deliveryDatetime) {
        this.deliveryDatetime = deliveryDatetime;
    }
}
