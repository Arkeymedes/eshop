import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/eshopdisplay")
public class EshopDisplayServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<String> authors = new ArrayList<>();
        List<String> genres = new ArrayList<>();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(
                   "jdbc:mysql://localhost:3306/ebookshop?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                   "myuser", "xxxx")) {
                
                // Get all authors
                try (PreparedStatement pstmt = conn.prepareStatement("SELECT DISTINCT author FROM books WHERE qty > 0");
                     ResultSet rs = pstmt.executeQuery()) {
                    
                    while (rs.next()) {
                        authors.add(rs.getString("author"));
                    }
                }
                
                // Get all genres
                try (PreparedStatement pstmt = conn.prepareStatement("SELECT DISTINCT genre FROM books WHERE qty > 0");
                     ResultSet rs = pstmt.executeQuery()) {
                    
                    while (rs.next()) {
                        genres.add(rs.getString("genre"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
        }
        
        // Store the authors and genres lists in request scope
        request.setAttribute("authors", authors);
        request.setAttribute("genres", genres);
        
        // Forward to the JSP page
        request.getRequestDispatcher("/ebookshop.jsp").forward(request, response);
    }
}