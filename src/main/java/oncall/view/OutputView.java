package oncall.view;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import oncall.model.domain.WorkInformation;

public class OutputView {
    private static final String NORMAL_DAY_FORMAT = "%d월 %d일 %s %s";
    private static final String HOLIDAY_FORMAT = "%d월 %d일 %s(휴일) %s";

    private static String formatting(WorkInformation workInformation, int date) {
        if (workInformation.isHoliday()) {
            return String.format(HOLIDAY_FORMAT, workInformation.getMonth(), date, workInformation.getDay()
                    , workInformation.getWorkerName());
        }
        return String.format(NORMAL_DAY_FORMAT, workInformation.getMonth(), date, workInformation.getDay()
                , workInformation.getWorkerName());
    }

    public void showSchedule(List<WorkInformation> workInformations) {
        AtomicInteger index = new AtomicInteger();
        workInformations.stream()
                .map(workInformation -> formatting(workInformation, index.getAndIncrement() + 1))
                .forEach(System.out::println);
    }
}
