package main.java.yes;

// import main.java.yes.ScoreOption;

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
        String winner = req.getParameter("Winner");
        String details = req.getParameter("Details");
        ArrayList<String> results = new ArrayList();
        results.add(winner);
        results.add(details);
        try {
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/data","webapp","password");
            Statement stmt=con.createStatement();
            String data_insert = "INSERT INTO reports (winner, situation) VALUES (?,?)";
            PreparedStatement preparedStmt = con.prepareStatement(data_insert);
            preparedStmt.setString (1, winner);
            preparedStmt.setString (2, details);
            preparedStmt.execute();
            ResultSet rs=stmt.executeQuery("select * from reports");  
            while(rs.next())  {
                results.add(rs.getString(1));
                results.add(rs.getString(2));
            }
            con.close(); 
        }
        catch(Exception e){ System.out.println(e);}  {
        } 

        req.setAttribute("results", results);
        RequestDispatcher view = req.getRequestDispatcher("result.jsp");
        view.forward(req, resp);
}
}