package oyyq.calendar.util;

import java.util.HashMap;
import java.util.Map;

public final class CalendarUtil {

    /**
     * Gregorian历法实施年份
     */
    public static final int    GREGORIAN_FIRST_YEAR      = 1582;
    /**
     * Gregorian历法实施月份
     */
    public static final int    GREGORIAN_FIRST_MONTH     = 10;
    /**
     * Gregorian历法实施日期
     */
    public static final int    GREGORIAN_FIRST_DATE      = 15;

    /**
     * Julian历法废弃年份
     */
    public static final int    JULIAN_LAST_YEAR          = 1582;
    /**
     * Julian历法废弃月份
     */
    public static final int    JULIAN_LAST_MONTH         = 10;
    /**
     * Julian历法废弃日期
     */
    public static final int    JULIAN_LAST_DATE          = 4;

    /**
     * Gregorian历TT1582年10月15日中午12点的儒略日
     */
    private static final int   JULIAN_GREGORIAN_BOUNDARY = 2299161;

    /**
     * Gregorian历TT2000年1月1日中午12点的儒略日
     */
    public static final double J2000                     = 2451545.0d;

    /**
     * 1000年的日数
     */
    public static final double DAYS_OF_1000_YEARS        = 365250.0d;

    /**
     * 100年的日数
     */
    public static final double DAYS_OF_CENTURY           = 36525.0d;

    /**
     * Gregorian闰年判断
     * 
     * @param y
     *            年份
     * @return 闰年返回true，平年返回false
     */
    public static boolean isGregorianLeapYear(int y) {
        return (y & 3) == 0 && y % 100 != 0 || y % 400 == 0;
    }

    /**
     * Julian闰年判断
     * 
     * @param y
     *            年份
     * @return 闰年返回true，平年返回false
     */
    public static boolean isJulianLeapYear(int y) {
        return (y & 3) == 0;
    }

    /**
     * {@value #JULIAN_LAST_YEAR}年(含)以前按照Julian历法，{@value #JULIAN_LAST_YEAR}年以后按照Gregorian历法。
     * 
     * @param y
     *            年份
     * @return 闰年返回true，平年返回false
     */
    public static boolean isLeapYear(int y) {
        if (y <= JULIAN_LAST_YEAR) {
            return isJulianLeapYear(y);
        }
        return isGregorianLeapYear(y);
    }

    /**
     * 计算Gregorian日期的儒略日数，以TT当天中午12点为准(结果是整数)。 算法摘自<a href=
     * "http://en.wikipedia.org/wiki/Julian_day" >英文维基百科<i>Julian Day</i>词条</a>。
     * 
     * @param year
     *            年份
     * @param month
     *            月份
     * @param date
     *            日期
     * @return 返回以Gregorian历法计算的儒略日数
     */
    public static int toJulianDateInGregorian(int year, int month, int date) {
        int a = (14 - month) / 12;
        int y = year + 4800 - a;
        int m = month + 12 * a - 3;
        return date + (153 * m + 2) / 5 + 365 * y + y / 4 - y / 100 + y / 400 - 32045;
    }

    /**
     * 计算Julian日期的儒略日数，以TT当天中午12点为准(结果是整数)。 算法摘自<a href= "http://en.wikipedia.org/wiki/Julian_day"
     * >英文维基百科<i>Julian Day</i>词条</a>。
     * 
     * @param year
     *            年份
     * @param month
     *            月份
     * @param date
     *            日期
     * @return 返回以Julian历法计算的儒略日数
     */
    public static int toJulianDateInJulian(int year, int month, int date) {
        int a = (14 - month) / 12;
        int y = year + 4800 - a;
        int m = month + 12 * a - 3;
        return date + (153 * m + 2) / 5 + 365 * y + y / 4 - 32083;
    }

