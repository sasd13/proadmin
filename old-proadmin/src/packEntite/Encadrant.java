package packEntite;

public class Encadrant {

	private String nom, prenom, email;
	private long demarrage_key;
	
	public Encadrant() {
		this.nom = null;
		this.prenom = null;
		this.email = null;
		this.demarrage_key = 0;
	}
	
	public Encadrant (String nom, String prenom, String email, long demarrage_key) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.demarrage_key = demarrage_key;
	}
	
	public String getNom () {
		return this.nom;
	}
	
	public String getPrenom () {
		return this.prenom;
	}
	
	public String getEmail () {
		return this.email;
	}
	
	public long getDemarrageKey () {
		return this.demarrage_key;
	}
	
	public void setNom (String nom) {
		this.nom = nom;
	}
	
	public void setPrenom (String prenom) {
		this.prenom = prenom;
	}
	
	public void setEmail (String email) {
		this.email = email;
	}
	
	public void setDemarrageKey (long demarrage_key) {
		this.demarrage_key = demarrage_key;
	}
	
}
