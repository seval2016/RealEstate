package com.project.contactmessage.mapper;

import com.project.contactmessage.dto.ContactMessageRequest;
import com.project.contactmessage.dto.ContactMessageResponse;
import com.project.contactmessage.entity.ContactMessage;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ContactMessageMapper {

    //!!! Request--> POJO
    public ContactMessage requestToContactMessage(ContactMessageRequest contactMessageRequest){
        return ContactMessage.builder()
                .contactName(contactMessageRequest.getContactName())
                .contactSubject(contactMessageRequest.getContactSubject())
                .contactMessage(contactMessageRequest.getContactMessage())
                .contactEmail(contactMessageRequest.getContactEmail())
                .dateTime(LocalDateTime.now())
                .build();
    }

    // !!! pojo --> Response
    public ContactMessageResponse contactMessageToResponse(ContactMessage contactMessage){
        return ContactMessageResponse.builder()
                .contactName(contactMessage.getContactName())
                .contactSubject(contactMessage.getContactSubject())
                .contactMessage(contactMessage.getContactMessage())
                .contactEmail(contactMessage.getContactEmail())
                .dateTime(LocalDateTime.now())
                .build();


    }

}
