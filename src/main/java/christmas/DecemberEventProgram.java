package christmas;

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
    }

}
