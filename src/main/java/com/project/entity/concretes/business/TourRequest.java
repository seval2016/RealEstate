
package com.project.entity.concretes.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.entity.concretes.user.User;
import com.project.entity.enums.TourRequestStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "tour_requests")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TourRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "tour_date", nullable = false)
    private LocalDate tourDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "US")
    @Column(name = "tour_time", nullable = false)
    private LocalTime tourTime;

     @Enumerated(EnumType.STRING)  // Enum türü kullanımı
    @Column(nullable = false, name = "status")
    private TourRequestStatus status;  // Değişiklik burada

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "US")
    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "US")
    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;

    @ManyToOne
    @JoinColumn(name = "owner_user_id", nullable = false)
    private User ownerUser;

    @ManyToOne
    @JoinColumn(name = "guest_user_id", nullable = false)
    private User guestUser;

    @ManyToOne
    @JoinColumn(name = "advert_id")
    private Advert advert;

    // İsteğe bağlı olarak, advertId'yi eklemek için bir alan tanımlayabilirsiniz
    // Ancak, advert ile birlikte kullanılabilir.
    // private Long advertId; // Yalnızca ihtiyaç varsa ekleyin
}










    // Tek bir user referansı yeterli olacak
    // @ManyToOne
    // @JoinColumn(name = "user_id") // Kullanıcı ile ilişkilendirmek için
    // private User user; // Bu alanı kaldırdık

    // Eğer owner ve guest referansları kullanılacaksa bunları koruyabilirsiniz
