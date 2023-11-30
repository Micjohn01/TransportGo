package michaelJohn.transportgo.service;

import michaelJohn.transportgo.data.dtos.request.RegisterDriverRequest;
import michaelJohn.transportgo.data.dtos.response.RegisterResponse;
import michaelJohn.transportgo.data.models.Driver;

import java.util.Optional;

public interface DriverService {
    RegisterResponse register(RegisterDriverRequest request);

    Optional<Driver> getDriverBy(Long driverId);

    void saveDriver (Driver driver);
}
