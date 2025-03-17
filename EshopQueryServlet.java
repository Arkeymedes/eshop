import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/eshopquery")
public class EshopQueryServlet extends HttpServlet {

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
      out.println("<title>EBook Shop - Book Selection</title>");
      out.println("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css'>");
      out.println("<style>");
      out.println(":root {");
      out.println("--primary-color: #3498db;");
      out.println("--secondary-color: #2c3e50;");
      out.println("--accent-color: #e74c3c;");
      out.println("--light-color: #ecf0f1;");
      out.println("--dark-color: #34495e;");
      out.println("}");
      out.println("* { margin: 0; padding: 0; box-sizing: border-box; }");
      out.println("body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; line-height: 1.6; color: var(--dark-color); background-color: #c38e7c; }");
      out.println(".container { width: 90%; max-width: 1200px; margin: 0 auto; }");
      out.println("header { background-color: #8e7cc3; color: white; padding: 1rem 0; box-shadow: 0 2px 5px rgba(0,0,0,0.1); position: relative; }");
      out.println(".header-content { display: flex; justify-content: space-between; align-items: center; }");
      out.println(".logo { font-size: 1.8rem; font-weight: bold; display: flex; align-items: center; font-family: 'Century Gothic', sans-serif; }");
      out.println(".logo i { margin-right: 0.9rem; }");
      out.println(".cart-icon { position: relative; cursor: pointer; font-size: 1.5rem; }");
      out.println(".cart-count { position: absolute; top: -10px; right: -10px; background-color: var(--accent-color); color: white; border-radius: 50%; width: 20px; height: 20px; display: flex; justify-content: center; align-items: center; font-size: 0.75rem; font-weight: bold; }");
      out.println("main { padding: 2rem 0; }");
      out.println(".books-container { background-color: white; padding: 2rem; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }");
      out.println(".section-title { color: var(--secondary-color); margin-bottom: 1.5rem; text-align: center; font-size: 1.8rem; }");
      out.println(".book-table { width: 100%; border-collapse: collapse; margin-top: 1rem; }");
      out.println(".book-table th { background-color: var(--secondary-color); color: white; text-align: left; padding: 1rem; }");
      out.println(".book-table td { padding: 1rem; border-bottom: 1px solid #ddd; }");
      out.println(".book-table tr:last-child td { border-bottom: none; }");
      out.println(".book-table tr:hover { background-color: #f5f5f5; }");
      out.println(".book-cover { width: 50px; height: 70px; background-color: #ddd; display: flex; justify-content: center; align-items: center; margin-right: 1rem; }");
      out.println(".book-info { display: flex; align-items: center; }");
      out.println(".add-to-cart { background-color: var(--primary-color); color: white; border: none; padding: 0.5rem 1rem; border-radius: 4px; cursor: pointer; }");
      out.println(".add-to-cart:hover { background-color: #2980b9; }");
      out.println(".customer-form { margin-top: 2rem; background-color: #f8f9fa; padding: 1.5rem; border-radius: 8px; }");
      out.println(".form-group { margin-bottom: 1rem; }");
      out.println("label { display: block; margin-bottom: 0.5rem; font-weight: bold; }");
      out.println("input[type='text'], input[type='email'] { width: 100%; padding: 0.75rem; border: 1px solid #ddd; border-radius: 4px; font-size: 1rem; }");
      out.println("button[type='submit'] { background-color: var(--accent-color); color: white; border: none; padding: 0.75rem 1.5rem; border-radius: 4px; cursor: pointer; font-size: 1rem; margin-top: 1rem; }");
      out.println("button[type='submit']:hover { background-color: #c0392b; }");
      out.println(".breadcrumb { margin-bottom: 1rem; }");
      out.println(".breadcrumb a { color: #f9f9f9; text-decoration: none; }");
      out.println(".breadcrumb span { margin: 0 0.5rem; }");
      out.println("footer { background-color: var(--secondary-color); color: white; text-align: center; padding: 1.5rem 0; margin-top: 2rem; }");
      out.println(".cart-popup { display: none; position: absolute; top: 100%; right: 0; background-color: white; color: #0c0c0c; font-size: 1.1rem; min-width: 300px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); border-radius: 8px; z-index: 100; padding: 1rem; }");
      out.println(".cart-popup.show { display: block; }");
      out.println(".cart-item { display: flex; justify-content: space-between; align-items: center; padding: 0.5rem 0; border-bottom: 1px solid #eee; }");
      out.println(".cart-total { margin-top: 1rem; text-align: right; font-weight: bold; }");
      out.println(".cart-actions { display: flex; justify-content: space-between; margin-top: 1rem; }");
      out.println(".cart-actions button { padding: 0.5rem 1rem; background-color: #4c4cff; color: white; border: none; border-radius: 4px; cursor: pointer; }");
      out.println(".cart-actions button.clear { background-color: var(--accent-color); }");
      out.println(".cart-empty { text-align: center; padding: 1rem; }");
      out.println("@media (max-width: 768px) { .book-cover { display: none; } .form-group { grid-template-columns: 1fr; } }");
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
      out.println("<div class='cart-icon' id='cartIcon'>");
      out.println("<i class='fas fa-shopping-cart'></i>");
      out.println("<span class='cart-count' id='cartCount'>0</span>");
      out.println("<div class='cart-popup' id='cartPopup'>");
      out.println("<div id='cartItems'>");
      out.println("<div class='cart-empty'>Your cart is empty</div>");
      out.println("</div>");
      out.println("<div class='cart-total'>Total: $<span id='cartTotal'>0.00</span></div>");
      out.println("<div class='cart-actions'>");
      out.println("<button class='clear' id='clearCart'>Clear</button>");
      out.println("<button id='checkout'>Checkout</button>");
      out.println("</div>");
      out.println("</div>");
      out.println("</div>");
      out.println("</div>");
      out.println("</header>");
      
