package oncall.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private final String requestMessage;

    public InputView(String requestMessage) {
        this.requestMessage = requestMessage;
    }

    private void showRequestMessage() {
        System.out.println(requestMessage);
    }

    public String getInput() {
        showRequestMessage();
        return Console.readLine();
    }
}
