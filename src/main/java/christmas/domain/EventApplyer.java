package christmas.domain;

public class EventApplyer {
    private static final Integer EVENT_APPLY_MIN_PRICE = 10_000;

    public static boolean isPossibleEvent(Integer totalOrderPrice) {
        if (totalOrderPrice < EVENT_APPLY_MIN_PRICE) {
            return false;
        }
        return true;
    }
}
