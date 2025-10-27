package racingcar.view;

import racingcar.domain.Car;

import java.util.List;

public class OutputView {
    public static void printRaceStartHeader() {
        System.out.println("\n실행 결과");
    }

    public static void printTrialResult(List<Car> cars) {
        for (Car car : cars) {
            String status = "-".repeat(car.getPosition());
            System.out.println(car.getName() + " : " + status);
        }
        System.out.println(); // 각 턴 사이에 빈 줄 삽입
    }

    public static void printWinners(List<String> winners) {
        String winnerString = String.join(", ", winners);
        System.out.println("최종 우승자 : " + winnerString);
    }
}
