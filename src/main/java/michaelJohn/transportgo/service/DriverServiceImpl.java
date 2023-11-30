package michaelJohn.transportgo.service;

import lombok.AllArgsConstructor;
import michaelJohn.transportgo.cloud.CloudService;
import michaelJohn.transportgo.data.dtos.request.RegisterDriverRequest;
import michaelJohn.transportgo.data.dtos.response.RegisterResponse;
import michaelJohn.transportgo.data.models.AppUser;
import michaelJohn.transportgo.data.models.Driver;
import michaelJohn.transportgo.data.repositories.DriverRepository;
import michaelJohn.transportgo.exception.ImageUploadException;
import michaelJohn.transportgo.notification.MailService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private  final ModelMapper modelMapper;
    private final CloudService cloudService;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterResponse register(RegisterDriverRequest request) {
        AppUser driverDetails = modelMapper.map(request, AppUser.class);
        driverDetails.setPassword();
        driverDetails.setCreatedAt(LocalDateTime.now().toString());

        var imageUrl = cloudService.upload(registerDriverRequest.getLicenseImage());
        if (imageUrl == null)
            throw new ImageUploadException("Driver Registration failed");

        Driver driver = Driver.builder()
                .user
        return null;
    }

    @Override
    public Optional<Driver> getDriverBy(Long driverId) {
        return Optional.empty();
    }

    @Override
    public void saveDriver(Driver driver) {

    }


}
