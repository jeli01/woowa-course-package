package christmas.domain;

public class Discount {
    private static final Integer CHRISTMAS_D_DAY_FIRST = 1;
    private static final Integer CHRISTMAS_D_DAY_LAST = 25;
    private static final Integer INITIAL_D_DAY_DISCOUNT = 1000;

    public static Integer obtainChristmasDDayDiscount(Order order) {
        final Integer discount = INITIAL_D_DAY_DISCOUNT;
        Integer extraDiscount = 0;
        if (order.isDayBiggerThan(new Day(CHRISTMAS_D_DAY_LAST))) {
            return 0;
        }

        extraDiscount += 100 * order.obtainDifferenceDay(new Day(CHRISTMAS_D_DAY_FIRST));
        return discount + extraDiscount;
    }
}
