package racingcar.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public static String[] getCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String input = Console.readLine();
        return input.split(",");
    }

    public static int getTrialCount() {
        System.out.println("시도할 횟수는 몇 회인가요?");
        String input = Console.readLine();
        return parseAndValidateTrialCount(input);
    }

    private static int parseAndValidateTrialCount(String input) {
        try {
            int count = Integer.parseInt(input);
            validatePositive(count);
            return count;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("시도 횟수는 숫자여야 합니다.");
        }
    }

    private static void validatePositive(int count) {
        if (count < 1) {
            throw new IllegalArgumentException("시도 횟수는 1 이상이어야 합니다.");
        }
    }
}
