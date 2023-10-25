package baseball;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현

        Computer computer = new Computer();
        User user = new User();
        System.out.println("숫자 야구 게임을 시작합니다.");

        while (true) {
            System.out.print("숫자를 입력해주세요 : ");
            String userNum = readLine();
            user.updateUserNum(userNum);

            computer.printResult(userNum);
            if (computer.isWin(userNum)) {
                System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
                String s = readLine();
                if (s.equals("1")) {
                    computer.updateGoalNum();
                } else {
                    return;
                }
            }
        }
    }
}