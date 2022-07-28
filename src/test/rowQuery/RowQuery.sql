/*영화 제목, 영화 이미지, 리뷰의 수, 평점 평균을 구하는 쿼리*/
SELECT DISTINCT movie.title, movie_image.img_name, COUNT(DISTINCT review.reviewnum), AVG(DISTINCT review.grade) FROM movie
LEFT JOIN review ON review.movie_mno = movie.mno
LEFT JOIN movie_image ON movie_image.movie_mno = movie.mno
GROUP BY movie.mno
ORDER BY movie.mno;