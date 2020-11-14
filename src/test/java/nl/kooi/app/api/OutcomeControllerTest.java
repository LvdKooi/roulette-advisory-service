package nl.kooi.app.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.kooi.app.api.dto.OutcomeDto;
import nl.kooi.app.domain.outcome.Outcome;
import nl.kooi.app.domain.service.OutcomeAdviceService;
import nl.kooi.app.domain.service.SessionService;
import nl.kooi.app.domain.session.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitConfig(OutcomeController.class)
class OutcomeControllerTest {

    @Autowired
    private OutcomeController outcomeController;

    @MockBean
    private OutcomeAdviceService outcomeAdviceService;

    @MockBean
    private SessionService sessionService;


    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    public void initTestDependencies() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(outcomeController).build();
    }


    @Test
    void findAllBySessionId() throws Exception {
        when(sessionService.findByIdAndUserId(1, 1234)).thenReturn(getTestSession());
        when(outcomeAdviceService.findOutcomesBySessionIdOrderByIdAsc(1)).thenReturn(getTestOutcomes());

        var mvcResult = mockMvc.perform(get("/users/1234/sessions/1/outcomes"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        var response = objectMapper.readValue(mvcResult.getContentAsString(), List.class);
        assertThat(response.size()).isEqualTo(2);
    }

    @Test
    void create() {

    }

    @Test
    void findLastOutcome() throws Exception {
        when(sessionService.findByIdAndUserId(1, 1234)).thenReturn(getTestSession());
        when(outcomeAdviceService.findLastOutcome(1)).thenReturn(getTestOutcomes().get(0));

        var mvcResult = mockMvc.perform(get("/users/1234/sessions/1/outcomes/last-outcome"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
        var response = objectMapper.readValue(mvcResult.getContentAsString(), OutcomeDto.class);

        assertThat(response.getOutcome()).isEqualTo(30);
    }

    @Test
    void deleteLastOutcome() {
    }

    private Session getTestSession() {
        var testSession = new Session();
        testSession.setId(1);
        testSession.setUserId(1234);
        testSession.setChipValue(BigDecimal.TEN);
        testSession.setDateTime(Instant.now());

        return testSession;
    }

    private List<Outcome> getTestOutcomes() {
        var outcome1 = new Outcome(1, 30, BigDecimal.TEN);
        var outcome2 = new Outcome(1, 12, BigDecimal.TEN);

        return List.of(outcome1, outcome2);
    }
}