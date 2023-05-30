package com.hami.Service.user;

import com.hami.DTO.userDto.ContactDto;
import com.hami.Entity.user.Contact;

import java.util.List;

public interface ContactService {
    ContactDto createContact(ContactDto contactDto);
    List<Contact> getAllContact();
    Contact getContactByEmail(String email);
    void deleteContactById(long id);

}
