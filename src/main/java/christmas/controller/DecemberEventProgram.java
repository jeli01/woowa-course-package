package christmas.controller;

import christmas.domain.Badge;
import christmas.domain.Day;
import christmas.domain.Event;
import christmas.domain.Order;
import christmas.domain.OrderMenus;
import christmas.view.Output;
import christmas.view.RetryInput;

public class DecemberEventProgram {
    private Order order;

    public void read() {
        Day day = RetryInput.readVisitDay();
        OrderMenus orderMenus = RetryInput.readMenu();
        order = new Order(day, orderMenus);
    }

    public void printIntroduction() {
        Output.printProgramIntroduction();
    }

    public void printEventBenefitIntroduction() {
        Output.printEventBenefitIntroduction();
        System.out.println();
    }

    public void printOrderMenu() {
        Output.printOrderMenu(order.getOrderMenus().getOrderMenus());
        System.out.println();
    }

    public void printTotalOrderPrice() {
        Integer totalPrice = order.obtainTotalPrice();
        Output.printTotalOrderPrice(totalPrice);
        System.out.println();
    }

    public void printGiftMenu() {
        Boolean isPossible = Event.isPossibleGetChampagne(order.obtainTotalPrice());
        Output.printGiftMenu(isPossible);
        System.out.println();
    }

    public void printBenefitList() {
        Output.printBenefitTitle();
        if (Event.isPossibleBenefit(order)) {
            Output.printNon();
            System.out.println();
            return;
        }
        Output.printChristmasDDayDiscount(Event.obtainChristmasDDayDiscount(order));
        Output.printWeekDiscount(Event.obtainWeekKindDiscount(order));
        Output.printSpecialDiscount(Event.obtainStarDiscount(order));
        Output.printGiftEvent(Event.isPossibleGetChampagne(order.obtainTotalPrice()));
        System.out.println();
    }

    public void printTotalBenefit() {
        Output.printTotalBenefit(Event.obtainTotalBenefitPrice(order));
        System.out.println();
    }

    public void printPayment() {
        Output.printPayment(order.obtainTotalPrice() - Event.obtainTotalDiscount(order));
        System.out.println();
    }

    public void printBadge() {
        Badge badge = Badge.obtainBadge(Event.obtainTotalBenefitPrice(order));
        Output.printBadge(badge);
    }
}
