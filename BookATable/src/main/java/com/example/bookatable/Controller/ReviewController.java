package com.example.bookatable.Controller;

import com.example.bookatable.Model.Api;
import com.example.bookatable.Model.Review;
import com.example.bookatable.Model.User;
import com.example.bookatable.Service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/review")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/all")
    public ResponseEntity<List<Review>> getReviews() {
        return ResponseEntity.status(200).body(reviewService.getReviews());
    }

    //add review
    @PostMapping("/add")
    public ResponseEntity addReview(@RequestBody Review review,@AuthenticationPrincipal User user){
        boolean isValid=reviewService.addReview(review,user);
        if (isValid) {
            return ResponseEntity.status(200).body(new Api("Review added!", 200));
        }else{
            return ResponseEntity.status(400).body(new Api("review insertion not permitted",400 ));}
    }

    @PutMapping("/edit")
    ResponseEntity<Api> updateReview(@RequestBody @Valid Review review, @AuthenticationPrincipal User user) {
        boolean  isValid = reviewService.updateReview(review,user.getId());
        if (isValid)
        return ResponseEntity.status(200).body(new Api("Review is updated",200));

        else
            return ResponseEntity.status(400).body(new Api("Invalid UserID",400 ));
    }

    @DeleteMapping("/delete/{reviewID}")
    public ResponseEntity deleteReview(@PathVariable Integer reviewID, @AuthenticationPrincipal User user){
        boolean isValid=reviewService.deleteReviewByID(reviewID,user);
        if(isValid){
            return ResponseEntity.status(200).body(new Api("Review deleted !",200));
        }else{
            return ResponseEntity.status(400).body(new Api("Invalid Review id",400 ));}
    }


    @GetMapping("/find/all")
    public ResponseEntity<List<Review>> getAllUserReviews(@AuthenticationPrincipal User user){
        return  ResponseEntity.status(200).body(reviewService.findAllByUserId(user.getId()));
    }
//    @GetMapping("/find/rate/{rate}")
//    public ResponseEntity<List<Review>> getRateBy (@PathVariable Integer rate){
//        List<Review> reviews = reviewService.getRateBy(rate);
//        return ResponseEntity.status(200).body(reviews);
//    }

}
