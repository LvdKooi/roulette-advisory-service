/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.kooi.app.domain.advises.Game.Roulette.RouletteTwoToOne.RowGame;


import nl.kooi.app.domain.RouletteDomainObject;
import nl.kooi.app.domain.advises.Game.Roulette.RouletteTwoToOne.RouletteTwoToOne;
import nl.kooi.representation.Outcome;

/**
 * @author Laurens van der Kooi
 */

public class RowGame extends RouletteTwoToOne {
    private boolean[] hitArray = {true, true, true};
    private Outcome outcome;
    private RouletteDomainObject roulette;

    public RowGame(double chipValue, char bettingSystem, RouletteDomainObject roulette) {
                super(chipValue, bettingSystem, roulette);
        }

    @Override
    public void setHits(int outcome) {
        hitArray[0] = roulette.getRow().equals(Outcome.LOW);
        hitArray[1] = roulette.getRow().equals(Outcome.MID);
        hitArray[2] = roulette.getRow().equals(Outcome.HI);
        this.setAdvice(hitArray);
    }

}
