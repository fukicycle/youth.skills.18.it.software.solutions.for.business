package jp.co.toyota.sato.youth.skills18.models;

import jp.co.toyota.sato.youth.skills18.entities.Office;

public class OfficeEarnView extends Office {
    private double earn;
    private double cost;

    public OfficeEarnView(int id, String name, int officeTypeId, String zipcode, int xLocation, int yLocation, int maxAmount, Integer parentOfficeId, double earn, double cost) {
        super(id, name, officeTypeId, zipcode, xLocation, yLocation, maxAmount, parentOfficeId);
        this.earn = earn;
        this.cost = cost;
    }

    public double getEarn() {
        return earn;
    }

    public void setEarn(double earn) {
        this.earn = earn;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
