package nl.kooi.app.domain.advises;

import nl.kooi.app.api.dto.Mapper;
import nl.kooi.app.api.dto.advises.FullAdviceDto;
import nl.kooi.app.domain.game.Game;
import nl.kooi.app.domain.rouletteoutcome.CompoundRouletteOutcome;
import nl.kooi.infrastructure.entity.Outcome;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FullAdvice {

    private String chipValue;
    private Collection<Outcome> outcomeList;
    private List<Game> gameArray;
    private BigDecimal totalProfit = new BigDecimal(0).setScale(2);

    public FullAdvice(String chipValue, Collection<Outcome> outcomeList) {
        this.chipValue = chipValue;
        this.outcomeList = outcomeList;

        gameArray = new ArrayList<>();
        gameArray.add(new OddEvenAdvice(chipValue, 8));
        gameArray.add(new RedBlackAdvice(chipValue, 8));
        gameArray.add(new HalfAdvice(chipValue, 8));
        gameArray.add(new DozenAdvice(chipValue));
        gameArray.add(new ColumnAdvice(chipValue));

        for (Outcome singleOutcome : outcomeList) {
                gameArray.forEach(g -> g.setHits(new CompoundRouletteOutcome(singleOutcome.getOutcome())));
            }
        }


    private static FullAdviceDto toDtoHelper(Game game, FullAdviceDto representation) {
        if (game instanceof DozenAdvice)
            representation.dozenAdvice = Mapper.map((DozenAdvice) game);
        if (game instanceof ColumnAdvice)
            representation.columnAdvice = Mapper.map((ColumnAdvice) game);
        if (game instanceof RedBlackAdvice)
            representation.redBlackAdvice = Mapper.map((RedBlackAdvice) game);
        if (game instanceof OddEvenAdvice)
            representation.oddEvenAdvice = Mapper.map((OddEvenAdvice) game);
        if (game instanceof HalfAdvice)
            representation.halfAdvice = Mapper.map((HalfAdvice) game);
        return representation;
    }

    public FullAdviceDto toDto() {

        FullAdviceDto representation = new FullAdviceDto();
        for (Game game : gameArray) {
            representation = toDtoHelper(game, representation);
        }
        return representation;

    }

    public BigDecimal getTotalProfit() {
        gameArray.forEach(g -> totalProfit = totalProfit.add(g.getProfit()));

        return totalProfit;
    }


}
