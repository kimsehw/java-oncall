package oncall.view;

public class InputViewFactory {

    private InputViewFactory() {
    }

    public static InputView createMonthDateInputView() {
        return new InputView(InputViewType.MONTH_DATE.getMessage());
    }

    public static InputView createWeekDayScheduleInputView() {
        return new InputView(InputViewType.WEEKDAY_SCHEDULE.getMessage());
    }

    public static InputView createHoliDayScheduleInputView() {
        return new InputView(InputViewType.HOLIDAY_SCHEDULE.getMessage());
    }
}
