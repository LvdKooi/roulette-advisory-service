package nl.kooi.app.domain;

import nl.kooi.app.domain.outcome.Outcome;
import nl.kooi.infrastructure.entity.OutcomeEntity;
import org.modelmapper.ModelMapper;

public class Mapper {
    private static ModelMapper modelMapper = new ModelMapper();

    public static OutcomeEntity map(Outcome outcome) {
        return modelMapper.map(outcome, OutcomeEntity.class);
    }

    public static Outcome map(OutcomeEntity outcomeEntity) {
        return modelMapper.map(outcomeEntity, Outcome.class);
    }

}
