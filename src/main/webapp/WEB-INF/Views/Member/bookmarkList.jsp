<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>북마크 목록</title>
</head>
<body>
    <h2>북마크 목록</h2>
    <table>
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>북마크 날짜</th>
            <th>삭제</th>
        </tr>
        <c:forEach var="bookmark" items="${bookmarkList}">
        <tr>
            <td>${bookmark.bookmarkId}</td>
            <td>
                <c:if test="${not empty bookmark.recipeId}">
                    <a href="RecipeView?recipeId=${bookmark.recipeId}">레시피 제목</a>
                </c:if>
                <c:if test="${not empty bookmark.cNum}">
                    <a href="CommunityView?cNum=${bookmark.cNum}">커뮤니티 제목</a>
                </c:if>
            </td>
            <td>${bookmark.markDate}</td>
            <td>
                <form action="Bookmark" method="post">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="bookmarkId" value="${bookmark.bookmarkId}">
                    <input type="submit" value="삭제">
                </form>
            </td>
        </tr>
        </c:forEach>
    </table>
</body>
</html>