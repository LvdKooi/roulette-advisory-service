package nl.kooi.app.domain.outcome;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcome;
import nl.kooi.app.exceptions.NotValidOutcomeException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import static nl.kooi.app.domain.rouletteoutcome.RouletteOutcome.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class Outcome {
    private int id;
    private int sessionId;
    private int outcome;
    private Boolean red;
    private Boolean black;
    private Boolean odd;
    private Boolean even;
    private Boolean firstHalf;
    private Boolean secondHalf;
    private Boolean firstColumn;
    private Boolean secondColumn;
    private Boolean thirdColumn;
    private Boolean firstDozen;
    private Boolean secondDozen;
    private Boolean thirdDozen;
    private Boolean zero;
    private BigDecimal totalProfit;

    public Outcome(int sessionId,
                   int outcome,
                   BigDecimal totalProfit,
                   Map<RouletteOutcome, Boolean> rouletteOutcomeBooleanMap
    ) {
        validateOutcome(outcome);
        this.sessionId = sessionId;
        this.totalProfit = totalProfit.setScale(2, RoundingMode.HALF_UP);
        this.outcome = outcome;
        setRouletteFields(rouletteOutcomeBooleanMap);

    }

    public Outcome(int sessionId,
                   int outcome,
                   Map<RouletteOutcome, Boolean> rouletteOutcomeBooleanMap) {
        this(sessionId, outcome, BigDecimal.ZERO, rouletteOutcomeBooleanMap);
    }

    public static void validateOutcome(int outcome) {
        if (!(outcome > -1 && outcome < 37)) {
            throw new NotValidOutcomeException("Not a valid roulette outcome. RouletteOutcome can only be within the range of 0 - 36.");
        }
    }

    private void setRouletteFields(Map<RouletteOutcome, Boolean> rouletteOutcomeBooleanMap) {

        this.red = rouletteOutcomeBooleanMap.get(RED);
        this.black = rouletteOutcomeBooleanMap.get(BLACK);
        this.odd = rouletteOutcomeBooleanMap.get(ODD);
        this.even = rouletteOutcomeBooleanMap.get(EVEN);
        this.firstHalf = rouletteOutcomeBooleanMap.get(FIRST_HALF);
        this.secondHalf = rouletteOutcomeBooleanMap.get(SECOND_HALF);
        this.firstColumn = rouletteOutcomeBooleanMap.get(FIRST_COLUMN);
        this.secondColumn = rouletteOutcomeBooleanMap.get(SECOND_COLUMN);
        this.thirdColumn = rouletteOutcomeBooleanMap.get(THIRD_COLUMN);
        this.firstDozen = rouletteOutcomeBooleanMap.get(FIRST_DOZEN);
        this.secondDozen = rouletteOutcomeBooleanMap.get(SECOND_DOZEN);
        this.thirdDozen = rouletteOutcomeBooleanMap.get(THIRD_DOZEN);
        this.zero = rouletteOutcomeBooleanMap.get(ZERO);
    }


}
