package christmas.domain;

public class EventApplier {
    private static final Integer APPLY_MIN_PRICE = 10_000;

    public static boolean isPossibleDiscountEvent(Integer totalOrderPrice) {
        if (totalOrderPrice < APPLY_MIN_PRICE) {
            return false;
        }
        return true;
    }
}
