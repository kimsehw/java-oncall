package oncall.model.domain.parser;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.stream.Stream;
import oncall.exception.ExceptionAnnounce;
import oncall.exception.InputException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class ScheduleInputParserTest {

    private final ScheduleInputParser scheduleInputParser = new ScheduleInputParser();

    @ParameterizedTest
    @MethodSource("generateScheduleInputException")
    void 스케줄_입력_예외(String input, String message) {
        assertThatThrownBy(() -> scheduleInputParser.parseScheduleInput(input))
                .isInstanceOf(InputException.class).hasMessage(message);
    }

    static Stream<Arguments> generateScheduleInputException() {
        return Stream.of(
                Arguments.of("12,준팍,도밥,고니,수아", InputException.INVALID_INPUT),
                Arguments.of("ㅇㅇ,준팍,도밥,고니,수아", InputException.INVALID_INPUT),
                Arguments.of("aa,준팍,도밥,고니,수아", InputException.INVALID_INPUT),
                Arguments.of("a루루,준팍,도밥,고니,수아", InputException.INVALID_INPUT),
                Arguments.of("일이삼사오육,준팍,도밥,고니,수아", InputException.INVALID_INPUT
                        + ExceptionAnnounce.NAME_LENGTH.getMessage()),
                Arguments.of("루루,,준팍,도밥,고니,수아", InputException.INVALID_INPUT
                        + ExceptionAnnounce.NAME_LENGTH.getMessage()),
                Arguments.of(",준팍,루루,도밥,고니,수아", InputException.INVALID_INPUT
                        + ExceptionAnnounce.NAME_LENGTH.getMessage()),
                Arguments.of("루루,도밥,고니,수아", InputException.INVALID_INPUT
                        + ExceptionAnnounce.MORE_EMPLOY.getMessage()),
                Arguments.of("루루,도밥,고니,수아,루루", InputException.INVALID_INPUT
                        + ExceptionAnnounce.DUPLICATE_NAME.getMessage()),
                Arguments.of("루루,도밥,고니,수아,준팍,"
                        + "루루,도밥,고니,수아,준팍,"
                        + "루루,도밥,고니,수아,준팍,"
                        + "루루,도밥,고니,수아,준팍,"
                        + "루루,도밥,고니,수아,준팍,"
                        + "루루,도밥,고니,수아,준팍,"
                        + "루루,도밥,고니,수아,준팍,"
                        + "루루,도밥,고니,수아,준팍", InputException.INVALID_INPUT
                        + ExceptionAnnounce.TOO_MUCH_WORKER.getMessage())
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"준팍,도밥,고니,수아,루루,글로,솔로스타,우코,슬링키,참새,도리"})
    void 순번_정상_입력(String input) {
        assertThatCode(() -> scheduleInputParser.parseScheduleInput(input))
                .doesNotThrowAnyException();
    }
}