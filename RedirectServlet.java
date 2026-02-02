import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String searchQuery = request.getParameter("searchQuery");
        
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            String googleUrl = "https://www.google.com/search?q=" + searchQuery.trim().replace(" ", "+");
            response.sendRedirect(googleUrl);
        } else {
            response.sendRedirect("redirect.html");
        }
    }
}