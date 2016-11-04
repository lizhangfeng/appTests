package bwf.androiddemos.utils;

/**
 * Created by Lizhangfeng on 2016/10/17 0017.
 * Description:
 */

public class ParabolaAlgorithm {

    public static void main(String[] args) {
        final float[][] points = { { 6, 15 }, { 15, 70 }, { 40, 60 } };
        calculate(points);
    }

    /**
     * a = (y1 * (x2 - x3) + y2 * (x3 - x1) + y3 * (x1 - x2)) / (x1 * x1 * (x2 -
     * x3) + x2 * x2 * (x3 - x1) + x3 * x3 * (x1 - x2))
     * b = (y1 - y2) / (x1 - x2) - a * (x1 + x2);
     * c = y1 - (x1 * x1) * a - x1 * b;
     *
     * 知抛物线三点求抛物线
     */
    private static void calculate(float[][] points) {
        float x1 = points[0][0];
        float y1 = points[0][1];
        float x2 = points[1][0];
        float y2 = points[1][1];
        float x3 = points[2][0];
        float y3 = points[2][1];

        final float a = (y1 * (x2 - x3) + y2 * (x3 - x1) + y3 * (x1 - x2))
                / (x1 * x1 * (x2 - x3) + x2 * x2 * (x3 - x1) + x3 * x3 * (x1 - x2));
        final float b = (y1 - y2) / (x1 - x2) - a * (x1 + x2);
        final float c = y1 - (x1 * x1) * a - x1 * b;

        System.out.println("-a->" + a + " b->" +b + " c->" +c);
    }

}
