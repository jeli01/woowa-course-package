package lotto;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        Program program = new Program();
        program.purchaseLottos();
        program.insertWinningNumbers();
        program.insertBonusNumber();
        program.showResult();
    }
}
