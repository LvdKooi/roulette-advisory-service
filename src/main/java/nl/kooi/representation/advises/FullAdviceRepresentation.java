package nl.kooi.representation.advises;

import javax.validation.constraints.NotNull;

public class FullAdviceRepresentation extends AdviceRepresentation {
    @NotNull
    public DozenAdviceRepresentation dozenAdvice;

    @NotNull
    public RowAdviceRepresentation rowAdvice;

    @NotNull
    public HalfAdviceRepresentation halfAdvice;

    @NotNull
    public RedBlackAdviceRepresentation redBlackAdvice;

    @NotNull
    public OddEvenAdviceRepresentation oddEvenAdvice;
}
