package packEntite;

public class ProjetSelect extends Projet {
	
	private boolean selected;

	public ProjetSelect (String idProjet, String niveauProjet, String titre, String description, boolean selected) {
		super(idProjet, niveauProjet, titre, description);
		
		this.selected = selected;
	}
	
	public boolean getSelected () {
		return this.selected;
	}
	
	public void setSelected (boolean selected) {
		this.selected = selected;
	}
	
	public Projet getProjet () {
		return (new Projet(this.getIdProjet(), this.getNiveauProjet(), this.getTitre(), this.getDescription()));
	}
	
}
