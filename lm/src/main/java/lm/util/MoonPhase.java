package lm.util;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by limin on 16/01/12.
 */
public class MoonPhase {
	private final static Calendar sCalendar = Calendar.getInstance();

	private final static TimeZone sTimeZone = sCalendar.getTimeZone();

	private final static int sTimeRawOffset = sTimeZone.getRawOffset() / (60 * 60 * 1000);

	public static int getMoonPhaseIndex(int year, int month, int day) {
		sCalendar.getTimeZone();
		double cycle = Astronomical.getMoonCycle(
				year, month, Astronomical.getDay(day, 0, 0, 0, sTimeRawOffset));
		int index = (int) ((cycle - (int)(cycle)) * 30);
		return index >= 30 ? 0 : index;
	}
}
