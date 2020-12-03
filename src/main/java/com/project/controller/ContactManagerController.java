package com.project.controller;

import com.project.bean.Contact;
import com.project.service.ContactManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactManagerController {

    @Autowired
    ContactManagerService contactManagerService;

    @GetMapping("contact/list")
    public List<Contact> fetchContacts() {
        return contactManagerService.fetchContacts();
    }

    @GetMapping("contact/{id}/list")
    public Contact fetchContact(@PathVariable int id) {
        return contactManagerService.fetchContact(id);
    }

    @PostMapping("contact/add")
    public String addContact(@RequestBody Contact contact) {
        return contactManagerService.addContact(contact);
    }

    @PutMapping("contact/{id}/update")
    public String updateContact(@PathVariable int id, @RequestBody Contact contact) {
        return contactManagerService.updateContact(id, contact);
    }

    @PutMapping("contact/{id}/activate")
    public String activateContact(@PathVariable int id) {
        return contactManagerService.activateContact(id);
    }

    @PutMapping("contact/{id}/deactivate")
    public String deactivateContact(@PathVariable int id) {
        return contactManagerService.deactivateContact(id);
    }

    @DeleteMapping("contact/{id}/delete")
    public String deleteContact(@PathVariable int id) {
        return contactManagerService.deleteContact(id);
    }

}
