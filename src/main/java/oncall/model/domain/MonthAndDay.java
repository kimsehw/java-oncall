package oncall.model.domain;

import java.util.List;
import oncall.model.domain.parser.MonthAndDayParser;

public class MonthAndDay {

    private final List<String> monthAndDay;

    public MonthAndDay(String input) {
        MonthAndDayParser monthAndDayParser = new MonthAndDayParser();
        monthAndDay = monthAndDayParser.parseMonthDayInput(input);
    }
}
