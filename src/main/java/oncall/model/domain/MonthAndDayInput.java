package oncall.model.domain;

import java.util.List;
import oncall.model.domain.parser.MonthAndDayParser;

public class MonthAndDayInput {

    private final List<String> monthAndDay;

    public MonthAndDayInput(String input) {
        MonthAndDayParser monthAndDayParser = new MonthAndDayParser();
        monthAndDay = monthAndDayParser.parseMonthDayInput(input);
    }

    public int getMonth() {
        return Integer.parseInt(monthAndDay.get(0));
    }

    public String getStartDay() {
        return monthAndDay.get(1);
    }
}
