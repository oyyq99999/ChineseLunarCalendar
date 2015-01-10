package oyyq.calendar.util;

import static oyyq.calendar.util.CalendarUtil.fromJulianDate;
import static oyyq.calendar.util.CalendarUtil.toJulianDate;
import static oyyq.calendar.util.MathUtil.modPi;
import static oyyq.calendar.util.MathUtil.newtonIteration;
import static oyyq.calendar.util.Vsop87dEarthUtil.getEarthEclipticLongitudeForSun;
import static oyyq.calendar.util.elp82simple.Elp82Util.getEarthEclipticLongitudeForMoon;
import static java.lang.Math.abs;

import java.util.ArrayList;
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
    public static ArrayList<Double> getJulianDayInYearAndMonthForNewMoon(int year, int month) {
        ArrayList<Double> jds = new ArrayList<Double>();
        double lastJd = 0.0d;
        for (int i = 0; i < 2; i++) {
            double jd1 = toJulianDate(year, month, 10 * (i + 1));
            double jd = newtonIteration((double x) -> modPi(getEarthEclipticLongitudeForSun(x)
                    - getEarthEclipticLongitudeForMoon(x)), jd1);
            Map<String, Number> cal = fromJulianDate(jd - CalendarUtil.getDeltaT(jd) / 86400 + 8.0 / 24.0);
            if (cal.get("month").intValue() == month && (jd - lastJd > 1e-7)) {
                jds.add(jd);
                lastJd = jd;
            }
        }
        return jds;
    }

    public static void main(String[] args) {
        for (int month = 1; month <= 12; month++) {
            ArrayList<Double> jds = getJulianDayInYearAndMonthForNewMoon(1995, month);
            for (double jd : jds) {
                jd -= CalendarUtil.getDeltaT(jd) / 86400; // 由TT转换成UTC
                Map<String, Number> cal = fromJulianDate(jd + 8.0 / 24.0); // 东8区
                System.out.println(String.format("%04d-%02d-%02d %02d:%02d:%09.6f",
                        cal.get("year"), cal.get("month"), cal.get("date"), cal.get("hour"),
                        cal.get("minute"), cal.get("second")));
            }
        }
    }

}