    /**
     * 计算儒略日数，以TT当天中午12点为准，{@value #JULIAN_LAST_YEAR}年{@value #JULIAN_LAST_MONTH}月
     * {@value #JULIAN_LAST_DATE}日及以前按照Julian历法，{@value #GREGORIAN_FIRST_YEAR}年
     * {@value #GREGORIAN_FIRST_MONTH}月{@value #GREGORIAN_FIRST_DATE}日及以后按照Gregorian历法，中间的日期按照
     * {@value #GREGORIAN_FIRST_YEAR}年{@value #GREGORIAN_FIRST_MONTH}月
     * {@value #GREGORIAN_FIRST_DATE}日计算。
     * 
     * @param y
     *            年份
     * @param m
     *            月份
     * @param d
     *            日期
     * @return 返回相应历法的儒略日数
     */
    public static int toJulianDate(int y, int m, int d) {
        if (y < JULIAN_LAST_YEAR) {
            return toJulianDateInJulian(y, m, d);
        }
        if (y == JULIAN_LAST_YEAR && m < JULIAN_LAST_MONTH) {
            return toJulianDateInJulian(y, m, d);
        }
        if (y == JULIAN_LAST_YEAR && m == JULIAN_LAST_MONTH && d <= JULIAN_LAST_DATE) {
            return toJulianDateInJulian(y, m, d);
        }

        if (y > GREGORIAN_FIRST_YEAR) {
            return toJulianDateInGregorian(y, m, d);
        }
        if (y == GREGORIAN_FIRST_YEAR && m > GREGORIAN_FIRST_MONTH) {
            return toJulianDateInGregorian(y, m, d);
        }
        if (y == GREGORIAN_FIRST_YEAR && m == GREGORIAN_FIRST_MONTH && d >= GREGORIAN_FIRST_DATE) {
            return toJulianDateInGregorian(y, m, d);
        }

        // 剩下的都是中间的，以Gregorian历法实施第一天计算
        return toJulianDateInGregorian(GREGORIAN_FIRST_YEAR, GREGORIAN_FIRST_MONTH,
                GREGORIAN_FIRST_DATE);
    }

    /**
     * 计算Gregorian时间的儒略日数。 算法摘自<a href= "http://en.wikipedia.org/wiki/Julian_day" >英文维基百科<i>Julian
     * Day</i>词条</a>。
     * 
     * @param year
     *            年份
     * @param month
     *            月份
     * @param date
     *            日期
     * @param hour
     *            小时
     * @param minute
     *            分钟
     * @param second
     *            秒数
     * @return 返回以Gregorian历法计算的儒略日数
     */
    public static double toJulianDateInGregorian(int year, int month, int date, int hour,
            int minute, double second) {
        int jdn = toJulianDateInGregorian(year, month, date);
        return jdn + (hour - 12) / 24.0d + minute / 1440.0d + second / 86400.0d;
    }

    /**
     * 计算Julian时间的儒略日数。 算法摘自<a href= "http://en.wikipedia.org/wiki/Julian_day" >英文维基百科<i>Julian
     * Day</i>词条</a>。
     * 
     * @param year
     *            年份
     * @param month
     *            月份
     * @param date
     *            日期
     * @param hour
     *            小时
     * @param minute
     *            分钟
     * @param second
     *            秒数
     * @return 返回以Julian历法计算的儒略日数
     */
    public static double toJulianDateInJulian(int year, int month, int date, int hour, int minute,
            double second) {
        int jdn = toJulianDateInJulian(year, month, date);
        return jdn + (hour - 12) / 24.0d + minute / 1440.0d + second / 86400.0d;
    }

    /**
     * 计算儒略日数，{@value #JULIAN_LAST_YEAR}年{@value #JULIAN_LAST_MONTH}月 {@value #JULIAN_LAST_DATE}
     * 日及以前按照Julian历法，{@value #GREGORIAN_FIRST_YEAR}年 {@value #GREGORIAN_FIRST_MONTH}月
     * {@value #GREGORIAN_FIRST_DATE}日及以后按照Gregorian历法，中间的日期按照 {@value #GREGORIAN_FIRST_YEAR}年
     * {@value #GREGORIAN_FIRST_MONTH}月 {@value #GREGORIAN_FIRST_DATE}日计算。
     * 
     * @param y
     *            年份
     * @param month
     *            月份
     * @param d
     *            日期
     * @param h
     *            小时
     * @param minute
     *            分钟
     * @param s
     *            秒数
     * @return 返回相应历法的儒略日数
     */
    public static double toJulianDate(int y, int month, int d, int h, int minute, double s) {
        int jdn = toJulianDate(y, month, d);
        return jdn + (h - 12) / 24.0d + minute / 1440.0d + s / 86400.0d;
    }

