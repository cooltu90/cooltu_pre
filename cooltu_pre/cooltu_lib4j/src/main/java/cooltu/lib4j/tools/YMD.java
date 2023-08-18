package cooltu.lib4j.tools;

import cooltu.lib4j.data.bean.MonthInfo;

import java.util.Calendar;
import java.util.Date;

public class YMD {

    private Calendar calendar;

    public YMD() {
        calendar = Calendar.getInstance();
    }

    public YMD(Calendar calendar) {
        this.calendar = calendar;
    }

    public static int getDays(int year, int month) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 2:
                return (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) ? 29 : 28;
            default:
                return 30;
        }
    }

    public int getDays() {
        return getDays(y(), m());
    }

    public static YMD obtain() {
        YMD ymd = new YMD();
        return ymd;
    }

    public YMD setCalendar(Calendar calendar) {
        this.calendar = calendar;
        return this;
    }

    public int y() {
        return calendar.get(Calendar.YEAR);
    }

    public int m() {
        return calendar.get(Calendar.MONTH) + 1;
    }

    public int d() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public int h() {
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public int minute() {
        return calendar.get(Calendar.MINUTE);
    }

    public int s() {
        return calendar.get(Calendar.SECOND);
    }


    public int week() {
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        if (week == 1) {
            week = 7;
        } else {
            week--;
        }
        return week;
    }


    public YMD y(int year) {
        calendar.set(Calendar.YEAR, year);
        return this;
    }

    public YMD m(int month) {
        calendar.set(Calendar.MONTH, month - 1);
        return this;
    }

    public YMD d(int day) {
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return this;
    }


    public String ymd() {
        return y() + "-" + StringTool.formatNumber(m(), 2) + "-" + StringTool.formatNumber(d(), 2);
    }

    public String ym() {
        return y() + "-" + StringTool.formatNumber(m(), 2);
    }

    public String ymdhms() {
        return y() + "-" + StringTool.formatNumber(m(), 2) + "-" + StringTool.formatNumber(d(), 2)
                + " " + StringTool.formatNumber(h(), 2) + ":" + StringTool.formatNumber(minute(), 2) + ":" +
                StringTool.formatNumber(s(), 2);
    }

    public YMD copy() {
        YMD ymd = YMD.obtain();
        ymd.copy(this);
        return ymd;
    }

    private void copy(YMD ymd) {
        this.calendar.setTimeInMillis(ymd.calendar.getTimeInMillis());
    }

    public YMD addMonth(int month) {
        calendar.add(Calendar.MONTH, month);
        return this;
    }

    public YMD addDay(int day) {
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return this;
    }

    public String ymd(String format) {
        return TimeTool.format(format, new Date(calendar.getTimeInMillis()));
    }


    public MonthInfo getMonthInfo() {
        MonthInfo monthInfo = new MonthInfo();
        YMD copy = copy().d(1);
        monthInfo.firstDayWeek = copy.week();
        monthInfo.weeks = MathTool.row(copy.getDays() + monthInfo.firstDayWeek - 1, 7);
        return monthInfo;
    }

}
