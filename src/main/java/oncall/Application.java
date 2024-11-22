package oncall;

import oncall.config.Config;
import oncall.controller.Controller;

public class Application {
    public static void main(String[] args) {
        Controller controller = Config.createController();
        controller.run();
    }
}
