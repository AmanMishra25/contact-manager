package com.project.service;

import com.project.bean.Contact;
import com.project.dao.ContactManagerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactMangerServiceImpl implements ContactManagerService {

    @Autowired
    ContactManagerDao contactManagerDao;

    private final static String ACTIVE = "ACTIVE";
    private final static String INACTIVE = "INACTIVE";

    @Override
    public List<Contact> fetchContacts() {
        System.out.println("Fetching contact list...");
        List<Contact> contactList = new ArrayList<>();

        try {
            contactList = contactManagerDao.fetchContacts();
        } catch (Exception e) {
            System.out.println("Error fetching contact list!");
            e.printStackTrace();
        }

        return contactList;
    }

    @Override
    public Contact fetchContact(int id) {
        System.out.println("Fetching contact...");
        Contact contact = new Contact();

        try {
            contact = contactManagerDao.fetchContact(id);
        } catch (Exception e) {
            System.out.println("Error fetching contact!");
            e.printStackTrace();
        }

        return contact;
    }

    @Override
    public String addContact(Contact contact) {
        System.out.println("Adding contact...");
        String responseMessage;
        contact.setStatus(ACTIVE);
        contact.setContactId(0);

        try {
            this.validateContactDetails(contact);
            int responseCode = contactManagerDao.addContact(contact);

            if (responseCode == 1) {
                responseMessage = "Success";
            } else {
                responseMessage = "Failed";
            }
        } catch (IllegalArgumentException ie) {
            System.out.println("Invalid contact details!");
            responseMessage = "Invalid contact details!";
            ie.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error adding contact!");
            responseMessage = "Failed";
            e.printStackTrace();
        }

        return responseMessage;
    }

    @Override
    public String updateContact(int id, Contact contact) {
        System.out.println("Updating contact...");
        String responseMessage;

        try {
            this.validateContactDetails(contact);
            int responseCode = contactManagerDao.updateContact(id, contact);

            if (responseCode == 1) {
                responseMessage = "Success";
            } else {
                responseMessage = "Failed";
            }
        } catch (IllegalArgumentException ie) {
            System.out.println("Invalid contact details!");
            responseMessage = "Invalid contact details!";
            ie.printStackTrace();

        } catch (Exception e) {
            System.out.println("Error updating contact!");
            responseMessage = "Failed";
            e.printStackTrace();
        }

        return responseMessage;
    }

    private void validateContactDetails(Contact contact) throws IllegalArgumentException {
        System.out.println("Validating contact details...");

        if (contact.getFirstName() == null || contact.getFirstName() == "") throw new IllegalArgumentException ();
        if (contact.getLastName() == null || contact.getLastName() == "") throw new IllegalArgumentException ();
        if (contact.getPhoneNumber().length() > 10) throw new IllegalArgumentException ();
        if (contact.getStatus() == null || contact.getFirstName() == "") throw new IllegalArgumentException ();
    }

    @Override
    public String activateContact(int id) {
        System.out.println("Activating contact...");
        String responseMessage;

        try {
            int responseCode = contactManagerDao.activateContact(id);

            if (responseCode == 1) {
                responseMessage = "Success";
            } else {
                responseMessage = "Failed";
            }
        } catch (Exception e) {
            System.out.println("Error activating contact!");
            responseMessage = "Failed";
            e.printStackTrace();
        }

        return responseMessage;
    }

    @Override
    public String deactivateContact(int id) {
        System.out.println("Deactivating contact...");
        String responseMessage;

        try {
            int responseCode = contactManagerDao.deactivateContact(id);

            if (responseCode == 1) {
                responseMessage = "Success";
            } else {
                responseMessage = "Failed";
            }
        } catch (Exception e) {
            System.out.println("Error deactivating contact!");
            responseMessage = "Failed";
            e.printStackTrace();
        }

        return responseMessage;
    }

    @Override
    public String deleteContact(int id) {
        System.out.println("Deleting contact...");
        String responseMessage;

        try {
            int responseCode = contactManagerDao.deleteContact(id);

            if (responseCode == 1) {
                responseMessage = "Success";
            } else {
                responseMessage = "Failed";
            }
        } catch (Exception e) {
            System.out.println("Error deleting contact!");
            responseMessage = "Failed";
            e.printStackTrace();
        }

        return responseMessage;
    }

}
