package com.project.repository.user;

import com.project.entity.concretes.user.User;
import com.project.entity.enums.RoleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameEquals(String username);


    User findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByPhone(String phone);

    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u JOIN u.userRole r WHERE r.roleName = ?1 ORDER BY u.firstName DESC")
    Page<User> findByUserByRole(String roleName, Pageable pageable);

    List<User> getUserByFirstNameContaining(String name);

    @Query("SELECT u FROM User u WHERE u.id IN :customerIds")
    List<User> findByIdsEquals(Long[] customerIds);


    @Query(value = "SELECT COUNT(u) FROM User u JOIN u.userRole ur WHERE ur.role = ?1")
    long countAdmin(RoleType role);




      Optional<User> findByEmail(String email);



    @Modifying
    @Query("DELETE FROM User u WHERE u.isBuiltIn = false")
    void deleteAllUsersExceptBuiltIn();

    List<User> findByUserRole_Role(RoleType roleType);

    Optional<User> findByResetPasswordCode(String code);

    default User findByUsernameOrEmail(String username, String email) {
        User user = findByUsername(username);
        if (user == null) {
            user = findByEmail(email).orElse(null);
        }
        return user;
    }


}

