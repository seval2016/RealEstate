package com.project.repository.business;


import com.project.entity.image.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImagesRepository extends JpaRepository<Images,Long> {
    List<Images> findByAdvert_Id(Long advertId);
    // Featured resimleri bulma
    List<Images> findByFeaturedTrue();

    // Belirli bir türdeki resimleri bulma
    List<Images> findByType(String type);

    // Resim adını kullanarak resmi bulma
    Optional<Images> findByName(String name);


}

