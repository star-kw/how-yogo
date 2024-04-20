<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>레시피 상세보기</title>
</head>
<body>
    <!-- 레시피 상세조회 -->
    <table>
        <tr>
            <th>제목</th>
            <td>${boardDto.recipeTitle}</td>
        </tr>
        <tr>
            <th>작성자</th>
            <td>${boardDto.nickname}</td>
        </tr>
        <tr>
            <th>재료</th>
            <td>${boardDto.ingredients}</td>
        </tr>
        <tr>
            <th>대표 이미지</th>
            <td><img src="/uploads/${boardDto.mainSName}" alt="대표 이미지"></td>
        </tr>
        <tr>
            <th>재료 이미지</th>
            <td><img src="/uploads/${boardDto.inSName}" alt="재료 이미지"></td>
        </tr>
        <tr>
            <th>카테고리</th>
            <td>${boardDto.recipeCategory}</td>
        </tr>
        <tr>
            <th>조회수</th>
            <td>${boardDto.hits}</td>
        </tr>
        <tr>
            <th>좋아요</th>
            <td>${boardDto.likecount}</td>
        </tr>
    </table>
    
    <h3>요리 순서</h3>
    <c:forEach var="step" items="${stepList}">
    <div>
        <h4>STEP ${step.recipeStep}</h4>
        <p>${step.stepContent}</p>
        <img src="/uploads/${step.imgSName}" alt="요리 순서 이미지">
    </div>
    </c:forEach>
    
    <!-- 작성자 메뉴 -->
    <a href="RecipeEdit?recipeId=${boardDto.recipeId}">수정</a>
    <a href="RecipeDelete?recipeId=${boardDto.recipeId}">삭제</a>
    
    <!-- 댓글 작성 폼 -->
    <form action="RecipeComment" method="post">
        <input type="hidden" name="action" value="insert">
        <input type="hidden" name="recipeId" value="${boardDto.recipeId}">
        <input type="hidden" name="nickname" value="아이언맨피카츄">
        <textarea name="commentContent" required></textarea>
        <input type="hidden" name="parentComment" value="0">
        <input type="submit" value="작성">
    </form>
    
    <!-- 댓글 목록 -->
    <c:forEach var="comment" items="${commentList}">
        <div>
            <p>${comment.nickname} | ${comment.postDate}</p>
            <p>${comment.commentContent}</p>
            <c:if test="${'아이언맨피카츄' == comment.nickname}">
                <form action="RecipeComment" method="post">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="commentId" value="${comment.commentId}">
                    <input type="hidden" name="recipeId" value="${boardDto.recipeId}">
                    <input type="submit" value="삭제">
                </form>
            </c:if>
        </div>
    </c:forEach>
    
    <!-- 대댓글 작성 폼 (각 댓글마다 생성) -->
	<c:forEach var="comment" items="${commentList}">
    <div>
        <!-- 댓글 내용 출력 -->
        <form action="RecipeComment" method="post">
            <input type="hidden" name="action" value="insert">
            <input type="hidden" name="recipeId" value="${boardDto.recipeId}">
            <input type="hidden" name="nickname" value="아이언맨피카츄">
            <input type="hidden" name="parentComment" value="${comment.commentId}">
            <textarea name="commentContent" required></textarea>
            <input type="submit" value="작성">
        </form>
    </div>
	</c:forEach>
    
    <!-- 좋아요 버튼 -->
    <c:choose>
    <c:when test="${dao.isLiked('아이언맨피카츄', boardDto.recipeId)}">
        <form action="RecipeLike" method="post">
            <input type="hidden" name="nickname" value="아이언맨피카츄">
            <input type="hidden" name="recipeId" value="${boardDto.recipeId}">
            <input type="submit" value="좋아요 취소">
        </form>
    </c:when>
    <c:otherwise>
        <form action="RecipeLike" method="post">
            <input type="hidden" name="nickname" value="아이언맨피카츄">
            <input type="hidden" name="recipeId" value="${boardDto.recipeId}">
            <input type="submit" value="좋아요">
        </form>
    </c:otherwise>
	</c:choose>
    
    <!-- 좋아요 개수 -->
    <p>좋아요 개수: ${likeCount}</p>
    
    <a href="RecipeList">목록보기</a>
</body>
</html>