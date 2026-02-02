import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Login Result</title></head><body>");
        
        if (password.length() < 8) {
            out.println("<h2>Hello " + username + ", your password is weak. Try a strong one.</h2>");
        } else {
            out.println("<h2>Welcome " + username + "</h2>");
        }
        
        out.println("<br><a href='login.html'>Back to Login</a>");
        out.println("</body></html>");
        out.close();
    }
}