package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class VisitDayTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 31})
    @DisplayName("날짜는 1이상 31이하의 숫자인 경우, 정상 동작한다.")
    void generateDay(Integer day) {
        assertThatCode(() -> {
            new Day(day);
        }).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 32})
    @DisplayName("날짜는 1이상 31이하의 숫자가 아닌 경우, 에러가 발생한다.")
    void validateDayRange(Integer day) {
        assertThatThrownBy(() -> {
            new Day(day);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("날짜는 1이상 31이하의 정수여야 합니다.");
    }
}