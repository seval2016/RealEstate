package com.project.repository.business;

import com.project.entity.concretes.business.Advert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdvertRepository extends JpaRepository<Advert, Long> {

}