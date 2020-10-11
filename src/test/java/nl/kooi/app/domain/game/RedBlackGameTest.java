package nl.kooi.app.domain.game;

import nl.kooi.app.domain.outcome.Outcome;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;


class RedBlackGameTest {
    private RedBlackGame redBlackGame;

    @BeforeEach
    public void initTestDependencies() {
        redBlackGame = new RedBlackGame(BigDecimal.TEN);
    }

    @Test
    public void redBlackGameChipValueTest() {
        assertThat(BigDecimal.TEN.setScale(2, RoundingMode.HALF_UP)).isEqualTo(redBlackGame.getChipValue());
    }

    @Test
    public void hitArrayRedTest() {
        var outcome = new Outcome(1, 1, BigDecimal.ZERO);
        redBlackGame.setHits(outcome);

        assertThat(redBlackGame.getHitArray()).isEqualTo(new boolean[]{true, false});
    }

    @Test
    public void hitArrayBlackTest() {
        var outcome = new Outcome(1, 2, BigDecimal.ZERO);
        redBlackGame.setHits(outcome);

        assertThat(redBlackGame.getHitArray()).isEqualTo(new boolean[]{false, true});
    }

    @Test
    public void hitArrayZeroTest() {
        var outcome = new Outcome(1, 0, BigDecimal.ZERO);
        redBlackGame.setHits(outcome);

        assertThat(redBlackGame.getHitArray()).isEqualTo(new boolean[]{false, false});
    }
}
