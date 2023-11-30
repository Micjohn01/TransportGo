package michaelJohn.transportgo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jackson.jsonpointer.JsonPointerException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.ReplaceOperation;
import michaelJohn.transportgo.data.dtos.response.RegisterResponse;
import michaelJohn.transportgo.data.dtos.response.RegisterPassengerRequest;
import michaelJohn.transportgo.data.models.AppUser;
import michaelJohn.transportgo.data.models.Passenger;
import michaelJohn.transportgo.exception.BusinessLogicException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PassengerServiceImplTest {

    @Autowired
    private PassengerService passengerService;

    private RegisterPassengerRequest request;

    @BeforeEach
    void setUp(){
        request = new RegisterPassengerRequest();
        request.setEmail("test@email.com");
        request.setPassword("testPassword");
        request.setName("Michael John");
    }

    @Test
    void registerTest(){

        RegisterResponse registerResponse = passengerService.register(request);
        assertThat(registerResponse).isNotNull();
        assertThat(registerResponse.getCode())
                .isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    public void getUserByIdTest(){
        var registerResponse = passengerService.register(request);
        Passenger foundPassenger = passengerService.getPassengerById(registerResponse.getId());
        assertThat(foundPassenger).isNotNull();
        AppUser userDetails = foundPassenger.getUserDetails();
        assertThat(userDetails.getName()).isEqualTo(request.getName());
    }

    @Test
    public void updatePassengerTest() throws JsonPointerException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree("2348136522994");
        JsonPatch updatePayLoad = new JsonPatch(List.of(
           new ReplaceOperation(new JsonPointer("/phoneNumber"),
           node)
        ));
        var registerResponse = passengerService.register(request);
        var updatedPassenger = passengerService.updatePassenger(registerResponse.getId(), updatePayLoad);
        assertThat(updatedPassenger).isNotNull();
        assertThat(updatedPassenger.getPhoneNumber()).isNotNull();
    }
    @Test
    public void deletePassengerTest(){
        var response = passengerService.register(request);
        passengerService.deletePassenger(response.getId());
        assertThrows(BusinessLogicException.class, ()->passengerService.getPassengerById(response.getId()));
    }
}