package nl.kooi.app.domain.outcome;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;


class OutcomeTest {


    @Test
    public void outcomeTestOne() {
        var outcome = new Outcome(1, 1, BigDecimal.TEN);

        assertThat(outcome.getSessionId()).isEqualTo(1);
        assertThat(outcome.getTotalProfit()).isEqualTo(BigDecimal.TEN.setScale(2, RoundingMode.HALF_UP));

        assertThat(outcome.getRed()).isTrue();
        assertThat(outcome.getBlack()).isFalse();
        assertThat(outcome.getOdd()).isTrue();
        assertThat(outcome.getEven()).isFalse();
        assertThat(outcome.getFirstHalf()).isTrue();
        assertThat(outcome.getSecondHalf()).isFalse();
        assertThat(outcome.getFirstColumn()).isTrue();
        assertThat(outcome.getSecondColumn()).isFalse();
        assertThat(outcome.getThirdColumn()).isFalse();
        assertThat(outcome.getFirstDozen()).isTrue();
        assertThat(outcome.getSecondDozen()).isFalse();
        assertThat(outcome.getThirdDozen()).isFalse();
        assertThat(outcome.getZero()).isFalse();
    }

    @Test
    public void outcomeTestSeventeen() {
        var outcome = new Outcome(1, 17, BigDecimal.TEN);

        assertThat(outcome.getRed()).isFalse();
        assertThat(outcome.getBlack()).isTrue();
        assertThat(outcome.getOdd()).isTrue();
        assertThat(outcome.getEven()).isFalse();
        assertThat(outcome.getFirstHalf()).isTrue();
        assertThat(outcome.getSecondHalf()).isFalse();
        assertThat(outcome.getFirstColumn()).isFalse();
        assertThat(outcome.getSecondColumn()).isTrue();
        assertThat(outcome.getThirdColumn()).isFalse();
        assertThat(outcome.getFirstDozen()).isFalse();
        assertThat(outcome.getSecondDozen()).isTrue();
        assertThat(outcome.getThirdDozen()).isFalse();
        assertThat(outcome.getZero()).isFalse();
    }

    @Test
    public void outcomeTestThirty() {
        var outcome = new Outcome(1, 30, BigDecimal.TEN);

        assertThat(outcome.getRed()).isTrue();
        assertThat(outcome.getBlack()).isFalse();
        assertThat(outcome.getOdd()).isFalse();
        assertThat(outcome.getEven()).isTrue();
        assertThat(outcome.getFirstHalf()).isFalse();
        assertThat(outcome.getSecondHalf()).isTrue();
        assertThat(outcome.getFirstColumn()).isFalse();
        assertThat(outcome.getSecondColumn()).isFalse();
        assertThat(outcome.getThirdColumn()).isTrue();
        assertThat(outcome.getFirstDozen()).isFalse();
        assertThat(outcome.getSecondDozen()).isFalse();
        assertThat(outcome.getThirdDozen()).isTrue();
        assertThat(outcome.getZero()).isFalse();
    }

    @Test
    public void outcomeTestZero() {
        var outcome = new Outcome(1, 0, BigDecimal.TEN);

        assertThat(outcome.getRed()).isFalse();
        assertThat(outcome.getBlack()).isFalse();
        assertThat(outcome.getOdd()).isFalse();
        assertThat(outcome.getEven()).isFalse();
        assertThat(outcome.getFirstHalf()).isFalse();
        assertThat(outcome.getSecondHalf()).isFalse();
        assertThat(outcome.getFirstColumn()).isFalse();
        assertThat(outcome.getSecondColumn()).isFalse();
        assertThat(outcome.getThirdColumn()).isFalse();
        assertThat(outcome.getFirstDozen()).isFalse();
        assertThat(outcome.getSecondDozen()).isFalse();
        assertThat(outcome.getThirdDozen()).isFalse();
        assertThat(outcome.getZero()).isTrue();
    }

}
