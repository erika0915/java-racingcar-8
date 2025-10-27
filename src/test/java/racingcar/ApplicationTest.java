package racingcar;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    @Test
    void 전진_정지_및_단독우승() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni", "1"); // pobi는 4(전진), woni는 3(정지)
                    assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
                },
                MOVING_FORWARD, STOP
        );
    }

    @Test
    void 공동_우승() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni,jun", "2");
                    assertThat(output()).contains(
                            "pobi : -", "woni : -", "jun : ", // 1회차 (pobi, woni 전진 / jun 정지)
                            "pobi : --", "woni : --", "jun : ", // 2회차 (pobi, woni 전진 / jun 정지)
                            "최종 우승자 : pobi, woni"
                    );
                },
                MOVING_FORWARD, MOVING_FORWARD, STOP,
                MOVING_FORWARD, MOVING_FORWARD, STOP
        );
    }

    @Test
    void 이름에_대한_예외_처리_5자초과() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> run("pobi,javaji", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("자동차 이름은 5자 이하여야 합니다.")
        );
    }

    @Test
    void 이름에_대한_예외_처리_공백() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> run("pobi,,woni", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("자동차 이름은 공백일 수 없습니다.")
        );
    }

    @Test
    void 시도_횟수에_대한_예외_처리_숫자아님() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> run("pobi,woni", "a"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("시도 횟수는 숫자여야 합니다.")
        );
    }

    @Test
    void 시도_횟수에_대한_예외_처리_1미만() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> run("pobi,woni", "0"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("시도 횟수는 1 이상이어야 합니다.")
        );
    }


    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
