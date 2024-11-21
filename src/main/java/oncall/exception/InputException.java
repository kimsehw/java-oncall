package oncall.exception;


public class InputException extends IllegalArgumentException {

    public static final String INVALID_INPUT = "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.";

    public InputException() {
        super(INVALID_INPUT);
    }
}