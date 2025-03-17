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
        * { margin: 0; 
            padding: 0; 
            box-sizing: 
            border-box; 
        }
        body { 
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            line-height: 1.6; 
            color: var(--dark-color); 
            background-color: #c38e7c; 
        }
        .container { 
            width: 90%; 
            max-width: 1200px; 
            margin: 0 auto;
        }
        header { 
            background-color: #8e7cc3; 
            color: white; 
            padding: 1rem 0; 
            box-shadow: 0 2px 5px rgba(0,0,0,0.1); 
        }
        .header-content { display: flex; 
            justify-content: space-between; 
            align-items: center; 
        }
        .logo { font-size: 1.8rem; 
            font-weight: bold; 
            display: flex; 
            align-items: center;
            font-family: 'Century Gothic', sans-serif; 
        }
        .logo i { margin-right: 0.9rem; }
        
        /* Main area styling */
        main { padding: 2rem 0; }
        .welcome-container { 
            background-color: #f9f9f9; 
            padding: 3rem; 
            border-radius: 8px; 
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            text-align: left;
        }
        .welcome-title { 
            color: var(--secondary-color); 
            margin-bottom: 1.5rem; 
            font-size: 2.5rem; 
        }
        .welcome-subtitle {
            color: var(--dark-color);
            margin-bottom: 2rem;
            font-size: 1.2rem;
        }
        .featured-books {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
            gap: 2rem;
            margin-top: 3rem;
        }
        .book-card {
            background-color: var(--light-color);
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
            transition: transform 0.3s ease;
        }
        .book-card:hover {
            transform: translateY(-5px);
        }
        .book-cover {
            height: 200px;
            background-color: #ddd;
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: 2rem;
            color: var(--primary-color);
        }
        .book-info {
            padding: 1.5rem;
        }
        .book-title {
            font-size: 1.2rem;
            margin-bottom: 0.5rem;
            color: var(--secondary-color);
        }
        .book-author {
            color: var(--dark-color);
            font-size: 0.9rem;
            margin-bottom: 1rem;
        }
        .book-price {
            font-weight: bold;
            color: var(--accent-color);
            margin-bottom: 1rem;
            font-size: 1.1rem;
        }
        .browse-section {
            display: flex;
            gap: 2rem;
            margin-top: 2rem;
        }
        .authors-container, .genres-container {
            flex: 1;
            background-color: #f9f9f9;
            padding: 1.5rem;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }
        .section-title {
            color: var(--secondary-color);
            margin-bottom: 1rem;
            font-size: 1.5rem;
        }
        .author-list, .genres-list {
            margin-bottom: 1rem;
        }
        .author-item, .genre-item {
            margin-bottom: 0.5rem;
        }
        
        footer { 
            background-color: var(--secondary-color); 
            color: white; 
            text-align: center; 
            padding: 1.5rem 0; 
            margin-top: 2rem; 
        }
    </style>

</head>
<body>
    <header>
        <div class="container header-content">
            <div class="logo">
                <i class="fas fa-book"></i>
                <span>RyT Bookstore</span>
            </div>
        </div>
    </header>
    
    <main class="container">
        <div class="welcome-container">
            <h1 class="welcome-title">Welcome to RyT Bookstore!</h1>
            <p class="welcome-subtitle">Discover your next favorite book from our extensive collection today.</p>
            
            <div class="featured-books">
                <div class="book-card">
                    <div class="book-cover">
                        <i class="fas fa-book"></i>
                    </div>
                    <div class="book-info">
                        <h3 class="book-title">Java for Beginners</h3>
                        <p class="book-author">By Tan Ah Teck</p>
                        <p class="book-price">$19.99</p>
                    </div>
                </div>
                
                <div class="book-card">
                    <div class="book-cover">
                        <i class="fas fa-book"></i>
                    </div>
                    <div class="book-info">
                        <h3 class="book-title">More Java</h3>
                        <p class="book-author">By Mohammad Ali</p>
                        <p class="book-price">$29.99</p>
                    </div>
                </div>
                
                <div class="book-card">
                    <div class="book-cover">
                        <i class="fas fa-book"></i>
                    </div>
                    <div class="book-info">
                        <h3 class="book-title">Java Web Development</h3>
                        <p class="book-author">By Kumar</p>
                        <p class="book-price">$24.99</p>
                    </div>
                </div>
            </div>
        </div>

        
        <div class="browse-section">
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
                    <div style="text-align: left;">
                        <button type="submit">View Selected Authors</button>
                    </div>
                </form>
            </div>

            <div class="genres-container">
                <h2 class="section-title">Browse by Genre</h2>
                <form action="eshopquery" method="get">
                    <div class="genres-list">
                        <% 
                        java.util.List<String> genres = (java.util.List<String>) request.getAttribute("genres");                        if (genres != null) {
                            for (String genre : genres) {
                        %>
                            <div class="genre-item">
                                <input type="checkbox" id="<%= genre %>" name="genre" value="<%= genre %>">
                                <label for="<%= genre %>"><%= genre %></label>
                            </div>
                        <% 
                            }
                        }
                        %>
                    </div>
                    <div style="text-align: left;">
                        <button type="submit">View Selected Genres</button>
                    </div>
                </form>
            </div>
        </div>
    </main>
    
    <footer>
        <div class="container">
            <p>&copy; 2025 Tarence Yong and Ryan Lim. All rights reserved.</p>
        </div>
    </footer>

    <script>
    document.addEventListener('DOMContentLoaded', function() {
        // Get the authors form
            const authorsForm = document.querySelector('.authors-container form');

        // Add submit event listener to the authors form
            authorsForm.addEventListener('submit', function(event) {
        // Get all checked author checkboxes
            const checkedAuthors = document.querySelectorAll('.authors-container input[name="author"]:checked');
        
        // If no authors are selected, prevent form submission and show an alert
            if (checkedAuthors.length === 0) {
                event.preventDefault();
                alert('Please select at least one author before submitting.');
            }
        });

        // Get the genres form
            const genresForm = document.querySelector('.genres-container form');

        // Add submit event listener to the genres form
            genresForm.addEventListener('submit', function(event) {

        // Get all checked genre checkboxes
            const checkedGenres = document.querySelectorAll('.genres-container input[name="genre"]:checked');
        
        // If no genres are selected, prevent form submission and show an alert
            if (checkedGenres.length === 0) {
                event.preventDefault();
            alert('Please select at least one genre before submitting.');
            }
        });
    });
    </script>
</body>
</html>