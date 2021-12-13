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

@WebServlet("/Search_Books")
public class Search_Books extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public Search_Books() {
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
            String selectSQL = "SELECT * FROM Books";
            preparedStatement = connection.prepareStatement(selectSQL);
         } else {
            String selectSQL = "SELECT * FROM Books WHERE TITLE LIKE ? || GENRE LIKE ? ";
            String input = keyword + "%";
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, input);
            preparedStatement.setString(2, input);
         }
         ResultSet rs = preparedStatement.executeQuery();

         while (rs.next()) {
            int id = rs.getInt("id");
            String book_title = rs.getString("TITLE").trim();
            String author = rs.getString("AUTHOR").trim();
            String isbn = rs.getString("ISBN").trim();
            String genre = rs.getString("GENRE").trim();
            String total_copies = rs.getString("TOTAL_COPIES").trim(); 
            String available_copies = rs.getString("AVAILABLE_COPIES").trim(); 
  

            if (keyword.isEmpty() || book_title.contains(keyword) || genre.contains(keyword)) {
               out.println("ID: " + id + "<br>");
               out.println("Title: " + book_title + "<br>");
               out.println("Author: " + author + "<br>");
               out.println("ISBN Number: " + isbn + "<br>");
               out.println("Genre: " + genre + "<br>");
               out.println("Total Copies: " + total_copies + "<br>");
               out.println("Available Copies: " + available_copies + "<br>");
               out.println("<br>");
            }
         }
         out.println("<a href=/Library_Database/Search_Books.html>Search Another</a> <br>");
         out.println("<a href=/Library_Database/Homepage_User.html>Return Home</a> <br>");
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

