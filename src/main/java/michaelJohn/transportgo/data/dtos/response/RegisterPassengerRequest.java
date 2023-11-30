package michaelJohn.transportgo.data.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RegisterPassengerRequest {
    private String email;
    @JsonProperty("full_name")
    private String name;
    @JsonProperty("password")
    private String password;
}
