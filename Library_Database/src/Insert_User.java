import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Insert_User")
public class Insert_User extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public Insert_User() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String firstName = request.getParameter("firstName");
      String lastName = request.getParameter("lastName");
      String accountNum = request.getParameter("accountNum");
      String accountBalance = null;
      String address = request.getParameter("address");
      String phone = request.getParameter("phone");
      String email = request.getParameter("email");

      Connection connection = null;
      String insertSql = " INSERT INTO Users (id, FIRST_NAME, LAST_NAME, ACCOUNT_NUM, ADDRESS, PHONE, EMAIL) values (default, ?,?,?,?,?,?)";

      try {
         LibraryDBConnection.getDBConnection();
         connection = LibraryDBConnection.connection;
         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
         preparedStmt.setString(1, firstName);
         preparedStmt.setString(2, lastName);
         preparedStmt.setString(3, accountNum);
         //preparedStmt.setString(4, accountBalance);
         preparedStmt.setString(4, address);
         preparedStmt.setString(5, phone);
         preparedStmt.setString(6, email);
         preparedStmt.execute();
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Insert New Library User";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h2 align=\"center\">" + title + "</h2>\n" + //
            "<ul>\n" + //

            "  <li><b>First Name</b>: " + firstName + "\n" + //
            "  <li><b>Last Name</b>: " + lastName + "\n" + //
            "  <li><b>Account Number</b>: " + accountNum + "\n" + //
           // "  <li><b>Account Balance</b>: " + accountBalance + "\n" + //
            "  <li><b>Address</b>: " + address + "\n" + //
            "  <li><b>Phone</b>: " + phone + "\n" + //
            "  <li><b>Email</b>: " + email + "\n" + //

            "</ul>\n");

      out.println("<a href=/Library_Database/Insert_user.html>Add Another</a> <br>");
      out.println("<a href=/Library_Database/Homepage.html>Return Home</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
