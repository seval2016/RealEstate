package com.project.contactmessage.repository;

import com.project.contactmessage.dto.ContactMessageResponse;
import com.project.contactmessage.entity.ContactMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
    Page<ContactMessage> findByEmailEquals(String email, Pageable pageable);


    Page<ContactMessage> findBySubjectEquals(String subject, Pageable pageable);

    @Query("SELECT c FROM ContactMessage c WHERE FUNCTION('DATE', c.dateTime) BETWEEN ?1 AND ?2")
    List<ContactMessage> findMessagesBetweenDates(LocalDate beginDate, LocalDate endDate);

    List<ContactMessage> findMessagesBetweenTimes(int startHour, int startMinute, int endHour, int endMinute);

}
