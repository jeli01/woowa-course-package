package christmas.domain;

public class Event {
    private static final Integer CHRISTMAS_D_DAY_FIRST = 1;
    private static final Integer CHRISTMAS_D_DAY_LAST = 25;
    private static final Integer INITIAL_D_DAY_DISCOUNT = 1_000;
    private static final Integer WEEK_KIND_DISCOUNT_UNIT = 2_023;
    private static final Integer STAR_DAY_DISCOUNT = 1_000;
    private static final Integer GET_CHAMPAGNE_PRICE = 120_000;

    public static Integer obtainChristmasDDayDiscount(Order order) {
        Integer totalOrderPrice = order.obtainTotalPrice();
        if (!EventApplyer.isPossibleEvent(totalOrderPrice)) {
            return 0;
        }

        final Integer discount = INITIAL_D_DAY_DISCOUNT;
        Integer extraDiscount = 0;
        if (order.isDayBiggerThan(new Day(CHRISTMAS_D_DAY_LAST))) {
            return 0;
        }
        extraDiscount += 100 * order.obtainDifferenceDay(new Day(CHRISTMAS_D_DAY_FIRST));
        return discount + extraDiscount;
    }

    public static Integer obtainWeekKindDiscount(Order order) {
        Integer totalOrderPrice = order.obtainTotalPrice();
        if (!EventApplyer.isPossibleEvent(totalOrderPrice)) {
            return 0;
        }

        Integer discount = 0;
        if (order.isWeekendDay()) {
            final Integer count = order.obtainMenuCategoryCount(MenuCategory.MAIN);
            discount += WEEK_KIND_DISCOUNT_UNIT * count;
            return discount;
        }
        final Integer count = order.obtainMenuCategoryCount(MenuCategory.DESSERT);
        discount += WEEK_KIND_DISCOUNT_UNIT * count;
        return discount;
    }

    public static Integer obtainStarDiscount(Order order) {
        Integer totalOrderPrice = order.obtainTotalPrice();
        if (!EventApplyer.isPossibleEvent(totalOrderPrice)) {
            return 0;
        }

        if (order.isStarDay()) {
            return STAR_DAY_DISCOUNT;
        }
        return 0;
    }

    public static boolean isPossibleGetChampagne(Integer totalOrderPrice) {
        if (totalOrderPrice >= GET_CHAMPAGNE_PRICE) {
            return true;
        }
        return false;
    }

    public static Badge obtainBadge(Order order) {
        Integer totalOrderPrice = order.obtainTotalPrice();
        if (!EventApplyer.isPossibleEvent(totalOrderPrice)) {
            return null;
        }

        final Integer totalBenefitPrice = obtainTotalBenefitPrice(order);

        return Badge.obtainBadgeByPrice(totalBenefitPrice);
    }

    public static Integer obtainTotalBenefitPrice(Order order) {
        Integer totalBenefitPrice = 0;
        totalBenefitPrice += obtainChristmasDDayDiscount(order);
        totalBenefitPrice += obtainWeekKindDiscount(order);
        totalBenefitPrice += obtainStarDiscount(order);
        return totalBenefitPrice;
    }

    public static Integer obtainTotalDiscount(Order order) {
        final Integer totalOrderPrice = order.obtainTotalPrice();
        final Integer ChristmasDDayDiscount = obtainChristmasDDayDiscount(order);
        final Integer weekKindDiscount = obtainWeekKindDiscount(order);
        final Integer starDiscount = obtainStarDiscount(order);
        final Integer totalDiscount = ChristmasDDayDiscount + weekKindDiscount + starDiscount;
        return Math.min(totalOrderPrice, totalDiscount);
    }
}
