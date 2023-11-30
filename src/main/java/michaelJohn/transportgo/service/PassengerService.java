package michaelJohn.transportgo.service;

import com.github.fge.jsonpatch.JsonPatch;
import michaelJohn.transportgo.data.dtos.response.RegisterResponse;
import michaelJohn.transportgo.data.dtos.response.RegisterPassengerRequest;
import michaelJohn.transportgo.data.models.Passenger;

public interface PassengerService {
    RegisterResponse register(RegisterPassengerRequest registerRequest);

    Passenger getPassengerById(Long userId);

    Passenger updatePassenger(Long passengerId, JsonPatch updatePayLoad);

    void deletePassenger(Long id);

//    Page<Passenger>


}
