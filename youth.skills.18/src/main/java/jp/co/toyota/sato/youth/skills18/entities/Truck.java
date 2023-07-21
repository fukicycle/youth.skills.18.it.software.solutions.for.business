package jp.co.toyota.sato.youth.skills18.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "trucks")
public class Truck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "truck_type_id")
    private int truckTypeId;
    @Column(name = "office_id")
    private int officeId;
    public Truck(int id, String name, int truckTypeId, int officeId) {
        this.id = id;
        this.name = name;
        this.truckTypeId = truckTypeId;
        this.officeId = officeId;
    }
    public Truck() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTruckTypeId() {
        return truckTypeId;
    }

    public void setTruckTypeId(int truckTypeId) {
        this.truckTypeId = truckTypeId;
    }

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }
}
