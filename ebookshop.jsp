<!DOCTYPE html>
<html>
<head>
    <title>Yet Another e-Bookshop</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container">
        <h2>Yet Another e-Bookshop</h2>
        <form method="get" action="eshopquery">
            <div class="form-group">
                <label>Choose an author:</label>
                <div class="author-list">
                    
                    <c:forEach items="${authors}" var="author">
                        <div class="author-item">
                            <input type="checkbox" name="author" value="${author}" /> ${author}
                        </div>
                        </c:forEach>
                
                </div>
            </div>
            <input type="submit" value="Search" class="btn-submit">
        </form>
    </div>
</body>
</html>
