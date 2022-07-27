package ml.mjuchallengers.ex7_mreview.repository;

import ml.mjuchallengers.ex7_mreview.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
