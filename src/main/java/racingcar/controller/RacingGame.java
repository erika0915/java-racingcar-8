package racingcar.controller;

import racingcar.domain.Car;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RacingGame {

    public void run() {
        List<Car> cars = setupCars();
        int trialCount = getTrialCount();
        runRaces(cars, trialCount);
        determineAndPrintWinners(cars);
    }

    private List<Car> setupCars() {
        String[] carNames = InputView.getCarNames();
        validateCarNames(carNames);
        return createCarsFromNames(carNames);
    }

    private List<Car> createCarsFromNames(String[] carNames) {
        List<Car> cars = new ArrayList<>();
        for (String name : carNames) {
            cars.add(new Car(name.trim())); // 이름 공백 제거
        }
        return cars;
    }

    private void validateCarNames(String[] carNames) {
        for (String name : carNames) {
            validateCarNameLength(name.trim());
            validateCarNameNotBlank(name.trim());
        }
    }

    private void validateCarNameLength(String name) {
        if (name.length() > 5) {
            throw new IllegalArgumentException("자동차 이름은 5자 이하여야 합니다.");
        }
    }

    private void validateCarNameNotBlank(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("자동차 이름은 공백일 수 없습니다.");
        }
    }

    private int getTrialCount() {
        return InputView.getTrialCount();
    }

    private void runRaces(List<Car> cars, int trialCount) {
        OutputView.printRaceStartHeader();
        for (int i = 0; i < trialCount; i++) {
            runSingleTrial(cars);
            OutputView.printTrialResult(cars);
        }
    }

    private void runSingleTrial(List<Car> cars) {
        for (Car car : cars) {
            car.tryMove();
        }
    }

    private void determineAndPrintWinners(List<Car> cars) {
        int maxPosition = findMaxPosition(cars);
        List<String> winners = findWinners(cars, maxPosition);
        OutputView.printWinners(winners);
    }

    private int findMaxPosition(List<Car> cars) {
        int maxPosition = 0;
        for (Car car : cars) {
            maxPosition = Math.max(maxPosition, car.getPosition());
        }
        return maxPosition;
    }

    private List<String> findWinners(List<Car> cars, int maxPosition) {
        return cars.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .map(Car::getName)
                .collect(Collectors.toList());
    }
}
