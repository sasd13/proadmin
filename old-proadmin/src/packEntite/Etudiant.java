package packEntite;

public class Etudiant {
	
	private String nomEtudiant, prenomEtudiant;
	
	public Etudiant () {
		this.nomEtudiant = null;
		this.prenomEtudiant = null;
	}
	
	public Etudiant (String nomEtudiant, String prenomEtudiant) {
		this.nomEtudiant = nomEtudiant;
		this.prenomEtudiant = prenomEtudiant;
	}
	
	public String getNomEtudiant () {
		return this.nomEtudiant;
	}
	
	public String getPrenomEtudiant () {
		return this.prenomEtudiant;
	}
	
	public void setNomEtudiant (String nomEtudiant) {
		this.nomEtudiant = nomEtudiant;
	}
	
	public void setPrenomEtudiant (String prenomEtudiant) {
		this.prenomEtudiant = prenomEtudiant;
	}
	
}
