package michaelJohn.transportgo.data.repositories;

import michaelJohn.transportgo.data.models.AppUser;
import michaelJohn.transportgo.data.models.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
