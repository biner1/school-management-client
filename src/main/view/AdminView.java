package main.view;

import main.controller.StaffController;
import main.controller.StudentController;
import main.controller.GradeController;

import java.util.Map;
import java.util.Scanner;

public class AdminView {
    Map<String, String> loggedInAdmin;

    Scanner sc;

    public AdminView(Map<String, String> admin) {
        loggedInAdmin = admin;
        sc = new Scanner(System.in);
    }

    public void login() {


        System.out.println("You are logged in as admin: " + loggedInAdmin.get("username"));
        int choice;
        while (true) {
            System.out.println("__________Enter admin choice___________");
            System.out.println("\n1.Staff operations\n2.Student operations\n3.Grades operations\n0.LogOut");

            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice == 1) {
                    staffOps();
                } else if (choice == 2) {
                    studentOps();
                } else if (choice == 3) {
                    gradeOps();
                } else if (choice == 0) {
                    break;
                } else {
                    System.out.println("please enter a given choice");
                }
            } catch (Exception e) {
                System.out.println("invalid input");
            }
        }// end of while
    }

    //____________________________Choice types______________________

    public void staffOps() {

        int choice;
        while (true) {
            System.out.println("\n_______Staff Operations________");
            System.out.println("""
                    1.Add Staff
                    2.Print number of Staffs
                    3.Display all staff
                    4.Delete Staff
                    0.back to main""");

            try {

                choice = Integer.parseInt(sc.nextLine());
                if (choice == 1) {
                    System.out.println("add staff");
                    int added = StaffController.addStaff();
                    if (added == 0) {
                        System.out.println("No staff added");
                    } else {
                        System.out.println("staff added successfully");
                    }
                } else if (choice == 2) {
                    System.out.println("the number of staffs is: " + StaffController.getStaffCount());
                } else if (choice == 3) {
                    StaffController.displayStaff(StaffController.getStaffs());
                } else if (choice == 4) {
                    StaffController.deleteStaff();
                } else if (choice == 0) {
                    break;
                } else {
                    System.out.println("Enter a valid choice");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("invalid choice");
            }
        }// end of while
    }// end of staffOps()

    public void studentOps() {

        int choice;
        while (true) {
            System.out.println("\n_______Student Operations________");
            System.out.println("""
                    1.Add Student
                    2.Print number of Students
                    3.Display all students
                    4.Display students by name
                    5.Delete student by name
                    6.Delete student by id
                    0.back to main""");

            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice == 1) {
                    System.out.println("add student");
                    int added = StudentController.addStudent();
                    if (added == 0) {
                        System.out.println("No staff added");
                    } else {
                        System.out.println("staff added successfully");
                    }
                } else if (choice == 2) {
                    System.out.println("the number of students is: " + StudentController.getStudentCount());
                } else if (choice == 3) {
                    StudentController.displayStudent(StudentController.getStudents());
                } else if (choice == 4) {
                    StudentController.displayStudent(StudentController.getStudentsUsername());
                } else if (choice == 5) {
                    StudentController.deleteStudentUsername();
                } else if (choice == 6) {
                    StudentController.deleteStudentId();
                } else if (choice == 0) {
                    break;
                } else {
                    System.out.println("Enter a valid choice");
                }
            } catch (Exception e) {
                System.out.println("invalid choice");
            }
        }// end of while
    } // end of studentOps()

    public void gradeOps() {

        int choice;
        while (true) {
            System.out.println("\n_______Grade Operations________");
            System.out.println("""
                    1.Add Grade
                    2.print number of Grades
                    3.Display all Grades
                    4.Display all students of a grade
                    5.Delete Grade
                    0.back to main""");
            try {

                choice = Integer.parseInt(sc.nextLine());
                if (choice == 1) {
                    System.out.println("add grade");
                    int added = GradeController.addGrade();
                    if (added == 0) {
                        System.out.println("No grade added");
                    } else {
                        System.out.println("grade added successfully");
                    }
                } else if (choice == 2) {
                    System.out.println("the number of grades are: " + GradeController.getGradeCount());
                } else if (choice == 3) {
                    System.out.println("display all grades");
                    String rs = null;
                    if (rs != null) {
                        System.out.println("displaying all grades");
                    } else {
                        System.out.println("No grades found");
                    }
                } else if (choice == 4) {
                    System.out.println("display all students of a grade");
                } else if (choice == 5) {
                    GradeController.deleteGrade();
                } else if (choice == 0) {
                    break;
                } else {
                    System.out.println("Enter a valid choice");
                }
            } catch (Exception e) {
                System.out.println("invalid input");
            }
        }// end of while
    }


    //____________________________________Grades operations__________________________
//
//    public void addGrade(){
//
//        int gradeYear;
//        String gradeName;
//
//        try {
//            System.out.println("Enter gradeName");
//            
//            gradeName = sc.nextLine();
//            System.out.println("Enter gradeYear");
//            
//            gradeYear = Integer.parseInt(sc.nextLine());
//            Grade grade = new Grade(1,gradeName,gradeYear);
//
//            int gradeAdded = GradeController.addGrade(grade);
//            if(gradeAdded == 0)
//                System.out.println("grade "+gradeName +" not added");
//            else
//                System.out.println("grade "+gradeName +" added");
//        }catch (Exception e){
//            System.out.println("invalid inputs");
//        }
//    }// end of addGrade()


//    public void printGrades(ResultSet grList){
//
//        try {
//            System.out.println("id|gradeName|gradeYear");
//            while (grList.next()) {
//                System.out.println(grList.getInt("id") + "|" + grList.getString("name") + "|" +
//                        grList.getInt("year"));
//            }
//        }catch (SQLException e){
//            System.err.println(e.getMessage());
//        }
//
//    }// end of printGrades(ResultSet)
//
//    public void printGradeStudents(){
//
//        String name = null;
//        int year = 0;
//        try {
//            System.out.println("Enter Grade Name");
//            
//            name = sc.nextLine();
//            System.out.println("Enter Grade Year");
//            
//            year = Integer.parseInt(sc.nextLine());
//            ResultSet stList = GradeController.getGradeStudents(name,year);
//            if(stList == null) {
//                System.out.println("There is no students in this grade");
//            }
//            else {
//                while(stList.next()){
//                    System.out.println(stList.getString("username") + "|" + stList.getString("gender")+ "|" +
//                            stList.getString("address") + "|" + stList.getString("phone") + "|" +
//                            stList.getString("name"));
//                }
//            }
//        } catch (IOException e) {
//            System.err.println(e.getMessage());
//        }catch (SQLException e){
//            System.err.println("SQLException in printGradeStudents()");
//            System.err.println(e.getMessage());
//        }
//
//    }//printGradeStudents()

}
