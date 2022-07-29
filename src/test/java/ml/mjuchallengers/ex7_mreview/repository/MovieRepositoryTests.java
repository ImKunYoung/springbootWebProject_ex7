package ml.mjuchallengers.ex7_mreview.repository;

import ml.mjuchallengers.ex7_mreview.entity.Movie;
import ml.mjuchallengers.ex7_mreview.entity.MovieImage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootTest
public class MovieRepositoryTests {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieImageRepository movieImageRepository;

    /*더미 데이터 삽입*/
    @Commit
    @Transactional
    @Test
    public void insertMovies() {

        IntStream.rangeClosed(1, 100).forEach(i -> {

            Movie movie = Movie.builder().title("Movie...."+i).build();

            System.out.println("--------------------------------------------");

            movieRepository.save(movie);

            int count = (int)(Math.random() * 5) +1; // 1, 2, 3, 4

            for(int j = 0; j < count; j++) {

                MovieImage movieImage = MovieImage.builder()
                        .uuid(UUID.randomUUID().toString()) // 임의의 번호 부여
                        .movie(movie)
                        .imgName("test"+j+".jpg")
                        .build();

                movieImageRepository.save(movieImage);

            }

        });

    }

    /*평점과 리뷰 개수 조회 쿼리*/
    @Test
    public void testListPage() {

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "mno"));

        Page<Object[]> result = movieRepository.getListPage(pageRequest);

        for(Object[] objects : result.getContent()) {
            System.out.println(Arrays.toString(objects));
        }

    }

    /*특정 영화의 모든 이미지와 평균 평점/리뷰 개수*/
    @Test
    public void testGetMovieWithAll() {

        Long mno = 1L;

        List<Object[]> result = movieRepository.getMovieWithAll(mno);

        System.out.println(result);

        for(Object[] objects : result) {
            System.out.println(Arrays.toString(objects));
        }

    }
}







