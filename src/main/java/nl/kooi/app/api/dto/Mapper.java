package nl.kooi.app.api.dto;

import nl.kooi.app.api.dto.advises.FullAdviseDto;
import nl.kooi.app.api.dto.metrics.SessionMetricsDto;
import nl.kooi.app.domain.advises.FullAdvise;
import nl.kooi.app.domain.metrics.SessionMetrics;
import org.modelmapper.ModelMapper;

public class Mapper {


    private static ModelMapper modelMapper = new ModelMapper();

    public static FullAdviseDto map(FullAdvise advice) {
        return modelMapper.map(advice, FullAdviseDto.class);
    }

    public static SessionMetricsDto map(SessionMetrics sessionMetrics) {
        return modelMapper.map(sessionMetrics, SessionMetricsDto.class);
    }
}
