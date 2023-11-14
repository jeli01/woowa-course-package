package christmas;

import christmas.domain.Badge;
import christmas.domain.Day;
import christmas.domain.Event;
import christmas.domain.Order;
import christmas.domain.OrderMenus;
import christmas.view.Input;
import christmas.view.Output;

public class DecemberEventProgram {
    private Day visitDay;
    private OrderMenus orderMenus;
    private Order order;

    public void printIntroduction() {
        Output.printProgramIntroduction();
    }

    public void readVisitDay() {
        while (true) {
            try {
                visitDay = Input.readVisitDay();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }

    public void readMenu() {
        while (true) {
            try {
                orderMenus = Input.readMenu();
                order = new Order(visitDay, orderMenus);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }

    public void printEventBenefitIntroduction() {
        Output.printEventBenefitIntroduction();
    }

    public void printOrderMenu() {
        Output.printOrderMenu(orderMenus.getOrderMenus());
    }

    public void printTotalOrderPrice() {
        Integer totalPrice = order.obtainTotalPrice();
        Output.printTotalOrderPrice(totalPrice);
    }

    public void printGiftMenu() {
        Boolean isPossible = Event.isPossibleGetChampagne(order.obtainTotalPrice());
        Output.printGiftMenu(isPossible);
        System.out.println();
    }

    public void printBenefitList() {
        System.out.println("<혜택 내역>");
        if (Event.obtainTotalDiscount(order) == 0 && !Event.isPossibleGetChampagne(order.obtainTotalPrice())) {
            System.out.println("없음");
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
