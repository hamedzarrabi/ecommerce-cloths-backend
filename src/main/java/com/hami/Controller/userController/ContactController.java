package com.hami.Controller.userController;

import com.hami.DTO.userDto.ContactDto;
import com.hami.Entity.user.Contact;
import com.hami.Service.user.ContactService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "CRUD Rest APIs for Contact resources")
@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired private ContactService contactService;

    @ApiOperation(value = "Create Contact REST API")
    @PostMapping("/create")
    public ResponseEntity<ContactDto> createContact(@RequestBody @Valid ContactDto contactDto) {
        return new ResponseEntity<>(contactService.createContact(contactDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get All Posts REST API")
    @GetMapping("/findAll")
    public ResponseEntity<List<Contact>> findAllContact() {
        List<Contact> contact = contactService.getAllContact();
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @ApiOperation(value = "Get Contact by email contact REST API")
    @GetMapping("/findByEmail")
    public ResponseEntity<Contact> getContactByEmail(String email) {
        Contact contact = contactService.getContactByEmail(email);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }
    @ApiOperation(value = "Deleted Contact by id REST API")
    @DeleteMapping("/deleteContactById")
    public ResponseEntity<String> deleteContactById(Long id) {
        contactService.deleteContactById(id);
        return new ResponseEntity<>("Deleted contact by id: " + id + " Successfully", HttpStatus.OK);
    }

}
