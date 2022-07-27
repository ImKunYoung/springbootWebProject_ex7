package ml.mjuchallengers.ex7_mreview.repository;

import ml.mjuchallengers.ex7_mreview.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
