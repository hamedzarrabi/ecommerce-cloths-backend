package com.hami.Service.impl.userImpl;

import com.hami.DTO.userDto.ContactDto;
import com.hami.Entity.user.Contact;
import com.hami.Exception.ResourceNotFoundException;
import com.hami.Repository.user.ContactRepository;
import com.hami.Service.user.ContactService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

     @Autowired  private ModelMapper mapper;
     @Autowired  private ContactRepository contactRepository;

    @Override
    public ContactDto createContact(ContactDto contactDto) {
        // convert DTO to entity
        Contact contact = mapToEntity(contactDto);
        Contact newContact = contactRepository.save(contact);

        // convert entity to DTO
        ContactDto contactResponse = mapToDTO(newContact);
        return contactResponse;
    }

    @Override
    public List<Contact> getAllContact() {
        return contactRepository.findAll();
    }

    @Override
    public Contact getContactByEmail(String email) {
        return contactRepository.findContactByEmail(email);
    }

    @Override
    public void deleteContactById(long id) {
        Contact contact = contactRepository.findById(id).orElseThrow
                (() -> new ResourceNotFoundException("Contact", "id", id));
        contactRepository.delete(contact);
    }

//    Convert Entity into DTO
    private ContactDto mapToDTO(Contact contact) {
        ContactDto contactDto = mapper.map(contact, ContactDto.class);
        return contactDto;
    }
//    Convert DTO to entity
    private Contact mapToEntity(ContactDto contactDto) {
        Contact contact = mapper.map(contactDto, Contact.class);
        return contact;
    }
}
