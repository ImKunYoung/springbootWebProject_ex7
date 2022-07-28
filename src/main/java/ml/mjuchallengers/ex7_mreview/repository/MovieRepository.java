package ml.mjuchallengers.ex7_mreview.repository;

import ml.mjuchallengers.ex7_mreview.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    /*평점과 리뷰 개수 조회 쿼리*/
    @Query("select m, max(mi), avg(coalesce(r.grade, 0)), count(distinct r) from Movie m left outer join Review r on r.movie = m left outer join MovieImage mi on mi.movie = m group by m")
    Page<Object[]> getListPage(Pageable pageable);
/*todo - 29*/
}
