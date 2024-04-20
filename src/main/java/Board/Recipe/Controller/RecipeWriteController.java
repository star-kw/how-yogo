package Board.Recipe.Controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Board.Recipe.DAO.RecipeBoardDAO;
import Board.Recipe.DAO.RecipeDAO;
import Board.Recipe.DTO.RecipeBoardDTO;
import Board.Recipe.DTO.RecipeDTO;
import Common.DBConnPool;
import Common.FileUtil;

@WebServlet("/RecipeWrite")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
public class RecipeWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 글쓰기 페이지로 이동
        request.getRequestDispatcher("/WEB-INF/Views/Board/Recipe/Write.jsp").forward(request, response);
    }
	
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DBConnPool con = new DBConnPool();
		try {
			// 폼값 받기
	        String nickname = req.getParameter("nickname");
	        String recipeTitle = req.getParameter("recipeTitle");
	        String ingredients = req.getParameter("ingredients");
	        String recipeCategory = req.getParameter("recipeCategory");
	        
	        // 파일 업로드
	        String saveDirectory = "C:\\\\Uploads";
	        Map<String, String> fileNames = FileUtil.uploadFile(req, saveDirectory);
	        
	        String mainOName = fileNames.get("mainOName");
	        String mainSName = fileNames.get("mainOName");
	        String inOName = fileNames.get("inOName");
	        String inSName = fileNames.get("inOName");
	        
	        // DTO에 저장
	        RecipeBoardDTO boardDto = new RecipeBoardDTO();
	        boardDto.setNickname(nickname);
	        boardDto.setRecipeTitle(recipeTitle);
	        boardDto.setIngredients(ingredients);
	        boardDto.setMainOName(mainOName);
	        boardDto.setMainSName(mainSName);
	        boardDto.setInOName(inOName);
	        boardDto.setInSName(inSName);
	        boardDto.setRecipeCategory(recipeCategory);
	        
	        // DAO를 통해 DB에 저장
	        RecipeBoardDAO boardDao = new RecipeBoardDAO();
	        int recipeId = boardDao.insertRecipeBoard(boardDto);
	        
	        // 요리 순서 저장
	        int recipeStep = 1;
	        while (req.getParameter("stepContent" + recipeStep) != null) {
	            String stepContent = req.getParameter("stepContent" + recipeStep);
	            String imgOName = fileNames.get("imgOName" + recipeStep);
	            String imgSName = fileNames.get("imgOName" + recipeStep);
	            
	            RecipeDTO recipeDto = new RecipeDTO();
	            recipeDto.setRecipeStep(recipeStep);
	            recipeDto.setRecipeId(recipeId);
	            recipeDto.setStepContent(stepContent);
	            recipeDto.setImgOName(imgOName);
	            recipeDto.setImgSName(imgSName);
	            
	            RecipeDAO recipeDao = new RecipeDAO();
	            recipeDao.insertRecipeStep(recipeDto);
	            
	            recipeStep++;
	        }
	        System.out.println("게시글 작성 성공!!얏호");
	        resp.sendRedirect("RecipeView?recipeId=" + recipeId);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("게시글 작성 실패ㅠ");
		} finally {
			con.close();
		}
    }
}