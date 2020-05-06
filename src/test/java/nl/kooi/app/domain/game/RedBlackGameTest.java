package nl.kooi.app.domain.game;

import lombok.var;
import nl.kooi.app.domain.outcome.Outcome;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcomeUtilities;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class RedBlackGameTest {
    private RedBlackGame redBlackGame;

    @Before
    public void initTestDependencies() {
        redBlackGame = new RedBlackGame(BigDecimal.TEN);
    }

    @Test
    public void redBlackGameChipValueTest() {
        assertThat("ChipValue is not equal to expectation.", BigDecimal.TEN.setScale(2, BigDecimal.ROUND_HALF_UP), equalTo(redBlackGame.getChipValue()));
    }

    @Test
    public void hitArrayRedTest() {
        var outcome = new Outcome(1, 1, BigDecimal.ZERO, RouletteOutcomeUtilities.getCompoundRouletteOutcome(1));
        redBlackGame.setHits(outcome);

        assertThat("HitArray doesn't match expectation.", new boolean[]{true, false}, equalTo(redBlackGame.getHitArray()));
    }

    @Test
    public void hitArrayBlackTest() {
        var outcome = new Outcome(1, 2, BigDecimal.ZERO, RouletteOutcomeUtilities.getCompoundRouletteOutcome(2));
        redBlackGame.setHits(outcome);

        assertThat("HitArray doesn't match expectation.", new boolean[]{false, true}, equalTo(redBlackGame.getHitArray()));
    }

    @Test
    public void hitArrayZeroTest() {
        var outcome = new Outcome(1, 0, BigDecimal.ZERO, RouletteOutcomeUtilities.getCompoundRouletteOutcome(0));
        redBlackGame.setHits(outcome);

        assertThat("HitArray doesn't match expectation.", new boolean[]{false, false}, equalTo(redBlackGame.getHitArray()));
    }
}
