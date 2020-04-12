package nl.kooi.app.api.dto;

import nl.kooi.app.api.dto.advises.*;
import nl.kooi.app.domain.advises.*;
import nl.kooi.app.domain.game.Game;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;

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

}
