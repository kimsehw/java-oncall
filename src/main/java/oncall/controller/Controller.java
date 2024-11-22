package oncall.controller;

import java.util.List;
import java.util.function.Supplier;
import oncall.model.domain.MonthAndDayInput;
import oncall.model.domain.ScheduleInput;
import oncall.model.domain.ScheduleManager;
import oncall.model.domain.WorkInformation;
import oncall.model.domain.parser.MonthAndDayParser;
import oncall.model.domain.parser.ScheduleInputParser;
import oncall.view.InputView;
import oncall.view.InputViewFactory;
import oncall.view.OutputView;

public class Controller {

    ScheduleManager scheduleManager;
    InputView inputView;
    OutputView outputView;

    public Controller(ScheduleManager scheduleManager, InputView inputView, OutputView outputView) {
        this.scheduleManager = scheduleManager;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        MonthAndDayInput monthAndDayInput = handle(this::handleMonthAndDayInput);
        ScheduleInput scheduleInput = handle(this::handleScheduleInput);
        List<WorkInformation> workInformations = scheduleManager.makeScheduleBoard(monthAndDayInput, scheduleInput);
        outputView.showSchedule(workInformations);
    }

    private MonthAndDayInput handleMonthAndDayInput() {
        inputView = InputViewFactory.createMonthDateInputView();
        String input = inputView.getInput();
        MonthAndDayParser monthAndDayParser = new MonthAndDayParser();
        return monthAndDayParser.parseMonthDayInput(input);
    }

    private ScheduleInput handleScheduleInput() {
        inputView = InputViewFactory.createWeekDayScheduleInputView();
        List<String> weekdayOrder = getScheduleOrder();
        inputView = InputViewFactory.createHoliDayScheduleInputView();
        List<String> holidayOrder = getScheduleOrder();
        return new ScheduleInput(weekdayOrder, holidayOrder);
    }

    private List<String> getScheduleOrder() {
        ScheduleInputParser scheduleInputParser = new ScheduleInputParser();
        return scheduleInputParser.parseScheduleInput(inputView.getInput());
    }

    public static <T> T handle(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
