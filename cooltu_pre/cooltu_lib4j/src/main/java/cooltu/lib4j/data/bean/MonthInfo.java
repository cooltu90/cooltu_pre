package cooltu.lib4j.data.bean;


import cooltu.lib4j.tools.MathTool;

public class MonthInfo {

    private String[] weekOfMonthNames = {"第一周", "第二周", "第三周", "第四周", "第五周"};

    public int firstDayWeek;
    public int weeks;

    public int weekOfMonth(int d) {
        return MathTool.row(d + firstDayWeek - 1, 7);
    }

    public String[] weekOfMonthNames() {
        String[] names = new String[weeks];
        for (int i = 0; i < weeks; i++) {
            names[i] = weekOfMonthNames[i];
        }
        return names;
    }

    public static String[] weekNames() {
        return new String[]{"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
    }

}
