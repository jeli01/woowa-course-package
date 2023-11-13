package christmas.domain;

import static christmas.domain.Menu.NOT_EXISTS_ERROR;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MenuTest {
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프", "타파스"})
    @DisplayName("메뉴판에 있으면, 정상 동작한다.")
    void generateMenu(String menu) {
        Assertions.assertThatCode(() -> {
            Menu.translateStringToMenu(menu);
        }).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"없는 메뉴", ""})
    @DisplayName("메뉴판에 없는 메뉴인 경우, 에러가 발생한다.")
    void checkInMenu(String menu) {
        Assertions.assertThatThrownBy(() -> {
            Menu.translateStringToMenu(menu);
        }).hasMessageContaining(NOT_EXISTS_ERROR);
    }
}
