package com.example.bookatable.Repository;

import com.example.bookatable.Model.Restaurant;
import com.example.bookatable.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    Restaurant findRestaurantById(Integer id);
    Restaurant findRestaurantByIdAndOwnerID(Integer restID, Integer ownerID);
    Restaurant findRestaurantByOwnerID(Integer id);
    List<Restaurant> findAllByName(String name);



}
