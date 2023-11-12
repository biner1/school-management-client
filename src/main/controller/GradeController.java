package main.controller;

import main.utility.ConnectionStream;
import main.utility.JsonToList;
import main.utility.RequestHandler;

import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class GradeController {

    private static Map<String, Map<String, String>> response;
    private static final RequestHandler req = new RequestHandler(ConnectionStream.getOut(), ConnectionStream.getIn());
    private static final Scanner sc = new Scanner(System.in);


    public static int addGrade() {
        Map<String, String> values;
        String gradeName;
        int gradeYear;
        try {
            System.out.println("Enter Grade Name");
            gradeName = sc.nextLine();
            System.out.println("Enter Grade Year");
            gradeYear = Integer.parseInt(sc.nextLine());

            values = Map.of("gradeName", gradeName, "gradeYear", String.valueOf(gradeYear));

            req.setRequest("values", values);
            response = req.sendRequest("grade", "POST");
            return Integer.parseInt(response.get("values").get("count"));
        } catch (Exception e) {
            System.out.println("invalid inputs");
        }
        return 0;
    }// end of addGrade()

    public static int getGradeCount() {
        response = req.sendRequest("getgradecount");
        return Integer.parseInt(response.get("values").get("count"));
    }// end of getGradeCount()

    public static LinkedList<Map<String, String>> getGrades() {
        response = req.sendRequest("allgrade");
        return JsonToList.jsonListToList(response.get("values").get("grades"));
    }// end of getGrades()

    public static String getStudentsOfGrade() {
        return null;
    }// end of getStudentsOfGrade()

    public static int deleteGrade() {
        Map<String, String> values;
        int gradeYear;
        String gradeName;
        try {
            System.out.println("Enter Grade Name");
            gradeName = sc.nextLine();
            System.out.println("Enter Grade Year");
            gradeYear = Integer.parseInt(sc.nextLine());

            values = Map.of("gradeName", gradeName, "gradeYear", String.valueOf(gradeYear));
            req.setRequest("values", values);
            response = req.sendRequest("grade", "DELETE");
            System.out.println(response.get("header").get("message"));
            return 1;
        } catch (Exception e) {
            System.out.println("invalid inputs");
        }
        return 0;
    }// end of deleteGrade()
}
