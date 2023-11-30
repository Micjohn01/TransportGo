package michaelJohn.transportgo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import michaelJohn.transportgo.cloud.CloudService;
import michaelJohn.transportgo.config.distance.DistanceConfig;
import michaelJohn.transportgo.data.dtos.response.RegisterResponse;
import michaelJohn.transportgo.data.dtos.response.RegisterPassengerRequest;
import michaelJohn.transportgo.data.models.AppUser;
import michaelJohn.transportgo.data.models.Passenger;
import michaelJohn.transportgo.data.models.Role;
import michaelJohn.transportgo.data.repositories.PassengerRepository;
import michaelJohn.transportgo.exception.BusinessLogicException;
import michaelJohn.transportgo.mapper.ParaMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;

@Service
@AllArgsConstructor
@Slf4j
//Slf4j This is used as console.log or sout
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;
    private final CloudService cloudService;
    private final DistanceConfig directionConfig;
//    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterResponse register(RegisterPassengerRequest registerRequest) {
        AppUser appUser = ParaMapper.map(registerRequest);
        appUser.setRoles(new HashSet<>());
        appUser.getRoles().add(Role.PASSENGER);
        appUser.setCreatedAt(LocalDateTime.now().toString());
//        appUser.setEmail(registerRequest.getEmail());
//        appUser.setPassword(registerRequest.getPassword());
//        appUser.setName(registerRequest.getName());

        Passenger passenger = new Passenger();
        passenger.setUserDetails(appUser);
        Passenger savedPassenger = passengerRepository.save(passenger);

        RegisterResponse registerResponse = getRegisterResponse(savedPassenger);

        return registerResponse;
    }

    @Override
    public Passenger getPassengerById(Long passengerId) {
        return passengerRepository.findById(passengerId).orElseThrow(() ->
                new BusinessLogicException(
                        String.format("Passenger with id %d not found", passengerId)));
    }

    @Override
    public Passenger updatePassenger(Long passengerId, JsonPatch updatePayLoad) {
        ObjectMapper mapper = new ObjectMapper();
        Passenger foundPassenger = getPassengerById(passengerId);
        //Passenger Object to node
        JsonNode node = mapper.convertValue(foundPassenger, JsonNode.class);
        try {
            //apply patch
            JsonNode updatedNode = updatePayLoad.apply(node);
            //node to Passenger Object
            var updatedPassenger = mapper.convertValue(updatedNode, Passenger.class);
            updatedPassenger = passengerRepository.save(updatedPassenger);
            return updatedPassenger;
        } catch (JsonPatchException exception) {
            log.error(exception.getMessage());
            throw new RuntimeException();
        }

    }

    @Override
    public void deletePassenger(Long id) {passengerRepository.deleteById(id);

    }

    private static RegisterResponse getRegisterResponse(Passenger savedPassenger) {
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setId(savedPassenger.getId());
        registerResponse.setCode(HttpStatus.CREATED.value());
        registerResponse.setSuccessful(true);
        registerResponse.setMessage("User Registration Successful");
        return registerResponse;
    }
}