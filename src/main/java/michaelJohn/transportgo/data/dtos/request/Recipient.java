package michaelJohn.transportgo.data.dtos.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Recipient {
    private String name;
    private String email;
}
