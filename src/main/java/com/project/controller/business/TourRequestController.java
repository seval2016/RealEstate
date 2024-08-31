package com.project.controller.business;

import com.project.entity.enums.StatusType;
import com.project.payload.request.business.tourRequestRequests.TourRequestCreateAndUpdateRequest;
import com.project.payload.response.business.ResponseMessage;
import com.project.payload.response.business.TourRequestResponse;
import com.project.service.business.TourRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tour-requests")
@RequiredArgsConstructor
public class TourRequestController {


    private final TourRequestService tourRequestService;

    // ----> S01
    @GetMapping("/auth")
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public ResponseEntity<Page<TourRequestResponse>> getUsersTourRequestByPage(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam(value = "sort", defaultValue = "tourDate") String sort,
            @RequestParam(value = "type", defaultValue = "asc") String type,
            HttpServletRequest servletRequest){

        return tourRequestService.getUsersTourRequest(page, size, sort, type, servletRequest);
    }

    // ----> S02
    @GetMapping("/admin")
    @PreAuthorize("hasAnyAuthority('MANAGER','ADMIN')")
    public ResponseEntity<Page<TourRequestResponse>> getAllTourRequestWithPage(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam(value = "sort", defaultValue = "tour_date") String sort,
            @RequestParam(value = "type", defaultValue = "asc") String type,
            HttpServletRequest servletRequest
    ){

        Page<TourRequestResponse> tourRequestResponses  = tourRequestService.getAllTourRequestWithPage(page,size,sort,type, servletRequest);

        return ResponseEntity.ok(tourRequestResponses);
    }

    // ----> S03
    @GetMapping("/{id}/auth")
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public ResponseEntity<TourRequestResponse> getUsersTourRequestDetails(@PathVariable Long id, HttpServletRequest servletRequest){
        return ResponseEntity.ok(tourRequestService.getUsersTourRequestDetails(id,servletRequest));
    }

    // ----> S04
    @GetMapping("/{id}/admin")
    @PreAuthorize("hasAnyAuthority('MANAGER','ADMIN')")
    public ResponseEntity<TourRequestResponse> getUsersTourRequestDetailsForAdminAndManager(@PathVariable Long id, HttpServletRequest servletRequest){

        return ResponseEntity.ok(tourRequestService.getUsersTourRequestDetailsForAdmin(id, servletRequest));
    }

    // ----> S05
    @PostMapping//!/tour-requests
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public ResponseMessage<TourRequestResponse> createTourRequest(@RequestBody @Valid TourRequestCreateAndUpdateRequest request, HttpServletRequest servletRequest){
        return  tourRequestService.createTourRequest(request,servletRequest);
    }

    // ----> S06
    @PutMapping("/{id}/auth")
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public ResponseMessage<TourRequestResponse> updateTourRequest(@PathVariable Long id, @RequestBody @Valid TourRequestCreateAndUpdateRequest request, HttpServletRequest servletRequest){
        return tourRequestService.updateTourRequest(id, request, servletRequest);
    }

    // ----> S07
    @PatchMapping("/{id}/cancel")
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public ResponseMessage<TourRequestResponse> cancelTourRequest(@PathVariable Long id,HttpServletRequest servletRequest){
        return tourRequestService.updateTourRequestCancel(id, servletRequest);
    }

    // ----> S08
    @PatchMapping("/{id}/approve")
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public ResponseMessage<TourRequestResponse> updateTourRequestApprove(@PathVariable("id") Long id,
                                                                         HttpServletRequest httpServletRequest){
        return tourRequestService.updateTourRequestApprove(id,httpServletRequest);
    }

    // ----> S09
    @PatchMapping("/{id}/decline")
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public ResponseMessage<TourRequestResponse> updateTourRequestDecline(
            @PathVariable("id") Long id,
            HttpServletRequest httpServletRequest){

        // status must be pending
        return tourRequestService.updateTourRequestDecline(id,httpServletRequest);
    }

    // ----> S10
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseMessage<TourRequestResponse> deleteTourRequest(@PathVariable("id") Long id,
                                                                  HttpServletRequest httpServletRequest){
        return tourRequestService.deleteTourRequest(id,httpServletRequest);
    }






}
