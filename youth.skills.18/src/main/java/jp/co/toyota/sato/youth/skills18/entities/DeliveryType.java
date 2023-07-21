package jp.co.toyota.sato.youth.skills18.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "delivery_types")
public class DeliveryType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;

    public DeliveryType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public DeliveryType() {
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
}
