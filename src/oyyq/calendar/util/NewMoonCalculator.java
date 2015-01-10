package oyyq.calendar.util;

import static oyyq.calendar.util.CalendarUtil.fromJulianDate;
import static oyyq.calendar.util.CalendarUtil.toJulianDate;
import static oyyq.calendar.util.MathUtil.modPi;
import static oyyq.calendar.util.MathUtil.newtonIteration;
import static oyyq.calendar.util.Vsop87dEarthUtil.getEarthEclipticLongitudeForSun;
import static oyyq.calendar.util.elp82simple.Elp82Util.getEarthEclipticLongitudeForMoon;

import java.util.ArrayList;
import java.util.Calendar;

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
            Calendar cal = fromJulianDate(jd);
            if (cal.get(Calendar.MONTH) + 1 == month && (jd - lastJd > 1e-7)) {
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
                Calendar cal = fromJulianDate(jd);
                System.out.println(String.format("%04d-%02d-%02d %02d:%02d:%02d.%03d",
                        cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DATE),
                        cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),
                        cal.get(Calendar.SECOND), cal.get(Calendar.MILLISECOND)));
            }
        }
    }

}
