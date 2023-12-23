package report.friction.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import report.friction.dto.ClimbingAreaDTO;
import report.friction.services.ClimbingAreaServiceImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClimbingAreaController.class)
@TestPropertySource(properties = "OPEN_WEATHER_API_KEY=dummyKey")
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class ClimbingAreaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClimbingAreaServiceImpl climbingAreaService;

    private ClimbingAreaDTO getMockClimbingAreaDTO(){
        return mock(ClimbingAreaDTO.class);
    }

    @Test
    void getDefaultApiMessageShouldReturnResponse() throws Exception{
        this.mockMvc.perform(get("")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN))
                .andExpect(content().string("friction.report API up and running"));
    }

    @Test
    void getDefaultApiMessageShouldReturnResponseAlternatePath() throws Exception{
        this.mockMvc.perform(get("/")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN))
                .andExpect(content().string("friction.report API up and running"));
    }

    @Test
    void getClimbingAreaShouldReturnResponse() throws Exception {
        when(climbingAreaService.getClimbingAreaData(any(String.class))).thenReturn(getMockClimbingAreaDTO());
        this.mockMvc.perform(get("/Leda")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}