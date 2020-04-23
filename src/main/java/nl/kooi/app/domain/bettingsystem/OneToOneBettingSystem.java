/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.kooi.app.domain.bettingsystem;

import lombok.var;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcome;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Laurens van der Kooi
 */

public class OneToOneBettingSystem extends BettingSystem {

    private boolean[][] outcomeArray;
    private int[] adviceArray;
    private int[][] winLossCountArray;
    private int profitCounter;
    private int maxProfit;

    public OneToOneBettingSystem(int bettingFactor, int delay) {
        super(bettingFactor, delay);
        outcomeArray = new boolean[2][delay];
        adviceArray = new int[2];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < delay; j++) {
                outcomeArray[i][j] = true;
            }
        }

    }

    private void setRounds() {
        setTotalRounds(getTotalRounds() + 1);
        if (getRounds() < getDelay()) {
            setRounds(getRounds() + 1);
        } else {
            setRounds(1);
        }
    }

    private void updateOutcomeArray(boolean[] hitArray) {
        for (int i = 0; i < 2; i++) {
            outcomeArray[i][getRounds() - 1] = hitArray[i];
        }
    }

    private void setWinLossCountArray(boolean[] hitArray) {
        winLossCountArray = new int[2][2];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < getDelay(); j++) {
                if (outcomeArray[i][j]) {
                    winLossCountArray[i][0]++; //win counts are stored in first slot of subarray
                } else {
                    winLossCountArray[i][1]++; //loss counts are stored in second slot of subarray
                }
            }
        }
    }

    @Override
    public void setHits(boolean[] hitArray) {
        updateOutcomeArray(hitArray);
        setRounds();
        setWinLossCountArray(hitArray);

        if (getTotalRounds() > getDelay()) {

            // calculate profitCounter
            profitCounter(hitArray);

            //looping through results and determining advice
            for (int i = 0; i < 2; i++) {

                // situation 1: a bet was made but it didn't hit
                if (adviceArray[i] > 0 && !hitArray[i]) {

                    adviceArray[i] *= getBettingFactor();

                    // situation 2: a bet was made, the bet was won but conditions still meet: time to bet again.
                } else if (winLossCountArray[i][1] >= getDelay() - 1 && hitArray[i]) {

                    adviceArray[i] = 1;

                    for (int j = 0; j < 2; j++) {
                        if (i == j) {
                            continue;
                        }
                        if (adviceArray[j] > 0) {
                            if (i < j) {
                                adviceArray[j] = 0;
                            } else {
                                adviceArray[j] = adviceArray[i];
                            }
                        }

                    }

                    // situation 3: the number of losses match the strategy, time to bet
                } else if (winLossCountArray[i][1] >= getDelay() - 1 && !hitArray[i]) {

                    adviceArray[i] = Math.max(1, adviceArray[i] *= getBettingFactor());


                    // situation 4: the number of losses doesn't match the conditions, no bets adviced.
                } else {

                    if (adviceArray[i] > 0) {
                        for (int j = 0; j < 2; j++) {
                            if (i == j) {
                                continue;
                            }
                            if (adviceArray[j] > 0) {
                                adviceArray[j] = 0;

                            }

                        }

                    }
                    adviceArray[i] = 0;
                }

                // final check: if there is an advice for all fields (which can occur in the rare event of a zero streak), override the lowest advice
                if (adviceArray[0] > 0 && adviceArray[1] > 0) {
                    int obsoleteAdvice = Math.min(adviceArray[0], adviceArray[1]);
                    for (int j = 0; j < adviceArray.length; j++) {
                        if (adviceArray[j] == obsoleteAdvice) {
                            adviceArray[j] = 0;
                            break;
                        }
                    }
                }

            }
        }

    }


    public int[] getAdviceArray() {
        return adviceArray;
    }

    @Override
    public int getProfitCounter() {
        return profitCounter;
    }

    private void profitCounter(boolean[] hitArray) {

        for (int i = 0; i < 2; i++) {
            if (adviceArray[i] > 0 && !hitArray[i]) {
                profitCounter -= adviceArray[i];
            } else if (adviceArray[i] > 0 && winLossCountArray[i][1] >= getDelay() - 1 && hitArray[i]) {
                profitCounter = ++maxProfit;
                break;
            } else {
                if (adviceArray[i] > 0 && !(winLossCountArray[i][1] >= getDelay() - 1 && !hitArray[i])) {

                    profitCounter = ++maxProfit;
                    break;
                }
            }
        }
    }

    public Map<RouletteOutcome, BigDecimal> getOneToOneAdviceMap(RouletteOutcome firstOutcome, RouletteOutcome secondOutcome, BigDecimal chipValue) {
        var map = new HashMap<RouletteOutcome, BigDecimal>();
        map.put(firstOutcome, chipValue.multiply(new BigDecimal(getAdviceArray()[0])));
        map.put(secondOutcome, chipValue.multiply(new BigDecimal(getAdviceArray()[1])));
        return map;
    }


}
