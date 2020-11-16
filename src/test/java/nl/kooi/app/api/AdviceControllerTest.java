package nl.kooi.app.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.kooi.app.api.dto.AdviceDto;
import nl.kooi.app.domain.advice.Advice;
import nl.kooi.app.domain.rouletteoutcome.RouletteOutcomeUtilities;
import nl.kooi.app.domain.service.OutcomeAdviceService;
import nl.kooi.app.domain.service.SessionService;
import nl.kooi.app.domain.session.Session;
import nl.kooi.app.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitConfig(AdviceController.class)
class AdviceControllerTest {

    @Autowired
    private AdviceController adviceController;

    @MockBean
    private SessionService sessionService;

    @MockBean
    private OutcomeAdviceService outcomeAdviceService;

    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    public void initTestDependencies() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(adviceController).build();
    }

    @Test
    void getAdvice() throws Exception {
        var advice = getTestAdvice();
        when(sessionService.findByIdAndUserId(1, 1234)).thenReturn(getTestSession());
        when(outcomeAdviceService.findLastAdvice(1)).thenReturn(advice);

        var mvcResult = mockMvc.perform(get("/users/1234/sessions/1/advices/last-advice"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        var response = objectMapper.readValue(mvcResult.getContentAsString(), AdviceDto.class);

        assertThat(response.getAdviceMap()).isEqualTo(advice.getAdviceMap());
        assertThat(response.getId()).isEqualTo(18);
    }

    @Test
    void getAdviceOfNonExistingUser() throws Exception {
        when(sessionService.findByIdAndUserId(1, 1234)).thenThrow(new NotFoundException("Session not found"));

        mockMvc.perform(get("/users/1234/sessions/1/advices/last-advice"))
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

    private Advice getTestAdvice() {
        var adviceMap = new TreeMap<>(RouletteOutcomeUtilities.getCompoundRouletteOutcome(12)
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, x -> BigDecimal.valueOf(Math.random() * 100))));

        return new Advice(18, adviceMap);
    }
}