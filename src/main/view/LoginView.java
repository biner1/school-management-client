package main.view;

import java.io.*;
import java.util.Map;
import java.util.Scanner;

import main.utility.RequestHandler;
import main.utility.ConnectionStream;

public class LoginView {

    public void login() throws IOException {

        Map<String, Map<String, String>> response;
        RequestHandler req = new RequestHandler(ConnectionStream.getOut(),ConnectionStream.getIn());
        Scanner sc = new Scanner(System.in);

        System.out.println("______________Login view__________________");
        String username;
        String password;

        while (true) {
            System.out.println("Enter UserName");
            username = sc.nextLine();
            System.out.println("Enter Password");
            password = sc.nextLine();

            req.setRequest("values", Map.of("password", password, "username", username));
            response = req.sendRequest("login","POST");

            if(response.get("header").get("status").equals("failed")){
                System.out.println("black");
                System.exit(1);
            }
            String role = response.get("values").get("role");
            System.out.println(role);

            switch (role) {
                case "ADMIN" -> {
                    AdminView admin = new AdminView(response.get("values"));
                    admin.login();
                }
                case "TEACHER" -> {
                    System.out.println("login as teacher");
//                        TeacherView teacher = new TeacherView(s);
//                        teacher.login(out,in);
                }
                case "ACCOUNTANT" -> {
                    System.out.println("login as accountant");
//                        main.view.AccountantView accountant = new main.view.AccountantView(s);
//                        accountant.login(out,in);
                }
            } // end of switch
        }// end of while

    }// end of login()
}
