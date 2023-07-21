package jp.co.toyota.sato.youth.skills18.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "offices")
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "office_type_id")
    private int officeTypeId;
    @Column(name = "zipcode")
    private String zipcode;
    @Column(name = "x_location")
    private int xLocation;
    @Column(name = "y_location")
    private int yLocation;
    @Column(name = "max_amount")
    private int maxAmount;
    @Column(name = "parent_office_id")
    private Integer parentOfficeId;

    public Office() {
    }

    public Office(int id, String name, int officeTypeId, String zipcode, int xLocation, int yLocation, int maxAmount, Integer parentOfficeId) {
        this.id = id;
        this.name = name;
        this.officeTypeId = officeTypeId;
        this.zipcode = zipcode;
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        this.maxAmount = maxAmount;
        this.parentOfficeId = parentOfficeId;
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

    public int getOfficeTypeId() {
        return officeTypeId;
    }

    public void setOfficeTypeId(int officeTypeId) {
        this.officeTypeId = officeTypeId;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public int getxLocation() {
        return xLocation;
    }

    public void setxLocation(int xLocation) {
        this.xLocation = xLocation;
    }

    public int getyLocation() {
        return yLocation;
    }

    public void setyLocation(int yLocation) {
        this.yLocation = yLocation;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Integer getParentOfficeId() {
        return parentOfficeId;
    }

    public void setParentOfficeId(Integer parentOfficeId) {
        this.parentOfficeId = parentOfficeId;
    }
}
