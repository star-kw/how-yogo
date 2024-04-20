<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	  <meta charset="UTF-8">
	  <meta name="viewport" content="width=device-width, initial-scale=1.0">
	  <title>how_yogo Top</title>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/Resources/CSS/Common/top.css">
	</head>
	
	<body>
		  <header>
		    <div class="banner">
		      <div class="banner_logo">
		        <a href="how_yogo" class="banner_link"><img src="<%= request.getContextPath() %>/Resources/Image/Common/header_logo2.png" class="banner_logo"></a>
		      </div>
		      
		      <div class="search_container">
			    <form method="get" action="RecipeList" >  
		            <input type="text" name="searchWord" placeholder="레시피 검색" class="search_txt" />
		            <button type="submit" class="search_btn">
            			<img src="<%= request.getContextPath() %>/Resources/Image/Common/search_icon.png" alt="검색" />
        			</button>
			    </form>
		      </div>
		
		      <div>
		        <div class="login_container">
		          <div class="login_button">
		          <a href="#" class="login_link">로그인</a>
		          </div>
		        </div>
		     </div>
		    </div>
		    
		    <div class="menu_bar">
		      <div class="RecipeList"><a href="RecipeList" class="menu_link">Recipe</a>
		          <ul class="recipe_menu">
		            <li><a href="RecipeList?recipeCategory=한식">한식</a></li>
		            <li><a href="RecipeList?recipeCategory=양식">양식</a></li>
		            <li><a href="RecipeList?recipeCategory=중식">중식</a></li>
		            <li><a href="RecipeList?recipeCategory=일식">일식</a></li>
		          </ul>
		      </div>
		
		      <div class="community_board">
		      <a href="#" class="menu_link">Community</a>
		        <ul class="community_menu">
		          <li><a href="#">커뮤니티</a></li>
		          <li><a href="#">같이 요리</a></li>
		          <li><a href="#">요리 신청</a></li>
		        </ul>
		      </div>
		      
		      <div class="space_menu"></div>
		      
		      <div class="notice_board"><a href="#" class="menu_link">Notice</a>
		        <ul class="notice_menu">
		          <li><a href="#">Notice</a></li>
		        </ul>
		      </div>
		
		      <div class="qna_board"><a href="#" class="menu_link">Q&A</a>
		        <ul class="qna_menu">
		          <li><a href="#">Q&A</a></li>
		        </ul>
		      </div>
		    </div>
		  </header>
		  <script src="<%= request.getContextPath() %>/Resources/JS/Common/top.js"></script>
	</body>
</html>