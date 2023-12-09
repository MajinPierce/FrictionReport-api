package report.friction.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import report.friction.dto.ClimbingAreaDTO;
import report.friction.services.ClimbingAreaServiceImpl;

import static org.mockito.ArgumentMatchers.any;
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

//    @Test
//    public void getDefaultApiMessageShouldReturnResponse() throws Exception{
//        this.mockMvc.perform(get("/api")).andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("text/plain;charset=utf-8"))
//                .andExpect(content().string("Friction.report API up and running"));
//    }
//
//    @Test
//    public void getDefaultApiMessageShouldReturnResponseAlternatePath() throws Exception{
//        this.mockMvc.perform(get("/api/")).andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("text/plain;charset=utf-8"))
//                .andExpect(content().string("Friction.report API up and running"));
//    }
//
//    @Test
//    public void getClimbingAreaShouldReturnResponse() throws Exception {
//        when(climbingAreaService.getClimbingAreaData(any(String.class))).thenReturn(new ClimbingAreaDTO());
//        this.mockMvc.perform(get("/api/Leda")).andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("application/json;charset=utf-8"));
//    }
}