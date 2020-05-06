package nl.kooi.app.domain.game;

import lombok.var;
import nl.kooi.app.domain.outcome.Outcome;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcomeUtilities;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class OddEvenGameTest {
    private OddEvenGame oddEvenGame;

    @Before
    public void initTestDependencies() {
        oddEvenGame = new OddEvenGame(BigDecimal.TEN);
    }

    @Test
    public void oddEvenGameChipValueTest() {
        assertThat("ChipValue is not equal to expectation.", BigDecimal.TEN.setScale(2, BigDecimal.ROUND_HALF_UP), equalTo(oddEvenGame.getChipValue()));
    }

    @Test
    public void hitArrayOddTest() {
        var outcome = new Outcome(1, 1, BigDecimal.ZERO, RouletteOutcomeUtilities.getCompoundRouletteOutcome(1));
        oddEvenGame.setHits(outcome);

        assertThat("HitArray doesn't match expectation.", new boolean[]{true, false}, equalTo(oddEvenGame.getHitArray()));
    }

    @Test
    public void hitArrayEvenTest() {
        var outcome = new Outcome(1, 2, BigDecimal.ZERO, RouletteOutcomeUtilities.getCompoundRouletteOutcome(2));
        oddEvenGame.setHits(outcome);

        assertThat("HitArray doesn't match expectation.", new boolean[]{false, true}, equalTo(oddEvenGame.getHitArray()));
    }

    @Test
    public void hitArrayZeroTest() {
        var outcome = new Outcome(1, 0, BigDecimal.ZERO, RouletteOutcomeUtilities.getCompoundRouletteOutcome(0));
        oddEvenGame.setHits(outcome);

        assertThat("HitArray doesn't match expectation.", new boolean[]{false, false}, equalTo(oddEvenGame.getHitArray()));
    }
}
