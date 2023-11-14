package christmas.domain;

import static christmas.domain.Gift.CHAMPAGNE;

public class Event {
    private static final Integer CHRISTMAS_D_DAY_FIRST = 1;
    private static final Integer CHRISTMAS_D_DAY_LAST = 25;
    private static final Integer INITIAL_D_DAY_DISCOUNT = 1_000;
    private static final Integer WEEK_KIND_DISCOUNT_UNIT = 2_023;
    private static final Integer STAR_DAY_DISCOUNT = 1_000;

    public static Integer obtainChristmasDDayDiscount(Order order) {
        Integer totalOrderPrice = order.obtainTotalPrice();
        if (!EventApplier.isPossibleDiscountEvent(totalOrderPrice)) {
            return 0;
        }

        final Integer discount = INITIAL_D_DAY_DISCOUNT;
        Integer extraDiscount = 0;
        if (order.isBiggerDay(new Day(CHRISTMAS_D_DAY_LAST))) {
            return 0;
        }
        extraDiscount += 100 * order.obtainDayDifference(new Day(CHRISTMAS_D_DAY_FIRST));
        return discount + extraDiscount;
    }

    public static Integer obtainWeekKindDiscount(Order order) {
        Integer totalOrderPrice = order.obtainTotalPrice();
        if (!EventApplier.isPossibleDiscountEvent(totalOrderPrice)) {
            return 0;
        }

        Integer discount = 0;
        if (order.isWeekendDay()) {
            Integer count = order.obtainMenuCategoryCount(MenuCategory.MAIN);
            discount += WEEK_KIND_DISCOUNT_UNIT * count;
            return discount;
        }
        Integer count = order.obtainMenuCategoryCount(MenuCategory.DESSERT);
        discount += WEEK_KIND_DISCOUNT_UNIT * count;
        return discount;
    }

    public static Integer obtainStarDiscount(Order order) {
        Integer totalOrderPrice = order.obtainTotalPrice();
        if (!EventApplier.isPossibleDiscountEvent(totalOrderPrice)) {
            return 0;
        }

        if (order.isStarDay()) {
            return STAR_DAY_DISCOUNT;
        }
        return 0;
    }

    public static boolean isPossibleGetChampagne(Integer totalOrderPrice) {
        if (totalOrderPrice >= CHAMPAGNE.getOrderPriceToGet()) {
            return true;
        }
        return false;
    }

    public static Badge obtainBadge(Order order) {
        Integer totalOrderPrice = order.obtainTotalPrice();
        if (!EventApplier.isPossibleDiscountEvent(totalOrderPrice)) {
            return null;
        }

        Integer totalBenefitPrice = obtainTotalBenefitPrice(order);

        return Badge.obtainBadge(totalBenefitPrice);
    }

    public static Integer obtainTotalBenefitPrice(Order order) {
        Integer totalBenefitPrice = 0;
        totalBenefitPrice += obtainChristmasDDayDiscount(order);
        totalBenefitPrice += obtainWeekKindDiscount(order);
        totalBenefitPrice += obtainStarDiscount(order);
        if (Event.isPossibleGetChampagne(order.obtainTotalPrice())) {
            totalBenefitPrice += CHAMPAGNE.getPrice();
        }
        return totalBenefitPrice;
    }

    public static Integer obtainTotalDiscount(Order order) {
        Integer totalOrderPrice = order.obtainTotalPrice();
        Integer ChristmasDDayDiscount = obtainChristmasDDayDiscount(order);
        Integer weekKindDiscount = obtainWeekKindDiscount(order);
        Integer starDiscount = obtainStarDiscount(order);
        Integer totalDiscount = ChristmasDDayDiscount + weekKindDiscount + starDiscount;
        return Math.min(totalOrderPrice, totalDiscount);
    }
}
