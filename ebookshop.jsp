<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        
        /* Navigation menu styling */
        nav ul { display: flex; list-style: none; }
        nav li { position: relative; margin-left: 1.5rem; }
        nav a { color: white; text-decoration: none; font-weight: 500; display: block; padding: 0.5rem; }
        nav a:hover { color: var(--light-color); }
        
        /* Dropdown styling */
        .dropdown-content {
            display: none;
            position: absolute;
            background-color: white;
            min-width: 200px;
            box-shadow: 0 8px 16px rgba(0,0,0,0.2);
            z-index: 1;
            border-radius: 4px;
            top: 100%;
            left: 0;
        }
        .dropdown-content a {
            color: var(--dark-color);
            padding: 0.75rem 1rem;
            text-decoration: none;
            display: block;
            border-bottom: 1px solid #f1f1f1;
        }
        .dropdown-content a:last-child {
            border-bottom: none;
        }
        .dropdown-content a:hover {
            background-color: #f1f1f1;
            color: var(--primary-color);
        }
        .dropdown:hover .dropdown-content {
            display: block;
        }
        .dropdown i {
            margin-left: 0.3rem;
        }
        
        /* Main area styling */
        main { padding: 2rem 0; }
        .welcome-container { 
            background-color: white; 
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
        .book-button {
            background-color: var(--primary-color);
            color: white;
            border: none;
            padding: 0.5rem 1rem;
            border-radius: 4px;
            cursor: pointer;
            font-size: 0.9rem;
            width: 100%;
            text-align: center;
        }
        .book-button:hover {
            background-color: #2980b9;
        }
        
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
            
            <nav>
                <ul>
                    <li><a href="index.jsp">Home</a></li>
                    <li class="dropdown">
                        <a href="#">Browse by Authors <i class="fas fa-chevron-down"></i></a>
                        <div class="dropdown-content">
                            <c:forEach var="author" items="${authorList}">
                                <a href="eshopquery?author=${author}"><c:out value="${author}" /></a>
                            </c:forEach>
                        </div>
                    </li>
                    <li class="dropdown">
                        <a href="#">Browse by Genres <i class="fas fa-chevron-down"></i></a>
                        <div class="dropdown-content">
                            <c:forEach var="genre" items="${genreList}">
                                <a href="eshopquery?genre=${genre}"><c:out value="${genre}" /></a>
                            </c:forEach>
                        </div>  
                    </li>
                </ul>
            </nav>
        </div>
    </header>
    
    <main class="container">
        <div class="welcome-container">
            <h1 class="welcome-title">Welcome to EBook Shop</h1>
            <p class="welcome-subtitle">Discover your next favorite book from our extensive collection</p>
            
            <div class="featured-books">
                <div class="book-card">
                    <div class="book-cover">
                        <i class="fas fa-book"></i>
                    </div>
                    <div class="book-info">
                        <h3 class="book-title">Java for Beginners</h3>
                        <p class="book-author">By Tan Ah Teck</p>
                        <p class="book-price">$19.99</p>
                        <button class="book-button">Add to Cart</button>
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
                        <button class="book-button">Add to Cart</button>
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
                        <button class="book-button">Add to Cart</button>
                    </div>
                </div>
            </div>
        </div>
    </main>
    
    <footer>
        <div class="container">
            <p>&copy; 2025 EBook Shop. All rights reserved.</p>
        </div>
    </footer>
</body>
</html>