package michaelJohn.transportgo.data.repositories;

import michaelJohn.transportgo.data.models.AppUser;
import michaelJohn.transportgo.data.models.BankInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankInformationRepository extends JpaRepository<BankInformation, Long> {
}

