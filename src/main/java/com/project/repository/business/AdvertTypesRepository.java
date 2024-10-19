package com.project.repository.business;

import com.project.entity.concretes.business.AdvertType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdvertTypesRepository extends JpaRepository<AdvertType, Long> {
    boolean existsByTitle(String title);

    Optional<AdvertType> findByTitle(String type);
}
