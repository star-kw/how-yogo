<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체 레시피</title>
<link rel="stylesheet" href="/Resources/CSS/Board/list.css">
</head>
<body>
	<jsp:include page="/WEB-INF/Views/Common/top.jsp"/>
	<input type="hidden" name="nickname" value="아이언맨피카츄" />
	
	<!-- 보기 형식 기능 -->
	<!-- 토글 방식 사용하기 -->

    <!-- 목록 테이블 -->
	<section id="listSection">
    <table border="1" width="90%">
        <tr>
            <th width="10%">번호</th>
            <th width="*">제목</th>
            <th width="15%">작성자</th>
            <th width="10%">조회수</th>
            <th width="10%">좋아요</th>
            <th width="8%">작성일</th>
        </tr>
<c:choose>    
    <c:when test="${ empty recipeLists }">  <!-- 게시물이 없을 때 -->
        <tr>
            <td colspan="6" align="center">
                등록된 게시물이 없습니다^^*
            </td>
        </tr>
    </c:when>
    <c:otherwise>  <!-- 게시물이 있을 때 -->
        <c:forEach items="${ recipeLists }" var="row" varStatus="loop">    
        <tr align="center">
            <td>${ row.recipeId }</td>
            <td align="left">  <!-- 제목(링크) -->
                <a href="RecipeView?recipeId=${ row.recipeId }">${ row.recipeTitle }</a> 
            </td> 
            <td>${ row.nickname }</td>
            <td>${ row.hits }</td>
            <td>${ row.likecount }</td>
            <td>${ row.postDate }</td>
        </tr>
        </c:forEach>        
    </c:otherwise>    
</c:choose>
    </table>
    </section>
    
    <!-- 피드 형식 -->
    <section id="feedSection" style="display: none;">
    </section>

    <!-- 하단 메뉴(바로가기, 글쓰기) -->
    <table border="1" width="90%" class="bottom">
        <tr align="center">
            <td>
                ${ map.pagingImg }
            </td>
            <td width="100">
            	<form action="RecipeWrite" method="get">
            	<input type="submit" value="글쓰기"/>
            	</form>
            </td>
        </tr>
    </table>
    <script src="<%= request.getContextPath() %>/Resources/JS/Board/recipeList.js"></script>
</body>
</html>