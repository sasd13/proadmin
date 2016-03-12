package packEntite;

public class EtudiantSelect extends Etudiant {
	
	private boolean selected;

	public EtudiantSelect (String nom, String prenom, boolean selected) {
		super(nom, prenom);
		
		this.selected = selected;
	}
	
	public boolean getSelected () {
		return this.selected;
	}
	
	public void setSelected (boolean selected) {
		this.selected = selected;
	}	
}
