package nl.kooi.app.api.dto.advises;

import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
public class FullAdviceDto {
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
