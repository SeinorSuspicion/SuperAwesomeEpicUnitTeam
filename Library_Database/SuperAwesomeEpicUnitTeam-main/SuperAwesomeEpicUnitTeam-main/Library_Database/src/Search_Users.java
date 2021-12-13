import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Search_Users")
public class Search_Users extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public Search_Users() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String keyword = request.getParameter("keyword");
      search(keyword, response);
   }

   void search(String keyword, HttpServletResponse response) throws IOException {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Database Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
            "transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");

      Connection connection = null;
      PreparedStatement preparedStatement = null;
      try {
    	  LibraryDBConnection.getDBConnection();
         connection = LibraryDBConnection.connection;

         if (keyword.isEmpty()) {
            String selectSQL = "SELECT * FROM Users";
            preparedStatement = connection.prepareStatement(selectSQL);
         } else {
            String selectSQL = "SELECT * FROM Users WHERE ACCOUNT_NUM LIKE ?"; //; || (FIRST_NAME LIKE ? && LAST_NAME LIKE ?)";
            String theName = keyword + "%";
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, theName);
            //preparedStatement.setString(2, theName);
         }
         ResultSet rs = preparedStatement.executeQuery();

         while (rs.next()) {
            int id = rs.getInt("id");
            String first_name = rs.getString("FIRST_NAME").trim();
            String last_name = rs.getString("LAST_NAME").trim();
            String account_num = rs.getString("ACCOUNT_NUM").trim();
            //String account_balance = rs.getString("ACCOUNT_BALANCE").trim();
            String address = rs.getString("ADDRESS").trim();
            String phone = rs.getString("PHONE").trim(); 
            String email = rs.getString("EMAIL").trim(); 
  

            if (keyword.isEmpty() || account_num.contains(keyword) || (first_name.contains(keyword) && last_name.contains(keyword) )) {
               out.println("ID: " + id + "<br>");
               out.println("User's Name: " + first_name + " " + last_name + "<br>");
               out.println("Account Number: " + account_num + "<br>");
               //out.println("Account Balance: " + account_balance + "<br>");
               out.println("Address: " + address + "<br>");
               out.println("Phone: " + phone + "<br>");
               out.println("Email: " + email + "<br>");
               out.println("HELLO MICHELLE <br>");
               out.println("<br>");
            }
         }
         out.println("<a href=/Library_Database/Search_Users.html>Search Another</a> <br>");
         out.println("<a href=/Library_Database/Homepage.html>Return Home</a> <br>");
         out.println("</body></html>");
         rs.close();
         preparedStatement.close();
         connection.close();
      } catch (SQLException se) {
         se.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if (preparedStatement != null)
               preparedStatement.close();
         } catch (SQLException se2) {
         }
         try {
            if (connection != null)
               connection.close();
         } catch (SQLException se) {
            se.printStackTrace();
         }
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
