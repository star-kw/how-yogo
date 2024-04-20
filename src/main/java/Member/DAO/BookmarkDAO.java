package Member.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Common.DBConnPool;
import Member.DTO.BookmarkDTO;

public class BookmarkDAO extends DBConnPool {
    // 북마크 등록
    public void insertBookmark(BookmarkDTO dto) {
        String query = "INSERT INTO bookmark (nickname, recipeId, C_NUM) VALUES (?, ?, ?)";
        try {
            psmt = con.prepareStatement(query);
            psmt.setString(1, dto.getNickname());
            psmt.setInt(2, dto.getRecipeId());
            psmt.setInt(3, dto.getC_NUM());
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }
    
    // 북마크 목록 조회
    public List<BookmarkDTO> selectBookmarkList(String nickname) {
        List<BookmarkDTO> bookmarkList = new ArrayList<>();
        String query = "SELECT * FROM bookmark WHERE nickname = ?";
        try {
            psmt = con.prepareStatement(query);
            psmt.setString(1, nickname);
            rs = psmt.executeQuery();
            while (rs.next()) {
                BookmarkDTO dto = new BookmarkDTO();
                dto.setBookmarkId(rs.getInt("bookmarkId"));
                dto.setNickname(rs.getString("nickname"));
                dto.setRecipeId(rs.getInt("recipeId"));
                dto.setC_NUM(rs.getInt("C_NUM"));
                dto.setMarkDate(rs.getDate("markDate"));
                bookmarkList.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return bookmarkList;
    }
    
    // 북마크 삭제
    public void deleteBookmark(int bookmarkId) {
        String query = "DELETE FROM bookmark WHERE bookmarkId = ?";
        try {
            psmt = con.prepareStatement(query);
            psmt.setInt(1, bookmarkId);
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }
}