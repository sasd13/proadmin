package packEntite;

public class Note {
	
	private long note, etudiant_key;
	
	public Note () {
		this.note = -4;
		this.etudiant_key = 0;
	}
	
	public Note (long note, long etudiant_key) {
		this.note = note;
		this.etudiant_key = etudiant_key;
	}
	
	public long getNote () {
		return this.note;
	}
	
	public long getEtudiantKey () {
		return this.etudiant_key;
	}
	
	public void setNote (long note) {
		this.note = note;
	}
	
	public void setEtudiantKey (long etudiant_key) {
		this.etudiant_key = etudiant_key;
	}

}
