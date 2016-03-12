package packEntite;

public class Projet {
	
	private String idProjet, niveauProjet, titre, description;
	
	public Projet () {
		this.idProjet = null;
		this.niveauProjet = null;
		this.titre = null;
		this.description = null;
	}
	
	public Projet (String idProjet, String niveauProjet, String titre, String description) {
		this.idProjet = idProjet;
		this.niveauProjet = niveauProjet;
		this.titre = titre;
		this.description = description;
	}
	
	public String getIdProjet () {
		return this.idProjet;
	}
	
	public String getNiveauProjet () {
		return this.niveauProjet;
	}
	
	public String getTitre () {
		return this.titre;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setIdProjet (String idProjet) {
		this.idProjet = idProjet;
	}
	
	public void setNiveauProjet (String niveauProjet) {
		this.niveauProjet = niveauProjet;
	}
	
	public void setTitre (String titre) {
		this.titre = titre;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

}
