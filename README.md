# springbootWebProject_ex7

![image](https://user-images.githubusercontent.com/46955032/181380846-880731db-eb9d-4ca1-af0b-906344d6189c.png)





TODO: - 목록 화면에서 영화의 제목과 이미지 하나, 영화 리뷰의 평점/리뷰 개수 출력한다

TODO: - 영화 조회 화면에서 영화와 영화의 이미지들, 리뷰의 평균점수.리뷰 개수를 함께 출력한다

TODO: - 리뷰에 대한 정보에는 회원의 이메일이나 닉네임과 같은 정보를 출력한다


<br/>
<br/>

### • EntityGraph 어노테이션

<hr/>

JPA를 이용하는 경우 Review 클래스와 연관 관계인 Member 클래스에 대한 Fetch 방식이 LAZY인 경우 Review 객체와 Member 객체를 조회할 수 없는 문제가 발생한다. 따라서 @Transactional 처리를 해주는데 각 Review 객체의 연관 관계가 있는 엔티티를 조회할 때마다 로딩이 발생하여 성능적인 문제가 발생할 수 있다
<br/>

##### 리포지터리
```java
public interface ReviewRepository extends JpaRepository<Review, Long> {

    /*특정 영화의 모든 리뷰와 회원의 닉네임 조회 쿼리*/
    List<Review> findByMovie(Movie movie);

}
```

<br/>

##### 테스트문
```java
 /*특정 영화의 모든 리뷰와 회원의 닉네임 조회 쿼리*/
    @Transactional
    @Test
    public void testGetMovieReviews() {

        Long mno = 3L;

        Movie movie = Movie.builder().mno(mno).build();

        List<Review> result = reviewRepository.findByMovie(movie);

        result.forEach(movieReview -> {

            System.out.println(movieReview.getReviewnum());
            System.out.print("\t"+movieReview.getGrade());
            System.out.print("\t"+movieReview.getText());
            System.out.print("\t"+movieReview.getMember().getEmail());
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        });
```

<br/>

 ##### 실행결과
```
Hibernate: 
    select
        review0_.reviewnum as reviewnu1_3_,
        review0_.moddate as moddate2_3_,
        review0_.regdate as regdate3_3_,
        review0_.grade as grade4_3_,
        review0_.member_mid as member_m6_3_,
        review0_.movie_mno as movie_mn7_3_,
        review0_.text as text5_3_ 
    from
        review review0_ 
    where
        review0_.movie_mno=?
62
	2	이 영화에 대한 느낌...62Hibernate: 
    select
        member0_.mid as mid1_0_0_,
        member0_.moddate as moddate2_0_0_,
        member0_.regdate as regdate3_0_0_,
        member0_.email as email4_0_0_,
        member0_.nickname as nickname5_0_0_,
        member0_.pw as pw6_0_0_ 
    from
        m_member member0_ 
    where
        member0_.mid=?
	r52@gmail.com~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
67
	4	이 영화에 대한 느낌...67Hibernate: 
    select
        member0_.mid as mid1_0_0_,
        member0_.moddate as moddate2_0_0_,
        member0_.regdate as regdate3_0_0_,
        member0_.email as email4_0_0_,
        member0_.nickname as nickname5_0_0_,
        member0_.pw as pw6_0_0_ 
    from
        m_member member0_ 
    where
        member0_.mid=?
	r37@gmail.com~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
129
	2	이 영화에 대한 느낌...129Hibernate: 
    select
        member0_.mid as mid1_0_0_,
        member0_.moddate as moddate2_0_0_,
        member0_.regdate as regdate3_0_0_,
        member0_.email as email4_0_0_,
        member0_.nickname as nickname5_0_0_,
        member0_.pw as pw6_0_0_ 
    from
        m_member member0_ 
    where
        member0_.mid=?
	r2@gmail.com~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
``` 

<br/>

이를 해결하기 위한 방법으론 크게 두가지 방법이 있는데
>1. @Query를 이용해 조인 처리 한다.
>2. @EntityGraph를 이용하여 Review 객체를 가져올 때 Member 객체를 로딩한다.

이중 @EntityGraph에 대해 살펴보자면, @EntityGraph를 사용하면 JPA에서 연관 관계의 FATCH 속성값이 LAZY로 지정된 경우 특정 기능을 수행할 때만 EAGER 로딩을 하도록 지정할 수 있다.

<br/>

@EntityGraph에는 다음과 같은 속성을 지정할 줄 수 있다.
> - attributePaths: 로딩 설정을 변경하고 싶은 속성의 이름을 배열로 명시
> - type: @EntityGraph를 어떤 방식으로 적용할 것인지 설정
> - FETCH 속성값은 attributePaths에 명시한 속성은 EAGER로 처리하고, 나머지는 LAZY로 처리
> - LOAD 속성값은 attributePaths에 명시한 속성은 EAGER로 처리하고, 나머지는 엔티티 클래스에 명시되거나 기본 형식으로 처리


<br/>

##### 수정된 리포지터리
```java
public interface ReviewRepository extends JpaRepository<Review, Long> {

    /*특정 영화의 모든 리뷰와 회원의 닉네임 조회 쿼리*/
    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Review> findByMovie(Movie movie);

}
```

##### 실행결과
```
Hibernate: 
    select
        review0_.reviewnum as reviewnu1_3_0_,
        member1_.mid as mid1_0_1_,
        review0_.moddate as moddate2_3_0_,
        review0_.regdate as regdate3_3_0_,
        review0_.grade as grade4_3_0_,
        review0_.member_mid as member_m6_3_0_,
        review0_.movie_mno as movie_mn7_3_0_,
        review0_.text as text5_3_0_,
        member1_.moddate as moddate2_0_1_,
        member1_.regdate as regdate3_0_1_,
        member1_.email as email4_0_1_,
        member1_.nickname as nickname5_0_1_,
        member1_.pw as pw6_0_1_ 
    from
        review review0_ 
    left outer join
        m_member member1_ 
            on review0_.member_mid=member1_.mid 
    where
        review0_.movie_mno=?
62
2
이 영화에 대한 느낌...62
r52@gmail.com
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
67
4
이 영화에 대한 느낌...67
r37@gmail.com
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
129
2
이 영화에 대한 느낌...129
r2@gmail.com
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```






