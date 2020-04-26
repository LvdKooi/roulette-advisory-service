package nl.kooi.app.domain;

import lombok.var;
import nl.kooi.app.domain.advises.Advise;
import nl.kooi.app.domain.outcome.Outcome;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcome;
import nl.kooi.app.domain.session.Session;
import nl.kooi.infrastructure.entity.AdviseEntity;
import nl.kooi.infrastructure.entity.OutcomeEntity;
import nl.kooi.infrastructure.entity.SessionEntity;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;

import java.math.BigDecimal;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import static nl.kooi.app.domain.rouletteoutcome.RouletteOutcome.*;

public class Mapper {
    private static ModelMapper modelMapper = new ModelMapper();
    private static Converter<Advise, AdviseEntity> adviseEntityConverter = new Converter<Advise, AdviseEntity>() {

        @Override
        public AdviseEntity convert(MappingContext<Advise, AdviseEntity> mappingContext) {
            var adviseEntity = new AdviseEntity();
            mappingContext
                    .getSource()
                    .getAdviceMap()
                    .entrySet()
                    .forEach(entry -> fullAdviseToAdviseEntityHelper(adviseEntity, entry));
            return adviseEntity;
        }
    };
    private static Converter<AdviseEntity, Advise> fullAdviseConverter = new Converter<AdviseEntity, Advise>() {

        @Override
        public Advise convert(MappingContext<AdviseEntity, Advise> mappingContext) {
            NavigableMap<RouletteOutcome, BigDecimal> adviseMap = new TreeMap<>();
            adviseEntityToFullAdviseHelper(adviseMap, mappingContext.getSource());
            return new Advise(adviseMap);
        }
    };

    public static void fullAdviseToAdviseEntityHelper(AdviseEntity entity, Map.Entry<RouletteOutcome, BigDecimal> entry) {

        switch (entry.getKey()) {
            case ZERO:
                break;
            case ODD:
                entity.setOddAdvice(entry.getValue());
                break;
            case EVEN:
                entity.setEvenAdvice(entry.getValue());
                break;
            case RED:
                entity.setRedAdvice(entry.getValue());
                break;
            case BLACK:
                entity.setBlackAdvice(entry.getValue());
                break;
            case FIRST_DOZEN:
                entity.setFirstDozenAdvice(entry.getValue());
                break;
            case SECOND_DOZEN:
                entity.setSecondDozenAdvice(entry.getValue());
                break;
            case THIRD_DOZEN:
                entity.setThirdDozenAdvice(entry.getValue());
                break;
            case FIRST_HALF:
                entity.setFirstHalfAdvice(entry.getValue());
                break;
            case SECOND_HALF:
                entity.setSecondHalfAdvice(entry.getValue());
                break;
            case FIRST_COLUMN:
                entity.setFirstColumnAdvice(entry.getValue());
                break;
            case SECOND_COLUMN:
                entity.setSecondColumnAdvice(entry.getValue());
                break;
            case THIRD_COLUMN:
                entity.setThirdColumnAdvice(entry.getValue());
                break;
        }
    }

    public static void adviseEntityToFullAdviseHelper(Map<RouletteOutcome, BigDecimal> adviseMap, AdviseEntity entity) {
        adviseMap.put(BLACK, entity.getBlackAdvice());
        adviseMap.put(RED, entity.getRedAdvice());
        adviseMap.put(ODD, entity.getOddAdvice());
        adviseMap.put(EVEN, entity.getEvenAdvice());
        adviseMap.put(FIRST_HALF, entity.getFirstHalfAdvice());
        adviseMap.put(SECOND_HALF, entity.getSecondHalfAdvice());
        adviseMap.put(FIRST_COLUMN, entity.getFirstColumnAdvice());
        adviseMap.put(SECOND_COLUMN, entity.getSecondColumnAdvice());
        adviseMap.put(THIRD_COLUMN, entity.getThirdColumnAdvice());
        adviseMap.put(FIRST_DOZEN, entity.getFirstDozenAdvice());
        adviseMap.put(SECOND_DOZEN, entity.getSecondDozenAdvice());
        adviseMap.put(THIRD_DOZEN, entity.getThirdDozenAdvice());
    }


    public static OutcomeEntity map(Outcome outcome) {
        return modelMapper.map(outcome, OutcomeEntity.class);
    }

    public static Outcome map(OutcomeEntity outcomeEntity) {
        return modelMapper.map(outcomeEntity, Outcome.class);
    }

    public static AdviseEntity map(Advise advise) {

        modelMapper.addConverter(adviseEntityConverter);
        return modelMapper.map(advise, AdviseEntity.class);
    }

    public static Advise map(AdviseEntity adviseEntity) {
        modelMapper.addConverter(fullAdviseConverter);
        return modelMapper.map(adviseEntity, Advise.class);
    }

    public static Session map(SessionEntity sessionEntity) {
        return modelMapper.map(sessionEntity, Session.class);
    }

    public static SessionEntity map(Session session) {
        return modelMapper.map(session, SessionEntity.class);
    }
}
