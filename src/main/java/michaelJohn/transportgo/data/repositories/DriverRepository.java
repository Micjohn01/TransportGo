package michaelJohn.transportgo.data.repositories;

import michaelJohn.transportgo.data.models.AppUser;
import michaelJohn.transportgo.data.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
}