      out.println("<main class='container'>");
      
      // Breadcrumb
      out.println("<div class='breadcrumb'>");
      out.println("  <a href='eshopdisplay'>Home</a> <span>></span> Book Selection");
      out.println("</div>");

      try (
         Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/ebookshop?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
               "myuser", "xxxx");
         Statement stmt = conn.createStatement();
      ) {
         String[] authors = request.getParameterValues("author");
         String[] genres = request.getParameterValues("genre");
         
         // Build SQL query based on selected filters
         StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM books WHERE qty > 0");
         
         // Add author filter if authors are selected
         if (authors != null && authors.length > 0) {
            sqlBuilder.append(" AND author IN (");
            for (int i = 0; i < authors.length; ++i) {
               if (i < authors.length - 1) {
                  sqlBuilder.append("'" + authors[i] + "', ");
               } else {
                  sqlBuilder.append("'" + authors[i] + "'");
               }
            }
            sqlBuilder.append(")");
         }
         
         // Add genre filter if genres are selected
         if (genres != null && genres.length > 0) {
            sqlBuilder.append(" AND genre IN (");
            for (int i = 0; i < genres.length; ++i) {
               if (i < genres.length - 1) {
                  sqlBuilder.append("'" + genres[i] + "', ");
               } else {
                  sqlBuilder.append("'" + genres[i] + "'");
               }
            }
            sqlBuilder.append(")");
         }
         
         // Add ordering
         sqlBuilder.append(" ORDER BY author ASC, title ASC");
         
         String sqlStr = sqlBuilder.toString();


         out.println("<div class='books-container'>");
         out.println("<h2 class='section-title'>Available Books</h2>");
         
         ResultSet rset = stmt.executeQuery(sqlStr);

         out.println("<table class='book-table'>");
         out.println("<tr>");
         out.println("<th>Book</th>");
         out.println("<th>Author</th>");
         out.println("<th>Price</th>");
         out.println("<th>Action</th>");
         out.println("</tr>");

         boolean hasBooks = false;
         
         while (rset.next()) {
            hasBooks = true;
            String id = rset.getString("id");
            String author = rset.getString("author");
            String title = rset.getString("title");
            String genre = rset.getString("genre");
            double price = rset.getDouble("price");
            
            out.println("<tr>");
            out.println("<td>");
            out.println("<div class='book-info'>");
            out.println("<div class='book-cover'><i class='fas fa-book'></i></div>");
            out.println("<div>" + title + "</div>");
            out.println("</div>");
            out.println("</td>");
            out.println("<td>" + author + "</td>");
            out.println("<td>$" + String.format("%.2f", price) + "</td>");
            out.println("<td><button class='add-to-cart' onclick='addToCart(\"" + id + "\", \"" + title.replace("\"", "\\\"") + "\", \"" + author.replace("\"", "\\\"") + "\", " + price + ")'>Add to Cart</button></td>");
            out.println("</tr>");
         }
         
         if (!hasBooks) {
            out.println("<tr><td colspan='4' style='text-align: center;'>No books available for the selected author(s).</td></tr>");
         }
         
         out.println("</table>");
         
         out.println("<div style='text-align: center; margin-top: 2rem;'>");
         out.println("<a href='eshopdisplay' style='display: inline-block; background-color: var(--secondary-color); color: white; padding: 0.3rem 1.5rem; border-radius: 4px; text-decoration: none; margin-right: 1rem;'>Back to Home</a>");
         out.println("<button id='proceedToCheckout' style='background-color: #4c4cff; color: white; border: none; padding: 0.68rem 1.5rem; border-radius: 4px; cursor: pointer;'>Proceed to Checkout</button>");
         out.println("</div>");
         
         out.println("</div>"); // End of books-container
         
         // Checkout form (initially hidden)
         out.println("<div id='checkoutForm' class='customer-form' style='display: none;'>");
         out.println("<h3 class='section-title'>Checkout</h3>");
         out.println("<form id='orderForm' action='eshoporder' method='get'>");
         out.println("<div id='selectedBooksInputs'></div>");
         out.println("<div class='form-group'>");
         out.println("<label for='cust_name'>Your Name:</label>");
         out.println("<input type='text' id='cust_name' name='cust_name' required>");
         out.println("</div>");
         out.println("<div class='form-group'>");
         out.println("<label for='cust_email'>Your Email:</label>");
         out.println("<input type='email' id='cust_email' name='cust_email' required>");
         out.println("</div>");
         out.println("<div class='form-group'>");
         out.println("<label for='cust_phone'>Your Phone:</label>");
         out.println("<input type='text' id='cust_phone' name='cust_phone' required>");
         out.println("</div>");
         out.println("<button type='submit'>Place Order</button>");
         out.println("</form>");
         out.println("</div>");
         
         out.println("</main>");
         
         // Footer

         out.println("<footer>");
         out.println("<div class='container'>");
         out.println("<p>&copy; 2025 Tarence Yong and Ryan Lim. All rights reserved.</p>");
         out.println("</div>");
         out.println("</footer>");
         
         out.println("<script>");
         out.println(getCartJavaScript());
         out.println("</script>");
         out.println("</body></html>");

      } catch(SQLException ex) {
         out.println("<div class='books-container'>");
         out.println("  <h2 class='section-title'>Error</h2>");
         out.println("  <p>Error: " + ex.getMessage() + "</p>");
         out.println("  <p>Check Tomcat console for details.</p>");
         out.println("</div>");
         out.println("</main>");
         out.println("<footer><div class='container'><p>&copy; 2025 Tarence Yong and Ryan Lim. All rights reserved.</p></div></footer>");
         out.println("</body></html>");
         ex.printStackTrace();
      }
   }
   
   // Helper method to include cart JavaScript
   private String getCartJavaScript() {
      return "// Shopping cart functionality\n" +
             "let cart = [];\n" +
             "\n" +
             "// Load cart from localStorage if available\n" +
             "document.addEventListener('DOMContentLoaded', function() {\n" +
             "    const savedCart = localStorage.getItem('ebookCart');\n" +
             "    if (savedCart) {\n" +
             "        cart = JSON.parse(savedCart);\n" +
             "        updateCartDisplay();\n" +
             "    }\n" +
             "    \n" +
             "    // Toggle cart popup\n" +
             "    document.getElementById('cartIcon').addEventListener('click', function() {\n" +
             "        document.getElementById('cartPopup').classList.toggle('show');\n" +
             "    });\n" +
             "    \n" +
             "    // Close cart popup when clicking outside\n" +
             "    document.addEventListener('click', function(event) {\n" +
             "        const cartIcon = document.getElementById('cartIcon');\n" +
             "        const cartPopup = document.getElementById('cartPopup');\n" +
             "        \n" +
             "        if (!cartIcon.contains(event.target) && cartPopup.classList.contains('show')) {\n" +
             "            cartPopup.classList.remove('show');\n" +
             "        }\n" +
             "    });\n" +
             "    \n" +
             "    // Clear cart\n" +
             "    document.getElementById('clearCart').addEventListener('click', function() {\n" +
             "        cart = [];\n" +
             "        updateCartDisplay();\n" +
             "        saveCart();\n" +
             "    });\n" +
             "    \n" +
             "    // Checkout button in cart\n" +
             "    document.getElementById('checkout').addEventListener('click', function() {\n" +
             "        if (cart.length > 0) {\n" +
             "            showCheckoutForm();\n" +
             "        } else {\n" +
             "            alert('Your cart is empty!');\n" +
             "        }\n" +
             "    });\n" +
             "    \n" +
             "    // Proceed to checkout button\n" +
             "    if (document.getElementById('proceedToCheckout')) {\n" +
             "        document.getElementById('proceedToCheckout').addEventListener('click', function() {\n" +
             "            if (cart.length > 0) {\n" +
             "                showCheckoutForm();\n" +
             "            } else {\n" +
             "                alert('Your cart is empty! Please add books to your cart first.');\n" +
             "            }\n" +
             "        });\n" +
             "    }\n" +
             "});\n" +
             "\n" +
             "// Add item to cart\n" +
             "function addToCart(id, title, author, price) {\n" +
             "    const existingItem = cart.find(item => item.id === id);\n" +
             "    \n" +
             "    if (existingItem) {\n" +
             "        existingItem.quantity++;\n" +
             "    } else {\n" +
             "        cart.push({\n" +
             "            id: id,\n" +
             "            title: title,\n" +
             "            author: author,\n" +
             "            price: price,\n" +
             "            quantity: 1\n" +
             "        });\n" +
             "    }\n" +
             "    \n" +
             "    updateCartDisplay();\n" +
             "    saveCart();\n" +
             "    \n" +
             "    // Show a brief confirmation\n" +
             "    const notification = document.createElement('div');\n" +
             "    notification.textContent = title + ' added to cart';\n" +
             "    notification.style.position = 'fixed';\n" +
             "    notification.style.bottom = '20px';\n" +
             "    notification.style.right = '20px';\n" +
             "    notification.style.backgroundColor = 'var(--primary-color)';\n" +
             "    notification.style.color = 'white';\n" +
             "    notification.style.padding = '10px 20px';\n" +
             "    notification.style.borderRadius = '4px';\n" +
             "    notification.style.zIndex = '1000';\n" +
             "    document.body.appendChild(notification);\n" +
             "    \n" +
             "    setTimeout(() => {\n" +
             "        notification.style.opacity = '0';\n" +
             "        notification.style.transition = 'opacity 0.5s';\n" +
             "        setTimeout(() => {\n" +
             "            document.body.removeChild(notification);\n" +
             "        }, 500);\n" +
             "    }, 2000);\n" +
             "}\n" +
             "\n" +
             "// Remove item from cart\n" +
             "function removeFromCart(id) {\n" +
             "    cart = cart.filter(item => item.id !== id);\n" +
             "    updateCartDisplay();\n" +
             "    saveCart();\n" +
             "}\n" +
             "\n" +
             "// Update cart display\n" +
             "function updateCartDisplay() {\n" +
             "    const cartItems = document.getElementById('cartItems');\n" +
             "    const cartCount = document.getElementById('cartCount');\n" +
             "    const cartTotal = document.getElementById('cartTotal');\n" +
             "    \n" +
             "    cartCount.textContent = cart.reduce((total, item) => total + item.quantity, 0);\n" +
             "    \n" +
             "    if (cart.length === 0) {\n" +
             "        cartItems.innerHTML = '<div class=\"cart-empty\">Your cart is empty</div>';\n" +
             "        cartTotal.textContent = '0.00';\n" +
             "        return;\n" +
             "    }\n" +
             "    \n" +
             "    let html = '';\n" +
             "    let total = 0;\n" +
             "    \n" +
             "    cart.forEach(item => {\n" +
             "        const itemTotal = item.price * item.quantity;\n" +
             "        total += itemTotal;\n" +
             "        \n" +
             "        html += `\n" +
             "            <div class=\"cart-item\">\n" +
             "                <div>\n" +
             "                    <div>${item.title}</div>\n" +
             "                    <div>$${item.price.toFixed(2)} x ${item.quantity}</div>\n" +
             "                </div>\n" +
             "                <div>\n" +
             "                    <button onclick=\"removeFromCart('${item.id}')\">×</button>\n" +
             "                </div>\n" +
             "            </div>\n" +
             "        `;\n" +
             "    });\n" +
             "    \n" +
             "    cartItems.innerHTML = html;\n" +
             "    cartTotal.textContent = total.toFixed(2);\n" +
             "}\n" +
             "\n" +
             "// Save cart to localStorage\n" +
             "function saveCart() {\n" +
             "    localStorage.setItem('ebookCart', JSON.stringify(cart));\n" +
             "}\n" +
             "\n" +
             "// Show checkout form\n" +
             "function showCheckoutForm() {\n" +
             "    document.getElementById('checkoutForm').style.display = 'block';\n" +
             "    document.getElementById('selectedBooksInputs').innerHTML = '';\n" +
             "    \n" +
             "    // Add hidden inputs for each book in cart\n" +
             "    cart.forEach(item => {\n" +
             "        for (let i = 0; i < item.quantity; i++) {\n" +
             "            const input = document.createElement('input');\n" +
             "            input.type = 'hidden';\n" +
             "            input.name = 'id';\n" +
             "            input.value = item.id;\n" +
             "            document.getElementById('selectedBooksInputs').appendChild(input);\n" +
             "        }\n" +
             "    });\n" +
             "    \n" +
             "    // Scroll to the checkout form\n" +
             "    document.getElementById('checkoutForm').scrollIntoView({ behavior: 'smooth' });\n" +
             "    \n" +
             "    // Add form submission handler\n" +
             "    document.getElementById('orderForm').addEventListener('submit', function() {\n" +
             "        // Clear the cart after successful submission\n" +
             "        localStorage.removeItem('ebookCart');\n" +
             "    });\n" +
             "}";
   }
}