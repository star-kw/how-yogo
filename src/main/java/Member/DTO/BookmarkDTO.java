package Member.DTO;

import java.sql.Date;

public class BookmarkDTO {
	private int bookmarkId;
	private String nickname;
	private int recipeId;
	private int C_NUM;
	private Date markDate;
	
	public int getBookmarkId() {
		return bookmarkId;
	}
	public void setBookmarkId(int bookmarkId) {
		this.bookmarkId = bookmarkId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getRecipeId() {
		return recipeId;
	}
	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}
	public int getC_NUM() {
		return C_NUM;
	}
	public void setC_NUM(int c_NUM) {
		C_NUM = c_NUM;
	}
	public Date getMarkDate() {
		return markDate;
	}
	public void setMarkDate(Date markDate) {
		this.markDate = markDate;
	}
}
