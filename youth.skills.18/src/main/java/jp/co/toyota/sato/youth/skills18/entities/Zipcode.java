package jp.co.toyota.sato.youth.skills18.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "zipcodes")
public class Zipcode {
    @Id
    private String zipcode;

    @Column(name = "prefecture")
    private String prefecture;

    @Column(name = "city")
    private String city;

    @Column(name = "town")
    private String town;

    @Column(name = "x_location")
    private int xLocation;

    @Column(name = "y_location")
    private int yLocation;

    public Zipcode(String zipcode, String prefecture, String city, String town, int xLocation, int yLocation) {
        super();
        this.zipcode = zipcode;
        this.prefecture = prefecture;
        this.city = city;
        this.town = town;
        this.xLocation = xLocation;
        this.yLocation = yLocation;
    }

    public Zipcode() {
        super();
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPrefecture() {
        return prefecture;
    }

    public void setPrefecture(String prefecture) {
        this.prefecture = prefecture;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
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

}
