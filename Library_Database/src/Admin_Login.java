

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Admin_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	   public Admin_Login() {
	      super();
	   }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        
        String password = "adminPassword";
        String pass = request.getParameter("password");
        
        if(pass.equals(password))
        {
            RequestDispatcher rs = request.getRequestDispatcher("Homepage_Admin.html");
            rs.forward(request, response);
        }
        else
        {
           out.println("Password incorrect");
           RequestDispatcher rs = request.getRequestDispatcher("adminLogin.html");
           rs.include(request, response);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
     }
}
