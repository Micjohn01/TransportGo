package michaelJohn.transportgo.data.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailNotificationRequest {
    private final Sender sender = new Sender("transportGo", "noreply@transportGo.net");
    private List<Recipient> to = new ArrayList<>();
    private final String subject = "welcome to transportGo";
    private String htmlContent;


}
