package nl.kooi.app.domain.bettingsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static nl.kooi.app.domain.rouletteoutcome.RouletteOutcome.BLACK;
import static nl.kooi.app.domain.rouletteoutcome.RouletteOutcome.RED;
import static org.assertj.core.api.Assertions.assertThat;


class OneToOneBettingSystemTest {

    private OneToOneBettingSystem bettingSystem;

    @BeforeEach
    public void initTestDependencies() {
        bettingSystem = new OneToOneBettingSystem(2, 4);
    }

    @Test
    public void firstAdviceTest() {
        var hitArray = new boolean[]{true, false};

        setHitsMultipleTimes(hitArray, 3);

        bettingSystem.setHits(hitArray);

        assertAdviceAndProfit(0, 1, 0);
    }

    @Test
    public void firstAdviceThenLoseTest() {
        var hitArray = new boolean[]{true, false};

        setHitsMultipleTimes(hitArray, 4);

        assertAdviceAndProfit(0, 1, 0);

        bettingSystem.setHits(hitArray);

        assertAdviceAndProfit(0, 2, -1);

    }

    @Test
    public void firstAdviceThenLoseTwoTimesZeroTestThenWin() {
        var hitArray = new boolean[]{true, false};

        setHitsMultipleTimes(hitArray, 4);

        assertAdviceAndProfit(0, 1, 0);

        hitArray = new boolean[]{false, false};

        bettingSystem.setHits(hitArray);

        assertAdviceAndProfit(0, 2, -1);

        bettingSystem.setHits(hitArray);

        assertAdviceAndProfit(0, 4, -3);

        hitArray = new boolean[]{false, true};
        bettingSystem.setHits(hitArray);

        assertAdviceAndProfit(0, 1, 1);
    }


    @Test
    public void firstAdviceThenWinAndAnotherChanceTest() {
        var hitArray = new boolean[]{true, false};

        setHitsMultipleTimes(hitArray, 4);

        assertAdviceAndProfit(0, 1, 0);

        hitArray = new boolean[]{false, true};

        bettingSystem.setHits(hitArray);

        assertAdviceAndProfit(0, 1, 1);
    }

    @Test
    public void firstAdviceThreeOutOfFour() {
        var hitArray = new boolean[]{false, true};

        setHitsMultipleTimes(hitArray, 3);

        hitArray = new boolean[]{true, false};

        bettingSystem.setHits(hitArray);

        assertAdviceAndProfit(1, 0, 0);
    }

    @Test
    public void noAdviceTwoOutOfFour() {
        var hitArray = new boolean[]{true, false};

        setHitsMultipleTimes(hitArray, 2);

        hitArray = new boolean[]{false, true};

        setHitsMultipleTimes(hitArray, 2);

        assertAdviceAndProfit(0, 0, 0);
    }

    private void setHitsMultipleTimes(boolean[] hitArray, int times) {
        for (int i = 0; i < times; i++) {
            bettingSystem.setHits(hitArray);
        }
    }

    private void assertAdviceAndProfit(int advice1, int advice2, int profit) {

        var adviceMap = bettingSystem.getOneToOneAdvice(RED, BLACK, BigDecimal.ONE).getAdviceMap();

        assertThat(bettingSystem.getAdviceArray()).isEqualTo(new int[]{advice1, advice2});
        assertThat(bettingSystem.getProfitCounter()).isEqualTo(profit);
        assertThat(adviceMap.get(RED)).isEqualTo(BigDecimal.valueOf(advice1));
        assertThat(adviceMap.get(BLACK)).isEqualTo(BigDecimal.valueOf(advice2));
    }

}
