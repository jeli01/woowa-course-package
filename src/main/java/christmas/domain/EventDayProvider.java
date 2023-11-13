package christmas.domain;

import java.util.ArrayList;
import java.util.List;

public class EventDayProvider {
    private static final Integer[] weekends = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30};

    public static List<Day> obtainWeekends() {
        List<Day> wrappingWeekends = new ArrayList<>();
        for (Integer weekend : weekends) {
            Day day = new Day(weekend);
            wrappingWeekends.add(day);
        }
        return wrappingWeekends;
    }
}
