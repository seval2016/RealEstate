package com.project.repository.business;

import com.project.entity.concretes.business.Advert;
import com.project.entity.concretes.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertRepository extends JpaRepository<Advert, Long> {

   // List<Advert> getAdvert();
   // List<User> getUsers();  //???????????????








}
