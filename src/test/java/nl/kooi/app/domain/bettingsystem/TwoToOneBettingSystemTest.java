package nl.kooi.app.domain.bettingsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static nl.kooi.app.domain.rouletteoutcome.RouletteOutcome.*;
import static org.assertj.core.api.Assertions.assertThat;


public class TwoToOneBettingSystemTest {

    private TwoToOneBettingSystem bettingSystem;

    @BeforeEach
    public void initTestDependencies() {
        bettingSystem = new TwoToOneBettingSystem(3, 4);
    }

    @Test
    public void firstAdviceTest() {
        var hitArray = new boolean[]{true, false, false};

        setHitsMultipleTimes(hitArray, 3);

        bettingSystem.setHits(hitArray);

        assertAdviceAndProfit(0, 1, 1, 0);
    }

    @Test
    public void firstAdviceThenLoseTest() {
        var hitArray = new boolean[]{false, true, false};

        setHitsMultipleTimes(hitArray, 4);

        assertAdviceAndProfit(1, 0, 1, 0);

        bettingSystem.setHits(hitArray);

        assertAdviceAndProfit(3, 0, 3, -2);

    }

    @Test
    public void firstAdviceThenLoseTwoTimesZeroTestThenWin() {
        var hitArray = new boolean[]{true, false, false};

        setHitsMultipleTimes(hitArray, 4);

        assertAdviceAndProfit(0, 1, 1, 0);

        hitArray = new boolean[]{false, false, false};

        bettingSystem.setHits(hitArray);

        assertAdviceAndProfit(0, 3, 3, -2);

        bettingSystem.setHits(hitArray);

        assertAdviceAndProfit(0, 9, 9, -8);

        hitArray = new boolean[]{false, true, false};
        bettingSystem.setHits(hitArray);

        assertAdviceAndProfit(0, 1, 1, 1);
    }


    @Test
    public void firstAdviceThenWinAndAnotherChanceTest() {
        var hitArray = new boolean[]{false, false, true};

        setHitsMultipleTimes(hitArray, 4);

        assertAdviceAndProfit(1, 1, 0, 0);

        hitArray = new boolean[]{false, true, false};

        bettingSystem.setHits(hitArray);

        assertAdviceAndProfit(1, 1, 0, 1);
    }

    @Test
    public void firstAdviceThreeOutOfFour() {
        var hitArray = new boolean[]{false, true, false};

        setHitsMultipleTimes(hitArray, 3);

        hitArray = new boolean[]{true, false, false};

        bettingSystem.setHits(hitArray);

        assertAdviceAndProfit(1, 0, 1, 0);
    }

    @Test
    public void noAdviceTwoOutOfFour() {
        var hitArray = new boolean[]{true, false, false};

        setHitsMultipleTimes(hitArray, 2);

        hitArray = new boolean[]{false, true, false};

        setHitsMultipleTimes(hitArray, 2);

        assertAdviceAndProfit(0, 0, 0, 0);
    }

    private void setHitsMultipleTimes(boolean[] hitArray, int times) {
        for (int i = 0; i < times; i++) {
            bettingSystem.setHits(hitArray);
        }
    }

    private void assertAdviceAndProfit(int advice1, int advice2, int advice3, int profit) {

        var adviceMap = bettingSystem.getTwoToOneAdvice(FIRST_COLUMN, SECOND_COLUMN, THIRD_COLUMN, BigDecimal.ONE).getAdviceMap();

        assertThat(bettingSystem.getAdviceArray()).isEqualTo(new int[]{advice1, advice2, advice3});
        assertThat(bettingSystem.getProfitCounter()).isEqualTo(profit);
        assertThat(adviceMap.get(FIRST_COLUMN)).isEqualTo(BigDecimal.valueOf(advice1));
        assertThat(adviceMap.get(SECOND_COLUMN)).isEqualTo(BigDecimal.valueOf(advice2));
        assertThat(adviceMap.get(THIRD_COLUMN)).isEqualTo(BigDecimal.valueOf(advice3));
    }

}
