package lm.date;

import java.util.Calendar;

/**
 * 月份信息
 * <p/>
 * Created by limin on 15/10/12.
 */
public final class Month implements Cloneable{
	private final static Calendar sCalendar;

	static {
		sCalendar = Calendar.getInstance();
		sCalendar.clear();
	}

	/**
	 * 平年每月的天数
	 */
	private final static int[] DEF_DAYS_OF_MONTH =
			{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	/**
	 * 闰年每月的天数
	 */
	private final static int[] LEAP_DAYS_OF_MONTH =
			{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	/**
	 * 年份
	 */
	private int year;

	/**
	 * 月份
	 * <p/>
	 * 范围：0 - 11 （对应：1月 - 12月）
	 */
	private int month;

	/**
	 * {@code true}为闰年，{@code false}平年
	 */
	private boolean isLeap;

	/**
	 * 该月的天数
	 */
	private int length;

	public Month() {
		// 默认系统当前月份
		Calendar calendar = (Calendar) sCalendar.clone();
		calendar.setTimeInMillis(System.currentTimeMillis());
		setMonth(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
	}

	/**
	 * 构造月份
	 *
	 * @param year  年份
	 * @param month 月份
	 * @see Month#setMonth(int, int)
	 */
	public Month(int year, int month) {
		setMonth(year, month);
	}

	/**
	 * 前一个月
	 *
	 * @return
	 */
	public Month previous() {
		return new Month(this.year, this.month - 1);
	}

	/**
	 * 下一个月
	 *
	 * @return
	 */
	public Month next() {
		return new Month(this.year, this.month + 1);
	}

	/**
	 * @param year  年份
	 * @param month 月份，可以是任意整数，但不一定是实际的月份。
	 *              <p/>
	 *              例如：设置为2015年-1月，实际为2014年12月
	 * @see Calendar#JANUARY
	 * @see Calendar#FEBRUARY
	 * @see Calendar#MARCH
	 * @see Calendar#APRIL
	 * @see Calendar#MAY
	 * @see Calendar#JUNE
	 * @see Calendar#JULY
	 * @see Calendar#AUGUST
	 * @see Calendar#SEPTEMBER
	 * @see Calendar#OCTOBER
	 * @see Calendar#NOVEMBER
	 * @see Calendar#DECEMBER
	 */
	public void setMonth(int year, int month) {
		Calendar calendar = (Calendar) sCalendar.clone();
		calendar.set(year, month, 1);
		this.year = calendar.get(Calendar.YEAR);
		this.month = calendar.get(Calendar.MONTH);

		this.isLeap = ((this.year % 4) == 0 && ((this.year % 100) != 0 || (this.year % 400) == 0));
		this.length = this.isLeap ? LEAP_DAYS_OF_MONTH[this.month] : DEF_DAYS_OF_MONTH[this.month];
	}

	/**
	 * @return 年份
	 */
	public int getYear() {
		return this.year;
	}

	/**
	 * @return 月份
	 * @see Calendar#JANUARY
	 * @see Calendar#FEBRUARY
	 * @see Calendar#MARCH
	 * @see Calendar#APRIL
	 * @see Calendar#MAY
	 * @see Calendar#JUNE
	 * @see Calendar#JULY
	 * @see Calendar#AUGUST
	 * @see Calendar#SEPTEMBER
	 * @see Calendar#OCTOBER
	 * @see Calendar#NOVEMBER
	 * @see Calendar#DECEMBER
	 */
	public int getMonth() {
		return this.month;
	}

	/**
	 * 平年、闰年
	 *
	 * @return {@code true}为闰年，{@code false}为平年
	 */
	public boolean isLeap() {
		return this.isLeap;
	}

	/**
	 * 当月的总共天数
	 *
	 * @return
	 */
	public int getLength() {
		return this.length;
	}

	public long monthStart() {
		return getTime(1);
	}

	public long monthEnd() {
		return getTime(length);
	}

	/**
	 * 当月某一天的星期
	 *
	 * @param day 日期，从1日开始
	 * @return 星期 {@code 日 ~ 六}
	 * @see Calendar#SUNDAY
	 * @see Calendar#MONDAY
	 * @see Calendar#TUESDAY
	 * @see Calendar#WEDNESDAY
	 * @see Calendar#THURSDAY
	 * @see Calendar#FRIDAY
	 * @see Calendar#SATURDAY
	 */
	public int getWeek(int day) {
		if(day <= 0 && day > getLength()) {
			throw new Error("Date Error.");
		}

		Calendar calendar = (Calendar) sCalendar.clone();
		calendar.set(year, month, day);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	public long getTime(int day) {
		Calendar calendar = (Calendar) sCalendar.clone();
		calendar.set(year, month, day);
		return calendar.getTimeInMillis();
	}

	@Override
	public boolean equals(Object o) {
		if(o != null && o instanceof Month) {
			Month m = (Month) o;
			return m.year == this.year && m.month == this.month;
		}

		return false;
	}

	public boolean equals(int year, int month) {
		return this.year == year && this.month == month;
	}

	@Override
	public String toString() {
		return String.format("%s --- Year: %04d Month: %02d\n",
				super.toString(),
				this.year,
				this.month + 1);
	}

	@Override
	public Object clone() {
		try {
			Month clone = (Month) super.clone();
			clone.year = year;
			clone.month = month;
			clone.isLeap = isLeap;
			clone.length = length;
			return clone;
		}
		catch(CloneNotSupportedException e) {
			throw new AssertionError(e);
		}
	}
}
