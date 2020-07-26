package nl.kooi.app.api.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class OutcomeDto {
    private int id;

    @NotNull(message = "Outcome can't be null.")
    @Min(value = 0, message = "The smallest number in roulette is 0.")
    @Max(value = 36, message = "The highest number in roulette is 36.")
    private Integer outcome;
}

