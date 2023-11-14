package christmas.view;

import christmas.domain.Day;
import christmas.domain.OrderMenus;

public class RetryInput {
    public static Day readVisitDay() {
        while (true) {
            try {
                return Input.readVisitDay();
            } catch (IllegalArgumentException e) {
                showErrorPlusPrefix(e);
            }
        }
    }

    public static OrderMenus readMenu() {
        while (true) {
            try {
                return Input.readMenu();
            } catch (IllegalArgumentException e) {
                showErrorPlusPrefix(e);
            }
        }
    }

    private static void showErrorPlusPrefix(IllegalArgumentException e) {
        System.out.println("[ERROR] " + e.getMessage());
    }
}
