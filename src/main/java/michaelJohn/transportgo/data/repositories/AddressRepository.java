package michaelJohn.transportgo.data.repositories;

import michaelJohn.transportgo.data.models.Address;
import michaelJohn.transportgo.data.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}

