package Board.Recipe.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Board.Recipe.DTO.RecipeCommentDTO;
import Common.DBConnPool;

public class RecipeCommentDAO extends DBConnPool {
    // 댓글 등록
    public void insertComment(RecipeCommentDTO dto) {
        String query = "INSERT INTO recipeComment (recipeId, nickname, commentContent, parentComment) VALUES (?, ?, ?, ?)";
        try {
            psmt = con.prepareStatement(query);
            psmt.setInt(1, dto.getRecipeId());
            psmt.setString(2, dto.getNickname());
            psmt.setString(3, dto.getCommentContent());
            if (dto.getParentComment() == null) {
                psmt.setNull(4, java.sql.Types.INTEGER);
            } else {
            		psmt.setInt(4, dto.getParentComment());
            }
            int result = psmt.executeUpdate();
            System.out.println("댓글 등록 결과: " + result);
        } catch (SQLException e) {
        	System.out.println("댓글 등록 중 예외발생");
            e.printStackTrace();
        } 
    }
    
    private boolean isValidCommentId(Integer parentComment) {
		return false;
	}

	// 댓글 목록 조회
    public List<RecipeCommentDTO> selectCommentList(int recipeId) {
        List<RecipeCommentDTO> commentList = new ArrayList<>();
        String query = "SELECT * FROM recipeComment WHERE recipeId = ? ORDER BY commentId";
        try {
            psmt = con.prepareStatement(query);
            psmt.setInt(1, recipeId);
            rs = psmt.executeQuery();
            while (rs.next()) {
                RecipeCommentDTO dto = new RecipeCommentDTO();
                dto.setCommentId(rs.getInt("commentId"));
                dto.setRecipeId(rs.getInt("recipeId"));
                dto.setNickname(rs.getString("nickname"));
                dto.setCommentContent(rs.getString("commentContent"));
                dto.setParentComment(rs.getInt("parentComment"));
                dto.setPostDate(rs.getDate("postDate"));
                commentList.add(dto);
            }
        } catch (SQLException e) {
        	System.out.println("댓글 목록 조회 중 예외발생");
            e.printStackTrace();
        } 
        return commentList;
    }
    
    // 댓글 삭제
    public void deleteComment(int commentId) {
        String query = "DELETE FROM recipeComment WHERE commentId = ?";
        try {
            psmt = con.prepareStatement(query);
            psmt.setInt(1, commentId);
            psmt.executeUpdate();
        } catch (SQLException e) {
        	System.out.println("댓글 삭제 중 예외발생");
            e.printStackTrace();
        } 
    }
}