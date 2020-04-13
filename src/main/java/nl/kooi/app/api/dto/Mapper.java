package nl.kooi.app.api.dto;

import nl.kooi.app.api.dto.advises.*;
import nl.kooi.app.api.dto.metrics.SessionMetricsDto;
import nl.kooi.app.domain.advises.*;
import nl.kooi.app.domain.game.Game;
import nl.kooi.app.domain.metrics.SessionMetrics;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Comparator;

import static java.math.RoundingMode.HALF_UP;

public class Mapper {
    private static Converter<FullAdvice, FullAdviceDto> fullAdviceConverter = new Converter<FullAdvice, FullAdviceDto>() {
        @Override
        public FullAdviceDto convert(MappingContext<FullAdvice, FullAdviceDto> mappingContext) {

            FullAdviceDto dto = new FullAdviceDto();

            for (Game game : mappingContext.getSource().getGameArray()) {
                if (game instanceof DozenAdvice)
                    dto.dozenAdvice = Mapper.map((DozenAdvice) game);
                if (game instanceof ColumnAdvice)
                    dto.columnAdvice = Mapper.map((ColumnAdvice) game);
                if (game instanceof RedBlackAdvice)
                    dto.redBlackAdvice = Mapper.map((RedBlackAdvice) game);
                if (game instanceof OddEvenAdvice)
                    dto.oddEvenAdvice = Mapper.map((OddEvenAdvice) game);
                if (game instanceof HalfAdvice)
                    dto.halfAdvice = Mapper.map((HalfAdvice) game);
            }
            return dto;
        }
    };

    private static Converter<SessionMetrics, SessionMetricsDto> sessionMetricsConverter = new Converter<SessionMetrics, SessionMetricsDto>() {
        @Override
        public SessionMetricsDto convert(MappingContext<SessionMetrics, SessionMetricsDto> mappingContext) {

            SessionMetricsDto destination = new SessionMetricsDto();
            SessionMetrics source = mappingContext.getSource();
            long totalRounds = source.getTotalNumberOfRounds();

            destination.totalNumberOfRounds = totalRounds;
            destination.redBlackMetrics.percentageBlack = roundsToPercentage(source.getTotalBlack(), totalRounds);
            destination.redBlackMetrics.percentageRed = roundsToPercentage(source.getTotalRed(), totalRounds);
            destination.oddEvenMetrics.percentageOdd = roundsToPercentage(source.getTotalOdd(), totalRounds);
            destination.oddEvenMetrics.percentageEven = roundsToPercentage(source.getTotalEven(), totalRounds);
            destination.halfMetrics.percentageFirstHalf = roundsToPercentage(source.getTotalFirstHalf(), totalRounds);
            destination.halfMetrics.percentageSecondHalf = roundsToPercentage(source.getTotalSecondHalf(), totalRounds);
            destination.dozenMetrics.percentageFirstDozen = roundsToPercentage(source.getTotalFirstDozen(), totalRounds);
            destination.dozenMetrics.percentageSecondDozen = roundsToPercentage(source.getTotalSecondDozen(), totalRounds);
            destination.dozenMetrics.percentageThirdDozen = roundsToPercentage(source.getTotalThirdDozen(), totalRounds);
            destination.columnMetrics.percentageFirstColumn = roundsToPercentage(source.getTotalFirstColumn(), totalRounds);
            destination.columnMetrics.percentageSecondColumn = roundsToPercentage(source.getTotalSecondColumn(), totalRounds);
            destination.columnMetrics.percentageThirdColumn = roundsToPercentage(source.getTotalThirdColumn(), totalRounds);
            destination.percentageZero = roundsToPercentage(source.getTotalZero(), totalRounds);
            destination.currentProfit = new BigDecimal(source.getOutcomeEntities().stream().filter(o -> o.getId() == source.getOutcomeId()).findFirst().get().getTotalProfit());
            destination.leastProfit = BigDecimal.valueOf(source.getProfits().stream().min(Comparator.naturalOrder()).orElse((double) 0));
            destination.topProfit = BigDecimal.valueOf(source.getProfits().stream().max(Comparator.naturalOrder()).orElse((double) 0));
            return destination;
        }

        private BigDecimal roundsToPercentage(long numberOfHits, long numberOfRounds) {
            MathContext mc = new MathContext(10, RoundingMode.HALF_UP);
            return new BigDecimal(numberOfHits).divide(new BigDecimal(numberOfRounds), mc).multiply(new BigDecimal(100), mc).setScale(3, HALF_UP);
        }
    };

    private static ModelMapper modelMapper = new ModelMapper();

    public static ColumnAdviceDto map(ColumnAdvice advice) {
        return modelMapper.map(advice, ColumnAdviceDto.class);
    }

    public static DozenAdviceDto map(DozenAdvice advice) {
        return modelMapper.map(advice, DozenAdviceDto.class);
    }

    public static HalfAdviceDto map(HalfAdvice advice) {
        return modelMapper.map(advice, HalfAdviceDto.class);
    }

    public static OddEvenAdviceDto map(OddEvenAdvice advice) {
        return modelMapper.map(advice, OddEvenAdviceDto.class);
    }

    public static RedBlackAdviceDto map(RedBlackAdvice advice) {
        return modelMapper.map(advice, RedBlackAdviceDto.class);
    }

    public static FullAdviceDto map(FullAdvice advice) {
        modelMapper.addConverter(fullAdviceConverter);
        return modelMapper.map(advice, FullAdviceDto.class);
    }

    public static SessionMetricsDto map(SessionMetrics sessionMetrics) {
        modelMapper.addConverter(sessionMetricsConverter);
        return modelMapper.map(sessionMetrics, SessionMetricsDto.class);
    }
}
