package com.project.contactmessage.controller;

import com.project.contactmessage.dto.ContactRequest;
import com.project.contactmessage.dto.ContactResponse;
import com.project.contactmessage.entity.Contact;
import com.project.contactmessage.service.ContactService;
import com.project.exception.ResourceNotFoundException;
import com.project.payload.response.business.ResponseMessage;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping("/saveContact") //http://localhost:8080/contacts/saveContact + POST + JSON
    public ResponseMessage<ContactResponse> saveContact(@Valid @RequestBody ContactRequest contactRequest){
        return contactService.saveContact(contactRequest);
    }//J01->


    @GetMapping("/getAllContactMessages") // http://localhost:8080/contacts/getAllContactMessages/?q=blabla&status=0&page=1&size=10&sort=date&type=asc
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    Page<ContactResponse> getAllContactMessages(
            @RequestParam(value = "q",required = false)String query,
            @RequestParam(value = "page", defaultValue = "0")int page,
            @RequestParam(value = "size", defaultValue = "20")int size,
            @RequestParam(value = "sort", defaultValue = "categoryId")String sort,
            @RequestParam(value = "type", defaultValue = "asc")String type
    ){
        return contactService.getAllContactMessages(query, page,size ,sort ,type);
    }//JO2-> postman çalışmıyor



    @GetMapping("/getContactById/{contactId}") //http://localhost:8080/contacts/getContactById
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<Contact> getContactByIdPath(@PathVariable Long contactId) {
        return ResponseEntity.ok(contactService.findContactByIdAndUpdateStatus(contactId));

    }//J03


    @DeleteMapping("/deleteById/{contactId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<String> deleteByIdPath(@PathVariable Long contactId) {
        try {
            String result = contactService.deleteById(contactId); // deleteById metodu başarılı ise bir mesaj döndürdüğünü varsayalım
            return ResponseEntity.ok(result);
        } catch (ResourceNotFoundException e) { // Örneğin, kayıt bulunamadığında atılacak özel bir istisna
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found with ID: " + contactId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the contact.");
        }
    }//J04



}