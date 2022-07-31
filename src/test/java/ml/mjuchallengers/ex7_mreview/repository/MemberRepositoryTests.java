package ml.mjuchallengers.ex7_mreview.repository;

import ml.mjuchallengers.ex7_mreview.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertMembers() {

        IntStream.rangeClosed(1, 100).forEach(i->{
            Member member = Member.builder()
                    .email("r"+i+"@gmail.com")
                    .pw("1111")
                    .nickname("reviewer" + i)
                    .build();

            memberRepository.save(member);
        });

    }

    /*member 삭제*/
    @Commit
    @Transactional
    @Test
    public void testDeleteMember() {

        Long mid = 1L;

        Member member = Member.builder().mid(mid).build();

        reviewRepository.deleteByMember(member);
        memberRepository.deleteById(mid);

    }

}
