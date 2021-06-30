package nl.kooi.app.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.kooi.app.api.dto.Mapper;
import nl.kooi.app.api.dto.OutcomeDto;
import nl.kooi.app.domain.outcome.Outcome;
import nl.kooi.app.domain.service.OutcomeAdviceService;
import nl.kooi.app.domain.service.SessionService;
import nl.kooi.app.domain.session.Session;
import nl.kooi.app.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
        mockMvc = MockMvcBuilders.standaloneSetup(outcomeController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @Test
    void findAllBySessionId() throws Exception {
        when(sessionService.findByIdAndUserId(1, 1234)).thenReturn(getTestSession());
        when(outcomeAdviceService.findOutcomesBySessionId(anyInt(), any(Pageable.class))).thenReturn(new PageImpl<>(getTestOutcomes()));

        mockMvc.perform(get("/users/1234/sessions/1/outcomes"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$['content']", hasSize(2)))
                .andExpect(jsonPath("$['content'][0].outcome", is(30)))
                .andExpect(jsonPath("$['content'][1].outcome", is(12)));
    }

    @Test
    void create() throws Exception {
        when(sessionService.findByIdAndUserId(1, 1234)).thenReturn(getTestSession());

        when(outcomeAdviceService.saveOutcomeAndAdvise(1234, 1, 30))
                .thenReturn(getTestOutcomes().get(0));

        var mvcResult = mockMvc.perform(post("/users/1234/sessions/1/outcomes")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(Mapper.map(getTestOutcomes().get(0)))))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        var response = objectMapper.readValue(mvcResult.getContentAsString(), OutcomeDto.class);

        assertThat(response.getOutcome()).isEqualTo(30);
    }

    @Test
    void createForUnknownSession() throws Exception {
        when(sessionService.findByIdAndUserId(1, 1234)).thenThrow(new NotFoundException("Unknown"));

        mockMvc.perform(post("/users/1234/sessions/1/outcomes")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(Mapper.map(getTestOutcomes().get(0)))))
                .andExpect(status().isNotFound());
    }

    @Test
    void findLastOutcome() throws Exception {
        when(sessionService.findByIdAndUserId(1, 1234)).thenReturn(getTestSession());
        when(outcomeAdviceService.findLastOutcome(1)).thenReturn(getTestOutcomes().get(0));

        var mvcResult = mockMvc.perform(get("/users/1234/sessions/1/outcomes/last-outcome"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        var response = objectMapper.readValue(mvcResult.getContentAsString(), OutcomeDto.class);

        assertThat(response.getOutcome()).isEqualTo(30);
    }

    @Test
    void findLastOutcomeForUnknownSession() throws Exception {
        when(sessionService.findByIdAndUserId(1, 1234)).thenThrow(new NotFoundException("Unknown"));

        mockMvc.perform(get("/users/1234/sessions/1/outcomes/last-outcome"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteLastOutcome() throws Exception {
        when(sessionService.findByIdAndUserId(1, 1234)).thenReturn(getTestSession());
        doNothing().when(outcomeAdviceService).deleteLastOutcome(1);
        mockMvc.perform(delete("/users/1234/sessions/1/outcomes/last-outcome"))
                .andExpect(status().isOk());

        verify(outcomeAdviceService, times(1)).deleteLastOutcome(1);
    }

    @Test
    void deleteLastOutcomeForUnknownSession() throws Exception {
        when(sessionService.findByIdAndUserId(1, 1234)).thenThrow(new NotFoundException("Unknown"));
        mockMvc.perform(delete("/users/1234/sessions/1/outcomes/last-outcome"))
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

    private List<Outcome> getTestOutcomes() {
        var outcome1 = new Outcome(1, 30, BigDecimal.TEN);
        var outcome2 = new Outcome(1, 12, BigDecimal.TEN);

        return List.of(outcome1, outcome2);
    }
}