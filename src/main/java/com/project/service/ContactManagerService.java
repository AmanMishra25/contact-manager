package com.project.service;

import com.project.bean.Contact;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactManagerService {

    List<Contact> fetchContacts();
    Contact fetchContact(int id);
    String addContact(Contact contact);
    String updateContact(int id, Contact contact);
    String activateContact(int id);
    String deactivateContact(int id);
    String deleteContact(int id);

}
