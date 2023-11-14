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

    public DecemberEventProgram() {
        this.order = null;
    }

    public void read() {
        printIntroduction();
        Day day = RetryInput.readVisitDay();
        OrderMenus orderMenus = RetryInput.readMenu();
        order = new Order(day, orderMenus);
    }

    public void write() {
        validateWrite();

        printEventBenefitIntroduction();
        printOrderMenu();
        printTotalOrderPrice();
        printGiftMenu();
        printBenefitList();
        printTotalBenefit();
        printPayment();
        printBadge();
    }

    private void validateWrite() {
        if (order == null) {
            throw new IllegalStateException("read 함수를 먼저 실행하셔야 합니다.");
        }
    }

    private void printIntroduction() {
        Output.printProgramIntroduction();
    }

    private void printEventBenefitIntroduction() {
        Output.printEventBenefitIntroduction();
        System.out.println();
    }

    private void printOrderMenu() {
        Output.printOrderMenu(order.getOrderMenus().getOrderMenus());
        System.out.println();
    }

    private void printTotalOrderPrice() {
        Integer totalPrice = order.obtainTotalPrice();
        Output.printTotalOrderPrice(totalPrice);
        System.out.println();
    }

    private void printGiftMenu() {
        Boolean isPossible = Event.isPossibleGetChampagne(order.obtainTotalPrice());
        Output.printGiftMenu(isPossible);
        System.out.println();
    }

    private void printBenefitList() {
        Output.printBenefitTitle();
        if (Event.isPossibleBenefit(order)) {
            Output.printNon();
            System.out.println();
            return;
        }
        printBenefitPriorContents();
        System.out.println();
    }

    private void printBenefitPriorContents() {
        Output.printChristmasDDayDiscount(Event.obtainChristmasDDayDiscount(order));
        Boolean isWeekend = order.isWeekendDay();
        Output.printWeekDiscount(isWeekend, Event.obtainWeekKindDiscount(order));
        Output.printSpecialDiscount(Event.obtainStarDiscount(order));
        Output.printGiftEvent(Event.isPossibleGetChampagne(order.obtainTotalPrice()));
    }

    private void printTotalBenefit() {
        Output.printTotalBenefit(Event.obtainTotalBenefitPrice(order));
        System.out.println();
    }

    private void printPayment() {
        Output.printPayment(order.obtainTotalPrice() - Event.obtainTotalDiscount(order));
        System.out.println();
    }

    private void printBadge() {
        Badge badge = Badge.obtainBadge(Event.obtainTotalBenefitPrice(order));
        Output.printBadge(badge);
    }
}
