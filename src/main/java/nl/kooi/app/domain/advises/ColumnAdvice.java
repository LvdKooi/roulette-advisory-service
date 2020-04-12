/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.kooi.app.domain.advises;


import nl.kooi.app.domain.rouletteoutcome.CompoundRouletteOutcome;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcome;
import nl.kooi.app.domain.game.RouletteTwoToOne;
import nl.kooi.app.api.dto.advises.ColumnAdviceDto;
import java.math.BigDecimal;

/**
 * @author Laurens van der Kooi
 */

public class ColumnAdvice extends RouletteTwoToOne {
    private boolean[] hitArray = {true, true, true};

    public ColumnAdvice(String chipValue) {
        super(chipValue);
    }

    @Override
    public void setHits(CompoundRouletteOutcome roulette) {
        hitArray[0] = roulette.getOutcomeBooleanMap().get(RouletteOutcome.FIRST_COLUMN);
        hitArray[1] = roulette.getOutcomeBooleanMap().get(RouletteOutcome.SECOND_COLUMN);
        hitArray[2] = roulette.getOutcomeBooleanMap().get(RouletteOutcome.THIRD_COLUMN);
        this.setAdvice(hitArray);
    }

    @Override
    public ColumnAdviceDto toRepresentationV1() {
        int[] adviceArray = bettingSystem.getAdviceArray();
        ColumnAdviceDto representation = new ColumnAdviceDto();
        representation.firstColumn = getChipValue().multiply(new BigDecimal(adviceArray[0]));
        representation.secondColumn = getChipValue().multiply(new BigDecimal(adviceArray[1]));
        representation.thirdColumn = getChipValue().multiply(new BigDecimal(adviceArray[2]));
        return representation;
    }

}
