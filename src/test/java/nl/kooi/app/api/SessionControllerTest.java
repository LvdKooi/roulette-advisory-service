package nl.kooi.app.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.kooi.app.api.dto.Mapper;
import nl.kooi.app.api.dto.SessionDto;
import nl.kooi.app.domain.service.SessionService;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
    void createWithKnownUser() throws Exception {

        when(sessionService.findByUserId(1234)).thenReturn(List.of(Mapper.map(getSessionDto())));
        when(sessionService.save(any())).thenReturn(Mapper.map(getSessionDto()));

        var mvcResult = mockMvc.perform(post("/users/1234/sessions/")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(getSessionDto())))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        var response = objectMapper.readValue(mvcResult.getContentAsString(), SessionDto.class);

        assertThat(response.getChipValue()).isEqualTo(getSessionDto().getChipValue());
        assertThat(response.getUserId()).isEqualTo(getSessionDto().getUserId());
    }

    @Test
    void findById() throws Exception {
        when(sessionService.findByIdAndUserId(1234, 1234)).thenReturn(Mapper.map(getSessionDto()));

        var mvcResult = mockMvc.perform(get("/users/1234/sessions/1234"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        var response = objectMapper.readValue(mvcResult.getContentAsString(), SessionDto.class);

        assertThat(response.getChipValue()).isEqualTo(getSessionDto().getChipValue());
        assertThat(response.getUserId()).isEqualTo(getSessionDto().getUserId());
    }

    @Test
    void findByUnknownId() throws Exception {
        when(sessionService.findByIdAndUserId(1234, 1234)).thenThrow(new NotFoundException("Unknown"));
        mockMvc.perform(get("/users/1234/sessions/1234"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteUnknownSession() throws Exception {
        when(sessionService.findByIdAndUserId(1234, 1234)).thenThrow(new NotFoundException("Unknown"));
        mockMvc.perform(delete("/users/1234/sessions/1234")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(getSessionDto())))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteKnownSession() throws Exception {
        when(sessionService.findByIdAndUserId(1234, 1234)).thenReturn(Mapper.map(getSessionDto()));
        var mvcResult = mockMvc.perform(delete("/users/1234/sessions/1234")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(getSessionDto())))
                .andExpect(status().isOk());
    }

    private SessionDto getSessionDto() {
        var session = new SessionDto();
        session.setChipValue(BigDecimal.TEN);
        session.setUserId(1234);
        return session;
    }
}