/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.kooi.app.domain.advises;


import lombok.Getter;
import nl.kooi.app.domain.game.RouletteTwoToOne;
import nl.kooi.app.domain.outcome.Outcome;

import java.math.BigDecimal;

/**
 * @author Laurens van der Kooi
 */

@Getter
public class ColumnAdvice extends RouletteTwoToOne {
    private boolean[] hitArray = {true, true, true};
    private BigDecimal firstColumn;
    private BigDecimal secondColumn;
    private BigDecimal thirdColumn;

    public ColumnAdvice(String chipValue) {
        super(chipValue);
    }

    @Override
    public void setHits(Outcome roulette) {
        hitArray[0] = roulette.getFirstColumn();
        hitArray[1] = roulette.getSecondColumn();
        hitArray[2] = roulette.getThirdColumn();
        this.setAdvice(hitArray);
    }

    @Override
    protected void setAdvice(boolean[] hitArray) {
        super.setAdvice(hitArray);
        firstColumn = getChipValue().multiply(new BigDecimal(bettingSystem.getAdviceArray()[0]));
        secondColumn = getChipValue().multiply(new BigDecimal(bettingSystem.getAdviceArray()[1]));
        thirdColumn = getChipValue().multiply(new BigDecimal(bettingSystem.getAdviceArray()[2]));
    }
}
