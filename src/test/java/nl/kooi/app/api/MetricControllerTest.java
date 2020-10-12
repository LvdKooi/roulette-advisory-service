package nl.kooi.app.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.kooi.app.api.dto.SessionMetricsDto;
import nl.kooi.app.domain.metric.SessionMetrics;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcomeUtilities;
import nl.kooi.app.domain.service.OutcomeAdviceService;
import nl.kooi.app.domain.service.SessionService;
import nl.kooi.app.domain.session.Session;
import nl.kooi.app.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitConfig(MetricController.class)
class MetricControllerTest {

    @Autowired
    private MetricController metricController;

    @MockBean
    private SessionService sessionService;

    @MockBean
    private OutcomeAdviceService outcomeAdviceService;

    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    public void initTestDependencies() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(metricController).build();
    }

    @Test
    void getMetrics() throws Exception {
        var metrics = getTestMetrics();
        when(sessionService.findByIdAndUserId(1, 1234)).thenReturn(getTestSession());
        when(outcomeAdviceService.getSessionsMetrics(1)).thenReturn(metrics);

        var mvcResult = mockMvc.perform(get("/users/1234/sessions/1/metrics"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        var response = objectMapper.readValue(mvcResult.getContentAsString(), SessionMetricsDto.class);

        assertThat(response.getOutcomePercentageMap()).isEqualTo(metrics.getOutcomePercentageMap());
        assertThat(response.getCurrentProfit()).isEqualTo(metrics.getCurrentProfit());
        assertThat(response.getLeastProfit()).isEqualTo(metrics.getLeastProfit());
        assertThat(response.getTopProfit()).isEqualTo(metrics.getTopProfit());
        assertThat(response.getTotalRounds()).isEqualTo(metrics.getTotalRounds());
    }

    @Test
    void getAdviceOfNonExistingUser() throws Exception {
        when(sessionService.findByIdAndUserId(1, 1234)).thenThrow(new NotFoundException("Session not found"));

        mockMvc.perform(get("/users/1234/sessions/1/metrics"))
                .andExpect(status().isNotFound());
    }

    private Session getTestSession() {
        var testSession = new Session();
        testSession.setId(1);
        testSession.setUserId(1234);
        testSession.setChipValue(BigDecimal.TEN);
        testSession.setDateTime(Instant.now());

        return testSession;
    }

    private SessionMetrics getTestMetrics() {
        var outcomeMap = RouletteOutcomeUtilities.getCompoundRouletteOutcome(12)
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, x -> 10L));


        return new SessionMetrics(outcomeMap,
                10L,
                BigDecimal.TEN,
                BigDecimal.ZERO,
                BigDecimal.TEN);
    }
}