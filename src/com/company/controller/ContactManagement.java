package com.company.controller;

import com.company.model.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactManagement implements ReadFile,WriteFile{
    List<Contact> contactList=new ArrayList<>();
    private static final String CONTACT_DATA_STORAGE="contactDataStorage.txt";

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    private static ContactManagement instance;
    public static ContactManagement getContactManagement(){
        if (instance ==null){
            instance =new ContactManagement();
        }
        return instance;
    }
    public ContactManagement() {
        File file1 = new File(CONTACT_DATA_STORAGE);
        if (file1.exists()) {
            try {
                readFile(CONTACT_DATA_STORAGE);

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else {
            try {
                writeFile(CONTACT_DATA_STORAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void displayAllContacts() throws IOException, ClassNotFoundException {
        if (contactList.size()>0){
            for (int i = 0; i < contactList.size(); i++) {
                System.out.println(contactList.get(i));
            }
        }else {
            System.err.println("Trống");
        }
    }
    public void addNewContact(Contact contact) throws IOException {
        contactList.add(contact);
        writeFile(CONTACT_DATA_STORAGE);
    }

    public void updateContact( String phoneNumber,Contact contact) {
        int index=-1;
        for (int i = 0; i < contactList.size(); i++) {
            if (contactList.get(i).getPhoneNumber().equals(phoneNumber) ){
                index=i;
            }
        }
        if ( index!=-1) {
            this.contactList.set(index,contact);
        }else {
            System.out.println("không có trong danh bạ");
        }
    }
    public  void removeContact(String keyword) throws IOException {
        Contact contact=null;
        for (int i = 0; i < contactList.size(); i++) {
            if (contactList.get(i).getPhoneNumber().equals(keyword) || contactList.get(i).getName().equals(keyword)){
                contact=contactList.get(i);
            }
        }
        if ( contact!=null) {
            this.contactList.remove(contact);
            writeFile(CONTACT_DATA_STORAGE);
        }else {
            System.out.println("không có trong danh bạ");
        }
    }
   public Contact findContact(String keyword) throws IOException, ClassNotFoundException {
       readFile(CONTACT_DATA_STORAGE);
        Contact contact=null;
       for (int i = 0; i < contactList.size(); i++) {
           if (contactList.get(i).getPhoneNumber().toLowerCase().contains(keyword.toLowerCase()) || contactList.get(i).getName().toLowerCase().contains(keyword.toLowerCase())){
               contact=contactList.get(i);
           }
       }
        return contact;
   }

   public void clearContactDataStorge() throws IOException {
       contactList.clear();
       writeFile(CONTACT_DATA_STORAGE);
   }
    @Override
    public void readFile(String path) throws IOException, ClassNotFoundException {
        InputStream is = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(is);
        this.contactList = (List<Contact>) ois.readObject();
    }


    @Override
    public void writeFile(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(this.contactList);
    }

    public void readFileCSV(String path) throws IOException, ClassNotFoundException {
        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = null;
        while ((line = reader.readLine()) != null) {
            String[] splitData = line.split(",");
            Contact contact = new Contact(splitData[0], splitData[1], splitData[2], splitData[3], splitData[4], splitData[5], splitData[6]);
            contactList.add(contact);
        }
        reader.close();
        writeFile(CONTACT_DATA_STORAGE);
    }

    public void writeFileCSV(String path) throws IOException, ClassNotFoundException {
        FileWriter writer=new FileWriter(path);
        for (int i = 0; i < contactList.size(); i++) {
            writer.write(contactList.get(i).toString());
        }
        writer.close();

    }

}