    private static Map<String, Number> fromJulianDateHelper(int a, double f) {
        int b = a + 1524;
        int c = (int) ((b - 122.1) / 365.25);
        int d = (int) (365.25 * c);
        int e = (int) ((b - d) / 30.6001);
        double dd = b - d - (int) (30.6001 * e) + f;
        int mm = e <= 13 ? e - 1 : e - 13;
        int yyyy = mm <= 2 ? c - 4715 : c - 4716;

        int year = yyyy;
        int month = mm;
        int date = (int) dd;
        int hour = (int) ((dd - date) * 24);
        dd = (dd - date) * 24 - hour;
        int minute = (int) (dd * 60);
        double second = (dd * 60 - minute) * 60;

        Map<String, Number> ret = new HashMap<>();
        ret.put("year", year);
        ret.put("month", month);
        ret.put("date", date);
        ret.put("hour", hour);
        ret.put("minute", minute);
        ret.put("second", second);

        return ret;
    }

    /**
     * 由儒略日计算对应的Gregorian历日期时间，算法参考<i>Jean Meeus</i>的<i>Astronomical Formulae for Calculators</i>
     * 
     * @param jd
     *            儒略日
     * @return 对应的Gregorian历TT日期时间的一个Map,各项的key为year, month, date, hour, minute,
     *         second。其中second是double型，其余均是int型。
     */
    public static Map<String, Number> fromJulianDateInGregorian(double jd) {
        int z = (int) (jd + 0.5);
        double f = jd + 0.5 - z;
        int alpha = (int) ((z - 1867216.25) / 36524.25);
        int a = z + 1 + alpha - alpha / 4;
        return fromJulianDateHelper(a, f);
    }

    /**
     * 由儒略日计算对应的Julian历日期时间，算法参考<i>Jean Meeus</i>的<i>Astronomical Formulae for Calculators</i>
     * 
     * @param jd
     *            儒略日
     * @return 对应的Julian历TT日期时间的一个Map,各项的key为year, month, date, hour, minute,
     *         second。其中second是double型，其余均是int型。
     */
    public static Map<String, Number> fromJulianDateInJulian(double jd) {
        int z = (int) (jd + 0.5);
        double f = jd + 0.5 - z;
        int a = z;
        return fromJulianDateHelper(a, f);
    }

    /**
     * 由儒略日计算对应的日期时间，算法参考<i>Jean Meeus</i>的<i>Astronomical Formulae for Calculators</i> 当儒略日小于<i>
     * {@value #JULIAN_GREGORIAN_BOUNDARY} - 0.5</i>时按Julian历法计算，以后按Gregorian历计算。
     * 
     * @param jd
     *            儒略日
     * @return 对应的TT日期时间的一个Map,各项的key为year, month, date, hour, minute,
     *         second。其中second是double型，其余均是int型。
     */
    public static Map<String, Number> fromJulianDate(double jd) {
        int z = (int) (jd + 0.5);
        if (z < JULIAN_GREGORIAN_BOUNDARY) {
            return fromJulianDateInJulian(jd);
        }
        return fromJulianDateInGregorian(jd);
    }

    /**
     * 计算儒略千年数
     * 
     * @param jd
     *            要计算的儒略日
     * @return 儒略千年数
     */
    public static double getJulianThousandYears(double jd) {
        return (jd - J2000) / DAYS_OF_1000_YEARS;
    }

    /**
     * 计算儒略世纪数
     * 
     * @param jd
     *            要计算的儒略日
     * @return 儒略世纪数
     */
    public static double getJulianCentury(double jd) {
        return (jd - J2000) / DAYS_OF_CENTURY;
    }

    /**
     * 计算Gregorian日历的星期几。算法摘自<a href= "http://en.wikipedia.org/wiki/Zeller%27s_congruence"
     * >英文维基百科<i>Zeller's congruence</i>词条</a>。
     * 
     * @param y
     *            年份
     * @param m
     *            月份
     * @param d
     *            日期
     * @return 星期几的数字表示，1-6表示星期一到星期六，0表示星期日
     */
    public static int getWeekdayForGregorian(int y, int m, int d) {
        int c = y / 100;
        y = y % 100;
        int w = d + 13 * (m + 1) / 5 + y + y / 4 + c / 4 - 2 * c - 1;
        while (w < 0) {
            w += 7;
        }
        return w % 7;
    }

