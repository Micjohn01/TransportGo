package michaelJohn.transportgo.data.repositories;

import michaelJohn.transportgo.data.models.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
