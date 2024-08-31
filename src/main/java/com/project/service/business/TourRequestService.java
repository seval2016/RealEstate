package com.project.service.business;


import com.project.entity.concretes.business.TourRequest;
import com.project.entity.concretes.user.User;
import com.project.entity.concretes.user.UserRole;
import com.project.entity.enums.Role;
import com.project.entity.enums.StatusType;
import com.project.exception.BadRequestException;
import com.project.exception.ResourceNotFoundException;
import com.project.payload.mappers.TourRequestMapper;
import com.project.payload.messages.ErrorMessages;
import com.project.payload.messages.SuccessMessages;
import com.project.payload.request.business.tourRequestRequests.TourRequestCreateAndUpdateRequest;
import com.project.payload.response.business.ResponseMessage;
import com.project.payload.response.business.TourRequestResponse;
import com.project.repository.business.TourRequestRepository;
import com.project.service.helper.MethodHelper;
import com.project.service.helper.PageableHelper;
import com.project.service.user.UserRoleService;
import com.project.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TourRequestService {

    private final TourRequestRepository tourRequestRepository;
    private final UserService userService;
    private final PageableHelper pageableHelper;
    private final TourRequestMapper tourRequestMapper;
    private final UserRoleService userRoleService;
    private final MethodHelper methodHelper;

//    @Transactional anotasyonu, Java'da özellikle Spring Framework içerisinde kullanılan bir anotasyondur.
//    Bu anotasyon, bir metodun veya sınıfın tamamının bir veritabanı işlemi (transaction) kapsamında yürütülmesini sağlar.
//    Yani, bir işlem blok olarak ele alınır;
//    bu işlem ya tamamen başarılı olur ve veritabanına yansıtılır ya da herhangi bir hata durumunda tüm işlem geri alınır (rollback yapılır



    // ----> S01
    public ResponseEntity<Page<TourRequestResponse>> getUsersTourRequest(int page, int size, String sort, String type, HttpServletRequest servletRequest) {

        User user = getUser(servletRequest);

        checkUserRole(user,Role.CUSTOMER);

        Pageable pageable =pageableHelper.getPageableWithProperties(page,size,sort,type);
        Page<TourRequest> usersTourRequests = tourRequestRepository.findAllByUserId(user.getId(),pageable);

        return ResponseEntity.ok(usersTourRequests.map(tourRequestMapper::mapTourRequestToResponse));
    }


    // ----> S02
    public  Page<TourRequestResponse> getAllTourRequestWithPage(int page, int size, String sort, String type, HttpServletRequest servletRequest) {
        User user = getUser(servletRequest);
        checkUserRole(user,Role.ADMIN,Role.MANAGER);
        Pageable pageable = pageableHelper.getPageableWithProperties(page,size,sort,type);
        Page<TourRequest> requests = tourRequestRepository.findAll(pageable);
        return requests.map(tourRequestMapper::mapTourRequestToResponse);
    }

    // ----> S03
    public TourRequestResponse getUsersTourRequestDetails(Long id, HttpServletRequest servletRequest) {

        User user = getUser(servletRequest);
        checkUserRole(user,Role.CUSTOMER);

        TourRequest tourRequest = isTourRequestExistById(id);
        return tourRequestMapper.mapTourRequestToResponse(tourRequest);
    }

    // ----> S04
    public TourRequestResponse getUsersTourRequestDetailsForAdmin(Long id, HttpServletRequest servletRequest) {

        User user = getUser(servletRequest);

        checkUserRole(user,Role.ADMIN,Role.MANAGER);

        TourRequest request = isTourRequestExistById(id);
        return tourRequestMapper.
                mapTourRequestToResponse(request);
    }

    // ----> S05
    public ResponseMessage<TourRequestResponse> createTourRequest(TourRequestCreateAndUpdateRequest request, HttpServletRequest servletRequest) {

        User user = getUser(servletRequest);

        checkUserRole(user,Role.CUSTOMER);

        //Advert advert = advertService.findAdvertById(request.getAdvert());

        TourRequest createdTourRequest = tourRequestMapper.createTourRequestRequestToTourRequest(request);
        createdTourRequest.setCreateAt(LocalDateTime.now(ZoneId.of("UTC")));
        createdTourRequest.setGuestUser(user);
        //createdTourRequest.setOwnerUser(advert.getUser());

        createdTourRequest.setStatus(StatusType.PENDING);
        //createdTourRequest.setAdvert(advert);

        TourRequest saved = tourRequestRepository.save(createdTourRequest);
        TourRequestResponse tourRequestResponse = tourRequestMapper.mapTourRequestToResponse(saved);
        return ResponseMessage.<TourRequestResponse>builder()
                .object(tourRequestResponse)
                .httpStatus(HttpStatus.CREATED)
                .message(SuccessMessages.TOUR_REQUEST_SAVED)
                .build();
    }

    // ----> S06
    public ResponseMessage<TourRequestResponse> updateTourRequest(Long id, TourRequestCreateAndUpdateRequest request, HttpServletRequest servletRequest) {

        User user = getUser(servletRequest);
        checkUserRole(user,Role.CUSTOMER);

        TourRequest tourRequestToUpdate = isTourRequestExistById(id);
        TourRequest saved = tourRequestMapper.TourRequestUpdateRequestToTourRequest(tourRequestToUpdate,request);

        saved.setCreateAt(saved.getCreateAt());
        tourRequestRepository.save(saved);
        TourRequestResponse response = tourRequestMapper.mapTourRequestToResponse(saved);
        return ResponseMessage.<TourRequestResponse>builder()
                .object(response)
                .httpStatus(HttpStatus.OK)
                .message(String.format(SuccessMessages.TOUR_REQUEST_UPDATED,id))
                .build();
    }

    // ----> S07
    public ResponseMessage<TourRequestResponse> updateTourRequestCancel(Long id, HttpServletRequest servletRequest) {
        User user = getUser(servletRequest);
        checkUserRole(user,Role.CUSTOMER);

        TourRequest tourRequest = isTourRequestExistById(id);

        tourRequest.setStatus(StatusType.Canceled);

        tourRequest = tourRequestRepository.save(tourRequest);

        return ResponseMessage.<TourRequestResponse>builder()
                .object(tourRequestMapper.mapTourRequestToResponse(tourRequest))
                .httpStatus(HttpStatus.OK)
                .message(SuccessMessages.TOUR_REQUEST_CANCELLED)
                .build();

    }

    // ----> S08
    public ResponseMessage<TourRequestResponse> updateTourRequestApprove(Long id, HttpServletRequest servletRequest) {

        User user = getUser(servletRequest);
        checkUserRole(user,Role.CUSTOMER);

        TourRequest tourRequest = isTourRequestExistById(id);

        tourRequest.setStatus(StatusType.Approved);

        tourRequest = tourRequestRepository.save(tourRequest);

        return ResponseMessage.<TourRequestResponse>builder()
                .object(tourRequestMapper.mapTourRequestToResponse(tourRequest))
                .httpStatus(HttpStatus.OK)
                .message(SuccessMessages.TOUR_REQUEST_APPROVED)
                .build();

    }

    // ----> S09
    public ResponseMessage<TourRequestResponse> updateTourRequestDecline(Long id, HttpServletRequest servletRequest) {

        User user = getUser(servletRequest);
        checkUserRole(user,Role.CUSTOMER);

        TourRequest tourRequest = isTourRequestExistById(id);

        tourRequest.setStatus(StatusType.Declined);

        tourRequest = tourRequestRepository.save(tourRequest);

        return ResponseMessage.<TourRequestResponse>builder()
                .object(tourRequestMapper.mapTourRequestToResponse(tourRequest))
                .httpStatus(HttpStatus.OK)
                .message(SuccessMessages.TOUR_REQUEST_DECLINE)
                .build();
    }

    // ----> S10
    public ResponseMessage<TourRequestResponse> deleteTourRequest(Long id, HttpServletRequest servletRequest) {


        User user = getUser(servletRequest);
        checkUserRole(user,Role.ADMIN,Role.MANAGER);


        TourRequest tourRequest = isTourRequestExistById(id);

        tourRequestRepository.delete(tourRequest);

         return ResponseMessage.<TourRequestResponse>builder()
                .httpStatus(HttpStatus.OK)
                .message(SuccessMessages.TOUR_REQUEST_DELETED)
                .build();
    }




    /*----------------------------------------->>>>>>>>>> YARDIMCI METHODLAR <<<<<<<<--------------------------------------------*/




    public TourRequest isTourRequestExistById(Long id ){
        return tourRequestRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(String.format(ErrorMessages.TOUR_REQUEST_NOT_FOUND)));
    }



    public User getUser(HttpServletRequest servletRequest){
        String username =(String) servletRequest.getAttribute("username");
        User user =  methodHelper.isUserExistByUsername(username);
        return user;
    }

    public void checkUserRole(User user, Role... roleTypes){

        Set<Role> roles = new HashSet<>();
        Collections.addAll(roles, roleTypes);
        Set<UserRole> rolesUserRole = roles.stream().map(userRoleService::getUserRole).collect(Collectors.toSet());

        for (UserRole role : user.getUserRole()) {
            if (!(rolesUserRole.contains(role))) {
                throw new BadRequestException(ErrorMessages.NOT_PERMITTED_METHOD_MESSAGE);
            }
        }
    }

}
