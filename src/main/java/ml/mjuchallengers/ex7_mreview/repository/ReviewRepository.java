package ml.mjuchallengers.ex7_mreview.repository;

import ml.mjuchallengers.ex7_mreview.entity.Member;
import ml.mjuchallengers.ex7_mreview.entity.Movie;
import ml.mjuchallengers.ex7_mreview.entity.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    /*특정 영화의 모든 리뷰와 회원의 닉네임 조회 쿼리*/
    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Review> findByMovie(Movie movie);

    /*삭제*/
    void deleteByMember(Member member);

}
