package nl.kooi.app;

import nl.kooi.app.domain.RouletteDomainObject;
import nl.kooi.app.exceptions.notValidOutcomeException;
import nl.kooi.representation.Outcome;
import nl.kooi.representation.RouletteRepresentationObject;


import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RequestMapping(path = "/roulette-outcome")
@RestController
public class RouletteOutcomeController {

    @RequestMapping(path = "/{outcome}", method = GET, produces = "application/json")
    public RouletteRepresentationObject sayOutcome(@PathVariable("outcome") int outcome) {
        return new RouletteDomainObject(outcome).toRepresentation();
    }

    @RequestMapping(path = "{outcome}/redblack", method = GET, produces = "application/json")
    public Outcome sayRedBlack(@PathVariable("outcome") int outcome) {
        RouletteRepresentationObject representation = new RouletteDomainObject(outcome).toRepresentation();
        return representation.redBlack;
    }

    @RequestMapping(path = "{outcome}/oddeven", method = GET, produces = "application/json")
    public Outcome sayOddEven(@PathVariable("outcome") int outcome) {
        RouletteRepresentationObject representation = new RouletteDomainObject(outcome).toRepresentation();
        return representation.oddEven;
    }

    @RequestMapping(path = "{outcome}/half", method = GET, produces = "application/json")
    public Outcome sayHalf(@PathVariable("outcome") int outcome) {
        RouletteRepresentationObject representation = new RouletteDomainObject(outcome).toRepresentation();
        return representation.half;
    }

    @RequestMapping(path = "{outcome}/row", method = GET, produces = "application/json")
    public Outcome sayRow(@PathVariable("outcome") int outcome) {
        RouletteRepresentationObject representation = new RouletteDomainObject(outcome).toRepresentation();
        return representation.row;
    }

    @RequestMapping(path = "{outcome}/dozen", method = GET, produces = "application/json")
    public Outcome sayDozen(@PathVariable("outcome") int outcome) {
        RouletteRepresentationObject representation = new RouletteDomainObject(outcome).toRepresentation();
        return representation.dozen;
    }
}
