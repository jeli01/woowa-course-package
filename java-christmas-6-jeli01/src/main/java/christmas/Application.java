package christmas;

import christmas.controller.DecemberEventProgram;

public class Application {
    public static void main(String[] args) {
        DecemberEventProgram program = new DecemberEventProgram();
        program.read();
        program.write();
    }
}