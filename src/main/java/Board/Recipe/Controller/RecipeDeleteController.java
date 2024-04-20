package Board.Recipe.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
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

@WebServlet("/RecipeDelete")
public class RecipeDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DBConnPool con = new DBConnPool();
		try {
	        // 게시물 번호 받기
	        String recipeId = req.getParameter("recipeId");
	        
	        // DAO를 통해 게시물과 첨부파일 삭제
	        RecipeBoardDAO boardDao = new RecipeBoardDAO();
	        RecipeBoardDTO boardDto = boardDao.selectRecipeBoard(recipeId);
	        FileUtil.deleteFile(req, "/uploads", boardDto.getMainSName());
	        FileUtil.deleteFile(req, "/uploads", boardDto.getInSName());
	        
	        RecipeDAO recipeDao = new RecipeDAO();
	        List<RecipeDTO> stepList = recipeDao.selectRecipeSteps(recipeId);
	        for (RecipeDTO step : stepList) {
	            FileUtil.deleteFile(req, "/uploads", step.getImgSName());
	        }
	        
	        recipeDao.deleteRecipeSteps(recipeId);
	        boardDao.deleteRecipeBoard(recipeId);
	        
	        // 게시물 목록으로 이동
	        System.out.println("게시글 삭제 성공!");
	        resp.sendRedirect("RecipeList");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("게시글 삭제 실패ㅠ");
		} finally {
			con.close();
		}
    }
}