/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.kooi.app.domain.advises.Game.Roulette.RouletteTwoToOne;


import nl.kooi.app.domain.RouletteDomainObject;
import nl.kooi.representation.RouletteOutcome;
import nl.kooi.representation.advises.RowAdviceRepresentation;
import java.math.BigDecimal;

/**
 * @author Laurens van der Kooi
 */

public class RowGame extends RouletteTwoToOne {
    private boolean[] hitArray = {true, true, true};

    public RowGame(String chipValue, char bettingSystem) {
        super(chipValue, bettingSystem);
    }

    @Override
    public void setHits(RouletteDomainObject roulette) {
        hitArray[0] = roulette.getRow().equals(RouletteOutcome.LOW);
        hitArray[1] = roulette.getRow().equals(RouletteOutcome.MID);
        hitArray[2] = roulette.getRow().equals(RouletteOutcome.HI);
        this.setAdvice(hitArray);
    }

    @Override
    public RowAdviceRepresentation toRepresentation() {
        int[] adviceArray = bettingSystem.getAdviceArray();
        RowAdviceRepresentation representation = new RowAdviceRepresentation();
        representation.lowRow = getChipValue().multiply(new BigDecimal(adviceArray[0]));
        representation.midRow = getChipValue().multiply(new BigDecimal(adviceArray[1]));
        representation.hiRow = getChipValue().multiply(new BigDecimal(adviceArray[2]));
        return representation;
    }

}
