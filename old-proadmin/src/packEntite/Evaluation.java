package packEntite;

public class Evaluation {
	
	private long notePlanning, noteCommunication;
	private String commentairePlanning, commentaireCommunication;
	
	public Evaluation () {
		this.notePlanning = 0;
		this.commentairePlanning = null;
		this.noteCommunication = 0;
		this.commentaireCommunication = null;
	}	
	
	public Evaluation (long notePlanning, String commentairePlanning, long noteCommunication, String commentaireCommunication) {
		this.notePlanning = notePlanning;
		this.commentairePlanning = commentairePlanning;
		this.noteCommunication = noteCommunication;
		this.commentaireCommunication = commentaireCommunication;
	}
	
	public long getNotePLanning () {
		return this.notePlanning;
	}
	
	public String getCommentairePlanning () {
		return this.commentairePlanning;
	}
	
	public long getNoteCommunication () {
		return this.noteCommunication;
	}
	
	public String getCommentaireCommunication () {
		return this.commentaireCommunication;
	}
	
	public void setNotePLanning (long notePlanning) {
		this.notePlanning = notePlanning;
	}
	
	public void setCommentairePlanning (String commentairePlanning) {
		this.commentairePlanning = commentairePlanning;
	}
	
	public void setNoteCommunication (long noteCommunication) {
		this.noteCommunication = noteCommunication;
	}
	
	public void setCommentaireCommunication (String commentaireCommunication) {
		this.commentaireCommunication = commentaireCommunication;
	}

}
