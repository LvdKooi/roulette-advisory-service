/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.kooi.app.domain.game;


import lombok.Getter;
import nl.kooi.app.domain.outcome.Outcome;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcome;

import java.math.BigDecimal;
import java.util.Map;

import static nl.kooi.app.domain.rouletteoutcome.RouletteOutcome.*;

/**
 * @author Laurens van der Kooi
 */

@Getter
public class ColumnGame extends RouletteTwoToOne {
    private boolean[] hitArray = {true, true, true};

    public ColumnGame(BigDecimal chipValue) {
        super(chipValue);
    }

    @Override
    public void setHits(Outcome roulette) {
        hitArray[0] = roulette.getFirstColumn();
        hitArray[1] = roulette.getSecondColumn();
        hitArray[2] = roulette.getThirdColumn();
        getTwoToOneBettingSystem().setHits(hitArray);
    }

    @Override
    public Map<RouletteOutcome, BigDecimal> getAdvice() {
        return getTwoToOneBettingSystem().getTwoToOneAdviceMap(FIRST_COLUMN, SECOND_COLUMN, THIRD_COLUMN, getChipValue());
    }
}
