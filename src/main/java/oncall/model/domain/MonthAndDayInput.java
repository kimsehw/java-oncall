package oncall.model.domain;

public class MonthAndDayInput {

    private final int month;
    private final String day;

    public MonthAndDayInput(int month, String day) {
        this.month = month;
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public String getStartDay() {
        return day;
    }
}
