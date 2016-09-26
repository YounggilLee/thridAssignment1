package ejd;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yglee
 */
public class Assignment1 {
   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String url = "jdbc:mysql://localhost:3306/ejd";
        String user = "root";
        String pass = "1234";
        

        Scanner in = new Scanner(System.in);

        JdbcHelper jh = new JdbcHelper();

        boolean connection = jh.connect(url, user, pass);

        String query1 = "select id, firstname, lastName from student inner join CourseStudent \n"
                + "on student.id = CourseStudent.studentId where CourseStudent.courseId = 'PROG10000'";

        String query2 = "select id, firstname, lastName from student inner join CourseStudent \n"
                + "on student.id = CourseStudent.studentId where CourseStudent.courseId = 'DBAS20000'";

        String query3 = "select id, firstname, lastName from student inner join CourseStudent \n"
                + "on student.id = CourseStudent.studentId where CourseStudent.courseId = 'MATH30000'";

        if (connection) {
            System.out.println("Please Select Course(1.PROG10000 2.DBAS20000 3.MATH30000");
            System.out.print("Enter Number: ");
            String input = in.nextLine();
            int userInput = Integer.parseInt(input);

            if (1 == userInput) {
                ResultSet resultSet = jh.query(query1);
                System.out.println("*** Student in PROG10000 Course ***");
                printResult(resultSet);
            }
            if (2 == userInput) {
                ResultSet resultSet = jh.query(query2);
                System.out.println("*** Student in PROG20000 Course ***");
                printResult(resultSet);
            }
            if (3 == userInput) {
                ResultSet resultSet = jh.query(query3);
                System.out.println("*** Student in PROG30000 Course ***");
                printResult(resultSet);
            }

        } else {
            System.out.println("please check jdbc connection");
        }

        jh.diconnect();
    }

     static void printResult(ResultSet resultSet) {
         
         ArrayList<Student> students = new ArrayList<>();
        try {
            while (resultSet.next()) {

                students.add(new Student(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)));

            }
        } catch (SQLException ex) {
            Logger.getLogger(JdbcHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (Student stdArray : students) {
            System.out.println(stdArray.getId() + "\t" + stdArray.getFirstName() + "\t" + stdArray.getLastName());  // Will invoke overrided `toString()` method
        }

        students.clear();
    }
    
    
}
