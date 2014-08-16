package oyyq.calendar.util;

import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static oyyq.calendar.util.CalendarUtil.fromJulianDate;
import static oyyq.calendar.util.CalendarUtil.toJulianDate;
import static oyyq.calendar.util.Vsop87dEarthUtil.getEarthEclipticLongitudeForSun;

import java.util.Map;

/**
 * 使用牛顿迭代法计算24节气的时间 求解的方程为: <br />
 * <i>f(x) = Vsop87dEarthUtil.getEarthEclipticLongitudeForSun(x) - angle = 0</i>
 * 
 * @author oyyq
 */
public class SolarTermsCalculator {

    private static final double RADIANS_PER_TERM = PI / 12;
    private static final double EPSILON          = 0.0000001d;
    private static final double DELTA       = 0.000005d;

    /**
     * 用牛顿迭代计算节气时间
     * 
     * @param term
     *            节气
     * @param year
     *            年份
     * @return 节气时间的儒略日
     */
    public static double getJulianDayInYearForTermOrder(SolarTerms term, int year) {
        int order = term.getOrder();
        double angle = (order - 1) * RADIANS_PER_TERM;
        int month = term.getMonth();
        int estimateDate = term.getEstimateDate();
        double jd1 = toJulianDate(year, month, estimateDate);
        double jd;
        do {
            jd = jd1;
            double fx = getEarthEclipticLongitudeForSun(jd) - angle;
            while (fx < -PI) {
                fx += 2 * PI;
            }
            while (fx > PI) {
                fx -= 2 * PI;
            }
            double fpx = (getEarthEclipticLongitudeForSun(jd + DELTA) - getEarthEclipticLongitudeForSun(jd
                    - DELTA))
                    / DELTA / 2;
            jd1 = jd - fx / fpx;
        } while (abs(jd1 - jd) > EPSILON);
        return jd;
    }

    public static void main(String[] args) {
        for (SolarTerms term : SolarTerms.values()) {
            double jd = getJulianDayInYearForTermOrder(term, 2011);
            jd -= CalendarUtil.getDeltaT(jd) / 86400; // 由TT转换成UTC
            Map<String, Number> cal = fromJulianDate(jd + 8.0 / 24.0); // 东8区
            System.out.println(term.getName()
                    + ": "
                    + String.format("%04d-%02d-%02d %02d:%02d:%09.6f", cal.get("year"),
                            cal.get("month"), cal.get("date"), cal.get("hour"), cal.get("minute"),
                            cal.get("second")));
        }
    }

}
