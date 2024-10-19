package com.project.contactmessage.mapper;

import com.project.contactmessage.dto.ContactRequest;
import com.project.contactmessage.dto.ContactResponse;
import com.project.contactmessage.entity.Contact;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ContactMapper {

    public Contact mapContactRequestTOContact(ContactRequest contactRequest){
        return Contact.builder()
                .firstName(contactRequest.getFirstName())
                .lastName(contactRequest.getLastName())
                .email(contactRequest.getEmail())
                .message(contactRequest.getMessage())
                .build();
    }
    public ContactResponse mapContactTOContactResponse(Contact contact){
        return ContactResponse.builder()
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .email(contact.getEmail())
                .message(contact.getMessage())
                .createAt(contact.getCreateAt())
                .build();
    }

}