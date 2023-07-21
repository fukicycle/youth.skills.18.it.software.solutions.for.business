package jp.co.toyota.sato.youth.skills18.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "delivery_schedules")
public class DeliverySchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "truck_id")
    private int truckId;
    @Column(name = "employee_id")
    private int employeeId;
    @Column(name = "delivery_type_id")
    private int deliveryTypeId;
    @Column(name = "estimated_date")
    private LocalDate estimatedDate;
    @Column(name = "estimated_start_time")
    private LocalTime estimatedStartTime;
    @Column(name = "estimated_end_time")
    private LocalTime estimatedEndTime;
    @Column(name = "actual_date")
    private LocalDate actualDate;
    @Column(name = "actual_start_time")
    private LocalTime actualStartTime;
    @Column(name = "actual_end_time")
    private LocalTime actualEndTime;
    @Column(name = "office_delivery_id")
    private int officeDeliveryId;


    public DeliverySchedule() {
    }

    public DeliverySchedule(int id, int truckId, int employeeId, int deliveryTypeId, LocalDate estimatedDate, LocalTime estimatedStartTime, LocalTime estimatedEndTime, LocalDate actualDate, LocalTime actualStartTime, LocalTime actualEndTime, int officeDeliveryId) {
        this.id = id;
        this.truckId = truckId;
        this.employeeId = employeeId;
        this.deliveryTypeId = deliveryTypeId;
        this.estimatedDate = estimatedDate;
        this.estimatedStartTime = estimatedStartTime;
        this.estimatedEndTime = estimatedEndTime;
        this.actualDate = actualDate;
        this.actualStartTime = actualStartTime;
        this.actualEndTime = actualEndTime;
        this.officeDeliveryId = officeDeliveryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTruckId() {
        return truckId;
    }

    public void setTruckId(int truckId) {
        this.truckId = truckId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getDeliveryTypeId() {
        return deliveryTypeId;
    }

    public void setDeliveryTypeId(int deliveryTypeId) {
        this.deliveryTypeId = deliveryTypeId;
    }

    public LocalDate getEstimatedDate() {
        return estimatedDate;
    }

    public void setEstimatedDate(LocalDate estimatedDate) {
        this.estimatedDate = estimatedDate;
    }

    public LocalTime getEstimatedStartTime() {
        return estimatedStartTime;
    }

    public void setEstimatedStartTime(LocalTime estimatedStartTime) {
        this.estimatedStartTime = estimatedStartTime;
    }

    public LocalTime getEstimatedEndTime() {
        return estimatedEndTime;
    }

    public void setEstimatedEndTime(LocalTime estimatedEndTime) {
        this.estimatedEndTime = estimatedEndTime;
    }

    public LocalDate getActualDate() {
        return actualDate;
    }

    public void setActualDate(LocalDate actualDate) {
        this.actualDate = actualDate;
    }

    public LocalTime getActualStartTime() {
        return actualStartTime;
    }

    public void setActualStartTime(LocalTime actualStartTime) {
        this.actualStartTime = actualStartTime;
    }

    public LocalTime getActualEndTime() {
        return actualEndTime;
    }

    public void setActualEndTime(LocalTime actualEndTime) {
        this.actualEndTime = actualEndTime;
    }

    public int getOfficeDeliveryId() {
        return officeDeliveryId;
    }

    public void setOfficeDeliveryId(int officeDeliveryId) {
        this.officeDeliveryId = officeDeliveryId;
    }
}
