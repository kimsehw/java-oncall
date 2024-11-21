package oncall.model.domain.parser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import oncall.constant.ConstantBox;
import oncall.exception.ExceptionAnnounce;
import oncall.exception.InputException;

public class ScheduleInputParser {

    private static final String INPUT_REGEX = "[^가-힣,]";
    public static final int MIN_EMPLOY = 5;
    public static final int MAX_WORKER = 35;

    public List<String> parseScheduleInput(String input) {
        input = input.trim();
        List<String> scheduleOrder = validateFormat(input);
        validateWorkerNumber(scheduleOrder);
        validateDuplication(scheduleOrder);
        return scheduleOrder;
    }

    private static void validateDuplication(List<String> scheduleOrder) {
        Set<String> scheduleNamePool = new HashSet<>(scheduleOrder);
        if (scheduleNamePool.size() != scheduleOrder.size()) {
            throw new InputException(ExceptionAnnounce.DUPLICATE_NAME);
        }
    }

    private static void validateWorkerNumber(List<String> scheduleOrder) {
        if (scheduleOrder.size() < MIN_EMPLOY) {
            throw new InputException(ExceptionAnnounce.MORE_EMPLOY);
        }

        if (scheduleOrder.size() > MAX_WORKER) {
            throw new InputException(ExceptionAnnounce.TOO_MUCH_WORKER);
        }
    }

    private static List<String> validateFormat(String input) {
        Pattern pattern = Pattern.compile(INPUT_REGEX);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            throw new InputException();
        }
        return Arrays.stream(input.split(ConstantBox.SEPARATOR))
                .map(ScheduleInputParser::validateName).toList();
    }

    private static String validateName(String name) {
        if (isValidName(name)) {
            throw new InputException(ExceptionAnnounce.NAME_LENGTH);
        }
        return name;
    }

    private static boolean isValidName(String name) {
        return name.isEmpty() || name.length() > 5;
    }

}
