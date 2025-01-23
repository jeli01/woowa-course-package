package christmas.domain;

public class MenuCount {
    private static final String RANGE_ERROR = "메뉴의 개수는 1이상 이어야 합니다.";

    private final Integer count;

    public MenuCount(Integer count) {
        validateCount(count);
        this.count = count;
    }

    public void validateCount(Integer count) {
        if (count <= 0) {
            throw new IllegalArgumentException(RANGE_ERROR);
        }
    }

    public Integer getRawCount() {
        return count;
    }
}