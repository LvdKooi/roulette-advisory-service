package nl.kooi.app.api.dto;

import nl.kooi.app.domain.advice.Advice;
import nl.kooi.app.domain.metric.SessionMetrics;
import nl.kooi.app.domain.outcome.Outcome;
import nl.kooi.app.domain.session.Session;
import nl.kooi.app.api.dto.AdviceDto;
import org.modelmapper.ModelMapper;

public class Mapper {

    private static ModelMapper modelMapper = new ModelMapper();

    public static AdviceDto map(Advice advice) {
        return modelMapper.map(advice, AdviceDto.class);
    }

    public static SessionMetricsDto map(SessionMetrics sessionMetrics) {
        return modelMapper.map(sessionMetrics, SessionMetricsDto.class);
    }

    public static OutcomeDto map(Outcome outcome) {
        return modelMapper.map(outcome, OutcomeDto.class);
    }

    public static SessionDto map(Session session) {
        return modelMapper.map(session, SessionDto.class);
    }

    public static Session map(SessionDto sessionDto) {
        return modelMapper.map(sessionDto, Session.class);
    }


}
