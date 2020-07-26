package nl.kooi.app.domain.game;

import nl.kooi.app.domain.outcome.Outcome;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class RedBlackGameTest {
    private RedBlackGame redBlackGame;

    @BeforeEach
    public void initTestDependencies() {
        redBlackGame = new RedBlackGame(BigDecimal.TEN);
    }

    @Test
    public void redBlackGameChipValueTest() {
        assertThat("ChipValue is not equal to expectation.", BigDecimal.TEN.setScale(2, RoundingMode.HALF_UP), equalTo(redBlackGame.getChipValue()));
    }

    @Test
    public void hitArrayRedTest() {
        var outcome = new Outcome(1, 1, BigDecimal.ZERO);
        redBlackGame.setHits(outcome);

        assertThat("HitArray doesn't match expectation.", new boolean[]{true, false}, equalTo(redBlackGame.getHitArray()));
    }

    @Test
    public void hitArrayBlackTest() {
        var outcome = new Outcome(1, 2, BigDecimal.ZERO);
        redBlackGame.setHits(outcome);

        assertThat("HitArray doesn't match expectation.", new boolean[]{false, true}, equalTo(redBlackGame.getHitArray()));
    }

    @Test
    public void hitArrayZeroTest() {
        var outcome = new Outcome(1, 0, BigDecimal.ZERO);
        redBlackGame.setHits(outcome);

        assertThat("HitArray doesn't match expectation.", new boolean[]{false, false}, equalTo(redBlackGame.getHitArray()));
    }
}
