package com.project.service;


import com.project.entity.concretes.user.User;
import com.project.exception.BadRequestException;
import com.project.exception.ResourceNotFoundException;
import com.project.payload.mappers.UserMapper;
import com.project.payload.messages.ErrorMessages;
import com.project.payload.request.authentication.LoginRequest;
import com.project.payload.request.business.ForgotPasswordRequest;
import com.project.payload.request.business.UpdatePasswordRequest;
import com.project.payload.response.UserResponse;
import com.project.payload.response.authentication.AuthResponse;
import com.project.repository.user.UserRepository;
import com.project.security.jwt.JwtUtils;
import com.project.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Email;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<AuthResponse> authenticateUser(LoginRequest loginRequest) {


        // Kullanıcı adı ve şifreyi al

         String email = loginRequest.getEmail() ;
      //    String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        // Kullanıcıyı email veya username ile bul
        Optional<User> user = userRepository.findByEmail(email);

        if (email == null) {
            throw new BadRequestException("Kullanıcı adı veya email bulunamadı");
        }

        // Kullanıcıyı valide et
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        // Kullanıcı bilgilerini context'e ata
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // JWT token oluştur
        String token = "Bearer " + jwtUtils.generateJwtToken(authentication);

        // Response nesnesi oluştur
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        // Kullanıcı rollerini al
        Set<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority) // Stream<String>
                .collect(Collectors.toSet());




        Optional<String> role = roles.stream().findFirst();

        AuthResponse.AuthResponseBuilder authResponse = AuthResponse.builder();
       // authResponse.username(userDetails.getUsername());
        authResponse.token(token.substring(7));
        authResponse.name(userDetails.getFirstName());
        authResponse.email(userDetails.getEmail());

        // Rol bilgisi varsa AuthResponse nesnesine ekle
        role.ifPresent(authResponse::role);

        return ResponseEntity.ok(authResponse.build());
    }




    public UserResponse findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return userMapper.mapUserToUserResponse(user);
    }

    public void updatePassword(UpdatePasswordRequest updatePasswordRequest, HttpServletRequest request) {
        String userName = (String) request.getAttribute("username");
        User user = userRepository.findByUsername(userName);


        // Built-In kontrolü
        if(Boolean.TRUE.equals(user.getIsBuiltIn())){
            throw new BadRequestException(ErrorMessages.NOT_PERMITTED_METHOD_MESSAGE);
        }

        // Eski şifre kontrolü
        if(!passwordEncoder.matches(updatePasswordRequest.getOldPassword(), user.getPasswordHash())){
            throw new BadRequestException(ErrorMessages.PASSWORD_NOT_MATCHED);
        }

        // Yeni şifreyi encode et ve güncelle
        String hashedPassword = passwordEncoder.encode(updatePasswordRequest.getNewPassword());
        user.setPasswordHash(hashedPassword);
        userRepository.save(user);
    }



    public void forgotPassword(ForgotPasswordRequest forgotPasswordRequest) {
        User user = userRepository.findByEmail(forgotPasswordRequest.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", forgotPasswordRequest.getEmail()));

        String resetPasswordCode = jwtUtils.generateResetCode(user);
        user.setResetPasswordCode(resetPasswordCode);
        userRepository.save(user);

        // Email gönderimi (emailService.sendPasswordResetEmail(user.getEmail(), resetPasswordCode);)
    }
}

































/*package com.project.service;

import com.project.entity.concretes.user.User;
import com.project.exception.BadRequestException;
import com.project.exception.ResourceNotFoundException;
import com.project.payload.mappers.UserMapper;
import com.project.payload.messages.ErrorMessages;

import com.project.payload.request.authentication.LoginRequest;
import com.project.payload.request.business.ForgotPasswordRequest;
import com.project.payload.request.business.UpdatePasswordRequest;

import com.project.payload.response.UserResponse;

import com.project.payload.response.authentication.AuthResponse;
import com.project.repository.user.UserRepository;
import com.project.security.jwt.JwtUtils;
import com.project.service.UserDetailsImpl;
//import com.project.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<AuthResponse> authenticateUser(LoginRequest loginRequest) {
        String username=loginRequest.getUsername();

        String password=loginRequest.getPassword();

        //!!! authenticationManager uzerinden kullanici valide ediliyor

        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        //!!! valide edilen kullanici context e atiliyor
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //!!! JWT token olusturuluyor
        String token = "Bearer " + jwtUtils.generateJwtToken(authentication);

        //!!! Response nesnesi olusturuluyor
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        //!!! grantedAuth --> Role ( String )
        Set<String> roles = userDetails.getAuthorities()
                .stream() // Stream<GrantedAuth>
                .map(GrantedAuthority::getAuthority) // Stream<String>
                .collect(Collectors.toSet());

        Optional<String> role = roles.stream().findFirst();

        AuthResponse.AuthResponseBuilder authResponse = AuthResponse.builder();
        authResponse.username(userDetails.getUsername());
        authResponse.token(token.substring(7));
        authResponse.name(userDetails.getFirstName());

        // !!! eger role bilgisi null degil ise AuthResponse nesnesi icine setleniyor
        role.ifPresent(authResponse::role);

        return ResponseEntity.ok(authResponse.build());
    }

    public UserResponse findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        //!!! Pojo --> DTO
        return userMapper.mapUserToUserResponse(user);
    }

    public void updatePassword(UpdatePasswordRequest updatePasswordRequest, HttpServletRequest request) {

        String userName = (String) request.getAttribute("username");
        User user = userRepository.findByUsername(userName);

        //!!! Built_IN kontrolu
        if(Boolean.TRUE.equals(user.getIsBuiltIn())){ // TRUE - FALSE - NULL ( NullPointerException )
            throw new BadRequestException(ErrorMessages.NOT_PERMITTED_METHOD_MESSAGE);
        }
        //!!! Eski sifre bilgisi dogrumu ?
        if(!passwordEncoder.matches(updatePasswordRequest.getOldPassword(), user.getPasswordHash())){
            throw new BadRequestException(ErrorMessages.PASSWORD_NOT_MATCHED);
        }
        //!!! Yeni sifre encode edilecek
        String hashedPassword = passwordEncoder.encode(updatePasswordRequest.getNewPassword());
        //!!! update
        user.setPasswordHash(hashedPassword);
        userRepository.save(user);
    }

    public void forgotPassword(ForgotPasswordRequest forgotPasswordRequest) {
        User user = userRepository.findByEmail(forgotPasswordRequest.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", forgotPasswordRequest.getEmail()));

        String resetPasswordCode = jwtUtils.generateResetCode(user);
        user.setResetPasswordCode(resetPasswordCode);
        userRepository.save(user);

        //Email gönderimi
        // emailService.sendPasswordResetEmail(user.getEmail(), resetPasswordCode);
    }





}*/