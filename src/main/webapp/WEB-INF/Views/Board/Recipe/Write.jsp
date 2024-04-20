<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>레시피 작성</title>
</head>
<body>
    <h2>레시피 작성</h2>
    <form action="RecipeWrite" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <th>제목</th>
                <td><input type="text" name="recipeTitle" required></td>
            </tr>
            <tr>
                <th>작성자</th>
                <td><input type="text" name="nickname" value="아이언맨피카츄" readonly></td>
            </tr>
            <tr>
                <th>재료</th>
                <td><textarea name="ingredients" required></textarea></td>
            </tr>
            <tr>
                <th>대표 이미지</th>
                <td><input type="file" name="mainOName" required></td>
            </tr>
            <tr>
                <th>재료 이미지</th>
                <td><input type="file" name="inOName" required></td>
            </tr>
            <tr>
                <th>카테고리</th>
                <td>
                    <select name="recipeCategory" required>
                        <option value="한식">한식</option>
                        <option value="양식">양식</option>
                        <option value="일식">일식</option>
                        <option value="중식">중식</option>
                        <option value="기타">기타</option>
                    </select>
                </td>
            </tr>
        </table>
        
        <h3>요리 순서</h3>
        <div id="stepContainer">
            <div>
                <h4>STEP 1</h4>
                <textarea name="stepContent1" required></textarea>
                <input type="file" name="imgOName1" required>
            </div>
        </div>
        <button type="button" onclick="addStep()">순서 추가</button>
        
        <input type="submit" value="작성 완료">
    </form>
    
    <script>
        let stepCount = 1;
        
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