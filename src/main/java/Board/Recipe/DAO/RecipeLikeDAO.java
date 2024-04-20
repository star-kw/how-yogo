package Board.Recipe.DAO;

import java.sql.SQLException;

import Board.Recipe.DTO.RecipeLikeDTO;
import Common.DBConnPool;

public class RecipeLikeDAO extends DBConnPool {
    // 좋아요 등록
    public void insertLike(RecipeLikeDTO dto) {
        String query = "INSERT INTO recipeLike (nickname, recipeId) VALUES (?, ?)";
        try {
            psmt = con.prepareStatement(query);
            psmt.setString(1, dto.getNickname());
            psmt.setInt(2, dto.getRecipeId());
            int result = psmt.executeUpdate();
            System.out.println("좋아요 등록 결과: " + result);
        } catch (SQLException e) {
        	System.out.println("좋아요 등록 중 예외 발생");
        	e.printStackTrace();
        } 
    }
    
    // 좋아요 상태 확인
    public boolean isLiked(String nickname, int recipeId) {
        boolean isLiked = false;
        String query = "SELECT COUNT(*) FROM recipeLike WHERE nickname = ? AND recipeId = ?";
        try {
            psmt = con.prepareStatement(query);
            psmt.setString(1, nickname);
            psmt.setInt(2, recipeId);
            rs = psmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                isLiked = (count > 0);
            }
        } catch (SQLException e) {
        	System.out.println("좋아요 상태 확인 중 예외발생");
            e.printStackTrace();
        } 
        return isLiked;
    }

    // 좋아요 취소
    public void cancelLike(String nickname, int recipeId) {
        String query = "DELETE FROM recipeLike WHERE nickname = ? AND recipeId = ?";
        try {
            psmt = con.prepareStatement(query);
            psmt.setString(1, nickname);
            psmt.setInt(2, recipeId);
            psmt.executeUpdate();
        } catch (SQLException e) {
        	System.out.println("좋아요 취소 작업 중 예외발생");
            e.printStackTrace();
        } 
    }
    
    // 좋아요 개수 조회
    public int selectLikeCount(int recipeId) {
        int likeCount = 0;
        String query = "SELECT COUNT(*) AS likeCount FROM recipeLike WHERE recipeId = ?";
        try {
            psmt = con.prepareStatement(query);
            psmt.setInt(1, recipeId);
            rs = psmt.executeQuery();
            if (rs.next()) {
                likeCount = rs.getInt("likeCount");
            }
        } catch (SQLException e) {
        	System.out.println("좋아요 개수 조회 중 예외발생");
            e.printStackTrace();
        } 
        return likeCount;
    }
    
}