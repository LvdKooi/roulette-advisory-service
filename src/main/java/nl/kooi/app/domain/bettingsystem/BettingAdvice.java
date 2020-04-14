package nl.kooi.app.domain.bettingsystem;

import lombok.var;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcome;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public interface BettingAdvice {

    OneToOneBettingSystem oneToOneBettingSystem = new OneToOneBettingSystem(2, 8);
    TwoToOneBettingSystem twoToOneBettingSystem = new TwoToOneBettingSystem(3, 4);

    Map<RouletteOutcome, BigDecimal> getAdvice(boolean[] hitArray);

    BigDecimal getProfit();

    static Map<RouletteOutcome, BigDecimal> getTwoToOneAdviceMap(RouletteOutcome firstOutcome, RouletteOutcome secondOutcome, RouletteOutcome thirdOutcome, BigDecimal chipValue) {
        var map = new HashMap<RouletteOutcome, BigDecimal>();
        map.put(firstOutcome, chipValue.multiply(new BigDecimal(BettingAdvice.twoToOneBettingSystem.getAdviceArray()[0])));
        map.put(secondOutcome, chipValue.multiply(new BigDecimal(BettingAdvice.twoToOneBettingSystem.getAdviceArray()[1])));
        map.put(thirdOutcome, chipValue.multiply(new BigDecimal(BettingAdvice.twoToOneBettingSystem.getAdviceArray()[2])));
        return map;
    }

    static Map<RouletteOutcome, BigDecimal> getOneToOneAdviceMap(RouletteOutcome firstOutcome, RouletteOutcome secondOutcome, BigDecimal chipValue) {
        var map = new HashMap<RouletteOutcome, BigDecimal>();
        map.put(firstOutcome, chipValue.multiply(new BigDecimal(BettingAdvice.oneToOneBettingSystem.getAdviceArray()[0])));
        map.put(secondOutcome, chipValue.multiply(new BigDecimal(BettingAdvice.oneToOneBettingSystem.getAdviceArray()[1])));
        return map;
    }

}
