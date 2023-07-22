package jp.co.toyota.sato.youth.skills18;

import jp.co.toyota.sato.youth.skills18.models.PointWrapper;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class TwoOpt {
    public static List<PointWrapper> getCalculatedRoute(List<PointWrapper> points) {
        List<PointWrapper> newRoute;
        double bestDist = getRouteLength(points);
        double newDist;
        int swaps = 1;

        while (swaps != 0) {
            swaps = 0;
            for (int i = 1; i < points.size() - 2; i++) {
                for (int j = i + 1; j < points.size() - 1; j++) {
                    if ((points.get(i).getPoint().distance(points.get(i - 1).getPoint()) + points.get(j + 1).getPoint().distance(points.get(j).getPoint())) >= (points.get(i).getPoint().distance(points.get(j + 1).getPoint()) + points.get(i - 1).getPoint().distance(points.get(j).getPoint()))) {
                        newRoute = swap(points, i, j); // pass arraylist and 2 points to be swapped.
                        newDist = getRouteLength(newRoute);
                        if (newDist < bestDist) { // if the swap results in an improved distance, increment counters and
                            // update distance/tour
                            points = newRoute;
                            bestDist = newDist;
                            swaps++;
                        }
                    }
                }
            }
        }
        return points;
    }

    public static double getRouteLength(List<PointWrapper> points) {
        double result = 0;
        Point2D prev = points.get(points.size() - 1).getPoint();
        for (PointWrapper point : points) {
            result += point.getPoint().distance(prev);
            prev = point.getPoint();
        }
        return result;
    }

    private static List<PointWrapper> swap(List<PointWrapper> points, int i, int j) {
        List<PointWrapper> newRoute = new ArrayList<>();
        int size = points.size();
        for (int c = 0; c <= i - 1; c++) {
            newRoute.add(points.get(c));
        }

        int dec = 0;
        for (int c = i; c <= j; c++) {
            newRoute.add(points.get(j - dec));
            dec++;
        }

        for (int c = j + 1; c < size; c++) {
            newRoute.add(points.get(c));
        }
        return newRoute;
    }
}
