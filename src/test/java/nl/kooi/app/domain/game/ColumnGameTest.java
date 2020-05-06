package nl.kooi.app.domain.game;

import lombok.var;
import nl.kooi.app.domain.outcome.Outcome;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcomeUtilities;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ColumnGameTest {
    private ColumnGame columnGame;

    @Before
    public void initTestDependencies() {
        columnGame = new ColumnGame(BigDecimal.TEN);
    }

    @Test
    public void columnGameChipValueTest() {
        assertThat("ChipValue is not equal to expectation.", BigDecimal.TEN.setScale(2, BigDecimal.ROUND_HALF_UP), equalTo(columnGame.getChipValue()));
    }

    @Test
    public void hitArrayFirstColumnTest() {
        var outcome = new Outcome(1, 1, BigDecimal.ZERO, RouletteOutcomeUtilities.getCompoundRouletteOutcome(1));
        columnGame.setHits(outcome);

        assertThat("HitArray doesn't match expectation.", new boolean[]{true, false, false}, equalTo(columnGame.getHitArray()));
    }

    @Test
    public void hitArraySecondColumnTest() {
        var outcome = new Outcome(1, 17, BigDecimal.ZERO, RouletteOutcomeUtilities.getCompoundRouletteOutcome(17));
        columnGame.setHits(outcome);

        assertThat("HitArray doesn't match expectation.", new boolean[]{false, true, false}, equalTo(columnGame.getHitArray()));
    }


    @Test
    public void hitArrayThirdColumnTest() {
        var outcome = new Outcome(1, 36, BigDecimal.ZERO, RouletteOutcomeUtilities.getCompoundRouletteOutcome(36));
        columnGame.setHits(outcome);

        assertThat("HitArray doesn't match expectation.", new boolean[]{false, false, true}, equalTo(columnGame.getHitArray()));
    }

    @Test
    public void hitArrayZeroTest() {
        var outcome = new Outcome(1, 0, BigDecimal.ZERO, RouletteOutcomeUtilities.getCompoundRouletteOutcome(0));
        columnGame.setHits(outcome);

        assertThat("HitArray doesn't match expectation.", new boolean[]{false, false, false}, equalTo(columnGame.getHitArray()));
    }


}
