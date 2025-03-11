import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.*;            // Tomcat 10 (Jakarta EE 9)
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
@WebServlet("/eshopdisplay")
public class EshopDisplayServlet extends HttpServlet {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/ebookshop";
    private static final String JDBC_USER = "myuser";
    private static final String JDBC_PASSWORD = "xxxx";
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        List<String> authors = new ArrayList<>();
        
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement("SELECT DISTINCT author FROM books WHERE qty > 0");
             ResultSet rs = pstmt.executeQuery()) {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            while (rs.next()) {
                authors.add(rs.getString("author"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Yet Another e-Bookshop</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Yet Another e-Bookshop</h2>");
        out.println("<form method='get' action='eshopquery'>");
        out.println("Choose an author:<br /><br />");
        
        for (String author : authors) {
            out.println("<input type='checkbox' name='author' value='" + author + "' />" + author + "<br>");
        }
        
        out.println("<br>");
        out.println("<input type='submit' value='Search' />");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
}
