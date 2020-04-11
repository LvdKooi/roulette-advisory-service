/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.kooi.app.domain.advises.game.roulette.roulettetwotoone;


import nl.kooi.app.domain.CompoundRouletteOutcome;
import nl.kooi.app.domain.RouletteOutcome;
import nl.kooi.representation.advises.roulettetwotoone.ColumnAdviceV1;
import java.math.BigDecimal;

/**
 * @author Laurens van der Kooi
 */

public class ColumnAdvice extends RouletteTwoToOne {
    private boolean[] hitArray = {true, true, true};

    public ColumnAdvice(String chipValue, char bettingSystem) {
        super(chipValue, bettingSystem);
    }

    @Override
    public void setHits(CompoundRouletteOutcome roulette) {
        hitArray[0] = roulette.getOutcomeBooleanMap().get(RouletteOutcome.FIRST_COLUMN);
        hitArray[1] = roulette.getOutcomeBooleanMap().get(RouletteOutcome.SECOND_COLUMN);
        hitArray[2] = roulette.getOutcomeBooleanMap().get(RouletteOutcome.THIRD_COLUMN);
        this.setAdvice(hitArray);
    }

    @Override
    public ColumnAdviceV1 toRepresentationV1() {
        int[] adviceArray = bettingSystem.getAdviceArray();
        ColumnAdviceV1 representation = new ColumnAdviceV1();
        representation.firstColumn = getChipValue().multiply(new BigDecimal(adviceArray[0]));
        representation.secondColumn = getChipValue().multiply(new BigDecimal(adviceArray[1]));
        representation.thirdColumn = getChipValue().multiply(new BigDecimal(adviceArray[2]));
        return representation;
    }

}
