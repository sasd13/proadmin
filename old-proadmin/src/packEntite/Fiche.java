package packEntite;

public class Fiche {
	
	private long archiver, numSemaine, chefProjet_key, groupe_key;
	
	public Fiche () {
		this.archiver = 0;
		this.numSemaine = 0;
		this.chefProjet_key = 0;
		this.groupe_key = 0;
	}
	
	public Fiche (long archiver, long numSemaine, long chefProjet_key, long groupe_key) {
		this.archiver = archiver;
		this.numSemaine = numSemaine;
		this.chefProjet_key = chefProjet_key;
		this.groupe_key = groupe_key;		
	}
	
	public long getArchiver() {
		return this.archiver;
	}
	
	public long getNumSemaine () {
		return this.numSemaine;
	}
	
	public long getChefProjet () {
		return this.chefProjet_key;
	}
	
	public long getGroupeKey () {
		return this.groupe_key;
	}
	
	public void setArchiver (long archiver) {
		this.archiver = archiver;
	}
	
	public void setNumSemaine (long numSemaine) {
		this.numSemaine = numSemaine;
	}
	
	public void setChefProjet (long chefProjet_key) {
		this.chefProjet_key = chefProjet_key;
	}
	
	public void setGroupeKey (long groupe_key) {
		this.groupe_key = groupe_key;
	}
}
