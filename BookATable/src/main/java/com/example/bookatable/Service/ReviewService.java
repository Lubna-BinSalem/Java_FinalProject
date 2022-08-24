package com.example.bookatable.Service;
import com.example.bookatable.Model.Review;
import com.example.bookatable.Model.User;
import com.example.bookatable.Repository.RestaurantRepository;
import com.example.bookatable.Repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;

    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }

    public boolean addReview(Review review,User user) {
        Integer ownerID=(restaurantRepository.findRestaurantById(review.getRestID())).getOwnerID();
//        System.out.println("1"+ownerID);
        Review review1=reviewRepository.findReviewByUserIDAndRestID(review.getUserID(), review.getRestID());
        System.out.println("2 ");
        System.out.print((ownerID.equals(review.getUserID())));
        if(ownerID.equals(review.getUserID())){
            return false;}
        if(review1==null){
        reviewRepository.save(review);
        return true;}
        else{
            return false;
        }
    }

    public boolean updateReview(Review review, Integer id) {
        Review oldReview=reviewRepository.findReviewByIdAndUserID(review.getId(),id);
        if(oldReview==null){
            return false;}
        oldReview.setMessage(review.getMessage());
        oldReview.setRate(review.getRate());
        reviewRepository.save(oldReview);
        return true;
    }

    public boolean deleteReviewByID(Integer reviewID, User user) {
        Review review=reviewRepository.findReviewById(reviewID);
        if(user.getId().equals(review.getUserID()) ){
            reviewRepository.delete(review);
            return true;
        }
        return false;
    }

    public List<Review> findAllByRestaurantId(Integer restId) {
        return reviewRepository.findReviewByRestID(restId);
    }
    public List<Review> findAllByUserId(Integer userId) {
        System.out.println(reviewRepository.findReviewsByUserID(userId));
        return reviewRepository.findReviewsByUserID(userId);
    }

//    public List<Review> getRateBy(Integer rate) {
//        return reviewRepository.findReviewsByRate(rate);
//    }


    public Double getAverageRate(Integer restaurantId) {
    List<Review> reviews=reviewRepository.findReviewByRestID(restaurantId);
    Double average=0.0;
    Integer all=0;
    if(reviews!=null){
        for (int i = 0; i < reviews.size(); i++) {
            all=all+reviews.get(i).getRate();
        }
        average= Double.valueOf(all/reviews.size());}
        return average;
    }
}
