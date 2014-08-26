package com.myandroid.itsbryan.addressbookapp.repository;
import com.myandroid.itsbryan.addressbookapp.domain.Contact;
import java.util.List;

/**
 * Created by its.Bryan on 8/20/2014.
 */
public interface DataSourceDAO {
    /*
     *   PERFORM CRUD OPERATIONS
     */
    public void createContact(Contact contact);
    public void updateContact(Contact contact);
    public Contact findContactDetailsByID(int id);
    public void deleteContact(Contact contact);
    public Contact getContact();
    public List<Contact> getAllContactsList();
    public int getNumberOfContacts();
}
