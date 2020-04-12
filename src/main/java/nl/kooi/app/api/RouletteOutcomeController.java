package nl.kooi.app.api;

import nl.kooi.app.domain.rouletteoutcome.CompoundRouletteOutcome;
import nl.kooi.representation.CompoundRouletteOutcomeV1;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RequestMapping(path = "/roulette-outcome/V1")
@RestController
public class RouletteOutcomeController {

    @RequestMapping(path = "/{outcome}", method = GET, produces = "application/json")
    public CompoundRouletteOutcomeV1 sayOutcome(@PathVariable("outcome") int outcome) {
        return new CompoundRouletteOutcome(outcome).toRepresentationV1();
    }
}
