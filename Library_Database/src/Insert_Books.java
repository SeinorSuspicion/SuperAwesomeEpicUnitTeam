import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Insert_Books")
public class Insert_Books extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public Insert_Books() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String book_title = request.getParameter("book_title");
      String author = request.getParameter("author");
      String isbn = request.getParameter("isbn");
      String genre = request.getParameter("genre");
      String total_copies = request.getParameter("total_copies");
      String available_copies  = request.getParameter("available_copies");
  

      Connection connection = null;
      String insertSql = " INSERT INTO Books (id, TITLE, AUTHOR, ISBN, GENRE, TOTAL_COPIES, AVAILABLE_COPIES) values (default, ?,?,?,?,?,?)";

      try {
         LibraryDBConnection.getDBConnection();
         connection = LibraryDBConnection.connection;
         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
         preparedStmt.setString(1, book_title);
         preparedStmt.setString(2, author);
         preparedStmt.setString(3, isbn);
         preparedStmt.setString(4, genre);
         preparedStmt.setString(5, total_copies);
         preparedStmt.setString(6, available_copies);
         preparedStmt.execute();
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Add New Book";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h2 align=\"center\">" + title + "</h2>\n" + //
            "<ul>\n" + //

            "  <li><b>Title</b>: " + book_title + "\n" + //
            "  <li><b>Author</b>: " + author + "\n" + //
            "  <li><b>ISBN</b>: " + isbn + "\n" + //
            "  <li><b>Genre</b>: " + genre + "\n" + //
            "  <li><b>Total Copies</b>: " + total_copies + "\n" + //
            "  <li><b>Available Copies</b>: " + available_copies + "\n" + //

            "</ul>\n");

      out.println("<a href=/Library_Database/Insert_Books.html>Add Another</a> <br>");
      out.println("<a href=/Library_Database/Homepage_Admin.html>Return Home</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
