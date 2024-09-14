package com.project.payload.mappers;

import com.project.entity.concretes.business.Contact;
import com.project.payload.request.business.ContactRequest;
import com.project.payload.response.business.ContactResponse;
import org.springframework.stereotype.Component;

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
