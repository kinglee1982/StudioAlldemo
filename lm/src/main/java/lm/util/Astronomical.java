package lm.util;

/**
 * 天文算法
 * Created by limin on 16/01/12.
 *
 * @see <a>http://scienceworld.wolfram.com</a>
 */
public class Astronomical {
	public static double getDay(int day, int hour, int minute, int second, int timeZone) {
		return day + (hour + (minute + second / 60.0) / 60.0) / 24.0 - timeZone * 24.0;
	}

	public static double getJulianDay(int year, int month, double day) {
		return 367 * year
				- (7 * (year + ((month + 9) / 12)) / 4)
				- (3 * (((year + (month - 9) / 7) / 100) + 1) / 4)
				+ (275 * month / 9) + day + 1721028.5;
	}

	public static double getMoonCycle(int year, int month, double day) {
		double jd = getJulianDay(year, month, day) - 0.5;
		return (jd - 2449128.59) / 29.53058867;
	}
}
