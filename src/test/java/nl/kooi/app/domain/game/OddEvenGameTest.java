package nl.kooi.app.domain.game;

import lombok.var;
import nl.kooi.app.domain.outcome.Outcome;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class OddEvenGameTest {
    private OddEvenGame oddEvenGame;

    @BeforeEach
    public void initTestDependencies() {
        oddEvenGame = new OddEvenGame(BigDecimal.TEN);
    }

    @Test
    public void oddEvenGameChipValueTest() {
        assertThat("ChipValue is not equal to expectation.", BigDecimal.TEN.setScale(2, RoundingMode.HALF_UP), equalTo(oddEvenGame.getChipValue()));
    }

    @Test
    public void hitArrayOddTest() {
        var outcome = new Outcome(1, 1, BigDecimal.ZERO);
        oddEvenGame.setHits(outcome);

        assertThat("HitArray doesn't match expectation.", new boolean[]{true, false}, equalTo(oddEvenGame.getHitArray()));
    }

    @Test
    public void hitArrayEvenTest() {
        var outcome = new Outcome(1, 2, BigDecimal.ZERO);
        oddEvenGame.setHits(outcome);

        assertThat("HitArray doesn't match expectation.", new boolean[]{false, true}, equalTo(oddEvenGame.getHitArray()));
    }

    @Test
    public void hitArrayZeroTest() {
        var outcome = new Outcome(1, 0, BigDecimal.ZERO);
        oddEvenGame.setHits(outcome);

        assertThat("HitArray doesn't match expectation.", new boolean[]{false, false}, equalTo(oddEvenGame.getHitArray()));
    }
}
