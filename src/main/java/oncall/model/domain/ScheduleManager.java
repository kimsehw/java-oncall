package oncall.model.domain;

import java.util.List;
import java.util.Map;
import oncall.constant.ConstantBox;

public class ScheduleManager {

    private int holidayIndex = 0;
    private int weekdayIndex = 0;
    private String beforeWorker = null;

    public List<WorkInformation> makeScheduleBoard(MonthAndDayInput monthAndDayInput, ScheduleInput scheduleInput) {
        Calender calenderGenerator = new Calender(monthAndDayInput);
        List<Day> calender = calenderGenerator.makeCalender();
        int month = monthAndDayInput.getMonth();
        Map<String, List<Worker>> scheduleTable = scheduleInput.getScheduleTable();
        return calender.stream()
                .map(day -> getWorkInformations(month, day, scheduleTable))
                .toList();
    }

    private WorkInformation getWorkInformations(int month, Day day, Map<String, List<Worker>> scheduleTable) {
        List<Worker> workers = getWorkers(day.isHoliDay(), scheduleTable);
        int workerIndex = getWorkerIndex(day.isHoliDay()) % workers.size();
        Worker dayWorker = getDailyWorker(workers, workerIndex);
        return new WorkInformation(month, dayWorker.getName(), day.getDayOfWeek(), day.isHolidayNotWeekend());
    }

    private int getWorkerIndex(boolean isHoliday) {
        if (isHoliday) {
            return holidayIndex++;
        }
        return weekdayIndex++;
    }

    private Worker getDailyWorker(List<Worker> workers, int workerIndex) {
        Worker dayWorker = workers.get(workerIndex);
        if (dayWorker.isChanged()) {
            dayWorker.setChanged(false);
            dayWorker = workers.get(workerIndex - 1);
        }
        if (beforeWorker != null && beforeWorker.equals(dayWorker.getName())) {
            dayWorker = workers.get(workerIndex + 1);
            dayWorker.setChanged(true);
        }
        beforeWorker = dayWorker.getName();
        return dayWorker;
    }

    private List<Worker> getWorkers(boolean isHolyDay, Map<String, List<Worker>> scheduleTable) {
        if (isHolyDay) {
            return scheduleTable.get(ConstantBox.HOLIDAY);
        }
        return scheduleTable.get(ConstantBox.WEEKDAY);
    }

    /*public static void main(String[] args) {
        ScheduleManager scheduleManager = new ScheduleManager();
        String w = "준팍,도밥,고니,수아,루루,글로,솔로스타,우코,슬링키,참새,도리";
        String h = "수아,루루,글로,솔로스타,우코,슬링키,참새,도리,준팍,도밥,고니";

        List<WorkInformation> make = scheduleManager.makeScheduleBoard(new MonthAndDayInput(5, "월"),
                new ScheduleInput(w, h));
        //make.forEach(info -> System.out.println(info.toString()));
        OutputView.showSchedule(make);
    }*/
}
