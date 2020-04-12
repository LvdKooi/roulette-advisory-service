package nl.kooi.app.api.dto.advises;

import javax.validation.constraints.NotNull;

public class FullAdviceDto extends AdviceDto {
    @NotNull
    public DozenAdviceDto dozenAdvice;

    @NotNull
    public ColumnAdviceDto columnAdvice;

    @NotNull
    public HalfAdviceDto halfAdvice;

    @NotNull
    public RedBlackAdviceDto redBlackAdvice;

    @NotNull
    public OddEvenAdviceDto oddEvenAdvice;
}
