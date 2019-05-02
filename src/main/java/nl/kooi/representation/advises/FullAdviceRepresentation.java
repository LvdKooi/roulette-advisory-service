package nl.kooi.representation.advises;

import javax.validation.constraints.NotNull;

public class FullAdviceRepresentation extends AdviceRepresentation {
    @NotNull
    DozenAdviceRepresentation dozenAdvice;

    @NotNull
    RowAdviceRepresentation rowAdvice;

    @NotNull
    HalfAdviceRepresentation halfAdvice;

    @NotNull
    RedBlackAdviceRepresentation redBlackAdvice;

    @NotNull
    OddEvenAdviceRepresentation oddEvenAdvice;
}
