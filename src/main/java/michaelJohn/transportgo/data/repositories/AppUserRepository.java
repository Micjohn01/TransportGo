package michaelJohn.transportgo.data.repositories;

import michaelJohn.transportgo.data.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
}
