package oncall.model.domain;

import java.util.List;
import oncall.model.domain.parser.MonthAndDayParser;

public class MonthAndDayInput {

    private final List<String> monthAndDay;

    public MonthAndDayInput(String input) {
        MonthAndDayParser monthAndDayParser = new MonthAndDayParser();
        monthAndDay = monthAndDayParser.parseMonthDayInput(input);
    }
}
