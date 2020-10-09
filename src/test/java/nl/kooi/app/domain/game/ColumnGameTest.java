package nl.kooi.app.domain.game;

import nl.kooi.app.domain.outcome.Outcome;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;


public class ColumnGameTest {
    private ColumnGame columnGame;

    @BeforeEach
    public void initTestDependencies() {
        columnGame = new ColumnGame(BigDecimal.TEN);
    }

    @Test
    public void columnGameChipValueTest() {
        assertThat(columnGame.getChipValue()).isEqualTo(BigDecimal.TEN.setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void hitArrayFirstColumnTest() {
        var outcome = new Outcome(1, 1, BigDecimal.ZERO);
        columnGame.setHits(outcome);

        assertThat(columnGame.getHitArray()).isEqualTo(new boolean[]{true, false, false});
    }

    @Test
    public void hitArraySecondColumnTest() {
        var outcome = new Outcome(1, 17, BigDecimal.ZERO);
        columnGame.setHits(outcome);

        assertThat(columnGame.getHitArray()).isEqualTo(new boolean[]{false, true, false});
    }


    @Test
    public void hitArrayThirdColumnTest() {
        var outcome = new Outcome(1, 36, BigDecimal.ZERO);
        columnGame.setHits(outcome);

        assertThat(columnGame.getHitArray()).isEqualTo(new boolean[]{false, false, true});
    }

    @Test
    public void hitArrayZeroTest() {
        var outcome = new Outcome(1, 0, BigDecimal.ZERO);
        columnGame.setHits(outcome);

        assertThat(columnGame.getHitArray()).isEqualTo(new boolean[]{false, false, false});
    }
}
