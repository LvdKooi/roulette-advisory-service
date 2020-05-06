package nl.kooi.app.domain.game;

import lombok.var;
import nl.kooi.app.domain.outcome.Outcome;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcomeUtilities;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DozenGameTest {
    private DozenGame dozenGame;

    @Before
    public void initTestDependencies() {
        dozenGame = new DozenGame(BigDecimal.TEN);
    }

    @Test
    public void dozenGameChipValueTest() {
        assertThat("ChipValue is not equal to expectation.", BigDecimal.TEN.setScale(2, BigDecimal.ROUND_HALF_UP), equalTo(dozenGame.getChipValue()));
    }

    @Test
    public void hitArrayFirstDozenTest() {
        var outcome = new Outcome(1, 1, BigDecimal.ZERO, RouletteOutcomeUtilities.getCompoundRouletteOutcome(1));
        dozenGame.setHits(outcome);

        assertThat("HitArray doesn't match expectation.", new boolean[]{true, false, false}, equalTo(dozenGame.getHitArray()));
    }

    @Test
    public void hitArraySecondDozenTest() {
        var outcome = new Outcome(1, 13, BigDecimal.ZERO, RouletteOutcomeUtilities.getCompoundRouletteOutcome(13));
        dozenGame.setHits(outcome);

        assertThat("HitArray doesn't match expectation.", new boolean[]{false, true, false}, equalTo(dozenGame.getHitArray()));
    }


    @Test
    public void hitArrayThirdDozenTest() {
        var outcome = new Outcome(1, 25, BigDecimal.ZERO, RouletteOutcomeUtilities.getCompoundRouletteOutcome(25));
        dozenGame.setHits(outcome);

        assertThat("HitArray doesn't match expectation.", new boolean[]{false, false, true}, equalTo(dozenGame.getHitArray()));
    }

    @Test
    public void hitArrayZeroTest() {
        var outcome = new Outcome(1, 0, BigDecimal.ZERO, RouletteOutcomeUtilities.getCompoundRouletteOutcome(0));
        dozenGame.setHits(outcome);

        assertThat("HitArray doesn't match expectation.", new boolean[]{false, false, false}, equalTo(dozenGame.getHitArray()));
    }
}
