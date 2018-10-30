package application;

import java.util.Date;

public class Comments {

	private int relationshipId;
	
	private int userId;
	private String userName;
	
	private int commentId;
	private String comment;
	private Date commentDate;
	
	private int qualificationId;
	private int qualification;
	private Date qualificationDate;
	
	public Comments() {
		// TODO Auto-generated constructor stub
		relationshipId = 0;
		userId = 0;
		userName = "";
		commentId = 0;
		comment = "";
		commentDate = new Date();
		qualificationId = 0;
		qualification = 0;
		qualificationDate = new Date();
	}

	//Gets y Sets
	
	/**
	 * @param relationshipId
	 * @param userId
	 * @param userName
	 * @param commentId
	 * @param comment
	 * @param commentDate
	 * @param qualificationId
	 * @param qualification
	 * @param qualificationDate
	 */
	public Comments(int relationshipId, int userId, String userName, int commentId, String comment, Date commentDate,
			int qualificationId, int qualification, Date qualificationDate) {
		
		this.relationshipId = relationshipId;
		this.userId = userId;
		this.userName = userName;
		this.commentId = commentId;
		this.comment = comment;
		this.commentDate = commentDate;
		this.qualificationId = qualificationId;
		this.qualification = qualification;
		this.qualificationDate = qualificationDate;
	}

	/**
	 * @return the relationshipId
	 */
	public int getRelationshipId() {
		return relationshipId;
	}

	/**
	 * @param relationshipId the relationshipId to set
	 */
	public void setRelationshipId(int relationshipId) {
		this.relationshipId = relationshipId;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the commentId
	 */
	public int getCommentId() {
		return commentId;
	}

	/**
	 * @param commentId the commentId to set
	 */
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the commentDate
	 */
	public Date getCommentDate() {
		return commentDate;
	}

	/**
	 * @param commentDate the commentDate to set
	 */
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	/**
	 * @return the qualificationId
	 */
	public int getQualificationId() {
		return qualificationId;
	}

	/**
	 * @param qualificationId the qualificationId to set
	 */
	public void setQualificationId(int qualificationId) {
		this.qualificationId = qualificationId;
	}

	/**
	 * @return the qualification
	 */
	public int getQualification() {
		return qualification;
	}

	/**
	 * @param qualification the qualification to set
	 */
	public void setQualification(int qualification) {
		this.qualification = qualification;
	}

	/**
	 * @return the qualificationDate
	 */
	public Date getQualificationDate() {
		return qualificationDate;
	}

	/**
	 * @param qualificationDate the qualificationDate to set
	 */
	public void setQualificationDate(Date qualificationDate) {
		this.qualificationDate = qualificationDate;
	}
	
	

}
