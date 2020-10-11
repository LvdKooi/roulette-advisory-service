package nl.kooi.app.domain.game;

import nl.kooi.app.domain.outcome.Outcome;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;


class OddEvenGameTest {
    private OddEvenGame oddEvenGame;

    @BeforeEach
    public void initTestDependencies() {
        oddEvenGame = new OddEvenGame(BigDecimal.TEN);
    }

    @Test
    public void oddEvenGameChipValueTest() {
        assertThat(oddEvenGame.getChipValue()).isEqualTo(BigDecimal.TEN.setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void hitArrayOddTest() {
        var outcome = new Outcome(1, 1, BigDecimal.ZERO);
        oddEvenGame.setHits(outcome);

        assertThat(oddEvenGame.getHitArray()).isEqualTo(new boolean[]{true, false});
    }

    @Test
    public void hitArrayEvenTest() {
        var outcome = new Outcome(1, 2, BigDecimal.ZERO);
        oddEvenGame.setHits(outcome);

        assertThat(oddEvenGame.getHitArray()).isEqualTo(new boolean[]{false, true});
    }

    @Test
    public void hitArrayZeroTest() {
        var outcome = new Outcome(1, 0, BigDecimal.ZERO);
        oddEvenGame.setHits(outcome);

        assertThat(oddEvenGame.getHitArray()).isEqualTo(new boolean[]{false, false});
    }
}
