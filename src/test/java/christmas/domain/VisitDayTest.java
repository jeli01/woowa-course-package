package christmas.domain;

import static christmas.domain.Day.DAY_RANGE_ERROR;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class VisitDayTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 31})
    @DisplayName("날짜는 1이상 31이하의 숫자인 경우, 정상 동작한다.")
    void generateDay(Integer day) {
        Assertions.assertThatCode(() -> {
            new Day(day);
        }).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 32})
    @DisplayName("날짜는 1이상 31이하의 숫자가 아닌 경우, 에러가 발생한다.")
    void validateDayRange(Integer day) {
        Assertions.assertThatThrownBy(() -> {
            new Day(day);
        }).hasMessageContaining(DAY_RANGE_ERROR);
    }
}