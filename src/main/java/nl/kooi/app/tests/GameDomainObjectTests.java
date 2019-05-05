package nl.kooi.app.tests;

import nl.kooi.app.domain.advises.Game.Game;
import nl.kooi.app.domain.advises.Game.Roulette.RouletteOneToOne.OddEven;
import nl.kooi.app.domain.advises.Game.Roulette.RouletteOneToOne.RouletteOneToOne;
import org.junit.jupiter.api.Test;
import nl.kooi.app.domain.RouletteDomainObject;
import nl.kooi.app.exceptions.notValidOutcomeException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;


public class GameDomainObjectTests {

    private static RouletteDomainObject roulette = new RouletteDomainObject(0);

    @Test
    public void oddEvenTests() {
        RouletteOneToOne game = new OddEven("5", 'D', 4);
        ArrayList<Integer> outcomeList = new ArrayList<>();
        outcomeList.add(1);
        outcomeList.add(3);
        outcomeList.add(5);
        outcomeList.add(7);
        outcomeList.add(9);
        outcomeList.add(11);
        outcomeList.add(13);

        for(int singleOutcome : outcomeList) {
            roulette.setOutcome(singleOutcome);
            game.setHits(roulette);
                        }

        game.printAdvice();

    }

}


