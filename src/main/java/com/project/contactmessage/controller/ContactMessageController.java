package com.project.contactmessage.controller;

import com.project.contactmessage.dto.ContactMessageRequest;
import com.project.contactmessage.dto.ContactMessageResponse;
import com.project.contactmessage.entity.ContactMessage;
import com.project.contactmessage.service.ContactMessageService;
import com.project.payload.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contactMessages")
@RequiredArgsConstructor
public class ContactMessageController {

    private final ContactMessageService contactMessageService;

    @PostMapping("/save") // http://localhost:8080/contactMessages/save + POST + JSON
    public ResponseMessage<ContactMessageResponse> save(@Valid @RequestBody ContactMessageRequest contactMessageRequest) {
        return contactMessageService.save(contactMessageRequest);
    }

    @GetMapping("/getAll") // http://localhost:8080/contactMessages/getAll + GET
    public Page<ContactMessageResponse> getAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "dateTime") String sort,
            @RequestParam(value = "type", defaultValue = "desc") String type


    ){
        return contactMessageService.getAll(page, size, sort, type);
    }


    @GetMapping("/searchByContactEmail") // http://localhost:8080/contactMessages/searchByContactEmail?contactEmail=aaa@bbb.com + GET
    public Page<ContactMessageResponse> searchByContactEmail(
            @RequestParam(value = "contactEmail") String contactEmail,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "dateTime") String sort,
            @RequestParam(value = "type", defaultValue = "desc") String type
    ) {
        return contactMessageService.searchByContactEmail(contactEmail,page,size,sort,type);
    }

    @GetMapping("/searchByContactSubject") // http://localhost:8080/contactMessages/searchByContactSubject?contactSubject=deneme
    public Page<ContactMessageResponse> searchByContactSubject(
            @RequestParam(value = "contactSubject") String contactSubject,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "dateTime") String sort,
            @RequestParam(value = "type", defaultValue = "desc") String type
    ) {
        return contactMessageService.searchByContactSubject(contactSubject,page,size,sort,type);
    }

    @DeleteMapping("/deleteById/{contactMessageId}") // http://localhost:8080/contactMessages/deleteById/11
    public ResponseEntity<String> deleteByIdPath(@PathVariable Long contactMessageId){
        return ResponseEntity.ok(contactMessageService.deleteById(contactMessageId));

    }

    @DeleteMapping("/deleteByIdParam") // http://localhost:8080/contactMessages/deleteByIdParam?contactMessageId=1
    public ResponseEntity<String> deleteById(@RequestParam(value = "contactMessageId") Long contactMessageId){
        return ResponseEntity.ok(contactMessageService.deleteById(contactMessageId));
    }

    @GetMapping("/searchBetweenDates") // http://localhost:8080/contactMessages/searchBetweenDates?beginDate=2024-08-26&endDate=2024-08-28 + GET
    public ResponseEntity<List<ContactMessage>> searchBetweenDates(
            @RequestParam(value = "beginDate") String beginDateString,
            @RequestParam(value = "endDate") String endDateString
    ){
        List<ContactMessage> contactMessages = contactMessageService.searchBetweenDates(beginDateString, endDateString);
        return ResponseEntity.ok(contactMessages);

    }

    @GetMapping("/searchBetweenTimes")//  http://localhost:8080/contactMessages/searchBetweenTimes?startHour=16&startMinute=00&endHour=17&endMinute=25 + GET
    public ResponseEntity<List<ContactMessage>> searchBetweenTimes(
            @RequestParam(value = "startHour") String startHourString,
            @RequestParam(value = "startMinute") String startMinuteString,
            @RequestParam(value = "endHour") String endHourString,
            @RequestParam(value = "endMinute") String endMinuteString
    )
    {
        List<ContactMessage> contactMessages = contactMessageService.searchBetweenTimes(startHourString,startMinuteString, endHourString, endMinuteString);
        return ResponseEntity.ok(contactMessages);
    }

    @GetMapping("/getByIdParam")// http://localhost:8080/contactMessages/getByIdParam?contactMessageId=5 + GET
    public ResponseEntity<ContactMessage> getById(@RequestParam(value = "contactMessageId") Long contactMessageId){
        return ResponseEntity.ok(contactMessageService.getContactMessageById(contactMessageId));
    }

    @GetMapping("/getById/{contactMessageId}")// http://localhost:8080/contactMessages/getById/5 + GET
    public ResponseEntity<ContactMessage> getByIdPath(@PathVariable Long contactMessageId) {
        return ResponseEntity.ok(contactMessageService.getContactMessageById(contactMessageId));
    }


}