    /**
     * 计算Julian日历的星期几。算法摘自<a href= "http://en.wikipedia.org/wiki/Zeller%27s_congruence"
     * >英文维基百科<i>Zeller's congruence</i>词条</a>。
     * 
     * @param y
     *            年份
     * @param m
     *            月份
     * @param d
     *            日期
     * @return 星期几的数字表示，1-6表示星期一到星期六，0表示星期日
     */
    public static int getWeekdayForJulian(int y, int m, int d) {
        int c = y / 100;
        y = y % 100;
        int w = d + 13 * (m + 1) / 5 + y + y / 4 + 4 - c;
        while (w < 0) {
            w += 7;
        }
        return w % 7;
    }

    /**
     * 计算星期几，{@value #JULIAN_LAST_YEAR}年{@value #JULIAN_LAST_MONTH}月 {@value #JULIAN_LAST_DATE}
     * 日及以前按照Julian历法，{@value #GREGORIAN_FIRST_YEAR}年 {@value #GREGORIAN_FIRST_MONTH}月
     * {@value #GREGORIAN_FIRST_DATE}日及以后按照Gregorian历法，中间的日期按照 {@value #GREGORIAN_FIRST_YEAR}年
     * {@value #GREGORIAN_FIRST_MONTH}月 {@value #GREGORIAN_FIRST_DATE}日计算。
     * 
     * @param y
     *            年份
     * @param m
     *            月份
     * @param d
     *            日期
     * @return 星期几的数字表示，1-6表示星期一到星期六，0表示星期日
     */
    public static int getWeekday(int y, int m, int d) {
        if (y < JULIAN_LAST_YEAR) {
            return getWeekdayForJulian(y, m, d);
        }
        if (y == JULIAN_LAST_YEAR && m < JULIAN_LAST_MONTH) {
            return getWeekdayForJulian(y, m, d);
        }
        if (y == JULIAN_LAST_YEAR && m == JULIAN_LAST_MONTH && d <= JULIAN_LAST_DATE) {
            return getWeekdayForJulian(y, m, d);
        }

        if (y > GREGORIAN_FIRST_YEAR) {
            return getWeekdayForGregorian(y, m, d);
        }
        if (y == GREGORIAN_FIRST_YEAR && m > GREGORIAN_FIRST_MONTH) {
            return getWeekdayForGregorian(y, m, d);
        }
        if (y == GREGORIAN_FIRST_YEAR && m == GREGORIAN_FIRST_MONTH && d >= GREGORIAN_FIRST_DATE) {
            return getWeekdayForGregorian(y, m, d);
        }

        // 剩下的都是中间的，以Gregorian历法实施第一天计算
        return getWeekdayForGregorian(GREGORIAN_FIRST_YEAR, GREGORIAN_FIRST_MONTH,
                GREGORIAN_FIRST_DATE);
    }

    /**
     * 计算地球时和UTC的时差，算法摘自<i><a
     * href="http://eclipse.gsfc.nasa.gov/SEhelp/deltatpoly2004.html">NASA网站</a></i><br />
     * ∆T = TT - UT 此算法在-1999年到3000年有效
     * 
     * @param jd
     *            儒略日
     * @return ∆T的值，单位为秒
     */
    public static double getDeltaT(double jd) {
        Map<String, Number> cal = fromJulianDate(jd);
        int year = cal.get("year").intValue();
        int month = cal.get("month").intValue();
        return getDeltaT(year, month);
    }

