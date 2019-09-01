package nl.kooi.representation.advises;

import javax.validation.constraints.NotNull;

public class FullAdviceRepresentationV1 extends AdviceRepresentation {
    @NotNull
    public DozenAdviceRepresentation dozenAdvice;

    @NotNull
    public ColumnAdviceRepresentation columnAdvice;

    @NotNull
    public HalfAdviceRepresentation halfAdvice;

    @NotNull
    public RedBlackAdviceRepresentation redBlackAdvice;

    @NotNull
    public OddEvenAdviceRepresentation oddEvenAdvice;
}
