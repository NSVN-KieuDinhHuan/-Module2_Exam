package com.company.view;

import com.company.controller.ContactManagement;
import com.company.model.Contact;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public  static Scanner scanner =new Scanner(System.in);
    private static final String CONTACTCSV= "data/contacts.csv";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int choice = -1; //Để nhập lựa chọn của người dùng
        ContactManagement contactManagement =ContactManagement.getContactManagement();
        do {
            menu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1: {
                    System.out.println("---Danh bạ--");
                    contactManagement.displayAllContacts();
                    break;
                }
                case 2: {
                    addContactmenu(contactManagement);
                    break;
                }
                case 3: {
                    updateContactMenu(contactManagement);
                    break;
                }
                case 4:{
                    deleteContactMenu(contactManagement);
                    break;
                }
                case 5:{
                    findContactMenu(contactManagement);
                    break;
                }
                case  6:{
                    readCSV(contactManagement);
                    break;
                }

                case  7:{
                    writeCSV(contactManagement);
                    break;
                }
            }
        } while (choice != 8);
    }

    private static void findContactMenu(ContactManagement contactManagement) throws IOException, ClassNotFoundException {
        System.out.println("nhập vào số diện thoại hoac họ tên để tìm kiếm");
        scanner.nextLine();
        String key=scanner.nextLine();
        System.out.println( contactManagement.findContact(key));
    }

    private static void addContactmenu(ContactManagement contactManagement) throws IOException {
        System.out.println("---Thêm mới sản phẩm---");
        scanner.nextLine();
        Contact contact = inputContact();
        contactManagement.addNewContact(contact);
    }

    private static void deleteContactMenu(ContactManagement contactManagement) throws IOException {
        System.out.println("nhập vào số diện thoại hoac họ tên để xóa");
        scanner.nextLine();
        String removeKey = scanner.nextLine();
        contactManagement.removeContact(removeKey);
    }

    private static void updateContactMenu(ContactManagement contactManagement) {
        System.out.println("---Cập nhật thông tin Danh bạ---");
        System.out.println("Nhập vao số điện thoại");
        scanner.nextLine();
        String phoneNumber=scanner.nextLine();
        Contact contact = inputContact();
        contactManagement.updateContact(phoneNumber,contact);
    }

    private static void readCSV(ContactManagement contactManagement) throws IOException, ClassNotFoundException {
        System.out.println("Thao tác sẽ xóa toàn bộ danh bạ hiện tại và cập nhật toàn bộ từ file có sẵn!!");
        System.out.println("Đồng ý chạy?   (Y / N)");
        scanner.nextLine();
        String input = scanner.nextLine();
        char answer = input.charAt(0);
        char agree = 'y';
        if (answer == agree) {
            contactManagement.clearContactDataStorge();
            contactManagement.readFileCSV(CONTACTCSV);
            System.out.println("Thao tác thành công.");
        } else {
            System.out.println("Hủy thao tác.");
        }
    }

    private static void writeCSV(ContactManagement contactManagement) throws IOException, ClassNotFoundException {
        System.out.println("Ghi bộ nhớ hiện tại vào file csv");
        System.out.println("Đồng ý chạy?   (Y / N)");
        scanner.nextLine();
        String input = scanner.nextLine();
        char answer = input.charAt(0);
        char agree = 'y';
        if (answer == agree) {
            contactManagement.writeFileCSV(CONTACTCSV);
            System.out.println("Thao tác thành công.");
        } else {
            System.out.println("Hủy thao tác.");
        }
    }

    public static Contact inputContact(){
        System.out.println("Nhập thông tin của Danh bạ");
        System.out.println("số điện thoai:");
        String phoneNumberRegex="0[\\d]{9}";
        String phoneNumber = InputRegex(phoneNumberRegex);
        System.out.println("Nhập Nhóm danh bạ:");
        String contactGroup = scanner.nextLine();
        System.out.println("Họ Tên:");
        String name=scanner.nextLine();
        System.out.println("Nhập giới tính");
        String sex = scanner.nextLine();
        System.out.println("Nhập địa chỉ");
        String adress=scanner.nextLine();
        System.out.println("Nhập ngày Sinh");
        String dateOfBirth=scanner.nextLine();
        String emailRegex= "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
        System.out.println("Nhập Email");
        String email = InputRegex(emailRegex);
        return new Contact(phoneNumber,contactGroup,name,sex,adress,dateOfBirth,email);
    }

    private static String InputRegex(String regex) {
        boolean isvalid=false;
        String input="";
        do {
            input=scanner.nextLine();
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);
            isvalid = matcher.matches();
            if (isvalid==false){
                System.err.println("Nhập lại, Sai định dạng");
            }
        }while (isvalid==false);
        return input;
    }

    public static void menu() {
        System.out.println("---TRƯƠNG TRÌNH QUẢN LÝ DANH BẠ---");
        System.out.println("chọn chức năng theo số để tiếp tục");
        System.out.println("1. Xem danh sách");
        System.out.println("2. Thêm mới");
        System.out.println("3. Cập nhật");
        System.out.println("4. Xóa ");
        System.out.println("5. Tìm kiếm");
        System.out.println("6. Đọc từ file");
        System.out.println("7. Ghi vào file");
        System.out.println("8. Thoát");
    }
}
