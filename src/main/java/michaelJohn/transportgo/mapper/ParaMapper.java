package michaelJohn.transportgo.mapper;

import lombok.NoArgsConstructor;
import michaelJohn.transportgo.data.dtos.response.RegisterPassengerRequest;
import michaelJohn.transportgo.data.models.AppUser;
@NoArgsConstructor
public class ParaMapper {
    public static AppUser map(RegisterPassengerRequest request) {
        AppUser appUser = new AppUser();
        appUser.setName(request.getName());
        appUser.setPassword(request.getPassword());
        appUser.setEmail(request.getEmail());
        return appUser;
    }
}
