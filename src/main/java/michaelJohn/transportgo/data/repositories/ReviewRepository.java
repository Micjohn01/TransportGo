package michaelJohn.transportgo.data.repositories;

import michaelJohn.transportgo.data.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
