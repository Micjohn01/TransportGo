package michaelJohn.transportgo.data.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class InviteAdminRequest {
    private String email;
    private String name;
}
