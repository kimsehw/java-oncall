package oncall.model.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import oncall.exception.ExceptionAnnounce;
import oncall.exception.InputException;
import oncall.model.domain.parser.ScheduleInputParser;

public class Scheduler {

    private List<String> weekdaySchedule;
    private List<String> holidaySchedule;

    public Scheduler(String weekdayScheduleInput, String holidayScheduleInput) {
        ScheduleInputParser scheduleInputParser = new ScheduleInputParser();
        weekdaySchedule = scheduleInputParser.parseScheduleInput(weekdayScheduleInput);
        holidaySchedule = scheduleInputParser.parseScheduleInput(holidayScheduleInput);
        validateWeekdayHolidaySchedule();
    }

    private void validateWeekdayHolidaySchedule() {
        validateLength();
        validateDiff();
    }

    private void validateLength() {
        if (weekdaySchedule.size() != holidaySchedule.size()) {
            throw new InputException(ExceptionAnnounce.WEEK_HOLIDAY_DIFF);
        }
    }

    private void validateDiff() {
        Set<String> weekdayWorkerPool = new HashSet<>(weekdaySchedule);
        if (!weekdayWorkerPool.containsAll(holidaySchedule)) {
            throw new InputException(ExceptionAnnounce.WEEK_HOLIDAY_DIFF);
        }
    }

}
