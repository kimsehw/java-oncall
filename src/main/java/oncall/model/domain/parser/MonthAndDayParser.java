package oncall.model.domain.parser;

import java.util.List;
import java.util.Set;
import oncall.constant.ConstantBox;
import oncall.exception.InputException;

public class MonthAndDayParser {

    public static final Set<String> DAYS = Set.of("월", "화", "수", "목", "금", "토", "일");

    public List<String> parseMonthDayInput(String input) {
        input = input.trim();
        List<String> monthAndDay = List.of(input.split(ConstantBox.SEPARATOR));
        validateLength(monthAndDay);
        validateMonth(monthAndDay);
        validateDay(monthAndDay);
        return monthAndDay;
    }

    private static void validateDay(List<String> monthAndDay) {
        String day = monthAndDay.get(1);
        if (isValidDay(day)) {
            throw new InputException();
        }
    }

    private static boolean isValidDay(String day) {
        return !DAYS.contains(day);
    }

    private static void validateMonth(List<String> monthAndDay) {
        try {
            int month = Integer.parseInt(monthAndDay.get(0));
            if (isValidMonth(month)) {
                throw new InputException();
            }
        } catch (NumberFormatException e) {
            throw new InputException();
        }
    }

    private static boolean isValidMonth(int month) {
        return month > 12 || month < 1;
    }

    private static void validateLength(List<String> monthAndDay) {
        if (monthAndDay.size() != 2) {
            throw new InputException();
        }
    }
}
