package oyyq.calendar.util;

import static java.lang.Math.PI;
import static oyyq.calendar.util.MathUtil.dmsToSeconds;
import static oyyq.calendar.util.MathUtil.secondsToRadians;

public class ElpMpp02Util {

    /**
     * D = W1 - T + 180˚
     * 
     * @param t
     *            儒略世纪数
     * @return D的值(rad)
     */
    private static double getD(double t) {
        return getW1(t) - getTu(t) + PI;
    }

    /**
     * F = W1 - W3
     * 
     * @param t
     *            儒略世纪数
     * @return F的值(rad)
     */
    private static double getF(double t) {
        return getW1(t) - getW3(t);
    }

    /**
     * l = W1 - W2
     * 
     * @param t
     *            儒略世纪数
     * @return l的值(rad)
     */
    private static double getL(double t) {
        return getW1(t) - getW2(t);
    }

    /**
     * lp = T - Omp
     * 
     * @param t
     *            儒略世纪数
     * @return lp的值(rad)
     */
    private static double getLp(double t) {
        return getTu(t) - getOmp(t);
    }

    /**
     * W1 = 218˚18´59.95571˝ + 1732559343.73604˝ * t - 6.8084˝ * t² + 0.006604˝ * t³ - 0.00003169˝ *
     * t⁴
     * 
     * @param t
     *            儒略世纪数
     * @return W1的值(rad)
     */
    private static double getW1(double t) {
        double t2 = t * t;
        double t3 = t2 * t;
        double t4 = t3 * t;
        return secondsToRadians(dmsToSeconds(218, 18, 59.95571) + 1732559343.73604 * t - 6.8084
                * t2 + 0.006604 * t3 - 0.00003169 * t4);
    }

    /**
     * W2 = 83˚21´11.67475˝ + 14643420.3171˝ * t - 38.2631˝ * t² - 0.045047˝ * t³ + 0.00021301˝ * t⁴
     * 
     * @param t
     *            儒略世纪数
     * @return W2的值(rad)
     */
    private static double getW2(double t) {
        double t2 = t * t;
        double t3 = t2 * t;
        double t4 = t3 * t;
        return secondsToRadians(dmsToSeconds(83, 21, 11.67475) + 14643420.3171 * t - 38.2631 * t2
                - 0.045047 * t3 + 0.00021301 * t4);
    }

    /**
     * W3 = 125˚02´40.39816˝ + 6967919.5383˝ * t - 6.3590˝ * t² + 0.007625˝ * t³ + 0.00003586˝ * t⁴
     * 
     * @param t
     *            儒略世纪数
     * @return W3的值(rad)
     */
    private static double getW3(double t) {
        double t2 = t * t;
        double t3 = t2 * t;
        double t4 = t3 * t;
        return secondsToRadians(dmsToSeconds(125, 2, 40.39816) + 6967919.5383 * t - 6.3590 * t2
                + 0.007625 * t3 + 0.00003586 * t4);
    }

    /**
     * T = 100˚27´59.13885˝ + 129597742.2930˝ * t - 0.0202˝ * t² + 0.000009˝ * t³ + 0.00000015˝ * t⁴
     * 
     * @param t
     *            儒略世纪数
     * @return T的值(rad)
     */
    private static double getTu(double t) {
        double t2 = t * t;
        double t3 = t2 * t;
        double t4 = t3 * t;
        return secondsToRadians(dmsToSeconds(100, 27, 59.13885) + 129597742.2930 * t - 0.0202 * t2
                + 0.000009 * t3 + 0.00000015 * t4);
    }

    /**
     * Omp = 102˚56´14.45766˝ + 1161.24342˝ * t + 0.529265˝ * t² - 0.00011814˝ * t³ + 0.000011379˝ *
     * t⁴
     * 
     * @param t
     *            儒略世纪数
     * @return Omp的值(rad)
     */
    private static double getOmp(double t) {
        double t2 = t * t;
        double t3 = t2 * t;
        double t4 = t3 * t;
        return secondsToRadians(dmsToSeconds(102, 56, 14.45766) + 1161.24342 * t + 0.529265 * t2
                - 0.00011814 * t3 + 0.000011379 * t4);
    }
}
