package com.example.bookatable.Service;

import com.example.bookatable.Model.Images;
import com.example.bookatable.Model.Restaurant;
import com.example.bookatable.Model.Review;
import com.example.bookatable.Model.User;
import com.example.bookatable.Repository.ImagesRepository;
import com.example.bookatable.Repository.RestaurantRepository;
import com.example.bookatable.Repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final ReviewRepository reviewRepository;
    private final ImagesRepository imagesRepository;

    public List<Restaurant> getAllRestaurant() {
        return restaurantRepository.findAll();
    }

    public void addRestaurant(Restaurant restaurant) {

        this.restaurantRepository.save(restaurant);
    }

    public boolean editRestaurant(Integer restID,Restaurant restaurant, Integer id) {
        Restaurant oldRestaurant=restaurantRepository.findRestaurantByIdAndOwnerID(restID,id);
       if(oldRestaurant==null){
           return false;}
        oldRestaurant.setName(restaurant.getName());
        oldRestaurant.setImage(restaurant.getImage());
        oldRestaurant.setPhoneNumber(restaurant.getPhoneNumber());
        oldRestaurant.setDescription(restaurant.getDescription());
        oldRestaurant.setAddress(restaurant.getAddress());
        oldRestaurant.setMenu(restaurant.getMenu());
        restaurantRepository.save(restaurant);
        return true;
    }
    public boolean deleteRestByID(Integer restID, User user) {
        Restaurant restaurant=restaurantRepository.findRestaurantById(restID);
        if (restaurant == null) {
            return false;
        }
        if(user.getId().equals(restaurant.getOwnerID()) ){
            restaurantRepository.delete(restaurant);
            return true;
        }
        return false;
    }


    public Restaurant getRestaurantById(Integer id) {
        Restaurant restaurant=restaurantRepository.findRestaurantById(id);
        return restaurant;
    }

    public List<Restaurant> getRestaurantByName(String name) {
        return restaurantRepository.findAllByName(name);
    }

    public List<Review> getRestaurantByRating(Integer restID, Integer rate) {
        List<Review> reviews= reviewRepository.findReviewByRateAndAndRestID(rate,restID);
        return reviews;

    }

    public void addRestaurantImage(String url, Integer restaurantID, Integer id) {
        Images image=new Images();
        image.setUserID(id);
        image.setRestID(restaurantID);
        image.setImage(url);
        imagesRepository.save(image);
    }

    public List<Images> getRestaurantImages(Integer restID) {
        return imagesRepository.findImagesByRestID(restID);
    }

    public List<Images> getRestaurantOwnerImages(Integer restID) {
        Integer userID=restaurantRepository.findRestaurantById(restID).getOwnerID();
        return  imagesRepository.findImagesByRestIDAndUserID(restID,userID);
    }

    public List<Images> getRestaurantUserImages(Integer restID) {
        Integer userID=restaurantRepository.findRestaurantById(restID).getOwnerID();
        return  imagesRepository.findImagesByRestIDAndUserIDNot(restID,userID);
    }
}
