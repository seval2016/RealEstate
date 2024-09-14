package com.project.service.business;


import com.project.entity.concretes.business.Advert;
import com.project.entity.concretes.business.TourRequest;
import com.project.entity.concretes.user.User;
import com.project.entity.concretes.user.UserRole;
import com.project.entity.enums.RoleType;
import com.project.entity.enums.TourRequestStatus;
import com.project.exception.BadRequestException;
import com.project.exception.ConflictException;
import com.project.exception.ResourceNotFoundException;
import com.project.payload.mappers.TourRequestMapper;
import com.project.payload.messages.ErrorMessages;
import com.project.payload.messages.SuccessMessages;
import com.project.payload.request.business.TourRequestCreateAndUpdateRequest;
import com.project.payload.request.business.TourRequestRequest;
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
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.HashSet;
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
    private final AdvertService advertService;

//    @Transactional anotasyonu, Java'da özellikle Spring Framework içerisinde kullanılan bir anotasyondur.
//    Bu anotasyon, bir metodun veya sınıfın tamamının bir veritabanı işlemi (transaction) kapsamında yürütülmesini sağlar.
//    Yani, bir işlem blok olarak ele alınır;
//    bu işlem ya tamamen başarılı olur ve veritabanına yansıtılır ya da herhangi bir hata durumunda tüm işlem geri alınır (rollback yapılır



    // ----> S01
    public Page<TourRequestResponse> getUsersTourRequestWithPageForCustomer(int page, int size, String sort, String type, HttpServletRequest servletRequest) {

        User user = getUser(servletRequest);

        checkUserRole(user, RoleType.CUSTOMER);

        Pageable pageable =pageableHelper.getPageableWithProperties(page,size,sort,type);
        Page<TourRequest> usersTourRequests = tourRequestRepository.findAllByUserId(user.getId(),pageable);

        return usersTourRequests.map(tourRequestMapper::tourRequestToTourRequestResponseForGuestUser);
    }


    // ----> S02
    public  Page<TourRequestResponse> getAllTourRequestWithPageForAdminAndManager(int page, int size, String sort, String type, HttpServletRequest servletRequest) {
        User user = getUser(servletRequest);
        checkUserRole(user, RoleType.ADMIN, RoleType.MANAGER);
        Pageable pageable = pageableHelper.getPageableWithProperties(page,size,sort,type);
        Page<TourRequest> TourRequests = tourRequestRepository.findAll(pageable);
        return TourRequests.map(tourRequestMapper::tourRequestToTourRequestResponse);
    }

    // ----> S03
    public TourRequestResponse getUsersTourRequestDetails(Long tourRequestId, HttpServletRequest servletRequest) {

        User user = getUser(servletRequest);
        checkUserRole(user, RoleType.CUSTOMER);

        TourRequest tourRequest = tourRequestRepository.findByIdForGuestAndOwnerUser(user.getId(),tourRequestId);
        return tourRequestMapper.tourRequestToTourRequestResponse(tourRequest);
    }

    // ----> S04
    public TourRequestResponse getUsersTourRequestDetailsForAdmin(Long id, HttpServletRequest servletRequest) {

        User user = getUser(servletRequest);

        checkUserRole(user, RoleType.ADMIN, RoleType.MANAGER);

        TourRequest request = isTourRequestExistById(id);
        return tourRequestMapper.
                tourRequestToTourRequestResponse(request);
    }

    // ----> S05
    public ResponseMessage<TourRequestResponse> createTourRequest(TourRequestRequest request, HttpServletRequest servletRequest) {

        User user = getUser(servletRequest);

        checkUserRole(user, RoleType.CUSTOMER);


         Advert advert = advertService.getAdvertById(request.getAdvertId());

         checkConflictTourRequestByGuestUser(user,request);
         checkConflictTourRequestByOwnerUser(advert.getUser(),request);

        TourRequest createdTourRequest = tourRequestMapper.createTourRequestRequestToTourRequest(request);
        createdTourRequest.setCreateAt(LocalDateTime.now(ZoneId.of("UTC")));
        createdTourRequest.setGuestUser(user);
        createdTourRequest.setOwnerUser(advert.getUser());

        createdTourRequest.setStatusStatus(TourRequestStatus.PENDING.getTourStatusValue());  //default'u setledim
        createdTourRequest.setAdvert(advert);


        TourRequest saved = tourRequestRepository.save(createdTourRequest);
        TourRequestResponse tourRequestResponse = tourRequestMapper.tourRequestToTourRequestResponse(saved);
        return ResponseMessage.<TourRequestResponse>builder()
                .object(tourRequestResponse)
                .status(HttpStatus.CREATED)
                .message(SuccessMessages.TOUR_REQUEST_SAVED)
                .build();
    }

    // ----> S06
    public ResponseMessage<TourRequestResponse> updateTourRequest(Long tourRequestId, @Valid TourRequestRequest request, HttpServletRequest servletRequest) {

        User user = getUser(servletRequest);
        checkUserRole(user, RoleType.CUSTOMER);



        TourRequest tourRequestToUpdate = tourRequestRepository.findByIdForGuestUser(user.getId(),tourRequestId);

        checkConflictTourRequestByGuestUser(user,request);
        checkConflictTourRequestByOwnerUser(tourRequestToUpdate.getOwnerUser(),request);

        TourRequest saved = tourRequestMapper.updateTourRequestRequestToTourRequest(tourRequestToUpdate,request);


        saved.setCreateAt(saved.getCreateAt());
        tourRequestRepository.save(saved);
        TourRequestResponse response = tourRequestMapper.tourRequestToTourRequestResponse(saved);
        return ResponseMessage.<TourRequestResponse>builder()
                .object(response)
                .status(HttpStatus.OK)
                .message(SuccessMessages.TOUR_REQUEST_UPDATED)
                .build();
    }

    // ----> S07
    public ResponseMessage<TourRequestResponse> updateTourRequestCancel(Long tourRequestId, HttpServletRequest servletRequest) {
        User user = getUser(servletRequest);
        checkUserRole(user, RoleType.CUSTOMER);

        TourRequest tourRequest = tourRequestRepository.findByIdForGuestUser(user.getId(),tourRequestId);

        tourRequest.setStatusStatus(TourRequestStatus.CANCELED.getTourStatusValue());

        tourRequest = tourRequestRepository.save(tourRequest);

        return ResponseMessage.<TourRequestResponse>builder()
                .object(tourRequestMapper.tourRequestToTourRequestResponseForGuestUser(tourRequest))
                .status(HttpStatus.OK)
                .message(SuccessMessages.TOUR_REQUEST_CANCELLED)
                .build();

    }

    // ----> S08
    public ResponseMessage<TourRequestResponse> updateTourRequestApprove(Long tourRequestId, HttpServletRequest servletRequest) {

        User user = getUser(servletRequest);
        checkUserRole(user, RoleType.CUSTOMER);


        TourRequest tourRequest = tourRequestRepository.findByIdForOwnerUser(user.getId(),tourRequestId);

        tourRequest.setStatusStatus(TourRequestStatus.APPROVED.getTourStatusValue());

        tourRequest = tourRequestRepository.save(tourRequest);

        return ResponseMessage.<TourRequestResponse>builder()
                .object(tourRequestMapper.tourRequestToTourRequestResponseForGuestUser(tourRequest))
                .status(HttpStatus.OK)
                .message(SuccessMessages.TOUR_REQUEST_APPROVED)
                .build();

    }

    // ----> S09
    public ResponseMessage<TourRequestResponse> updateTourRequestDecline(Long tourRequestId, HttpServletRequest servletRequest) {

        User user = getUser(servletRequest);
        checkUserRole(user, RoleType.CUSTOMER);

        TourRequest tourRequest = tourRequestRepository.findByIdForOwnerUser(user.getId(),tourRequestId);

        tourRequest.setStatusStatus(TourRequestStatus.DECLINED.getTourStatusValue());

        tourRequest = tourRequestRepository.save(tourRequest);

        return ResponseMessage.<TourRequestResponse>builder()
                .object(tourRequestMapper.tourRequestToTourRequestResponseForGuestUser(tourRequest))
                .status(HttpStatus.OK)
                .message(SuccessMessages.TOUR_REQUEST_DECLINE)
                .build();
    }

    // ----> S10
    public ResponseMessage<TourRequestResponse> deleteTourRequest(Long id, HttpServletRequest servletRequest) {


        User user = getUser(servletRequest);
        checkUserRole(user, RoleType.ADMIN, RoleType.MANAGER);


        TourRequest tourRequest = isTourRequestExistById(id);

        tourRequestRepository.delete(tourRequest);

         return ResponseMessage.<TourRequestResponse>builder()
                .status(HttpStatus.OK)
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

    public void checkUserRole(User user, RoleType... roleTypes){

        Set<RoleType> roles = new HashSet<>();
        Collections.addAll(roles, roleTypes);
        Set<UserRole> rolesUserRole = roles.stream().map(userRoleService::getUserRole).collect(Collectors.toSet());

        for (UserRole role : user.getUserRole()) {
            if (!(rolesUserRole.contains(role))) {
                throw new BadRequestException(ErrorMessages.NOT_PERMITTED_METHOD_MESSAGE);
            }
        }
    }

    public void checkConflictTourRequestByGuestUser(User guestUser, TourRequestRequest tourRequestRequest) {
        for(TourRequest tourRequest : guestUser.getTourRequests()){
            if(tourRequest.getTourDate().equals(tourRequestRequest.getTourDate())){
                throw new ConflictException(ErrorMessages.CONFLICT_TOUR_DATE);
            }
        }

    }

    public void checkConflictTourRequestByOwnerUser(User ownerUser, TourRequestRequest tourRequestRequest) {
        for (TourRequest tourRequest : ownerUser.getTourRequests()){
            if(tourRequest.getTourDate().equals(tourRequestRequest.getTourDate())){
                throw new ConflictException(ErrorMessages.CONFLICT_TOUR_DATE);
            }
        }
    }

    }



