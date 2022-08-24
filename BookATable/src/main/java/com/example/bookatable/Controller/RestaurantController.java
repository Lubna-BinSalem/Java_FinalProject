package com.example.bookatable.Controller;

import com.example.bookatable.Model.*;
import com.example.bookatable.Service.RestaurantService;
import com.example.bookatable.Service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/rest")
public class RestaurantController {

      private final RestaurantService restaurantService;
      private final ReviewService reviewService;


    @GetMapping("/all")
    public ResponseEntity<List<Restaurant>> getAllRestaurant() {
        List<Restaurant> restaurants = restaurantService.getAllRestaurant();
//        System.out.println("print me"+restaurants);
        return ResponseEntity.status(200).body(restaurants);
    }

    @PostMapping("/add")
    public ResponseEntity addRestaurant(@RequestBody @Valid Restaurant restaurant) {
        restaurantService.addRestaurant(restaurant);
        return ResponseEntity.status(200).body(new Api("Restaurant Added !",200));
    }

    @PutMapping("/edit/{restid}")
    ResponseEntity<Api> editRestaurant(@PathVariable Integer restid,@RequestBody @Valid Restaurant restaurant,@AuthenticationPrincipal User user) {
        boolean  isValid = restaurantService.editRestaurant(restid,restaurant,user.getId());
        if (!isValid)
            return ResponseEntity.status(400).body(new Api("Invalid UserID",400 ));
        else
            return ResponseEntity.status(200).body(new Api("Restaurant is updated",200));
    }

    @DeleteMapping("/delete/{restID}")
    public ResponseEntity deleteRestaurant(@PathVariable Integer restID, @AuthenticationPrincipal User user){
        boolean isValid=restaurantService.deleteRestByID(restID,user);
        if(isValid){
            return ResponseEntity.status(200).body("Restaurant deleted !");
        }else{
            return ResponseEntity.status(400).body(new Api("Invalid Restaurant id",400 ));}
    }

    @GetMapping("/find/id/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Integer id) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        return ResponseEntity.status(200).body(restaurant);
    }
    //get Restaurant by name
    @GetMapping("/find/name/{name}")
    public ResponseEntity<List<Restaurant>> getRestaurantByName(@PathVariable String name) {
        List<Restaurant> restaurant = restaurantService.getRestaurantByName(name);
        return ResponseEntity.status(200).body(restaurant);
    }
    @GetMapping("/find/averageRate/{restaurantId}")
    public ResponseEntity getAverageRestRate(@PathVariable Integer restaurantId){
        return ResponseEntity.status(200).body(reviewService.getAverageRate(restaurantId));
    }

    @GetMapping("/find/restID/{restaurantId}")
    public ResponseEntity<List<Review>> getAllRestaurantReviews(@PathVariable Integer restaurantId){
        return  ResponseEntity.status(200).body(reviewService.findAllByRestaurantId(restaurantId));
    }
    @GetMapping("/find/byRate")
    public ResponseEntity<List<Review>> getRestaurantReviewsByRating(@RequestParam Integer restaurantID,@RequestParam Integer rate) {
        List<Review> reviews = restaurantService.getRestaurantByRating(restaurantID,rate);
        return ResponseEntity.status(200).body(reviews);
    }

    @PostMapping("/add/image")
    public ResponseEntity addRestaurant(@RequestParam String url,@RequestParam Integer restaurantID,@AuthenticationPrincipal User user) {
        restaurantService.addRestaurantImage(url,restaurantID,user.getId());
        return ResponseEntity.status(200).body(new Api("Restaurant image Added !",200));
    }

    @GetMapping("/find/images/{restID}")
    public ResponseEntity<List<Images>> getRestaurantImages(@PathVariable Integer restID) {
        List<Images> images = restaurantService.getRestaurantImages(restID);
        return ResponseEntity.status(200).body(images);
    }

    @GetMapping("/find/images/owner/{restID}")
    public ResponseEntity<List<Images>> getRestaurantOwnerImages(@PathVariable Integer restID) {
        List<Images> images = restaurantService.getRestaurantOwnerImages(restID);
        return ResponseEntity.status(200).body(images);
    }

    @GetMapping("/find/images/user/{restID}")
    public ResponseEntity<List<Images>> getRestaurantUserImages(@PathVariable Integer restID) {
        List<Images> images = restaurantService.getRestaurantUserImages(restID);
        return ResponseEntity.status(200).body(images);
    }
}
