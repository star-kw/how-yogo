package Board.Recipe.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Board.Recipe.DAO.RecipeLikeDAO;
import Board.Recipe.DTO.RecipeLikeDTO;
import Common.DBConnPool;

@WebServlet("/RecipeLike")
public class RecipeLikeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DBConnPool con = new DBConnPool();
		try {
			String nickname = req.getParameter("nickname");
	        int recipeId = Integer.parseInt(req.getParameter("recipeId"));
	        
	        RecipeLikeDAO dao = new RecipeLikeDAO();
	        
	        if (dao.isLiked(nickname, recipeId)) {
	            // 이미 좋아요를 누른 상태이면 좋아요 취소 처리
	            dao.cancelLike(nickname, recipeId);
	        } else {
	            // 좋아요를 누르지 않은 상태이면 좋아요 등록 처리
	            RecipeLikeDTO dto = new RecipeLikeDTO();
	            dto.setNickname(nickname);
	            dto.setRecipeId(recipeId);
	            dao.insertLike(dto);
	        }
	        
	        resp.sendRedirect("RecipeView?recipeId=" + recipeId);
		} catch(Exception e) {
			System.out.println("좋아요 프로세스 실패 ㅠㅠ");
			e.printStackTrace();
		} finally {
			con.close();
		}
    }
}