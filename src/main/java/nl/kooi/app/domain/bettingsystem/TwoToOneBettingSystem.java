/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.kooi.app.domain.bettingsystem;

/**
 * @author Laurens van der Kooi
 */

public class TwoToOneBettingSystem extends BettingSystem {

    private boolean[][] outcomeArray;
    private int[] adviceArray;
    private int winLossCountArray[][];
    private int profitCounter;

    public TwoToOneBettingSystem(int bettingFactor, int delay, char system) {
        super(bettingFactor, delay, system);
        outcomeArray = new boolean[3][delay];
        adviceArray = new int[3];

        for (int i = 0; i < 3; i++) {
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
        for (int i = 0; i < 3; i++) {
            outcomeArray[i][getRounds() - 1] = hitArray[i];
        }
    }

    private void setWinLossCountArray(boolean[] hitArray) {
        winLossCountArray = new int[3][2];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < getDelay(); j++) {
                if (outcomeArray[i][j]) {
                    winLossCountArray[i][0]++; //win counts are stored in first slot of subarray
                } else {
                    winLossCountArray[i][1]++; //loss counts are stored in second slot of subarray
                }
            }
        }
    }

    public void compoundDefferedMartingGale(boolean[] hitArray) {
        updateOutcomeArray(hitArray);
        setRounds();
        setWinLossCountArray(hitArray);


        if (getTotalRounds() > getDelay()) {

            // calculate profitCounter
            profitCounter(hitArray);

            //loop through results and determine advice
            for (int i = 0; i < 3; i++) {

                // situation 1: a bet was made but it didn't hit
                if (adviceArray[i] > 0 && !hitArray[i]) {

                    adviceArray[i] *= super.getBettingFactor();


                    // situation 2: a bet was made, the bet was won but conditions still meet: time to bet again.
                } else if (winLossCountArray[i][1] >= getDelay() - 1 && hitArray[i]) {


                    adviceArray[i] = 1;


                    for (int j = 0; j < 3; j++) {
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

                    // situation 3: the number of losses matches the strategy, time to bet
                } else if (winLossCountArray[i][1] >= getDelay() - 1 && !hitArray[i]) {

                    for (int j = 0; j < 3; j++) {
                        if (i == j) {
                            continue;
                        }

                        //check whether there are other fields eligible for a bet
                        if (winLossCountArray[j][1] >= getDelay() - 1) {
                            adviceArray[i] = Math.max(1, adviceArray[i] *= super.getBettingFactor());
                            break;

                            //if not, not bets advices
                        } else {
                            adviceArray[i] = 0;

                        }

                    }

                    // situation 4: the number of losses doesn't match the conditions, no bets adviced.
                } else {

                    //if a bet was adviced (meaning that the bet has now won, and the field isn't eligable for a new bet)...
                    if (adviceArray[i] > 0) {

                        //...find the other bet of last round.
                        for (int j = 0; j < 3; j++) {
                            if (i == j) {
                                continue;
                            }

                            // when found...
                            if (adviceArray[j] > 0) {
                                for (int k = 0; k < 3; k++) {
                                    if (j == k || i == k) {
                                        continue;
                                    }
                                    // ...check if the field that wasn't adviced in the last round is now eligable for a bet
                                    if (winLossCountArray[k][1] >= getDelay() - 1 && i > j) {
                                        // if so, that fields will be set to 1 by the algorithm of situation 3 meaning that the other bet of last round also needs to be set to 1.
                                        adviceArray[j] = 1;

                                    } else {
                                        adviceArray[j] = 0;
                                    }
                                }
                            }
                        }

                    }
                    adviceArray[i] = 0;
                }
            }

            // final check: if there is an advice for all three fields (which can occur in the rare event of a zero streak), override the lowest advice
            if (adviceArray[0] > 0 && adviceArray[1] > 0 && adviceArray[2] > 0) {
                int obsoleteAdvice = Math.min(Math.min(adviceArray[0], adviceArray[1]), adviceArray[2]);
                for (int j = 0; j < adviceArray.length; j++) {
                    if (adviceArray[j] == obsoleteAdvice) {
                        adviceArray[j] = 0;
                        break;
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

    //    helper method to set profitCounter
    private void profitCounter(boolean[] hitArray) {
        int maxProfit = 0;

        for (int i = 0; i < 3; i++) {
            if (winLossCountArray[i][1] >= getDelay() - 1 && hitArray[i]) {
                profitCounter = ++maxProfit;
                break;
            } else if (adviceArray[i] > 0 && !hitArray[i]) {
                profitCounter -= adviceArray[i];
            } else {
                if (adviceArray[i] > 0) {

                    profitCounter = ++maxProfit;
                    break;
                }
            }
        }
    }
}

