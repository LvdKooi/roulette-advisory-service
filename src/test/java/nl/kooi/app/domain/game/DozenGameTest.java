package nl.kooi.app.domain.game;

import nl.kooi.app.domain.outcome.Outcome;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;


class DozenGameTest {
    private DozenGame dozenGame;

    @BeforeEach
    public void initTestDependencies() {
        dozenGame = new DozenGame(BigDecimal.TEN);
    }

    @Test
    public void dozenGameChipValueTest() {
        assertThat(dozenGame.getChipValue()).isEqualTo(BigDecimal.TEN.setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void hitArrayFirstDozenTest() {
        var outcome = new Outcome(1, 1, BigDecimal.ZERO);
        dozenGame.setHits(outcome);

        assertThat(dozenGame.getHitArray()).isEqualTo(new boolean[]{true, false, false});
    }

    @Test
    public void hitArraySecondDozenTest() {
        var outcome = new Outcome(1, 13, BigDecimal.ZERO);
        dozenGame.setHits(outcome);

        assertThat(dozenGame.getHitArray()).isEqualTo(new boolean[]{false, true, false});
    }

    @Test
    public void hitArrayThirdDozenTest() {
        var outcome = new Outcome(1, 25, BigDecimal.ZERO);
        dozenGame.setHits(outcome);

        assertThat(dozenGame.getHitArray()).isEqualTo(new boolean[]{false, false, true});
    }

    @Test
    public void hitArrayZeroTest() {
        var outcome = new Outcome(1, 0, BigDecimal.ZERO);
        dozenGame.setHits(outcome);

        assertThat(dozenGame.getHitArray()).isEqualTo(new boolean[]{false, false, false});
    }
}
