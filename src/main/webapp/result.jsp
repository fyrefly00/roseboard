<!DOCTYPE html>
<html>
<head>
    <title>Scoreboard</title>
    <%@page import="java.sql.*;"%>
</head>
<body>
    <%
    int rpoints = 0;
    int tpoints = 0;
    try
    {
        Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/data","webapp","password");
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery("select * from reports;");
    %><table border=1 align=center style="text-align:center">
      <thead>
          <tr>
             <th>Winner</th>
             <th>Details</th>
          </tr>
      </thead>
      <tbody>
        <%while(rs.next())
        {
            String winner =rs.getString("winner");
            String situation = rs.getString("situation");
            if(winner.equals("Point for Rose")) {
                rpoints ++;
            }
            else if(winner.equals("Point for Technology")) {
                tpoints++;
            }
            %>
            <tr>
      
                <td><%=rs.getString("winner")%></td>
                <td><%=rs.getString("situation")%></td>
            </tr>
            <%}%>
            
           </tbody>
        </table><br>


    
 
    

    <%
    st.close();
    con.close();
    out.println("<br>Total Score <br><br>");
    out.println("<br>Rose: " + rpoints + "<br><br>");
    out.println("<br>Technology: " + tpoints +  "<br><br>");

    }


    catch(Exception e){
        out.print(e.getMessage());%><br><%
    }




    %>
</body>
<center>
<form method="post" action="inputscore">
    <br>
     <select name="Winner" size="1">
         <option>Point for Rose</option>
         <option>Point for Technology</option>
     </select>
     <br><br>
     <label for="Details">Details:</label>
     <input type="text" id="scoredetails" name="Details"><br><br>
     <input type="submit">
 </form>
</center>
</html>