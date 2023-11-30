package michaelJohn.transportgo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import michaelJohn.transportgo.data.dtos.response.RegisterPassengerRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class PassengerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp(){
    }

    @Test
    public void testRegisterPassenger() throws Exception {
        RegisterPassengerRequest request =
                RegisterPassengerRequest.builder()
                        .name("Michael")
                        .email("MichaelJohn@yahoo.com")
                        .password("Micjohn")
                        .build();

//        String jsonRequest = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/v1/passenger")
//                        .content(jsonRequest)
                        .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE)).
                andExpect(status().is(HttpStatus.CREATED.value()))
                .andDo(print());
    }



}