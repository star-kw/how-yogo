package Board.Recipe.DTO;

import java.sql.Date;

public class RecipeCommentDTO {
	private int recipeId;
	private String nickname;
	private int commentId;
	private Integer parentComment;
	private String commentContent;
	private Date postDate;
	
	public int getRecipeId() {
		return recipeId;
	}
	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public Integer getParentComment() {
		return parentComment;
	}
	public void setParentComment(Integer parentComment) {
		this.parentComment = parentComment;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	
	
}
