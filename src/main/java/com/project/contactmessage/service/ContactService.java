package com.project.contactmessage.service;


import com.project.contactmessage.dto.ContactRequest;
import com.project.contactmessage.dto.ContactResponse;
import com.project.contactmessage.entity.Contact;
import com.project.contactmessage.mapper.ContactMapper;
import com.project.contactmessage.repository.ContactRepository;
import com.project.exception.ResourceNotFoundException;
import com.project.payload.messages.SuccessMessages;
import com.project.payload.response.business.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactService {


    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    public ResponseMessage<ContactResponse> saveContact(ContactRequest contactRequest) {

        //Gelen request db ye kaydedilmesi için pojo ya çevriliyor
        Contact contactMessage=contactMapper.mapContactRequestTOContact(contactRequest);
        //Gelen mesaj db ye kaydediliyor
        Contact createdMessage=contactRepository.save(contactMessage);

        return ResponseMessage.<ContactResponse>builder()
                .message(SuccessMessages.CONTACT_MESSAGE_CREATED)
                .httpStatus(HttpStatus.CREATED)
                .object(contactMapper.mapContactTOContactResponse(createdMessage))
                .build();
    }


    public Page<ContactResponse>getAllContactMessages(String q, int page, int size, String sort, String type){
        Pageable pageable = PageRequest.of(Integer.parseInt(q),page, Sort.by(sort).ascending());
        return contactRepository.findAll(pageable).map(contactMapper::mapContactTOContactResponse);
    }


    public Contact getContactById(Long contactId) {
        return contactRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id " + contactId));

    }//J03

    public Contact findContactByIdAndUpdateStatus(Long contactId){
        return contactRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id " + contactId));

    }

    public String deleteById(Long contactId) {
        // Kayıt var mı kontrol et
        if (!contactRepository.existsById(contactId)) {
            throw new ResourceNotFoundException("Contact not found with ID: " + contactId);
        }

        // Kayıt mevcutsa sil
        contactRepository.deleteById(contactId);
        return SuccessMessages.CONTACT_MESSAGE_DELETED;
    }
}