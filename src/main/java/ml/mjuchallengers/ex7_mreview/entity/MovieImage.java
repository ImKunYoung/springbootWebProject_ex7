package ml.mjuchallengers.ex7_mreview.entity;

import lombok.*;

import javax.persistence.*;
/*나중에 사용할 이미지에 대한 정보 기록*/
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class MovieImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inum;

    private String uuid;

    private String umgName;

    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Movie movie;

}
