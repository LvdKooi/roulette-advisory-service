package nl.kooi.app.api.dto;

import nl.kooi.app.api.dto.advises.*;
import nl.kooi.app.domain.advises.*;
import org.modelmapper.ModelMapper;

public class Mapper {


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

}
