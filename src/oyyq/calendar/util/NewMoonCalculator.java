package oyyq.calendar.util;

import static oyyq.calendar.util.CalendarUtil.fromJulianDate;
import static oyyq.calendar.util.CalendarUtil.toJulianDate;
import static oyyq.calendar.util.MathUtil.modPi;
import static oyyq.calendar.util.MathUtil.newtonIteration;
import static oyyq.calendar.util.Vsop87dEarthUtil.getEarthEclipticLongitudeForSun;
import static oyyq.calendar.util.elpmpp02.ElpMpp02Util.getEarthEclipticLongitudeForMoon;

import java.util.Map;

/**
 * 使用牛顿迭代法计算日月合朔的时间 求解的方程为: <br />
 * <i>f(x) = Vsop87dEarthUtil.getEarthEclipticLongitudeForSun(x) -
 * ElpMpp02Util.getEarthEclipticLongitudeForMoon(x) = 0</i>
 * 
 * @author oyyq
 */
public class NewMoonCalculator {

    /**
     * 用牛顿迭代计算日月合朔时间
     * 
     * @param term
     *            节气
     * @param year
     *            年份
     * @return 节气时间的儒略日
     */
    public static double getJulianDayInYearAndMonthForNewMoon(int year, int month) {
        double jd1 = toJulianDate(year, month, 15);
        double jd = newtonIteration((double x) -> modPi(getEarthEclipticLongitudeForSun(x)
                - getEarthEclipticLongitudeForMoon(x)), jd1);
        return jd;
    }

    public static void main(String[] args) {
        for (int month = 1; month <= 12; month++) {
            double jd = getJulianDayInYearAndMonthForNewMoon(2012, month);
            jd -= CalendarUtil.getDeltaT(jd) / 86400; // 由TT转换成UTC
            Map<String, Number> cal = fromJulianDate(jd + 8.0 / 24.0); // 东8区
            System.out.println(String.format("%04d-%02d-%02d %02d:%02d:%09.6f", cal.get("year"),
                    cal.get("month"), cal.get("date"), cal.get("hour"), cal.get("minute"),
                    cal.get("second")));
        }
    }

}