    /**
     * 计算地球时和UTC的时差，算法摘自<i><a
     * href="http://eclipse.gsfc.nasa.gov/SEhelp/deltatpoly2004.html">NASA网站</a></i><br />
     * ∆T = TT - UT 此算法在-1999年到3000年有效
     * 
     * @param year
     *            要计算的年份
     * @param month
     *            要计算的月份
     * @return ∆T的值，单位为秒
     */
    public static double getDeltaT(int year, int month) {
        double y = year + (month - 0.5) / 12;
        if (year < -500) {
            double u = (year - 1820) / 100.0;
            return -20 + 32 * u * u;
        } else if (year < 500) {
            double u = y / 100;
            double u2 = u * u;
            double u3 = u2 * u;
            double u4 = u3 * u;
            double u5 = u4 * u;
            double u6 = u5 * u;
            return 10583.6 - 1014.41 * u + 33.78311 * u2 - 5.952053 * u3 - 0.1798452 * u4
                    + 0.022174192 * u5 + 0.0090316521 * u6;
        } else if (year < 1600) {
            double u = (y - 1000) / 100;
            double u2 = u * u;
            double u3 = u2 * u;
            double u4 = u3 * u;
            double u5 = u4 * u;
            double u6 = u5 * u;
            return 1574.2 - 556.01 * u + 71.23472 * u2 + 0.319781 * u3 - 0.8503463 * u4
                    - 0.005050998 * u5 + 0.0083572073 * u6;
        } else if (year < 1700) {
            double t = y - 1600;
            double t2 = t * t;
            double t3 = t2 * t;
            return 120 - 0.9808 * t - 0.01532 * t2 + t3 / 7129;
        } else if (year < 1800) {
            double t = y - 1700;
            double t2 = t * t;
            double t3 = t2 * t;
            double t4 = t3 * t;
            return 8.83 + 0.1603 * t - 0.0059285 * t2 + 0.00013336 * t3 - t4 / 1174000;
        } else if (year < 1860) {
            double t = y - 1800;
            double t2 = t * t;
            double t3 = t2 * t;
            double t4 = t3 * t;
            double t5 = t4 * t;
            double t6 = t5 * t;
            double t7 = t6 * t;
            return 13.72 - 0.332447 * t + 0.0068612 * t2 + 0.0041116 * t3 - 0.00037436 * t4
                    + 0.0000121272 * t5 - 0.0000001699 * t6 + 0.000000000875 * t7;
        } else if (year < 1900) {
            double t = y - 1860;
            double t2 = t * t;
            double t3 = t2 * t;
            double t4 = t3 * t;
            double t5 = t4 * t;
            return 7.62 + 0.5737 * t - 0.251754 * t2 + 0.01680668 * t3 - 0.0004473624 * t4 + t5
                    / 233174;
        } else if (year < 1920) {
            double t = y - 1900;
            double t2 = t * t;
            double t3 = t2 * t;
            double t4 = t3 * t;
            return -2.79 + 1.494119 * t - 0.0598939 * t2 + 0.0061966 * t3 - 0.000197 * t4;
        } else if (year < 1941) {
            double t = y - 1920;
            double t2 = t * t;
            double t3 = t2 * t;
            return 21.20 + 0.84493 * t - 0.076100 * t2 + 0.0020936 * t3;
        } else if (year < 1961) {
            double t = y - 1950;
            double t2 = t * t;
            double t3 = t2 * t;
            return 29.07 + 0.407 * t - t2 / 233 + t3 / 2547;
        } else if (year < 1986) {
            double t = y - 1975;
            double t2 = t * t;
            double t3 = t2 * t;
            return 45.45 + 1.067 * t - t2 / 260 - t3 / 718;
        } else if (year < 2005) {
            double t = y - 2000;
            double t2 = t * t;
            double t3 = t2 * t;
            double t4 = t3 * t;
            double t5 = t4 * t;
            return 63.86 + 0.3345 * t - 0.060374 * t2 + 0.0017275 * t3 + 0.000651814 * t4
                    + 0.00002373599 * t5;
        } else if (year < 2050) {
            double t = y - 2000;
            double t2 = t * t;
            return 62.92 + 0.32217 * t + 0.005589 * t2;
        } else if (year < 2150) {
            double u = (y - 1820) / 100;
            double u2 = u * u;
            return -20 + 32 * u2 - 0.5628 * (2150 - y);
        } else {
            double u = (y - 1820) / 100;
            double u2 = u * u;
            return -20 + 32 * u2;
        }
    }

    public static void main(String[] args) {
        int[] years = {1, 4, 100, 1900, 400, 2000, 2008, 0, -3, -200, -400};
        for (int y : years) {
            System.out.println(y + ": " + isLeapYear(y));
        }
        System.out.println(toJulianDate(2000, 1, 1));
        System.out.println(toJulianDateInGregorian(2000, 1, 1));
        System.out.println(toJulianDateInJulian(2000, 1, 1));
        System.out.println(toJulianDate(1582, 10, 4));
        System.out.println(toJulianDateInGregorian(1582, 10, 4));
        System.out.println(toJulianDateInJulian(1582, 10, 4));
        System.out.println(toJulianDate(1582, 10, 10));
        System.out.println(toJulianDateInGregorian(1582, 10, 10));
        System.out.println(toJulianDateInJulian(1582, 10, 10));
        System.out.println(getWeekday(1582, 1, 1));
        System.out.println(toJulianDate(2014, 8, 12, 0, 0, 0.0d));
        System.out.println(toJulianDate(1599, 12, 29, 12, 0, 0.0d));
    }
}
