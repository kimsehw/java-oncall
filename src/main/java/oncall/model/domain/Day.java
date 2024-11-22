package oncall.model.domain;

import oncall.constant.ConstantBox;

public class Day {

    private boolean isHoliDay;
    private String dayOfWeek;

    public Day(boolean isHoliDay, String dayOfWeek) {
        this.isHoliDay = isHoliDay;
        this.dayOfWeek = dayOfWeek;
    }

    public boolean isHoliDay() {
        return isHoliDay;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public boolean isHolidayNotWeekend() {
        return isHoliDay && (!ConstantBox.WEEKEND.contains(dayOfWeek));
    }
}
