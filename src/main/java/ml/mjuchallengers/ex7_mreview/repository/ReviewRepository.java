package ml.mjuchallengers.ex7_mreview.repository;

import ml.mjuchallengers.ex7_mreview.entity.Movie;
import ml.mjuchallengers.ex7_mreview.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    /*특정 영화의 모든 리뷰 조회 쿼리*/
    List<Review> findByMovie(Movie movie);

}
