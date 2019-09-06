package nl.kooi.representation.advises;

import nl.kooi.representation.advises.rouletteonetoone.HalfAdviceV1;
import nl.kooi.representation.advises.rouletteonetoone.OddEvenAdviceV1;
import nl.kooi.representation.advises.rouletteonetoone.RedBlackAdviceV1;
import nl.kooi.representation.advises.roulettetwotoone.ColumnAdviceV1;
import nl.kooi.representation.advises.roulettetwotoone.DozenAdviceV1;

import javax.validation.constraints.NotNull;

public class FullAdviceV1 extends AdviceRepresentation {
    @NotNull
    public DozenAdviceV1 dozenAdvice;

    @NotNull
    public ColumnAdviceV1 columnAdvice;

    @NotNull
    public HalfAdviceV1 halfAdvice;

    @NotNull
    public RedBlackAdviceV1 redBlackAdvice;

    @NotNull
    public OddEvenAdviceV1 oddEvenAdvice;
}
