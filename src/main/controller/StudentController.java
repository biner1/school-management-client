package main.controller;

import main.utility.ConnectionStream;
import main.utility.JsonToList;
import main.utility.RequestHandler;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StudentController {
    private static Map<String, Map<String, String>> response;
    private static final RequestHandler req = new RequestHandler(ConnectionStream.getOut(), ConnectionStream.getIn());
    private static final Scanner sc = new Scanner(System.in);

    public static int addStudent() {
        Map<String, String> values;
        String username, gender, date, email, address, phone, password;
        int gradeId;
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
            System.out.println("Enter gradeId");
            gradeId = Integer.parseInt(sc.nextLine());
            System.out.println("Enter Password");
            password = sc.nextLine();

            values = Map.of("username", username,
                    "gender", gender, "date", date
                    , "email", email, "address", address, "phone", phone
                    , "gradeId", String.valueOf(gradeId), "password", password);

            req.setRequest("values", values);
            response = req.sendRequest("student", "POST");
            return Integer.parseInt(response.get("values").get("count"));
        } catch (Exception e) {
            System.out.println("invalid inputs");
        }
        return 0;
    }// end of addStudent()

    public static String getStudentCount() {
        response = req.sendRequest("getstudentcount");
        return response.get("values").get("count") + "";
    }// end of getStaffCount()

    public static int deleteStudentUsername() {
        Map<String, String> values;
        String username;
        try {
            System.out.println("Enter Student Username");
            username = sc.nextLine();
            values = Map.of("username", username);
            req.setRequest("values", values);
            response = req.sendRequest("studentusername", "DELETE");
            System.out.println(response.get("header").get("message"));
            return 1;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return 0;
    }// end of deleteStudentUsername()

    public static int deleteStudentId() {
        Map<String, String> values;
        int id;
        try {
            System.out.println("Enter Student ID");
            id = Integer.parseInt(sc.nextLine());
            values = Map.of("id", String.valueOf(id));
            req.setRequest("values", values);
            response = req.sendRequest("studentid", "DELETE");
            System.out.println(response.get("header").get("message"));
            return 1;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return 0;
    }// end of deleteStudentId()

    public static LinkedList<Map<String, String>> getStudents() {
        response = req.sendRequest("allstudent");
        Map<String, String> students = response.get("values");
        return JsonToList.jsonListToList(students.get("students"));
    }// end of getStudents()

    public static LinkedList<Map<String, String>> getStudentsUsername() {
        Map<String, String> values;
        String username;

        try {
            System.out.println("Enter Student Username");
            username = sc.nextLine();
            values = Map.of("username", username);
            req.setRequest("values", values);
            response = req.sendRequest("studentusername");
            Map<String, String> students = response.get("values");
            return JsonToList.jsonListToList(students.get("students"));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return new LinkedList<Map<String, String>>();
    }// end of getStudentUsername()

    public static void displayStudent(LinkedList<Map<String, String>> studentList) {
        System.out.println(response.get("header").get("message"));
        System.out.println("id|username|gender|birthdate|email|address|phone|gradeId");
        for (Map<String, String> student : studentList) {
            System.out.println(student.get("id") + "|" + student.get("username") + "|" +
                    student.get("gender") + "|" + student.get("birthdate") + "|" +
                    student.get("email") + "|" + student.get("address") + "|" +
                    student.get("phone") + "|" + student.get("gradeId"));
        }
    }// end of displayStudent()

}
