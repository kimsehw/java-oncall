package oncall.config;

import oncall.controller.Controller;
import oncall.model.domain.ScheduleManager;
import oncall.view.InputView;
import oncall.view.OutputView;

public class Config {

    public static Controller createController() {
        return new Controller(scheduleManager(), inputView(), outputView());
    }

    private static ScheduleManager scheduleManager() {
        return new ScheduleManager();
    }

    private static InputView inputView() {
        return new InputView(null);
    }

    private static OutputView outputView() {
        return new OutputView();
    }
}
