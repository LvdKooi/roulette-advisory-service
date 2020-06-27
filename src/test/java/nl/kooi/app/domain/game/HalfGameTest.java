package nl.kooi.app.domain.game;

import lombok.var;
import nl.kooi.app.domain.outcome.Outcome;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class HalfGameTest {
    private HalfGame halfGame;

    @BeforeEach
    public void initTestDependencies() {
        halfGame = new HalfGame(BigDecimal.TEN);
    }

    @Test
    public void halfGameChipValueTest() {
        assertThat("ChipValue is not equal to expectation.", BigDecimal.TEN.setScale(2, RoundingMode.HALF_UP), equalTo(halfGame.getChipValue()));
    }

    @Test
    public void hitArrayFirstHalfTest() {
        var outcome = new Outcome(1, 1, BigDecimal.ZERO);
        halfGame.setHits(outcome);

        assertThat("HitArray doesn't match expectation.", new boolean[]{true, false}, equalTo(halfGame.getHitArray()));
    }

    @Test
    public void hitArraySecondHalfTest() {
        var outcome = new Outcome(1, 33, BigDecimal.ZERO);
        halfGame.setHits(outcome);

        assertThat("HitArray doesn't match expectation.", new boolean[]{false, true}, equalTo(halfGame.getHitArray()));
    }

    @Test
    public void hitArrayZeroTest() {
        var outcome = new Outcome(1, 0, BigDecimal.ZERO);
        halfGame.setHits(outcome);

        assertThat("HitArray doesn't match expectation.", new boolean[]{false, false}, equalTo(halfGame.getHitArray()));
    }
}
