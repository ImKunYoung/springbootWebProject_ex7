package ml.mjuchallengers.ex7_mreview.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewnum;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Member member;

    private int grade;

    private String text;

}
