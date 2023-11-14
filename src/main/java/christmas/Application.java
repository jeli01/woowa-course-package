package christmas;

import christmas.controller.DecemberEventProgram;

public class Application {
    public static void main(String[] args) {
        DecemberEventProgram program = new DecemberEventProgram();
        program.printIntroduction();
        program.read();
        program.printEventBenefitIntroduction();
        program.printOrderMenu();
        program.printTotalOrderPrice();
        program.printGiftMenu();
        program.printBenefitList();
        program.printTotalBenefit();
        program.printPayment();
        program.printBadge();
    }
}