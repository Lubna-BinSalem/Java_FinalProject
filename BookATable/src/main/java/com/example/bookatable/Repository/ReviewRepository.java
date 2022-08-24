package com.example.bookatable.Repository;

import com.example.bookatable.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findReviewByRateAndAndRestID(Integer rate,Integer restID);
    Review findReviewById(Integer id);
    Review findReviewByIdAndUserID(Integer id, Integer userID);
    List<Review> findReviewByRestID(Integer RestID);
    List<Review> findReviewsByUserID(Integer UserID);
    List<Review> findReviewsByRate(Integer rate);
    Review findReviewByUserIDAndRestID(Integer userID, Integer restID);

}
