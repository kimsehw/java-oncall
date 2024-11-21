package oncall.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.stream.Stream;
import oncall.exception.InputException;
import oncall.model.domain.parser.MonthAndDayParser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class MonthAndDayInputParserTest {

    @ParameterizedTest
    @MethodSource("generateMonthDayException")
    void 월_요일_입력_예외(String input) {
        MonthAndDayParser monthAndDayParser = new MonthAndDayParser();
        assertThatThrownBy(() -> monthAndDayParser.parseMonthDayInput(input))
                .isInstanceOf(InputException.class);
    }

    static Stream<Arguments> generateMonthDayException() {
        return Stream.of(
                Arguments.of("1 월"),
                Arguments.of("13,월"),
                Arguments.of("-1,월"),
                Arguments.of("12,워"),
                Arguments.of("12;월"),
                Arguments.of("12,,월")
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"12,토", "5,월"})
    void 월_요일_정상_입력(String input) {
        MonthAndDayParser monthAndDayParser = new MonthAndDayParser();
        assertThatCode(() -> monthAndDayParser.parseMonthDayInput(input))
                .doesNotThrowAnyException();
    }
}