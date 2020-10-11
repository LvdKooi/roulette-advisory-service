package nl.kooi.app.domain.game;

import nl.kooi.app.domain.outcome.Outcome;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;

class HalfGameTest {
    private HalfGame halfGame;

    @BeforeEach
    public void initTestDependencies() {
        halfGame = new HalfGame(BigDecimal.TEN);
    }

    @Test
    public void halfGameChipValueTest() {
        assertThat(halfGame.getChipValue()).isEqualTo(BigDecimal.TEN.setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void hitArrayFirstHalfTest() {
        var outcome = new Outcome(1, 1, BigDecimal.ZERO);
        halfGame.setHits(outcome);

        assertThat(halfGame.getHitArray()).isEqualTo(new boolean[]{true, false});
    }

    @Test
    public void hitArraySecondHalfTest() {
        var outcome = new Outcome(1, 33, BigDecimal.ZERO);
        halfGame.setHits(outcome);

        assertThat(halfGame.getHitArray()).isEqualTo(new boolean[]{false, true});
    }

    @Test
    public void hitArrayZeroTest() {
        var outcome = new Outcome(1, 0, BigDecimal.ZERO);
        halfGame.setHits(outcome);

        assertThat(halfGame.getHitArray()).isEqualTo(new boolean[]{false, false});
    }
}
