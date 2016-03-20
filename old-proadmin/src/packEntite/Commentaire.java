package packEntite;

public class Commentaire {
	
	private String commentaire;
	
	public Commentaire() {
		this.commentaire = null;
	}
	
	public Commentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	
	public String getCommentaire () {
		return this.commentaire;
	}
	
	public void setCommentaire (String commentaire) {
		this.commentaire = commentaire;
	}

}
