package christmas.domain;

import static christmas.domain.MenuCount.COUNT_ERROR;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MenuCountTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 1000})
    @DisplayName("메뉴의 개수가 1이상의 숫자일 경우, 정상 동작한다.")
    void generateMenu(Integer count) {
        Assertions.assertThatCode(() -> {
            new MenuCount(count);
        }).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1000})
    @DisplayName("메뉴의 개수가 1이상의 숫자가 아닌 경우, 에러가 발생한다.")
    void checkInMenu(Integer count) {
        Assertions.assertThatThrownBy(() -> {
            new MenuCount(count);
        }).hasMessageContaining(COUNT_ERROR);
    }
}