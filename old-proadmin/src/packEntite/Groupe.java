package packEntite;

public class Groupe {
	
	private long annee, nbrEtudiant, projet_key;
	private String idGroupe;
	
	public Groupe () {
		this.idGroupe = null;
		this.annee = 0;
		this.nbrEtudiant = 0;
		this.projet_key = 0;
	}
	
	public Groupe (String idGroupe, long annee, long nbrEtudiant, long projet_key) {
		this.idGroupe = idGroupe;
		this.annee = annee;
		this.nbrEtudiant = nbrEtudiant;
		this.projet_key = projet_key;
	}
	
	public String getIdGroupe () {
		return this.idGroupe;
	}
	
	public long getAnnee () {
		return this.annee;
	}
	
	public long getNbrEtudiant() {
		return this.nbrEtudiant;
	}
	
	public long getProjetKey () {
		return this.projet_key;
	}
	
	public void setIdGroupe (String idGroupe) {
		this.idGroupe = idGroupe;
	}
	
	public void setAnnee (long annee) {
		this.annee = annee;
	}
	
	public void setNbrEtudiant (long nbrEtudiant) {
		this.nbrEtudiant = nbrEtudiant;
	}
	
	public void setProjetKey (long projet_key) {
		this.projet_key = projet_key;
	}

}
