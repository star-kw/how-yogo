package Board.Recipe.Controller;

import java.io.IOException;
import java.util.List;
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

@WebServlet("/RecipeEdit")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
public class RecipeEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	DBConnPool con = new DBConnPool();
    	try {
        // 게시물 번호 받기
        String recipeId = req.getParameter("recipeId");
        
        // DAO를 통해 레시피 정보 받기
        RecipeBoardDAO boardDao = new RecipeBoardDAO();
        RecipeBoardDTO boardDto = boardDao.selectRecipeBoard(recipeId);
        
        // 요리 순서 정보 받기
        RecipeDAO recipeDao = new RecipeDAO();
        List<RecipeDTO> stepList = recipeDao.selectRecipeSteps(recipeId);
        
        // 뷰에 전달
        req.setAttribute("boardDto", boardDto);
        req.setAttribute("stepList", stepList);
        req.getRequestDispatcher("/WEB-INF/Views/Board/Recipe/Edit.jsp").forward(req, resp);
    	} catch(Exception e) {
    		e.printStackTrace();
    		System.out.println("게시글 정보 받기 실패");
    	} finally {
    		con.close();
    	}
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	DBConnPool con = new DBConnPool();
    	try {
	        // 게시물 번호 받기
	        String recipeId = req.getParameter("recipeId");
	        System.out.println("recipeId:"+recipeId);
	        
	        // 폼값 받기
	        String recipeTitle = req.getParameter("recipeTitle");
	        String ingredients = req.getParameter("ingredients");
	        String recipeCategory = req.getParameter("recipeCategory");
	        System.out.println("recipeTitle:"+recipeTitle);
	        System.out.println("ingredients:" + ingredients);
	        System.out.println("recipeCategory:" + recipeCategory);
	        
	        // 파일 업로드
	        String saveDirectory = "C:\\\\Uploads";
	        Map<String, String> fileNames = FileUtil.uploadFile(req, saveDirectory);
	        String mainOName = fileNames.get("mainOName");
	        String mainSName = fileNames.get("mainOName");
	        String inOName = fileNames.get("inOName");
	        String inSName = fileNames.get("inOName");
	        
	        // DTO에 저장
	        RecipeBoardDTO boardDto = new RecipeBoardDTO();
	        boardDto.setRecipeId(Integer.parseInt(recipeId));
	        boardDto.setRecipeTitle(recipeTitle);
	        boardDto.setIngredients(ingredients);
	        boardDto.setMainOName(mainOName);
	        boardDto.setMainSName(mainSName);
	        boardDto.setInOName(inOName);
	        boardDto.setInSName(inSName);
	        boardDto.setRecipeCategory(recipeCategory);
	        
	        // DAO를 통해 DB에 업데이트
	        RecipeBoardDAO boardDao = new RecipeBoardDAO();
	        boardDao.updateRecipeBoard(boardDto);
	        
	        RecipeDAO recipeDao = new RecipeDAO();
	        recipeDao.deleteRecipeSteps(recipeId);
	        
	        // 요리 순서 저장
	        int recipeStep = 1;
	        while (req.getParameter("stepContent" + recipeStep) != null) {
	            String stepContent = req.getParameter("stepContent" + recipeStep);
	            String imgOName = fileNames.get("imgOName" + recipeStep);
	            String imgSName = fileNames.get("imgOName" + recipeStep);
	            
	            RecipeDTO recipeDto = new RecipeDTO();
	            recipeDto.setRecipeStep(recipeStep);
	            recipeDto.setRecipeId(Integer.parseInt(recipeId));
	            recipeDto.setStepContent(stepContent);
	            recipeDto.setImgOName(imgOName);
	            recipeDto.setImgSName(imgSName);
	            recipeDao.insertRecipeStep(recipeDto);
	            
	            recipeStep++;
	        }
	        
	        resp.sendRedirect("RecipeView?recipeId=" + recipeId);
	        System.out.println("게시글 수정 성공!");
    	} catch(Exception e) {
    		e.printStackTrace();
    		System.out.println("게시글 수정 실패ㅠㅠ");
    	} finally {
    		con.close();
    	}
    }
}