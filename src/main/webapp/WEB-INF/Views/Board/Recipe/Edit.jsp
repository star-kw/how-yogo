<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>레시피 수정</title>
</head>
<body>
    <h2>레시피 수정</h2>
    <form action="RecipeEdit" method="post" enctype="multipart/form-data">
        <input type="hidden" name="recipeId" value="${boardDto.recipeId}">
        <table>
            <tr>
                <th>제목</th>
                <td><input type="text" name="recipeTitle" value="${boardDto.recipeTitle}" required></td>
            </tr>
            <tr>
                <th>재료</th>
                <td><textarea name="ingredients" required>${boardDto.ingredients}</textarea></td>
            </tr>
            <tr>
                <th>대표 이미지</th>
                <td>
                    <img src="/uploads/${boardDto.mainSName}" alt="대표 이미지">
                    <input type="file" name="mainOName">
                </td>
            </tr>
            <tr>
                <th>재료 이미지</th>
                <td>
                    <img src="/uploads/${boardDto.inSName}" alt="재료 이미지">
                    <input type="file" name="inOName">
                </td>
            </tr>
            <tr>
                <th>카테고리</th>
                <td>
                    <select name="recipeCategory" required>
                        <option value="한식" ${boardDto.recipeCategory == '한식' ? 'selected' : ''}>한식</option>
                        <option value="양식" ${boardDto.recipeCategory == '양식' ? 'selected' : ''}>양식</option>
                        <option value="일식" ${boardDto.recipeCategory == '일식' ? 'selected' : ''}>일식</option>
                        <option value="중식" ${boardDto.recipeCategory == '중식' ? 'selected' : ''}>중식</option>
                        <option value="기타" ${boardDto.recipeCategory == '기타' ? 'selected' : ''}>기타</option>
                    </select>
                </td>
            </tr>
        </table>
        
        <h3>요리 순서</h3>
        <div id="stepContainer">
            <c:forEach var="step" items="${stepList}" varStatus="status">
            <div>
                <h4>STEP ${status.count}</h4>
                <textarea name="stepContent${status.count}" required>${step.stepContent}</textarea>
                <img src="/uploads/${step.imgSName}" alt="요리 순서 이미지">
                <input type="file" name="imgOName${status.count}">
            </div>
            </c:forEach>
        </div>
        <button type="button" onclick="addStep()">순서 추가</button>
        
        <input type="submit" value="수정 완료">
    </form>
    
    <script>
        let stepCount = ${stepList.size()};
        
        function addStep() {
            stepCount++;
            let stepDiv = document.createElement("div");
            stepDiv.innerHTML = `
                <h4>STEP \${stepCount}</h4>
                <textarea name="stepContent\${stepCount}" required></textarea>
                <input type="file" name="imgOName\${stepCount}" required>
            `;
            document.getElementById("stepContainer").appendChild(stepDiv);
        }
    </script>
</body>
</html>