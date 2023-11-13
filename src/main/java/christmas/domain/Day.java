package christmas.domain;

public class Day {
    public static final String DAY_RANGE_ERROR = "날짜는 1이상 31이하의 정수여야 합니다.";
    private static final Integer LOWER_BOUND_DAY = 1;
    private static final Integer UPPER_BOUND_DAY = 31;

    private final Integer day;

    public Day(Integer day) {
        validateDay(day);
        this.day = day;
    }

    private void validateDay(Integer day) {
        if (!(LOWER_BOUND_DAY <= day && day <= UPPER_BOUND_DAY)) {
            throw new IllegalArgumentException(DAY_RANGE_ERROR);
        }
    }

    public boolean isBiggerThan(Day compare) {
        if (day > compare.day) {
            return true;
        }
        return false;
    }

    public Integer obtainDifferenceDay(Day compare) {
        return Math.abs(day - compare.day);
    }
}