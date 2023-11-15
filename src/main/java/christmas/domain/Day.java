package christmas.domain;

import java.util.List;

public class Day {
    private static final String RANGE_ERROR = "날짜는 1이상 31이하의 정수여야 합니다.";
    private static final Integer LOWER_BOUND = 1;
    private static final Integer UPPER_BOUND = 31;

    private final Integer day;

    public Day(Integer day) {
        validateRange(day);
        this.day = day;
    }

    private void validateRange(Integer day) {
        if (!(LOWER_BOUND <= day && day <= UPPER_BOUND)) {
            throw new IllegalArgumentException(RANGE_ERROR);
        }
    }

    public boolean isBigger(Day compare) {
        if (day > compare.day) {
            return true;
        }
        return false;
    }

    public Integer obtainDifference(Day compare) {
        return Math.abs(day - compare.day);
    }

    public boolean isWeekend() {
        List<Day> weekends = EventDayProvider.obtainWeekends();
        if (weekends.contains(new Day(day))) {
            return true;
        }
        return false;
    }

    public boolean isStar() {
        List<Day> starDays = EventDayProvider.obtainStarDays();
        if (starDays.contains(new Day(day))) {
            return true;
        }
        return false;
    }

    public Integer getDay() {
        return day;
    }

    @Override
    public boolean equals(Object object) {
        Day product = (Day) object;
        if (product.day == this.day) {
            return true;
        }
        return false;
    }
}