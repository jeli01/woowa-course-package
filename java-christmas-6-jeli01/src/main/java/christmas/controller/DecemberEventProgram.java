package christmas.controller;

import static christmas.domain.Event.getPossibleChampagneCount;
import static christmas.domain.Event.isPossibleBenefit;
import static christmas.domain.Event.obtainBadge;
import static christmas.domain.Event.obtainChristmasDDayDiscount;
import static christmas.domain.Event.obtainStarDiscount;
import static christmas.domain.Event.obtainTotalBenefitPrice;
import static christmas.domain.Event.obtainTotalDiscount;
import static christmas.domain.Event.obtainWeekKindDiscount;
import static christmas.domain.Gift.CHAMPAGNE;
import static christmas.view.Output.printProgramIntroduction;

import christmas.domain.Badge;
import christmas.domain.Day;
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

    private void printIntroduction() {
        printProgramIntroduction();
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

    private void printEventBenefitIntroduction() {
        Output.printEventBenefitIntroduction(order.getRawDay());
        System.out.println();
    }

    private void printOrderMenu() {
        Output.printOrderMenu(order.getRawOrderMenus());
        System.out.println();
    }

    private void printTotalOrderPrice() {
        Integer totalPrice = order.obtainTotalPrice();
        Output.printTotalOrderPrice(totalPrice);
        System.out.println();
    }

    private void printGiftMenu() {
        Integer count = getPossibleChampagneCount(order.obtainTotalPrice());
        Output.printGiftMenu(CHAMPAGNE.getName(), count);
        System.out.println();
    }

    private void printBenefitList() {
        Output.printBenefitTitle();
        if (!isPossibleBenefit(order)) {
            Output.printNon();
            System.out.println();
            return;
        }
        printBenefitPriorContents();
        System.out.println();
    }

    private void printBenefitPriorContents() {
        Output.printChristmasDDayDiscount(obtainChristmasDDayDiscount(order));
        Boolean isWeekend = order.isWeekendDay();
        Output.printWeekDiscount(isWeekend, obtainWeekKindDiscount(order));
        Output.printSpecialDiscount(obtainStarDiscount(order));
        Output.printGiftEvent(CHAMPAGNE.getPrice(), getPossibleChampagneCount(order.obtainTotalPrice()));
    }

    private void printTotalBenefit() {
        Output.printTotalBenefit(obtainTotalBenefitPrice(order));
        System.out.println();
    }

    private void printPayment() {
        Output.printPayment(order.obtainTotalPrice() - obtainTotalDiscount(order));
        System.out.println();
    }

    private void printBadge() {
        Badge badge = obtainBadge(order);
        Output.printBadge(badge);
    }
}
