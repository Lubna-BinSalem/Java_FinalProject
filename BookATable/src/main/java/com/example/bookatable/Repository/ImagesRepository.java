package com.example.bookatable.Repository;

import com.example.bookatable.Model.Images;
import com.example.bookatable.Model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImagesRepository extends JpaRepository<Images, Integer> {
    List<Images> findImagesByRestID(Integer restID);
    List<Images> findImagesByRestIDAndUserID(Integer restID, Integer UserID);
    List<Images> findImagesByRestIDAndUserIDNot(Integer restID, Integer UserID);
}
