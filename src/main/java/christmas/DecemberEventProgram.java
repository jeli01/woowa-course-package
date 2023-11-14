package christmas;

import christmas.domain.Day;
import christmas.domain.Order;
import christmas.view.Input;
import christmas.view.Output;

public class DecemberEventProgram {
    private Day visitDay;
    private Order order;

    public void printIntroduction() {
        Output.printIntroduction();
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
                order = new Order(visitDay, Input.readMenu());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }
}
