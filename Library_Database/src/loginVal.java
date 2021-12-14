

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

public class loginVal {
    public static boolean checkUser(String email,String password) 
    {
        boolean st =false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {

            //loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");

            //creating connection with the database
            LibraryDBConnection.getDBConnection();
            connection = LibraryDBConnection.connection;
            preparedStatement = connection.prepareStatement("SELECT * FROM LOGIN WHERE email=? and password=?");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            st = rs.next();

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return st;                 
    }   
}

