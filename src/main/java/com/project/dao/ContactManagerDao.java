package com.project.dao;

import com.project.bean.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ContactManagerDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final static String fetchContacts = "select * from contacts;";
    private final static String fetchContact = "select * from contacts where id = ?";
    private final static String insertContact = "insert into contacts (first_name, last_name, email, phone_number, status) values (?, ?, ?, ?, ?)";
    private final static String updateContact = "update contacts set first_name = ?, last_name = ?, email = ?, phone_number = ? where id = ?";
    private final static String activateContact = "update contacts set status = 'ACTIVE' where id = ?";
    private final static String deactivateContact = "update contacts set status = 'INACTIVE' where id = ?";
    private final static String deleteContact = "delete from contacts where id = ?";

    /**
     *
     * @return list of all the contacts sotred in database
     */
    public List<Contact> fetchContacts() {
        try {
            return this.jdbcTemplate.query(fetchContacts,
                new RowMapper<Contact>() {
                    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Contact contact = new Contact();
                        contact.setContactId(rs.getInt("id"));
                        contact.setFirstName(rs.getString("first_name"));
                        contact.setLastName(rs.getString("last_name"));
                        contact.setEmail(rs.getString("email"));
                        contact.setPhoneNumber(rs.getString("phone_number"));
                        contact.setStatus(rs.getString("status"));

                        return contact;
                    }
                });
        } catch (Exception e) {
            System.out.println("Error while fetching contact list from database.");
            e.printStackTrace();
            return new ArrayList<Contact>();
        }
    }

    /**
     *
     * @param id contactId of contact whose detail is required
     * @return list the details of single contact
     */
    public Contact fetchContact(int id) {
        try {
            return this.jdbcTemplate.queryForObject(fetchContact, new Object[] { id },
                new RowMapper<Contact>() {
                    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Contact contact = new Contact();
                        contact.setContactId(rs.getInt("id"));
                        contact.setFirstName(rs.getString("first_name"));
                        contact.setLastName(rs.getString("last_name"));
                        contact.setEmail(rs.getString("email"));
                        contact.setPhoneNumber(rs.getString("phone_number"));
                        contact.setStatus(rs.getString("status"));

                        return contact;
                    }
                });
        } catch (Exception e) {
            System.out.println("Error while fetching contact from database.");
            e.printStackTrace();
            return new Contact();
        }
    }

    /**
     *
     * @param contact contact details to be added
     * @return inserts a new contact in database with status ACTIVE and returns success or failure
     */
    public int addContact(Contact contact) {
        try {
            this.jdbcTemplate.update(insertContact, new Object[] { contact.getFirstName(), contact.getLastName(),
            contact.getEmail(), contact.getPhoneNumber(), contact.getStatus() });
            return 1;
        } catch (Exception e) {
            System.out.println("Error while adding contact to database.");
            e.printStackTrace();
            return 0;
        }
    }

    /**
     *
     * @param id contact id of the contact whose detail is to be udpated
     * @param contact contact details to be updated
     * @return updates contact deatils except id and status and return success or failure
     */
    public int updateContact(int id, Contact contact) {
        try {
            this.jdbcTemplate.update(updateContact, new Object[] { contact.getFirstName(), contact.getLastName(),
                    contact.getEmail(), contact.getPhoneNumber(), id });
            return 1;
        } catch (Exception e) {
            System.out.println("Error while updating contact in database.");
            e.printStackTrace();
            return 0;
        }
    }

    /**
     *
     * @param id contact id of the contact to be activated
     * @return updated the status of specified contact to ACTIVE
     */
    public int activateContact(int id) {
        try {
            this.jdbcTemplate.update(activateContact, new Object[] { id });
            return 1;
        } catch (Exception e) {
            System.out.println("Error while activating contact in database.");
            e.printStackTrace();
            return 0;
        }
    }

    /**
     *
     * @param id contact id of the contact to be deactivated
     * @return updated the status of specified contact to INACTIVE
     */
    public int deactivateContact(int id) {
        try {
            this.jdbcTemplate.update(deactivateContact, new Object[] { id });
            return 1;
        } catch (Exception e) {
            System.out.println("Error while deactivating contact in database.");
            e.printStackTrace();
            return 0;
        }
    }

    /**
     *
     * @param id contact id of the contact to be deleted
     * @return deletes contact details from database
     */
    public int deleteContact(int id) {
        try {
            this.jdbcTemplate.update(deleteContact, new Object[] { id });
            return 1;
        } catch (Exception e) {
            System.out.println("Error while deleting contact in database.");
            e.printStackTrace();
            return 0;
        }
    }

}
