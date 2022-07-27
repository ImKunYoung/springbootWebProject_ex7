package ml.mjuchallengers.ex7_mreview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Ex7MreviewApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ex7MreviewApplication.class, args);
    }
    /*TODO: - 목록 화면에서 영화의 제목과 이미지 하나, 영화 리뷰의 평점/리뷰 개수 출력한다*/
    /*TODO: - 영화 조회 화면에서 영화와 영화의 이미지들, 리뷰의 평균점수.리뷰 개수를 함께 출력한다*/
    /*TODO: - 리뷰에 대한 정보에는 회원의 이메일이나 닉네임과 같은 정보를 출력한다*/
}
