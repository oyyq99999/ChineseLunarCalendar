package oyyq.calendar.util;

import static java.lang.Math.PI;
import static java.lang.Math.toRadians;

public class MathUtil {

    /**
     * 把角秒换算成弧度
     * 
     * @param seconds
     *            角秒
     * @return 对应的弧度值
     */
    public static double secondsToRadians(double seconds) {
        return toRadians(secondsToDegrees(seconds));
    }

    /**
     * 把角度限制在[0, 2π]之间
     * 
     * @param r
     *            原角度(rad)
     * @return 转换后的角度(rad)
     */
    public static double mod2Pi(double r) {
        while (r < 0) {
            r += PI * 2;
        }
        while (r > 2 * PI) {
            r -= PI * 2;
        }
        return r;
    }

    /**
     * 把角秒换算成角度
     * 
     * @param seconds
     *            角秒
     * @return 对应的弧度值
     */
    public static double secondsToDegrees(double seconds) {
        return seconds / 3600;
    }

    /**
     * 把度分秒表示的角度换算成度(deg)
     * 
     * @param d
     *            度
     * @param m
     *            分
     * @param s
     *            秒
     * @return 换算成度的值
     */
    public static double dmsToDegrees(int d, int m, double s) {
        return d + m / 60.0 + s / 3600;
    }

    /**
     * 把度分秒表示的角度换算成秒(arcsecond)
     * 
     * @param d
     *            度
     * @param m
     *            分
     * @param s
     *            秒
     * @return 换算成秒的值
     */
    public static double dmsToSeconds(int d, int m, double s) {
        return d * 3600 + m * 60 + s;
    }

    /**
     * 把度分秒表示的角度换算成弧度(rad)
     * 
     * @param d
     *            度
     * @param m
     *            分
     * @param s
     *            秒
     * @return 换算成弧度的值
     */
    public static double dmsToRadians(int d, int m, double s) {
        return toRadians(dmsToDegrees(d, m, s));
    }
}
