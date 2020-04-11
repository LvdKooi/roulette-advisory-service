package nl.kooi.app.api.v1;

import nl.kooi.app.domain.CompoundRouletteOutcome;
import nl.kooi.app.domain.RouletteOutcome;
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

    @RequestMapping(path = "{outcome}/redblack", method = GET, produces = "application/json")
    public RouletteOutcome sayRedBlack(@PathVariable("outcome") int outcome) {
        CompoundRouletteOutcomeV1 representation = new CompoundRouletteOutcome(outcome).toRepresentationV1();
        return representation.redBlack;
    }

    @RequestMapping(path = "{outcome}/oddeven", method = GET, produces = "application/json")
    public RouletteOutcome sayOddEven(@PathVariable("outcome") int outcome) {
        CompoundRouletteOutcomeV1 representation = new CompoundRouletteOutcome(outcome).toRepresentationV1();
        return representation.oddEven;
    }

    @RequestMapping(path = "{outcome}/half", method = GET, produces = "application/json")
    public RouletteOutcome sayHalf(@PathVariable("outcome") int outcome) {
        CompoundRouletteOutcomeV1 representation = new CompoundRouletteOutcome(outcome).toRepresentationV1();
        return representation.half;
    }

    @RequestMapping(path = "{outcome}/column", method = GET, produces = "application/json")
    public RouletteOutcome sayColumn(@PathVariable("outcome") int outcome) {
        CompoundRouletteOutcomeV1 representation = new CompoundRouletteOutcome(outcome).toRepresentationV1();
        return representation.column;
    }

    @RequestMapping(path = "{outcome}/dozen", method = GET, produces = "application/json")
    public RouletteOutcome sayDozen(@PathVariable("outcome") int outcome) {
        CompoundRouletteOutcomeV1 representation = new CompoundRouletteOutcome(outcome).toRepresentationV1();
        return representation.dozen;
    }
}
