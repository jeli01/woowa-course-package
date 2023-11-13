package christmas.domain;

public class MenuCount {
    public static final String COUNT_ERROR = "메뉴의 개수는 1이상 이어야 합니다.";

    private final Integer value;

    public MenuCount(Integer value) {
        validateCount(value);
        this.value = value;
    }

    public void validateCount(Integer value) {
        if (value <= 0) {
            throw new IllegalArgumentException(COUNT_ERROR);
        }
    }

    public Integer obtainSumWithCount(Integer input) {
        return input + value;
    }

    public Integer obtainPriceMultiplyCount(Integer price) {
        return price * value;
    }
}