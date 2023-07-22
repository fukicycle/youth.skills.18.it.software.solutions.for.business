package jp.co.toyota.sato.youth.skills18.models;

import jp.co.toyota.sato.youth.skills18.entities.Delivery;

import java.awt.geom.Point2D;

public class PointWrapper {
    public Point2D point;
    public Delivery delivery;

    public PointWrapper(Point2D point, Delivery delivery) {
        this.point = point;
        this.delivery = delivery;
    }

    public PointWrapper() {
    }

    public Point2D getPoint() {
        return point;
    }

    public void setPoint(Point2D point) {
        this.point = point;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }
}
