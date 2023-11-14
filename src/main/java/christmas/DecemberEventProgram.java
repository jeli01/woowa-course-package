package christmas;

import christmas.domain.Day;
import christmas.view.Input;
import christmas.view.Output;

public class DecemberEventProgram {
    private Day visitDay;

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
}
