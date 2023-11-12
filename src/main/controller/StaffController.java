package main.controller;


import main.utility.RequestHandler;
import main.utility.ConnectionStream;
import main.utility.JsonToList;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StaffController {

    private static Map<String, Map<String, String>> response;
    private static final RequestHandler req = new RequestHandler(ConnectionStream.getOut(), ConnectionStream.getIn());
    private static final Scanner sc = new Scanner(System.in);


    public static String getStaffCount() {
        response = req.sendRequest("getstaffcount");
        return response.get("values").get("count") + "";
    }// end of getStaffCount()

    public static int addStaff() {
        Map<String, String> values;
        String username, gender, date, email, address, phone, password;
        String role;
        try {
            System.out.println("Enter Username");
            username = sc.nextLine();
            System.out.println("Enter Gender");
            gender = sc.nextLine();
            System.out.println("Enter Birthdate");
            date = sc.nextLine();
            System.out.println("Enter Email");
            email = sc.nextLine();
            System.out.println("Enter Address");
            address = sc.nextLine();
            System.out.println("Enter Phone");
            phone = sc.nextLine();
            System.out.println("Enter Role");
            role = sc.nextLine();
            System.out.println("Enter Salary");
            String salary = String.valueOf(Integer.parseInt(sc.nextLine()));
            System.out.println("Enter Password");
            password = sc.nextLine();

            values = Map.of("username", username,
                    "gender", gender, "date", date
                    , "email", email
                    , "address", address
                    , "phone", phone
                    , "role", role
                    , "salary", salary
                    , "password", password);

            req.setRequest("values", values);
            response = req.sendRequest("staff", "POST");
            return Integer.parseInt(response.get("values").get("count"));
        } catch (Exception e) {
            System.out.println("invalid inputs");
        }
        return 0;
    }// end of addStaff()

    public static List<Map<String, String>> getStaffs() {
        response = req.sendRequest("allstaff");
        Map<String, String> staffs = response.get("values");
        List<Map<String, String>> staffsList = JsonToList.jsonListToList(staffs.get("staffs"));
        return staffsList;
    }// end of getStaffs()

    public static void deleteStaff() {
        System.out.println("Enter Staff username to remove");
        String username = sc.nextLine();
        req.setRequest("values", Map.of("username", username));
        response = req.sendRequest("staff", "DELETE");
        System.out.println(response.get("header").get("message"));
    }// end of deleteStaff()

    public static void displayStaff(List<Map<String, String>> staffsList){
        System.out.println("id|username|gender|birthdate|email|address|phone|role|salary");
        for (Map<String, String> staff : staffsList) {
            System.out.println(staff.get("id") + "|" + staff.get("username") + "|" +
                    staff.get("gender") + "|" + staff.get("birthdate") + "|" +
                    staff.get("email") + "|" + staff.get("address") + "|" +
                    staff.get("phone") + "|" + staff.get("role") + "|" +
                    staff.get("salary"));
        }
    }// end of displayStaff()

}
