package nl.kooi.app.domain;

import nl.kooi.app.domain.advises.game.Game;
import nl.kooi.app.domain.advises.game.roulette.rouletteonetoone.OddEvenAdvice;
import nl.kooi.representation.advises.OddEvenAdviceRepresentation;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class GameDomainObjectTests {

    private static CompoundRouletteOutcomeObject roulette = new CompoundRouletteOutcomeObject(0);

    @Test
    public void oddEvenTests() {
        Game game = new OddEvenAdvice("5", 'D', 4);
        OddEvenAdviceRepresentation representation;
        Integer[] outcomeArray = {1,3,5,7,9,11,13};
        ArrayList<Integer> outcomeList = new ArrayList<>();

        for(Integer outcome : outcomeArray){
            outcomeList.add(outcome);
                    }

        for (int singleOutcome : outcomeList) {
            roulette.setOutcome(singleOutcome);
            game.setHits(roulette);
        }

        representation = (OddEvenAdviceRepresentation) game.toRepresentation();
        assertThat("Advice even", representation.even, is(new BigDecimal(40.00).setScale(2)));
        assertThat("Advice odd", representation.odd, is(new BigDecimal(0.00).setScale(2)));

        roulette.setOutcome(2);
        game.setHits(roulette);

        representation = (OddEvenAdviceRepresentation) game.toRepresentation();
        assertThat("Advice even", representation.even, is(new BigDecimal(5.00).setScale(2)));
        assertThat("Advice odd", representation.odd, is(new BigDecimal(0.00).setScale(2)));

    }

}


