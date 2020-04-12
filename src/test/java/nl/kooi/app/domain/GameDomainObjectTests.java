package nl.kooi.app.domain;

import nl.kooi.app.api.dto.Mapper;
import nl.kooi.app.api.dto.advises.OddEvenAdviceDto;
import nl.kooi.app.domain.advises.OddEvenAdvice;
import nl.kooi.app.domain.game.Game;
import nl.kooi.app.domain.rouletteoutcome.CompoundRouletteOutcome;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class GameDomainObjectTests {

    private static CompoundRouletteOutcome roulette = new CompoundRouletteOutcome(0);

    @Test
    public void oddEvenTests() {
        Game game = new OddEvenAdvice("5",  4);
        OddEvenAdviceDto representation;
        Integer[] outcomeArray = {1,3,5,7,9,11,13};
        ArrayList<Integer> outcomeList = new ArrayList<>();

        for(Integer outcome : outcomeArray){
            outcomeList.add(outcome);
                    }

        for (int singleOutcome : outcomeList) {
            roulette = new CompoundRouletteOutcome(singleOutcome);
            game.setHits(roulette);
        }

        representation = Mapper.map((OddEvenAdvice) game);
        assertThat("Advice even", representation.even, is(new BigDecimal(40.00).setScale(2)));
        assertThat("Advice odd", representation.odd, is(new BigDecimal(0.00).setScale(2)));

        roulette = new CompoundRouletteOutcome(2);
        game.setHits(roulette);

        representation = Mapper.map((OddEvenAdvice) game);
        assertThat("Advice even", representation.even, is(new BigDecimal(5.00).setScale(2)));
        assertThat("Advice odd", representation.odd, is(new BigDecimal(0.00).setScale(2)));

    }

}


