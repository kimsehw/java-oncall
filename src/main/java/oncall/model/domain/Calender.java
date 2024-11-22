package oncall.model.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import oncall.constant.ConstantBox;

public class Calender {
    public static final Map<Integer, Integer> MONTH_DATE = new HashMap<Integer, Integer>() {
        {
            put(1, 31);
            put(2, 28);
            put(3, 31);
            put(4, 30);
            put(5, 31);
            put(6, 30);
            put(7, 31);
            put(8, 31);
            put(9, 30);
            put(10, 31);
            put(11, 30);
            put(12, 31);
        }
    };
    public static final int NOHOLIDAY = -1;
    public static final Map<Integer, List<Integer>> HOLIDAY = new HashMap<Integer, List<Integer>>() {
        {
            put(1, List.of(1));
            put(2, List.of(NOHOLIDAY));
            put(3, List.of(1));
            put(4, List.of(NOHOLIDAY));
            put(5, List.of(5));
            put(6, List.of(6));
            put(7, List.of(NOHOLIDAY));
            put(8, List.of(15));
            put(9, List.of(NOHOLIDAY));
            put(10, List.of(3, 9));
            put(11, List.of(NOHOLIDAY));
            put(12, List.of(25));
        }
    };
    public static final Map<String, Integer> DAY_MOD = new HashMap<String, Integer>() {
        {
            put("일", 0);
            put("월", 1);
            put("화", 2);
            put("수", 3);
            put("목", 4);
            put("금", 5);
            put("토", 6);
        }
    };

    private final MonthAndDayInput monthAndDayInput;

    public Calender(MonthAndDayInput monthAndDayInput) {
        this.monthAndDayInput = monthAndDayInput;
    }

    public List<Day> makeCalender() {
        int numberOfDate = MONTH_DATE.get(monthAndDayInput.getMonth());
        int startDayNumber = DAY_MOD.get(monthAndDayInput.getStartDay());
        List<Integer> holidays = HOLIDAY.get(monthAndDayInput.getMonth());

        List<Day> calender = new ArrayList<>();
        for (int dayCount = 0; dayCount < numberOfDate; dayCount++) {
            String day = getWorkDay(dayCount + startDayNumber);
            if (holidays.contains(dayCount + 1) || ConstantBox.WEEKEND.contains(day)) {
                calender.add(new Day(true, day));
                continue;
            }
            calender.add(new Day(false, day));
        }
        return calender;
    }

    public String getWorkDay(int dayOrder) {
        for (String key : DAY_MOD.keySet()) {
            if (DAY_MOD.get(key).equals(dayOrder % 7)) {
                return key;
            }
        }
        throw new IllegalArgumentException("??????");
    }
}
