package Board.Recipe.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Board.Recipe.DTO.RecipeDTO;
import Common.DBConnPool;

public class RecipeDAO extends DBConnPool{
    
    // 레시피 순서 조회
    public List<RecipeDTO> selectRecipeSteps(String recipeId) {
        List<RecipeDTO> list = new ArrayList<>();
        String query = "SELECT * FROM recipe WHERE recipeId = ? ORDER BY recipeStep";
        
        try {
            psmt = con.prepareStatement(query);
            psmt.setString(1, recipeId);
            
            try (ResultSet rs = psmt.executeQuery()) {
                while (rs.next()) {
                    RecipeDTO dto = new RecipeDTO();
                    dto.setRecipeStep(rs.getInt("recipeStep"));
                    dto.setRecipeId(rs.getInt("recipeId"));
                    dto.setStepContent(rs.getString("stepContent"));
                    dto.setImgOName(rs.getString("imgOName"));
                    dto.setImgSName(rs.getString("imgSName"));
                    list.add(dto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    // 레시피 순서 등록
    public void insertRecipeStep(RecipeDTO dto) {
        String query = "INSERT INTO recipe (recipeStep, recipeId, stepContent, imgOName, imgSName) "
                     + "VALUES (?, ?, ?, ?, ?)";
        
        try {
            psmt = con.prepareStatement(query);
            psmt.setInt(1, dto.getRecipeStep());
            psmt.setInt(2, dto.getRecipeId());
            psmt.setString(3, dto.getStepContent());
            psmt.setString(4, dto.getImgOName());
            psmt.setString(5, dto.getImgSName());
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // 레시피 순서 삭제
    public void deleteRecipeSteps(String recipeId) {
        String query = "DELETE FROM recipe WHERE recipeId = ?";
        
        try {
            psmt = con.prepareStatement(query);
            psmt.setString(1, recipeId);
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}