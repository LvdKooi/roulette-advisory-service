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

@RestController
public class RouletteOutcomeController {

    @RequestMapping(path="/roulette-outcome/{outcome}")
    public RouletteRepresentationObject sayOutcome(@PathVariable("outcome") int outcome)  {    // als /roulette-outcome?outcome=.. wordt aangeroepen dan wordt deze methode aangeroepen
        return new RouletteDomainObject(outcome).toRepresentation();
    }

}