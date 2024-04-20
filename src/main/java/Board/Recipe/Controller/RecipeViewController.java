package Board.Recipe.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Board.Recipe.DAO.RecipeBoardDAO;
import Board.Recipe.DAO.RecipeCommentDAO;
import Board.Recipe.DAO.RecipeDAO;
import Board.Recipe.DAO.RecipeLikeDAO;
import Board.Recipe.DTO.RecipeBoardDTO;
import Board.Recipe.DTO.RecipeCommentDTO;
import Board.Recipe.DTO.RecipeDTO;
import Common.DBConnPool;

@WebServlet("/RecipeView")
public class RecipeViewController extends HttpServlet {
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
	        boardDao.updateHits(recipeId);
	        
	        // 요리 순서 정보 받기
	        RecipeDAO recipeDao = new RecipeDAO();
	        List<RecipeDTO> stepList = recipeDao.selectRecipeSteps(recipeId);
	        
	        // 댓글 목록 받기
            RecipeCommentDAO commentDao = new RecipeCommentDAO();
            List<RecipeCommentDTO> commentList = commentDao.selectCommentList(Integer.parseInt(recipeId));

            // 좋아요 개수 받기
            RecipeLikeDAO likeDao = new RecipeLikeDAO();
            int likeCount = likeDao.selectLikeCount(Integer.parseInt(recipeId));
	        
	        // 뷰에 전달
	        req.setAttribute("boardDto", boardDto);
	        req.setAttribute("stepList", stepList);
	        req.setAttribute("commentList", commentList);
            req.setAttribute("likeCount", likeCount);
	        req.getRequestDispatcher("/WEB-INF/Views/Board/Recipe/View.jsp").forward(req, resp);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("게시글 불러오기 실패");
		} finally {
			con.close();
		}
    }
}