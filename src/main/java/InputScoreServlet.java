package main.java.yes;

import main.java.yes.ScoreOption;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import java.sql.*;
import java.util.Arrays;
import java.util.ArrayList;


@WebServlet(
        name = "InputScoreServlet",
        urlPatterns = "/inputscore"
)
public class InputScoreServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("got to this point");
        // String scoreOption = req.getParameter("Type");

        RoseBoard roseboard = new RoseBoard();
        // ScoreOption l = ScoreOption.valueOf(req.getParameter("Winner"));
        String winner = req.getParameter("Winner");
        String details = req.getParameter("Details");
        // List results = new ArrayList();
        // String []results = new String[2];
        // results[0] = winner;
        // results[1] = details;
        // List results = roseboard.verifyInput(l);

        // List<String> results = Arrays.asList(winner, details);
        ArrayList<String> results = new ArrayList();
        results.add(winner);
        results.add(details);
        try {
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/data","webapp","password");
            Statement stmt=con.createStatement();
            String data_insert = "INSERT INTO reports (winner, situation) VALUES (?,?)";
            // String data_insert = "INSERT INTO reports SET winner = results[0], situation = 'results[1]'";
            // stmt.executeUpdate(data_insert);
            PreparedStatement preparedStmt = con.prepareStatement(data_insert);
            preparedStmt.setString (1, winner);
            preparedStmt.setString (2, details);
            preparedStmt.execute();
            ResultSet rs=stmt.executeQuery("select * from reports");  
            while(rs.next())  {
                // System.out.println(rs.getString(1)+"  "+rs.getString(2));  
                results.add(rs.getString(1));
                results.add(rs.getString(2));
            }
            con.close(); 
        }
        catch(Exception e){ System.out.println(e);}  {
        } 
        
        for (String val:results) {
            System.out.println(val);
        }
        req.setAttribute("results", results);
        RequestDispatcher view = req.getRequestDispatcher("result.jsp");
        view.forward(req, resp);
        System.out.println("got to this second");
        // System.out.println(results.get(1));

    // try{  
    //     // Class.forName("com.mysql.jdbc.Driver");  
    //     Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/data","webapp","password");  
    //     //here sonoo is database name, root is username and password  
    //     Statement stmt=con.createStatement();  
    //     ResultSet rs=stmt.executeQuery("select * from reports");  
    //     while(rs.next())  
    //     System.out.println(rs.getString(1)+"  "+rs.getString(2));  
    //     con.close(); 


    // }
    // catch(Exception e){ System.out.println(e);}  
    // } 

 
}
}