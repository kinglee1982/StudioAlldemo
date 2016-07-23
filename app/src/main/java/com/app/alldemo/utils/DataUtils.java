package com.app.alldemo.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 字符串转换到时间格式
 * 增加一周的日期格式的字符串
 * 增加一天的日期格式的字符串
 * 增加一个月的日期格式的字符串
 * 指定日期按周累加到最近的日期
 * 指定日期按天累加最近的日期
 * 指定日期按月累加最近的日期
 * 获取日期之间的天数
 * 获取当前的时间
 * 获取当前的小时
 * 获取某个日期的年月日
 * 获取当前时间增加days天之后的日期
 * 创建指定的date
 * 日期的格式化-date
 * 日期的格式化-String
 * 通过日期获取年月日
 * 计算两日期之间的距离
 * long型转换为日期格式
 * 两个日期之间的月份
 * 某个日期月份的天数
 * 某个日期最大的年天数
 * 此日期在此年的位置
 */
public class DataUtils {
	private static DataUtils instance;
	public static DataUtils getInstance() {
		if (instance == null) {
			instance = new DataUtils();
		}
		return instance;
	}
	/**
	 * 字符串转换到时间格式
	 * 
	 * @param dateStr
	 *            需要转换的字符串
	 * @param formatStr
	 *            需要格式的目标字符串 举例 yyyy-MM-dd
	 * @return Date 返回转换后的时间
	 * @throws ParseException
	 *             转换异常
	 */
	public Date StringToDate(String dateStr, String formatStr) {
		DateFormat sdf = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 增加一周的日期格式的字符串
	 * 
	 * @param dateStr
	 *            日期格式的字符串
	 * @param formatStr
	 *            日期格式
	 * @return
	 */
	public String addWeek(String dateStr, String formatStr) {
		String time = "";
		Date data = StringToDate(dateStr, formatStr);
		if (data != null) {
			data.setDate(data.getDate() + 7);
			time = new SimpleDateFormat(formatStr).format(data);
		}
		return time;
	}
	/**
	 * 增加一天的日期格式的字符串
	 * 
	 * @param dateStr
	 *            日期格式的字符串
	 * @param formatStr
	 *            日期格式
	 * @return
	 */
	public String addDay(String dateStr, String formatStr) {
		String time = "";
		Date data = StringToDate(dateStr, formatStr);
		if (data != null) {
			data.setDate(data.getDate() + 1);
			time = new SimpleDateFormat(formatStr).format(data);
		}
		return time;
	}
	/**
	 * 增加一个月的日期格式的字符串
	 * 
	 * @param dateStr
	 *            日期格式的字符串
	 * @param formatStr
	 *            日期格式
	 * @return
	 */
	public String addMonth(String dateStr, String formatStr) {
		String time = "";
		Date data = StringToDate(dateStr, formatStr);
		if (data != null) {
			data.setMonth(data.getMonth() + 1);
			time = new SimpleDateFormat(formatStr).format(data);
		}
		return time;
	}
	/**
	 * 指定日期按周累加到最近的日期
	 * @param data 起始日期
	 * @return
	 */
	public Date getbeforeWeek(Date data) {
		Date nowdate = new Date();
		while (data.before(nowdate)) {
			data.setDate(data.getDate() + 7);
			getbeforeWeek(data);
		}
		return data;
	}
	/**
	 * 指定日期按天累加最近的日期
	 * @param data
	 *            起始日期
	 * @return
	 */
	public Date getbeforeDay(Date data) {
		Date nowdate = new Date();
		while (data.before(nowdate)) {
			data.setDate(data.getDate() + 1);
			getbeforeDay(data);
		}
		return data;
	}
	/**
	 * 指定日期按月累加最近的日期
	 * 
	 * @param data
	 *            起始日期
	 * @return
	 */
	public Date getbeforeMonth(Date data) {
		Date nowdate = new Date();
		while (data.before(nowdate)) {
			data.setMonth(data.getMonth() + 1);
			getbeforeMonth(data);
		}
		return data;
	}
	/**
	 * 获取日期之间的天数
	 * 
	 * @param toData
	 *            日期 2008/4/25
	 * @param fromData
	 *            日期 2008/4/20
	 * @param formData
	 *            日期格式 yyyy/MM/dd
	 * @return
	 */
	public long getDatas(String toData, String fromData, String formData) {
		try {
			SimpleDateFormat df = new SimpleDateFormat(formData);
			long to = df.parse(toData).getTime();
			long from = df.parse(fromData).getTime();
			long data = (to - from) / (1000 * 60 * 60 * 24);
			return data;
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}
	/**
	 * 获取当前的时间 yyyyMMdd
	 * 
	 * @return
	 */
	public String getTime(String format) {
		String time = new SimpleDateFormat(format).format(Calendar
				.getInstance().getTime());
		return time;
	}

	/**
	 * 获取当前的时间
	 * 
	 * @return
	 */
	public String getTimes(String format) {
		Calendar calendar = Calendar.getInstance(Locale.getDefault());
		Date d = new Date();
		d.setTime(calendar.getTimeInMillis());
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(d);
	}
	/**
	 * 获取当前的小时
	 */
	public int getHours() {
		Calendar calendar = Calendar.getInstance(Locale.getDefault());
		calendar.get(Calendar.HOUR);
		Date d = new Date();
		return d.getHours();
	}
	/**
	 * 获取某个日期的年月日
	 * @param data
	 */
	public void getDay(Date data){
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int mon = cal.get(Calendar.MONTH)+1;//Calendar里取出来的month比实际的月份少1，所以要加上
		int year = cal.get(Calendar.YEAR);
	}
	/**
	 * 获取当前时间增加days天之后的日期
	 * @param days
	 */
	public void getDatas(int days){
		Date date=Calendar.getInstance().getTime();
		date.setDate(date.getDate()+days);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int mon = cal.get(Calendar.MONTH)+1;//Calendar里取出来的month比实际的月份少1，所以要加上
		int year = cal.get(Calendar.YEAR);
		System.out.println("year"+year+"month:"+mon+"day:"+day);
	}
	/**
	 * 创建指定的date
	 * @return
	 */
	public Date getdate(){
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(Calendar.YEAR,2013);//设置年
		gc.set(Calendar.MONTH, 8);//这里0是1月..以此向后推
		gc.set(Calendar.DAY_OF_MONTH, 29);//设置天
		gc.set(Calendar.HOUR_OF_DAY,5);//设置小时
		gc.set(Calendar.MINUTE, 7);//设置分
		gc.set(Calendar.SECOND, 6);//设置秒
		gc.set(Calendar.MILLISECOND,200);//设置毫秒
		Date date = gc.getTime();
		return date;
	}
	/**
	 * 通过日期获取年月日
	 * @param date
	 */
	public void getDates(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int mon = cal.get(Calendar.MONTH)+1;//Calendar里取出来的month比实际的月份少1，所以要加上
		int year = cal.get(Calendar.YEAR);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
	}
	/**
	 * 创建指定的date
	 * @return
	 */
	public Date getDates(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2015);
		cal.set(Calendar.MONTH, 7);
		cal.set(Calendar.DAY_OF_MONTH, 13);
		Date date=cal.getTime();
		return date;
	}
	/**
	 * 日期的格式化-date yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public Date formatDate(String format,String dateString){
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		try {
			Date currentTime_2 = formatter.parse(dateString);
			return currentTime_2;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 日期的格式化-String
	 * @return
	 */
	public String getDateString(String format,Date date) {
		return new SimpleDateFormat(format).format(date);
	}
	/**
	 * 计算两日期之间的距离
	 * @param smdate
	 * @param bdate
	 * @return
	 */
	public int daysBetween(Date smdate, Date bdate){
		long between_days;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			smdate = sdf.parse(sdf.format(smdate));
			bdate = sdf.parse(sdf.format(bdate));
			Calendar cal = Calendar.getInstance();
			cal.setTime(smdate);
			long time1 = cal.getTimeInMillis();
			cal.setTime(bdate);
			long time2 = cal.getTimeInMillis();
			between_days = (time2 - time1) / (1000 * 3600 * 24);
			return Integer.parseInt(String.valueOf(between_days));
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 计算两日期之间的距离
	 */
	public int daysBetween(String smdate, String bdate){
		long between_days;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(smdate));
			long time1 = cal.getTimeInMillis();
			cal.setTime(sdf.parse(bdate));
			long time2 = cal.getTimeInMillis();
			between_days = (time2 - time1) / (1000 * 3600 * 24);
			return Integer.parseInt(String.valueOf(between_days));
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}
	/**
     * long型转换为日期格式
     * @param format
     * @param mills
     * @return
     */
    public String forTimeMills(String format,long mills){
        Date date=new Date(System.currentTimeMillis());
        return new SimpleDateFormat(format).format(date);
    }
	/**
	 * 两个日期之间的月份
	 * @param date1
	 * @param date2
	 * @return 格式到月不能到天 yyyyMM
	 */
	private static int getMonths(Date date1, Date date2) {
		int iMonth = 0;
		int flag = 0;
		try {
			Calendar objCalendarDate1 = Calendar.getInstance();
			objCalendarDate1.setTime(date1);

			Calendar objCalendarDate2 = Calendar.getInstance();
			objCalendarDate2.setTime(date2);

			if (objCalendarDate2.equals(objCalendarDate1)){
				return 0;
			}
			//赋值objCalendarDate2大
			if (objCalendarDate1.after(objCalendarDate2)) {
				Calendar temp = objCalendarDate1;
				objCalendarDate1 = objCalendarDate2;
				objCalendarDate2 = temp;
			}
			if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) < objCalendarDate1.get(Calendar.DAY_OF_MONTH)){
				flag = 1;
			}
			if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1.get(Calendar.YEAR)){
				iMonth = ((objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1.get(Calendar.YEAR))
						* 12 + objCalendarDate2.get(Calendar.MONTH) - flag)
						- objCalendarDate1.get(Calendar.MONTH);
			}else{
				iMonth = objCalendarDate2.get(Calendar.MONTH)
						- objCalendarDate1.get(Calendar.MONTH) - flag;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return iMonth;
	}
	/**
	 * 某个日期月份的天数
	 * @param dayInt
	 */
	public void getMaxDays(int dayInt){
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			Date dayDate = formatter.parse(String.valueOf(dayInt));
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dayDate);
			int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			System.out.println("Max Day: " + maxDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 某个日期最大的年天数
	 * @return
	 */
	public int getMaxYearDays(int year){
		if((year%4==0 && year%100!=0) || year%400==0){
			return 366;
		}else{
			return 365;
		}
	}

	/**
	 * 此日期在此年的位置
	 * @return
	 */
	public int getYearPosition(int year,int month,int day){
		int secondDays=28;
		if((year%4==0 && year%100!=0) || year%400==0){
			secondDays=29;
		}
		switch(month-1)
		{
			case 12:day += 31;
			case 11:day += 30;
			case 10:day += 31;
			case 9:day += 30;
			case 8:day += 31;
			case 7:day += 31;
			case 6:day += 30;
			case 5:day += 31;
			case 4:day += 30;
			case 3:day += 31;
			case 2:day += secondDays;
			case 1:day += 31;
		}
		return day;
	}
}
