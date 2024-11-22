package oncall.model.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import oncall.exception.ExceptionAnnounce;
import oncall.exception.InputException;

public class ScheduleInput {

    private List<String> weekdaySchedule;
    private List<String> holidaySchedule;

    public ScheduleInput(List<String> weekdayScheduleInput, List<String> holidayScheduleInput) {
        weekdaySchedule = weekdayScheduleInput;
        holidaySchedule = holidayScheduleInput;
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

    public Map<String, List<Worker>> getScheduleTable() {
        return Map.of(
                "weekday", transferToWorker(weekdaySchedule),
                "holiday", transferToWorker(holidaySchedule)
        );
    }

    private List<Worker> transferToWorker(List<String> schedule) {
        return schedule.stream()
                .map(name -> new Worker(name, false))
                .toList();
    }
}
