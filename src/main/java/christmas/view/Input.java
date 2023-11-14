package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Day;

public class Input {
    public static Day readVisitDay() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String line = Console.readLine();
        try {
            Integer input = Integer.parseInt(line);
            Day day = new Day(input);
            return day;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }
}
