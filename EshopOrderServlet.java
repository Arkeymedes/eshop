import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/eshoporder")
public class EshopOrderServlet extends HttpServlet {

   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      
      out.println("<!DOCTYPE html>");
      out.println("<html lang='en'>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
      out.println("<title>EBook Shop - Order Confirmation</title>");
      out.println("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css'>");
      out.println("<style>");
      out.println(":root {");
      out.println("--primary-color: #3498db;");
      out.println("--secondary-color: #2c3e50;");
      out.println("--accent-color: #e74c3c;");
      out.println("--light-color: #ecf0f1;");
      out.println("--dark-color: #34495e;");
      out.println("--success-color: #2ecc71;");
      out.println("}");
      out.println("* { margin: 0; padding: 0; box-sizing: border-box; }");
      out.println("body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; line-height: 1.6; color: var(--dark-color); background-color: #c38e7c; }");
      out.println(".container { width: 90%; max-width: 1200px; margin: 0 auto; }");
      out.println("header { background-color: #8e7cc3; color: white; padding: 1rem 0; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }");
      out.println(".header-content { display: flex; justify-content: space-between; align-items: center; }");
      out.println(".logo { font-size: 1.8rem; font-weight: bold; display: flex; align-items: center; }");
      out.println(".logo i { margin-right: 0.5rem; }");
      out.println("main { padding: 2rem 0; }");
      out.println(".order-container { background-color: #f9f9f9; padding: 2rem; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }");
      out.println(".section-title { color: var(--secondary-color); margin-bottom: 1.5rem; text-align: center; font-size: 1.8rem; }");
      out.println(".order-success { text-align: center; margin-bottom: 2rem; }");
      out.println(".order-success i { font-size: 4rem; color: var(--success-color); margin-bottom: 1rem; }");
      out.println(".order-details { background-color: #f8f9fa; padding: 1.5rem; border-radius: 8px; margin-bottom: 2rem; }");
      out.println(".order-details h3 { margin-bottom: 1rem; color: var(--secondary-color); }");
      out.println(".detail-row { display: flex; justify-content: space-between; margin-bottom: 0.5rem; padding-bottom: 0.5rem; border-bottom: 1px solid #eee; }");
      out.println(".detail-row:last-child { border-bottom: none; }");
      out.println(".ordered-books { margin-top: 2rem; }");
      out.println(".book-item { display: flex; justify-content: space-between; align-items: center; padding: 1rem; border-bottom: 1px solid #eee; }");
      out.println(".book-info { display: flex; align-items: center; }");
      out.println(".book-cover { width: 50px; height: 70px; background-color: #ddd; display: flex; justify-content: center; align-items: center; margin-right: 1rem; }");
      out.println(".action-button { display: inline-block; background-color: var(--primary-color); color: white; padding: 0.75rem 1.5rem; border-radius: 4px; text-decoration: none; text-align: center; }");
      out.println(".action-button:hover { background-color: #2980b9; }");
      out.println(".breadcrumb { margin-bottom: 1rem; }");
      out.println(".breadcrumb a { color: #f9f9f9; text-decoration: none; }");
      out.println(".breadcrumb span { margin: 0 0.5rem; }");
      out.println("footer { background-color: var(--secondary-color); color: white; text-align: center; padding: 1.5rem 0; margin-top: 2rem; }");
      out.println(".error-message { background-color: #fff3cd; border: 1px solid #ffeeba; color: #856404; padding: 1rem; border-radius: 4px; margin-bottom: 1rem; }");
      out.println("@media (max-width: 768px) { .book-cover { display: none; } }");
      out.println("</style>");
      out.println("</head>");
      out.println("<body>");
      
      // Header
      out.println("<header>");
      out.println("<div class='container header-content'>");
      out.println("<div class='logo'>");
      out.println("<i class='fas fa-book'></i>");
      out.println("<span>RyT Bookstore</span>");
      out.println("</div>");
      out.println("</div>");
      out.println("</header>");
      
      out.println("<main class='container'>");
      
      // Breadcrumb
      out.println("<div class='breadcrumb'>");
      out.println("<a href='eshopdisplay'>Home</a> <span>></span> <a href='javascript:history.back()'>Book Selection</a> <span>></span> Order Confirmation");
      out.println("</div>");

      try (
         Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/ebookshop?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
               "myuser", "xxxx");
         Statement stmt = conn.createStatement();
      ) {
         String[] ids = request.getParameterValues("id");
         String custName = request.getParameter("cust_name");
         String custPhone = request.getParameter("cust_phone");
         String custEmail = request.getParameter("cust_email");
         
         if (ids != null && custName != null && custPhone != null && custEmail != null) {
            out.println("<div class='order-container'>");
            
            // Success message
            out.println("<div class='order-success'>");
            out.println("<i class='fas fa-check-circle'></i>");
            out.println("<h2>Order Placed Successfully!</h2>");
            out.println("<p>Thank you for your order. A confirmation email has been sent to your email address.</p>");
            out.println("</div>");
            
            // Customer details
            out.println("<div class='order-details'>");
            out.println("<h3>Customer Details</h3>");
            out.println("<div class='detail-row'><strong>Name:</strong> " + custName + "</div>");
            out.println("<div class='detail-row'><strong>Email:</strong> " + custEmail + "</div>");
            out.println("<div class='detail-row'><strong>Phone:</strong> " + custPhone + "</div>");
            out.println("</div>");
            
            // Order details
            out.println("<div class='order-details'>");
            out.println("<h3>Order Summary</h3>");
            out.println("<div class='ordered-books'>");
            
            double totalAmount = 0;
            for (int i = 0; i < ids.length; ++i) {
               // Get book details
               String bookSql = "SELECT * FROM books WHERE id = " + ids[i];
               ResultSet bookRs = stmt.executeQuery(bookSql);
               
               if (bookRs.next()) {
                  String title = bookRs.getString("title");
                  String author = bookRs.getString("author");
                  double price = bookRs.getDouble("price");
                  totalAmount += price;
                  
                  out.println("<div class='book-item'>");
                  out.println("<div class='book-info'>");
                  out.println("<div class='book-cover'><i class='fas fa-book'></i></div>");
                  out.println("<div>");
                  out.println("<div><strong>" + title + "</strong></div>");
                  out.println("<div>" + author + "</div>");
                  out.println("</div>");
                  out.println("</div>");
                  out.println("<div>$" + String.format("%.2f", price) + "</div>");
                  out.println("</div>");
               }
               
               // Update book quantity
               String updateSql = "UPDATE books SET qty = qty - 1 WHERE id = " + ids[i];
               stmt.executeUpdate(updateSql);
               
               // Create order record
               String insertSql = "INSERT INTO order_records (id, qty_ordered, cust_name, cust_phone, cust_email) VALUES ("
                  + ids[i] + ", 1, '" + custName + "', '" + custPhone + "', '" + custEmail + "')";
               stmt.executeUpdate(insertSql);
            }
            
            out.println("</div>");
            out.println("<div class='detail-row' style='margin-top: 1rem; font-weight: bold;'><strong>Total:</strong> $" + String.format("%.2f", totalAmount));
            out.println("</div>");
            
            // Continue shopping button
            out.println("<div style='text-align: center; margin-top: 2rem;'>");
            out.println("<a href='eshopdisplay' class='action-button'>Continue Shopping</a>");
            out.println("</div>");
            
            out.println("</div>"); // End of order-container
         } else {
            out.println("<div class='order-container'>");
            out.println("  <div class='error-message'>");
            out.println("    <h3>Error Processing Order</h3>");
            out.println("    <p>Please ensure you have selected books and provided all required information.</p>");
            out.println("  </div>");
            out.println("  <div style='text-align: center;'>");
            out.println("    <a href='javascript:history.back()' class='action-button'>Go Back</a>");
            out.println("    <a href='eshopdisplay' class='action-button' style='margin-left: 1rem;'>Start Over</a>");
            out.println("  </div>");
            out.println("</div>");
         }
      } catch(SQLException ex) {
         out.println("<div class='order-container'>");
         out.println("  <div class='error-message'>");
         out.println("    <h3>Database Error</h3>");
         out.println("    <p>Error: " + ex.getMessage() + "</p>");
         out.println("    <p>Check Tomcat console for details.</p>");
         out.println("  </div>");
         out.println("  <div style='text-align: center;'>");
         out.println("    <a href='javascript:history.back()' class='action-button'>Go Back</a>");
         out.println("    <a href='eshopdisplay' class='action-button' style='margin-left: 1rem;'>Start Over</a>");
         out.println("  </div>");
         out.println("</div>");
         ex.printStackTrace();
      }
      
      out.println("</main>");
      
      // Footer
      out.println("<footer>");
      out.println("  <div class='container'>");
      out.println("    <p>&copy; 2025 Tarence Yong and Ryan Lim. All rights reserved.</p>");
      out.println("  </div>");
      out.println("</footer>");
      
      out.println("<script>");
      out.println("  // Clear the cart after successful order");
      out.println("  localStorage.removeItem('ebookCart');");
      out.println("</script>");
      out.println("</body></html>");
   }
}