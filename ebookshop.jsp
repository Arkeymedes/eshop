<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EBook Shop</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <style>
        :root {
            --primary-color: #3498db;
            --secondary-color: #2c3e50;
            --accent-color: #e74c3c;
            --light-color: #ecf0f1;
            --dark-color: #34495e;
        }
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; line-height: 1.6; color: var(--dark-color); background-color: var(--light-color); }
        .container { width: 90%; max-width: 1200px; margin: 0 auto; }
        header { background-color: var(--primary-color); color: white; padding: 1rem 0; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
        .header-content { display: flex; justify-content: space-between; align-items: center; }
        .logo { font-size: 1.8rem; font-weight: bold; display: flex; align-items: center; }
        .logo i { margin-right: 0.5rem; }
        main { padding: 2rem 0; }
        .authors-container { background-color: white; padding: 2rem; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
        .section-title { color: var(--secondary-color); margin-bottom: 1.5rem; text-align: center; font-size: 1.8rem; }
        .author-list { display: grid; grid-template-columns: repeat(auto-fill, minmax(200px, 1fr)); gap: 1rem; }
        .author-item { padding: 0.5rem; }
        button[type="submit"] { background-color: var(--accent-color); color: white; border: none; padding: 0.75rem 1.5rem; border-radius: 4px; cursor: pointer; font-size: 1rem; margin-top: 1.5rem; }
        button[type="submit"]:hover { background-color: #c0392b; }
        footer { background-color: var(--secondary-color); color: white; text-align: center; padding: 1.5rem 0; margin-top: 2rem; }
    </style>
</head>
<body>
    <header>
        <div class="container header-content">
            <div class="logo">
                <i class="fas fa-book"></i>
                <span>EBook Shop</span>
            </div>
        </div>
    </header>
    
    <main class="container">
        <div class="authors-container">
            <h2 class="section-title">Browse by Author</h2>
            <form action="eshopquery" method="get">
                <div class="author-list">
                    <% 
                    java.util.List<String> authors = (java.util.List<String>) request.getAttribute("authors");                        if (authors != null) {
                            for (String author : authors) {
                    %>
                            <div class="author-item">
                                <input type="checkbox" id="<%= author %>" name="author" value="<%= author %>">
                                <label for="<%= author %>"><%= author %></label>
                            </div>
                    <% 
                            }
                        }
                    %>
                </div>
                <div style="text-align: center;">
                    <button type="submit">View Selected Authors</button>
                </div>
            </form>
        </div>
    </main>
    
    <footer>
        <div class="container">
            <p>&copy; 2025 EBook Shop. All rights reserved.</p>
        </div>
    </footer>
</body>
</html>
