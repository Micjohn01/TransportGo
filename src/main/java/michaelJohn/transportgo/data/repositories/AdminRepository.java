package michaelJohn.transportgo.data.repositories;

import michaelJohn.transportgo.data.models.Admin;
import michaelJohn.transportgo.data.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}

