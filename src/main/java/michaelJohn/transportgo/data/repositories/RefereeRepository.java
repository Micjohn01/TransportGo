package michaelJohn.transportgo.data.repositories;

import michaelJohn.transportgo.data.models.AppUser;
import michaelJohn.transportgo.data.models.Referee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefereeRepository extends JpaRepository<Referee, Long> {
}
