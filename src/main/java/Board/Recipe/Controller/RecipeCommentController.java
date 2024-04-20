package Board.Recipe.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Board.Recipe.DAO.RecipeCommentDAO;
import Board.Recipe.DTO.RecipeCommentDTO;
import Common.DBConnPool;

@WebServlet("/RecipeComment")
public class RecipeCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBConnPool con = new DBConnPool();
		try {
			// 댓글 등록 처리
	        String action = req.getParameter("action");
	        if (action != null && action.equals("insert")) {
	            RecipeCommentDTO dto = new RecipeCommentDTO();
	            dto.setRecipeId(Integer.parseInt(req.getParameter("recipeId")));
	            dto.setNickname(req.getParameter("nickname"));
	            dto.setCommentContent(req.getParameter("commentContent"));
	            
	            String parentCommentParam = req.getParameter("parentComment");
	            if (parentCommentParam != null && !parentCommentParam.isEmpty()) {
	            	dto.setParentComment(Integer.parseInt(parentCommentParam));
	            } else {
	            	dto.setParentComment(null);
	            }
	
	            RecipeCommentDAO dao = new RecipeCommentDAO();
	            dao.insertComment(dto);
	        }
	        
	        // 댓글 삭제 처리
	        if (action != null && action.equals("delete")) {
	            int commentId = Integer.parseInt(req.getParameter("commentId"));
	            RecipeCommentDAO dao = new RecipeCommentDAO();
	            dao.deleteComment(commentId);
	        }
	        
	        resp.sendRedirect("RecipeView?recipeId=" + req.getParameter("recipeId"));
	        return;
		} catch(Exception e) {
			System.out.println("댓글 작성/삭제 실패 ㅠㅠ");
			e.printStackTrace();
		} finally {
			con.close();
		}
    }
}