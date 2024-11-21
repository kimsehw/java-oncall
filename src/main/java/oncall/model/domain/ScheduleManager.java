package oncall.model.domain;

import java.util.List;
import java.util.Map;

public class ScheduleManager {

    public static final String HOLIDAY = "holiday";

    public List<WorkInformation> makeScheduleBoard(MonthAndDayInput monthAndDayInput, ScheduleInput scheduleInput) {
        Calender calenderGenerator = new Calender(monthAndDayInput);
        List<Day> calender = calenderGenerator.makeCalender();

        Map<String, List<Worker>> scheduleTable = scheduleInput.getScheduleTable();
        Worker beforeWorker = null;
        int holidayIndex = 0;
        int weekdayIndex = 0;
        for (Day day : calender) {
            Worker dayWorker;
            if (day.isHolyDay()) {
                dayWorker = scheduleTable.get(HOLIDAY).get(holidayIndex);
                if (beforeWorker != null && beforeWorker.equals(dayWorker)) {
                    dayWorker = scheduleTable.get(HOLIDAY).get(holidayIndex + 1);
                    dayWorker.setChanged(true);
                }
            }
        }

        return null;
    }

    ;
}
