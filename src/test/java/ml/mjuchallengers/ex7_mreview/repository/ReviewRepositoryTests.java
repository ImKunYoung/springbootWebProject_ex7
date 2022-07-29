package ml.mjuchallengers.ex7_mreview.repository;

import ml.mjuchallengers.ex7_mreview.entity.Member;
import ml.mjuchallengers.ex7_mreview.entity.Movie;
import ml.mjuchallengers.ex7_mreview.entity.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class ReviewRepositoryTests {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertMovieReviews() {

        IntStream.rangeClosed(1, 200).forEach(i -> {

            Long mno = (long)(Math.random() * 100) + 1;
            Movie movie = Movie.builder().mno(mno).build();

            Long mid = (long)(Math.random() * 100) + 1;
            Member member = Member.builder().mid(mid).build();

            int grade = (int)(Math.random()*5)+1;

            Review review = Review.builder()
                    .member(member)
                    .movie(movie)
                    .grade(grade)
                    .text("이 영화에 대한 느낌..."+i)
                    .build();

            reviewRepository.save(review);

        });

    }

    /*특정 영화의 모든 리뷰와 회원의 닉네임 조회 쿼리*/
    @Transactional
    @Test
    public void testGetMovieReviews() {

        Long mno = 3L;

        Movie movie = Movie.builder().mno(mno).build();

        List<Review> result = reviewRepository.findByMovie(movie);

        result.forEach(movieReview -> {

            System.out.println(movieReview.getReviewnum());
            System.out.println(movieReview.getGrade());
            System.out.println(movieReview.getText());
            System.out.println(movieReview.getMember().getEmail());
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        });

    }

}
