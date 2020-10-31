package nl.kooi.app.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.kooi.app.api.dto.Mapper;
import nl.kooi.app.api.dto.SessionDto;
import nl.kooi.app.domain.service.SessionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitConfig(SessionController.class)
class SessionControllerTest {

    @Autowired
    private SessionController sessionController;

    @MockBean
    private SessionService sessionService;

    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    public void initTestDependencies() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(sessionController).build();
    }

    @Test
    void createWithUnknownUser() throws Exception {
        var session = new SessionDto();
        session.setChipValue(BigDecimal.TEN);
        session.setUserId(1234);

        var mvcResult = mockMvc.perform(post("/users/1234/sessions/")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(session)))
                .andExpect(status().isNotFound());
    }

    @Test
    void createWithKnownUser() throws Exception {
        var session = new SessionDto();
        session.setChipValue(BigDecimal.TEN);
        session.setUserId(1234);

        when(sessionService.findByUserId(1234)).thenReturn(List.of(Mapper.map(session)));
        when(sessionService.save(any())).thenReturn(Mapper.map(session));

        var mvcResult = mockMvc.perform(post("/users/1234/sessions/")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(session)))
                .andExpect(status().isOk());
    }

    @Test
    void findById() {
    }

    @Test
    void delete() {
    }
}