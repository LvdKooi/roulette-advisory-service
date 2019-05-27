/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.kooi.app.domain.advises.Game.Roulette.RouletteTwoToOne;


import nl.kooi.app.domain.RouletteDomainObject;
import nl.kooi.representation.RouletteOutcome;
import nl.kooi.representation.advises.ColumnAdviceRepresentation;
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
    public void setHits(RouletteDomainObject roulette) {
        hitArray[0] = roulette.getColumn().equals(RouletteOutcome.FIRST);
        hitArray[1] = roulette.getColumn().equals(RouletteOutcome.SECOND);
        hitArray[2] = roulette.getColumn().equals(RouletteOutcome.THIRD);
        this.setAdvice(hitArray);
    }

    @Override
    public ColumnAdviceRepresentation toRepresentation() {
        int[] adviceArray = bettingSystem.getAdviceArray();
        ColumnAdviceRepresentation representation = new ColumnAdviceRepresentation();
        representation.firstColumn = getChipValue().multiply(new BigDecimal(adviceArray[0]));
        representation.secondColumn = getChipValue().multiply(new BigDecimal(adviceArray[1]));
        representation.thirdColumn = getChipValue().multiply(new BigDecimal(adviceArray[2]));
        return representation;
    }

}
