package nl.kooi.app;

import nl.kooi.app.domain.CompoundRouletteOutcomeObject;
import nl.kooi.representation.RouletteOutcome;
import nl.kooi.representation.CompoundRouletteOutcomeRepresentationV1;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RequestMapping(path = "/roulette-outcome/V1")
@RestController
public class RouletteOutcomeController {

    @RequestMapping(path = "/{outcome}", method = GET, produces = "application/json")
    public CompoundRouletteOutcomeRepresentationV1 sayOutcome(@PathVariable("outcome") int outcome) {
        return new CompoundRouletteOutcomeObject(outcome).toRepresentation();
    }

    @RequestMapping(path = "{outcome}/redblack", method = GET, produces = "application/json")
    public RouletteOutcome sayRedBlack(@PathVariable("outcome") int outcome) {
        CompoundRouletteOutcomeRepresentationV1 representation = new CompoundRouletteOutcomeObject(outcome).toRepresentation();
        return representation.redBlack;
    }

    @RequestMapping(path = "{outcome}/oddeven", method = GET, produces = "application/json")
    public RouletteOutcome sayOddEven(@PathVariable("outcome") int outcome) {
        CompoundRouletteOutcomeRepresentationV1 representation = new CompoundRouletteOutcomeObject(outcome).toRepresentation();
        return representation.oddEven;
    }

    @RequestMapping(path = "{outcome}/half", method = GET, produces = "application/json")
    public RouletteOutcome sayHalf(@PathVariable("outcome") int outcome) {
        CompoundRouletteOutcomeRepresentationV1 representation = new CompoundRouletteOutcomeObject(outcome).toRepresentation();
        return representation.half;
    }

    @RequestMapping(path = "{outcome}/column", method = GET, produces = "application/json")
    public RouletteOutcome sayColumn(@PathVariable("outcome") int outcome) {
        CompoundRouletteOutcomeRepresentationV1 representation = new CompoundRouletteOutcomeObject(outcome).toRepresentation();
        return representation.column;
    }

    @RequestMapping(path = "{outcome}/dozen", method = GET, produces = "application/json")
    public RouletteOutcome sayDozen(@PathVariable("outcome") int outcome) {
        CompoundRouletteOutcomeRepresentationV1 representation = new CompoundRouletteOutcomeObject(outcome).toRepresentation();
        return representation.dozen;
    }
}
