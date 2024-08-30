package com.project.repository.image;

import com.project.entity.image.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query("SELECT * FROM Image i WHERE i.id = :imageId")
    Optional<Image> findById(Long imageId);


}
