package racingcar.domain;

import camp.nextstep.edu.missionutils.Randoms;

public class Car {
    private static final int MOVE_THRESHOLD = 4;

    private final String name;
    private int position = 0;

    public Car(String name) {
        this.name = name;
    }

    public void tryMove(){
        if (shouldMove()){
            this.position++;
        }
    }

    public String getName() {
        return name;
    }
    public int getPosition() {
        return position;
    }

    private boolean shouldMove() {
        int randomNum = Randoms.pickNumberInRange(0,9);
        return randomNum >= MOVE_THRESHOLD;
    }
}
