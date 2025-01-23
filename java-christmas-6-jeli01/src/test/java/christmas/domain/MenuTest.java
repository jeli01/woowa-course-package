package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MenuTest {
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프", "타파스"})
    @DisplayName("메뉴판에 있으면, 정상 동작한다.")
    void generateMenu(String menu) {
        assertThatCode(() -> {
            Menu.translate(menu);
        }).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"없는 메뉴", ""})
    @DisplayName("메뉴판에 없는 메뉴인 경우, 에러가 발생한다.")
    void checkInMenu(String menu) {
        assertThatThrownBy(() -> {
            Menu.translate(menu);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("해당 문자열은 메뉴의 종류가 아닙니다.");
    }
}
