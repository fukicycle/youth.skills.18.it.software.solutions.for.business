package jp.co.toyota.sato.youth.skills18.models;

import jp.co.toyota.sato.youth.skills18.entities.DeliveryType;

public class DeliveryTypeWrapper extends DeliveryType {
    private String color;

    public DeliveryTypeWrapper() {
    }

    public DeliveryTypeWrapper(int id, String name, String color) {
        super(id, name);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
